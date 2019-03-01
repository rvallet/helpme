package fr.dawan.mvc1.controlers.admincontroler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.mvc1.beans.Advice;
import fr.dawan.mvc1.beans.ProblemType;
import fr.dawan.mvc1.dao.AdviceDao;
import fr.dawan.mvc1.dao.ProblemTypeDao;

@Controller
public class AdminAdvicesControler {

	@Autowired
	AdviceDao adviceDao;
	@Autowired
	ProblemTypeDao problemTypeDao;

	public void setAdviceDao(AdviceDao adviceDao) {
		this.adviceDao = adviceDao;
	}

	public void setProblemTypeDao(ProblemTypeDao problemTypeDao) {
		this.problemTypeDao = problemTypeDao;
	}

	@GetMapping("/admin/advice")
	public String afficherListeAdvice(Model m) {
		if (!m.containsAttribute("advices"))
			m.addAttribute("advices", adviceDao.findAll());

		return "/admin/list/listeadvices";
	}

	@GetMapping("admin/advice/add-advice")
	public String ajouterAdvice(Model m) {
		Advice a = new Advice();
		a.setUseDate(null);
		String title = "Ajouter un conseil";
		m.addAttribute("lipbtypes", problemTypeDao.findAll());
		m.addAttribute("advice", a);
		m.addAttribute("title", title);
		m.addAttribute("isAdd", true);
		return "/admin/form/advice-form";
	}

	@PostMapping("/admin/advice/save-advice")
	public String saveAdvice(Model m, @Valid @ModelAttribute("advice") Advice a, BindingResult result,
			@RequestParam("pbtypes") int[] pbtypes, HttpServletRequest req) {
		if (result.hasErrors()) {
			String title = "Ajouter un conseil";
			m.addAttribute("title", title);
			m.addAttribute("advice", a);
			m.addAttribute("lipbtypes", problemTypeDao.findAll());
			m.addAttribute("errors", result.getAllErrors());
			return "/admin/form/advice-form";
		}
		if (a.getIdAdvice() != null) {
			String title = a.getTitle();
			String content = a.getContent();
			adviceDao.getHibernateTemplate().refresh(a);
			a.setTitle(title);
			a.setContent(content);
		}
		req.getSession().removeAttribute("pbTypeId");
		List<ProblemType> lipbtype = new ArrayList<>();
		if (pbtypes.length > 1) {
			a.setLiProblemType(lipbtype);
			for (int i : pbtypes) {
				if (i != 0) {
					ProblemType pb = problemTypeDao.findById(i);
					pb.getAdvices().add(a);
					a.getLiProblemType().add(pb);
					problemTypeDao.Update(pb);
				}
			}
		} else
			a.setLiProblemType(null);
		adviceDao.Update(a);
		return "redirect:/admin/advice";

	}

	@GetMapping("/admin/advice/{action}/{id}")
	public String update(@PathVariable("action") String action, @PathVariable("id") long id, Model m,
			HttpServletRequest req) {
		String cible = "redirect:/admin/advice";
		req.getSession().removeAttribute("pbTypeId");
		if (action.equals("remove")) {
			Advice a = adviceDao.findById(id);
			for (ProblemType pb : a.getLiProblemType()) {
				pb.getAdvices().remove(a);
				problemTypeDao.Update(pb);
			}
			a.setLiProblemType(null);
			adviceDao.Update(a);
			adviceDao.Remove(id);
			return cible;
		} else if (action.equals("update")) {
			Advice a = adviceDao.findById(id);
			for (ProblemType pb : a.getLiProblemType()) {
				pb.getAdvices().remove(a);
				problemTypeDao.Update(pb);
			}
			a.setLiProblemType(null);
			adviceDao.Update(a);
			cible = "admin/form/advice-form";
			m.addAttribute("lipbtypes", problemTypeDao.findAll());
			m.addAttribute("advice", a);
			m.addAttribute("isAdd", false);
			m.addAttribute("title", "Modification du conseil");
			return cible;
		}
		return cible;
	}

	@PostMapping("/admin/advice/advice-research")
	public String rechercher(Model m, HttpServletRequest req) { // @ModelAttribute("advice") Advice adv, BindingResult
																// result
		String searchField = req.getParameter("searchField");
		if (searchField != null) {
			List<Advice> advSearch = adviceDao.FindByTitleOrDesc(searchField);
			System.out.println("-------------->> " + advSearch.size());
			m.addAttribute("advices", advSearch);
			m.addAttribute("res", (advSearch.size() == 0 || advSearch == null));
		}
		return afficherListeAdvice(m);
	}
}
