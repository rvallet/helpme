package fr.dawan.mvc1.controlers.admincontroler;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.mvc1.beans.Testemony;
import fr.dawan.mvc1.beans.Testemony.TestemonysStatus;
import fr.dawan.mvc1.dao.ProblemTypeDao;
import fr.dawan.mvc1.dao.TestemonyDao;

@Controller
public class AdminTestemonyControler {

	@Autowired
	TestemonyDao testemonyDao;

	public void setTestemonyDao(TestemonyDao testemonyDao) {
		this.testemonyDao = testemonyDao;
	}

	@Autowired
	ProblemTypeDao problemTypeDao;

	public void setProblemTypeDao(ProblemTypeDao problemTypeDao) {
		this.problemTypeDao = problemTypeDao;
	}

	@GetMapping("/admin/testemony")
	public String afficherListeTestemony(Model m) {
		if (!m.containsAttribute("testemonies"))
			m.addAttribute("testemonies", testemonyDao.findAll());
		return "/admin/list/listetestemony";
	}

	@PostMapping("/admin/testemony/save-testemony")
	public String saveTestemony(Model m, @ModelAttribute("testemony") Testemony a,
			@RequestParam("theproblemtype") int pbtype, HttpServletRequest req) throws UnsupportedEncodingException {
		Testemony t = testemonyDao.findById(a.getIdTemoignage());
		a.setDateWrite(t.getDateWrite());
		a.setProblemTypes(null);
		testemonyDao.Update(a);
		a.setProblemTypes(problemTypeDao.findById(pbtype));
		testemonyDao.Update(a);
		return "redirect:/admin/testemony";
	}

	@GetMapping("admin/testemony/add-testemony")
	public String addTestemony(Model m) {
		Testemony t = new Testemony();
		t.setDateWrite(null);
		String title = "Ajouter";
		m.addAttribute("lipbtypes", problemTypeDao.findAll());
		m.addAttribute("Testemony", t);
		m.addAttribute("title", title);
		m.addAttribute("isAdd", true);
		return "admin/form/testemony-form";
	}

	@GetMapping("/admin/testemony/{action}/{id}")
	public String update(@PathVariable("action") String action, @PathVariable("id") long id, Model m,
			HttpServletRequest req) {
		String cible = "redirect:/admin/testemony";
		Testemony t = testemonyDao.findById(id);
		req.getSession().removeAttribute("pbTypeId");
		if (action.equals("remove")) {
			t.setProblemTypes(null);
			testemonyDao.Update(t);
			testemonyDao.Remove(id);
			return cible;
		} else if (action.equals("update")) {
			cible = "admin/form/testemony-form";
			m.addAttribute("liproblemType", problemTypeDao.findAll());
			m.addAttribute("testemony", t);
			m.addAttribute("liStatus", TestemonysStatus.values());
			m.addAttribute("title", "Modification du témoignage");
			return cible;
		}
		return cible;
	}

	@PostMapping("/admin/testemony/testemony-research")
	public String rechercher(Model m, HttpServletRequest req) { // @ModelAttribute("advice") Advice adv, BindingResult
																// result
		String searchField = req.getParameter("searchField");
		if (searchField != null) {
			List<Testemony> testSearch = testemonyDao.FindByTitleOrDesc(searchField);
			m.addAttribute("testemonies", testSearch);
			m.addAttribute("res", (testSearch.size() == 0 || testSearch == null));
		}
		return afficherListeTestemony(m);
	}

}
