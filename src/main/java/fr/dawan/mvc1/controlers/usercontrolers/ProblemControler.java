package fr.dawan.mvc1.controlers.usercontrolers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.mvc1.beans.Objective;
import fr.dawan.mvc1.beans.Problem;
import fr.dawan.mvc1.beans.Problem.ProblemFrequence;
import fr.dawan.mvc1.beans.ProblemType;
import fr.dawan.mvc1.beans.ProblemType.ProblemCategory;
import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.AdviceDao;
import fr.dawan.mvc1.dao.ObjectiveDao;
import fr.dawan.mvc1.dao.ProblemDao;
import fr.dawan.mvc1.dao.ProblemTypeDao;
import fr.dawan.mvc1.dao.UserDao;

@Controller
public class ProblemControler {

	@Autowired
	UserDao userDao;
	@Autowired
	ProblemTypeDao problemTypeDao;
	@Autowired
	ProblemDao problemDao;
	@Autowired
	AdviceDao adviceDao;
	@Autowired
	ObjectiveDao objectiveDao;

	public void setProblemTypeDao(ProblemTypeDao problemTypeDao) {
		this.problemTypeDao = problemTypeDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setProblemDao(ProblemDao problemDao) {
		this.problemDao = problemDao;
	}

	public void setAdviceDao(AdviceDao adviceDao) {
		this.adviceDao = adviceDao;
	}

	public void setObjectiveDao(ObjectiveDao objectiveDao) {
		this.objectiveDao = objectiveDao;
	}

	@GetMapping("/problem")
	public String addProblem(Model m, HttpServletRequest req) {
		List<ProblemType> typeproblems = problemTypeDao.findAll();
		User u = (User) req.getSession().getAttribute("user");
		if (!u.getLiProblem().isEmpty()) {
			for (Problem pb : u.getLiProblem()) {
				Long idRemove = pb.getProblemType().getIdProblemTypes();
				for (ProblemType pt : typeproblems)
					if (pt.getIdProblemTypes() == idRemove && pb.isActive()) {
						typeproblems.remove(typeproblems.indexOf(pt));
					}
			}
		}
		m.addAttribute("typeproblems", typeproblems);
		m.addAttribute("problemType", new ProblemType());
		return "/problem/choiceproblem";
	}

	@PostMapping("/problem/choice")
	public String typeChoice(Model m, @ModelAttribute("problemType") ProblemType problemType, HttpSession session) {
		session.setAttribute("pbTypeId", problemType.getIdProblemTypes());
		problemType = problemTypeDao.findById(problemType.getIdProblemTypes());
		if (problemType.getIdProblemTypes() == 1 || problemType.getIdProblemTypes() == 2) {
			problemType.setPbCategory(ProblemCategory.ADDICTION);
		} else
			problemType.setPbCategory(ProblemCategory.FULFILLEMENT);
		User u = (User) session.getAttribute("user");
		if (!u.getLiProblem().isEmpty()) {
			for (Problem pb : u.getLiProblem()) {
				if (pb.getProblemType().getIdProblemTypes() == problemType.getIdProblemTypes() && !pb.isActive()) {
					m.addAttribute("pb", pb);
				}
			}

		} else {
			Problem pb = new Problem();
			m.addAttribute("pb", pb);
		}
		m.addAttribute("problemType", problemType);
		if (problemType.getPbCategory().equals(ProblemCategory.ADDICTION)) {
			m.addAttribute("prefix", problemType.getTitle().toLowerCase());
			return "problem/addiction-form";
		} else
			return "home";
	}

	/**
	 * Récupération des données du problem de l'utilisateur, redirection vers la
	 * homePage avec le tableaux de bord généré
	 * 
	 * @param unitCost
	 * @param startQuantity
	 * @param problemFrequence
	 * 
	 */
	@PostMapping("/problem/save-problem")
	public String addTabacProblem(Model m, @Valid @ModelAttribute("pb") Problem pb, BindingResult result,
			@RequestParam("problemType") String pbtype, HttpSession session) {
		if (result.getAllErrors().size() > 1) {
			ObjectError error = result.getAllErrors().get(1);
			m.addAttribute("pb", pb);
			m.addAttribute("problemType", problemTypeDao.findById(Integer.parseInt(pbtype)));
			m.addAttribute("error", error);
			return "/problem/addiction-form";
		}
		Problem pbtemp = null;
		ProblemType problemType = problemTypeDao.findById((Long) session.getAttribute("pbTypeId"));
		pb.setProblemType(problemType);
		User u = (User) session.getAttribute("user");
		if (pb.getIdProblem() == null) {
			List<Problem> lipb = new ArrayList<>();
			Set<User> liusers = new HashSet<>();
			pb.setCreationDate(new Date());
			pb.setUnitCost(pb.getPackPrice() / pb.getPackQuantity());
			pb.setTitle(problemType.getTitle() + " : " + pb.getObjective().toString());
			pb.setProblemFrequence(ProblemFrequence.DAILY);
			lipb.add(pb);
			u.setLiProblem(lipb);
			liusers.add(u);
			pb.setLiUser(liusers);
			pb.setActive(true);
			problemDao.insert(pb);
			userDao.Update(u);
			session.removeAttribute("pbTypeId");
		} else {
			pbtemp = problemDao.findById(pb.getIdProblem());
			pbtemp.setStartQuantity(pb.getStartQuantity());
			pbtemp.setMotivation(pb.getMotivation());
			pbtemp.setPackPrice(pb.getPackPrice());
			pbtemp.setCreationDate(new Date());
			pbtemp.setObjective(pb.getObjective());
			problemDao.Update(pbtemp);
		}
		if (pb.getObjective().equals(Problem.ProblemObjective.TO_STOP)) {
			session.setAttribute("user", userDao.findById(((User) session.getAttribute("user")).getIdUser()));
			return "home";
		} else {
			if (pbtemp == null) {
				pb.setActive(false);
				problemDao.Update(pb);
				m.addAttribute("pb", pb);
			} else
				m.addAttribute("pb", pbtemp);
			m.addAttribute("pbType", pb.getProblemType());
			m.addAttribute("obj", new Objective());
			session.setAttribute("user", userDao.findById(((User) session.getAttribute("user")).getIdUser()));
			return "problem/objective-form";
		}
	}

	@PostMapping("/problem/save-objective")
	public String saveObjective(Model m, @ModelAttribute("obj") Objective obj, @RequestParam("idPb") Long idPb,
			HttpSession session) {
		User user = userDao.findById(((User) session.getAttribute("user")).getIdUser());
		Problem pb = problemDao.findById(idPb);
		user.getLiProblem().remove(pb);
		pb.setActive(true);
		obj.setTitle(pb.getObjective().toString() + " : " + pb.getProblemType().getTitle());
		Date today = new Date();
		obj.setStartDate(today);
		obj.setEndDate(new Date(today.getTime() + 691200000l));
		obj.setFinalQuantity(0);
		Map<Integer, Integer> consoWeek = new HashMap<>();
		for (int i = 0; i < 7; i++) {
			consoWeek.put(i + 1, (int) pb.getStartQuantity());
		}

		obj.setRealConso(consoWeek);
		obj.setProblem(pb);
		obj.setStatus(Objective.ObjectiveStatus.IN_PROGRESS);
		if (pb.getLiObjectives().isEmpty()) {
			pb.setLiObjectives(new HashSet<Objective>());
		}
		pb.getLiObjectives().add(obj);
		objectiveDao.Update(obj);
		problemDao.Update(pb);
		user.getLiProblem().add(pb);
		session.setAttribute("user", user);
		return "redirect:/";
	}
}
