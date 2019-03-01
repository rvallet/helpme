package fr.dawan.mvc1.controlers.admincontroler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.ProblemDao;
import fr.dawan.mvc1.dao.UserDao;
import fr.dawan.mvc1.tools.PasswordEncoderArgon2;
import fr.dawan.mvc1.tools.Tools;

@Controller
public class AdminControler {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProblemDao problemDao;

	@GetMapping("/admin/dashboard") // Nom de l'url pour lié.
	public String showDashboard() {

		return "admin/espace-admin"; // JSP
	}

	@GetMapping("/admin/users")
	public String showListeUsers(Model m, @RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max) {
		if (page == null)
			page = 1;
		if (max == null)
			max = 5;

		int start = (page - 1) * max;
		List<User> liUser = userDao.findAll(start, max);
		m.addAttribute("page", page);
		m.addAttribute("max", max);
		m.addAttribute("suivExist", (page * max) < userDao.nbUsers());
		User ux = new User();
		m.addAttribute("liUser", liUser);
		m.addAttribute("problems", problemDao.findAll());
		m.addAttribute("isAdd", true);
		ux.setPseudo("");
		m.addAttribute("search", ux);
		return "admin/list/users";
//		@RequestParam(name="update")
//		UserDao.Update(@RequestParam(name="id"));

	}

	@GetMapping("/admin/users/{action}/{id}")
	public String update(@PathVariable("action") String action, @PathVariable("id") long id, Model m) {
		String cible = "redirect:/admin/users?page=1&max=5";
		if (action.equals("remove")) {
			userDao.Remove(id);
			return cible;
		} else if (action.equals("update")) {
			User u = userDao.findById(id);
			cible = "admin/form/user-form";
			m.addAttribute("u", u);
			m.addAttribute("isAdd", false);
			m.addAttribute("title", "Modification de l'utilisateur");
			return cible;
		}
		return cible;
	}

	@PostMapping("/admin/save-user")
	public String sauvegarde(@ModelAttribute("u") User u, BindingResult result, Model m) {
		String cible = "redirect:/admin/users?page=1&max=5";
		User thisUser = userDao.findByEmail(u.getEmail());
//		if (result.hasErrors()) {
//			m.addAttribute("u", u);
//			m.addAttribute("errors", result.getAllErrors());
//			cible = "admin/form/user-form";
//			return cible;
//		} else 
		if (u == null || u.getIdUser() == null) {
			u.setCreationDate(new Date());
			CharSequence pwdCharSeq = new StringBuilder(u.getPassword());
			u.setPassword(new PasswordEncoderArgon2().encode(pwdCharSeq));
			userDao.insert(u);
			return cible;
		} else {
			thisUser.setEmail(u.getEmail());
			thisUser.setPseudo(u.getPseudo());
			thisUser.setSexe(u.getSexe());
			thisUser.setStatut(u.getStatut());
			System.out.println("Test => " + u);
			userDao.Update(u);
			return cible;
		}
	}

	@GetMapping("/admin/add-user")
	public String addUser(Model m) {
		m.addAttribute("isAdd", true);
		m.addAttribute("u", new User());
		m.addAttribute("title", "Ajout d'un utilisateur");
		return "admin/form/user-form";
	}

	@PostMapping("/admin/user-research")
	public String rechercher(Model m, @ModelAttribute("search") User u, BindingResult result) {
		List<User> liSearch = userDao.FindByNameOrEmail(u.getPseudo());
		m.addAttribute("liUser", liSearch);
		m.addAttribute("u", u);
		m.addAttribute("res", (liSearch.size() == 0 || liSearch == null));
		return "admin/list/users";
	}

	@GetMapping("/admin/export-users")
	public void exportCsv(HttpServletResponse response) throws Exception {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment;filename=\"users.csv\"");
		ServletOutputStream out = response.getOutputStream();
		out.write("Pseudo;Email;Sexe;Status".getBytes());
		out.write("\n".getBytes());
		for (User u : userDao.findAll()) {
			StringBuilder ligne = new StringBuilder();
			ligne.append(u.getPseudo()).append(";");
			ligne.append(u.getEmail()).append(";");
			ligne.append(u.getSexe()).append(";");
			ligne.append(u.getStatusStr()).append(";");
			ligne.append("\n");
			out.write(ligne.toString().getBytes());
		}
		out.close();
	}

	@PostMapping("/admin/import-users")
	public String uploadCsv(Model m, HttpServletRequest request, @RequestParam("file") MultipartFile file)
			throws Exception {
		if (!file.isEmpty()) {
			try {
				byte[] contentBytes = file.getBytes();
				String dirPath = "C:/uploads";
				// String dirPath = request.getServletContext().getRealPath("")+"/uploads";
				File dir = new File(dirPath);
				if (!dir.exists())
					dir.mkdirs();

				String filePath = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
				try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
					bos.write(contentBytes);
				}

				List<User> li = Tools.importCsv(filePath);
				System.out.println(li.size());
				for (User u : li) {
					// TODO Check si user exist
					userDao.insert(u);
				}
				Files.delete(Paths.get(filePath));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "redirect:/admin/users";
	}

	@GetMapping("/admin/disconnect")
	public String disconnect(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/";
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setProblemDao(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

}