package fr.dawan.mvc1.controlers.admincontroler;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.dawan.mvc1.beans.Problem;
import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.ProblemDao;
import fr.dawan.mvc1.dao.ProblemTypeDao;
import fr.dawan.mvc1.dao.UserDao;

@Controller
public class AdminProblemsControler {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProblemDao problemDao;
	@Autowired
	private ProblemTypeDao problemTypeDao;

	public void setProblemTypeDao(ProblemTypeDao problemTypeDao) {
		this.problemTypeDao = problemTypeDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setProblemDao(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	@GetMapping("/admin/problems")
	public String afficherProblems(Model m) {
		if (problemDao.findAll().isEmpty()) {
			m.addAttribute("problems", new ArrayList<>());
		} else
			m.addAttribute("problems", problemDao.findAll());
		return ("/admin/list/listeproblems");
	}

	@GetMapping("admin/problem/remove/{id}")
	public String update(@PathVariable("id") long id, Model m, HttpSession session) {
		Problem p = problemDao.findById(id);
		p.setLiObjectives(null);
		p.setLiUser(null);
		p.setProblemType(null);
		problemDao.Update(p);
		problemDao.Remove(id);
		session.setAttribute("user", userDao.findById(((User) session.getAttribute("user")).getIdUser()));
		return "redirect:/admin/problems";
	}

	@GetMapping("/admin/problemstypes")
	public String afficherProblemType(Model m) {
		m.addAttribute("problemtype", problemTypeDao.findAll());
		return "/admin/list/listeproblemtype";
	}

	// TODO: Recherche des problèmes via l'adresse mail de l'user + ajout d'une
	// colonnne mail user rattaché au problème
}
