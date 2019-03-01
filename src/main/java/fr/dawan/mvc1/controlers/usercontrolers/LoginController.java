package fr.dawan.mvc1.controlers.usercontrolers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.UserDao;
import fr.dawan.mvc1.formsbeans.LoginForm;
import fr.dawan.mvc1.tools.PasswordEncoderArgon2;

@Controller
public class LoginController {

	@Autowired // on injecte le bean créé au démarrage
	private UserDao userDao;

	@GetMapping("/authenticate")
	public String showLogin(Model model) {
		// L'objet login form sert à initialiser le formulaire et à récupérer
		// les données saisies une fois le formulaire validé.
		LoginForm f = new LoginForm();
		model.addAttribute("login-form", f);
		return "account/login"; // WEB-INF/views/login.jsp
	}

	@PostMapping("check-login")
	public String checkLogin(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("login-form") LoginForm form, BindingResult result) {
		String cible = "redirect:/admin/dashboard";
		if (result.hasErrors()) {
			model.addAttribute("errors", result);
			model.addAttribute("login-form", form);
			return "account/login";
		}
		User u = userDao.findByEmail(form.getEmail());
//		TODO Remove this:
//		System.out
//				.println("Verif Argon2 : " + new PasswordEncoderArgon2().matches(form.getPassword(), u.getPassword()));
		if (u != null && new PasswordEncoderArgon2().matches(form.getPassword(), u.getPassword())) {
			request.getSession().setAttribute("email", u.getEmail());
			request.getSession().setAttribute("user_id", u.getIdUser());
			request.getSession().setAttribute("user", u);
			request.getSession().setAttribute("status", u.getStatusStr());
			request.getSession().setAttribute("isAuth", true);
			if (u.getStatut().equals(User.StatusUser.ADMIN)) {
				return "redirect:/admin/dashboard";
			} else
				return "home";
		} else {
			model.addAttribute("msg", "Erreur d'authentification");
			model.addAttribute("login-form", form);
			cible = "account/login";
		}
		return cible;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}