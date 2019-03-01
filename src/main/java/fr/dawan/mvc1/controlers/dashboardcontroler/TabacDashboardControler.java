package fr.dawan.mvc1.controlers.dashboardcontroler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.mvc1.beans.Advice;
import fr.dawan.mvc1.beans.Objective;
import fr.dawan.mvc1.beans.Objective.ObjectiveStatus;
import fr.dawan.mvc1.beans.Problem;
import fr.dawan.mvc1.beans.Problem.ProblemObjective;
import fr.dawan.mvc1.beans.ProblemType;
import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.AdviceDao;
import fr.dawan.mvc1.dao.ObjectiveDao;
import fr.dawan.mvc1.dao.ProblemDao;
import fr.dawan.mvc1.dao.ProblemTypeDao;
import fr.dawan.mvc1.dao.UserDao;
import fr.dawan.mvc1.tools.Calculation;

@Controller
public class TabacDashboardControler {

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

	@GetMapping("/Tabac/{id}")
	public String TabacDashboard(Model m, HttpServletRequest req, @PathVariable("id") Long idProblem) {
		// Initialisation
		Problem p = problemDao.findById(idProblem);
		ProblemType pbtype = p.getProblemType();
		List<Advice> advices = adviceDao.findAll();
		Advice advice = adviceDaily(pbtype, advices);
		m.addAttribute("advice", advice);
		req.getSession().setAttribute("idPb", idProblem);
		req.getSession().setAttribute("pbType", pbtype);
		SimpleDateFormat formater = new SimpleDateFormat("'le' dd MMMM yyyy");

		if (p.getObjective().equals(Problem.ProblemObjective.TO_STOP)) {

			m.addAttribute("money", Calculation.moneySave(p));
			m.addAttribute("abstinence", Calculation.absTimeSec(p));
			m.addAttribute("stopDate", formater.format(p.getCreationDate()));
			m.addAttribute("cigaretteNotSmoke", Calculation.notConsume(p));
			m.addAttribute("time", Calculation.timeFormat(Calculation.timeSave(p)));
			m.addAttribute("height", Calculation.heightNotSmokeGeek(p));

			return "dashboard/addiction/tabac/stop-tabac";
		} else {
			Objective obj = p.getLiObjectives().stream()
					.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null);

			if ((obj.getEndDate().getTime() - new Date().getTime()) > 0) {
				m.addAttribute("money", Calculation.reducMoneySave(p));
				m.addAttribute("objStart", obj);
				m.addAttribute("cigaretteNotSmoke", Calculation.quantityNotSmoke(p));
				return "dashboard/addiction/tabac/reduc-tabac";
			} else {
				m.addAttribute("conso", obj.getRealConso());
				m.addAttribute("boolEnd", true);
				m.addAttribute("pb", p);
				return "dashboard/addiction/reduc-week-point";
			}
		}
	}

	@GetMapping("dashboard/motivation")
	public String motivation(Model m, HttpServletRequest req) {
		Problem p = problemDao.findById((long) req.getSession().getAttribute("idPb"));
		m.addAttribute("motivation", p.getMotivation());
		return "dashboard/addiction/motivation";
	}

	@GetMapping("dashboard/cracked")
	public String cracked(Model m, HttpServletRequest req) {
		Problem p = problemDao.findById((long) req.getSession().getAttribute("idPb"));
		m.addAttribute("motivation", p.getMotivation());
		return "dashboard/addiction/cracked";
	}

	@GetMapping("reduc-tabac-conso")
	public String consommation(Model m, HttpSession session) {
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		Objective o = p.getLiObjectives().stream()
				.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null);

		Map<Integer, Integer> conso = o.getRealConso();
		long nbDay = (((new Date()).getTime() - o.getStartDate().getTime()) / 86400000);
		if (nbDay > 7)
			nbDay = 7;
		Map<Integer, Integer> consoToShow = new HashMap<Integer, Integer>();
		for (int i = 0; i < nbDay; i++) {
			consoToShow.put(i + 1, conso.get(i + 1));
		}
		m.addAttribute("consoIsEmpty", consoToShow.size() == 0);
		m.addAttribute("conso", consoToShow);
		m.addAttribute("pb", p);
		return "dashboard/addiction/reduc-week-point";
	}

	@GetMapping("reduc-tabac-cancel")
	public String cancelObj(Model m, HttpSession session) {
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		Objective o = p.getLiObjectives().stream()
				.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null);
		o.setStatus(ObjectiveStatus.CANCELED);
		p.setActive(false);
		objectiveDao.Update(o);
		problemDao.Update(p);
		session.setAttribute("user", userDao.findById(((User) session.getAttribute("user")).getIdUser()));
		return "dashboard/addiction/reduc-end";
	}

	@PostMapping("save-tabac-conso")
	public String saveConso(HttpSession session, Model m, @RequestParam("map") Integer[] conso) {
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		Objective o = p.getLiObjectives().stream()
				.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null);
		for (int i = 0; i < conso.length; i++) {
			o.getRealConso().put(i + 1, conso[i]);
		}
		o.setQuantityNotSmoke(Calculation.quantityNotSmoke(p));
		objectiveDao.Update(o);
		ProblemType pbtype = p.getProblemType();
		List<Advice> advices = adviceDao.findAll();
		Advice advice = adviceDaily(pbtype, advices);

		if ((o.getEndDate().getTime() - new Date().getTime()) > 0) {
			m.addAttribute("advice", advice);
			m.addAttribute("money", Calculation.reducMoneySave(p));
			m.addAttribute("objStart", p.getLiObjectives().stream()
					.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null));
			m.addAttribute("cigaretteNotSmoke", Calculation.quantityNotSmoke(p));
			return "dashboard/addiction/tabac/reduc-tabac";

		} else {
			actionAtReductionEnd(p, o);
			p.setActive(false);
			problemDao.Update(p);
			session.setAttribute("user", userDao.findById(((User) session.getAttribute("user")).getIdUser()));
			return "dashboard/addiction/reduc-end";
		}
	}

	@GetMapping("reduc-end-stop")
	public String fromDecreaseToStop(HttpSession session) {
		User u = userDao.findById(((User) session.getAttribute("user")).getIdUser());
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		u.getLiProblem().remove(p);
		p.setCreationDate(new Date());
		p.setObjective(ProblemObjective.TO_STOP);
		p.setActive(true);
		problemDao.Update(p);
		u.getLiProblem().add(p);
		session.setAttribute("user", u);
		return "home";
	}

	@GetMapping("reduc-end-reducAgain")
	public String fromDecreaseToDecrease(Model m, HttpSession session) {
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		m.addAttribute("pbType", p.getProblemType());
		m.addAttribute("obj", new Objective());
		m.addAttribute("pb", p);
		session.setAttribute("user", userDao.findById(((User) session.getAttribute("user")).getIdUser()));
		return "problem/objective-form";
	}

	@GetMapping("reinit-stop")
	public String reinitStop(HttpSession session) {
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		Objective o = new Objective();
		o.setTitle("crackedSave");
		o.setStatus(ObjectiveStatus.COMPLETED);
		o.setMoneySave(Calculation.moneySave(p));
		o.setQuantityNotSmoke(Calculation.notConsume(p));
		o.setProblem(p);
		p.getLiObjectives().add(o);
		p.setCreationDate(new Date());
		problemDao.Update(p);
		return "redirect:/Tabac/" + p.getIdProblem();
	}

	@GetMapping("from-stop-to-reduc")
	public String fromStopToReduc(Model m, HttpSession session) {
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		p.setObjective(ProblemObjective.TO_DECREASE);
		p.setActive(false);
		problemDao.Update(p);
		m.addAttribute("pbType", p.getProblemType());
		m.addAttribute("obj", new Objective());
		m.addAttribute("pb", p);
		session.setAttribute("user", userDao.findById(((User) session.getAttribute("user")).getIdUser()));
		return "problem/objective-form";
	}

	@GetMapping("money")
	public String toMoneyJsp(Model m, HttpSession session) {
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		Objective o = p.getLiObjectives().stream()
				.filter(x -> x.getStatus().equals(Objective.ObjectiveStatus.IN_PROGRESS)).findFirst().orElse(null);
		if (p.getObjective().equals(ProblemObjective.TO_STOP)) {
			m.addAttribute("AmountOfMoneySave", Calculation.moneySave(p));
		} else {
			o.setFinalQuantity(0);
			m.addAttribute("AmountOfMoneySave", Calculation.reducMoneySave(p));
		}
		m.addAttribute("whatToBuy", Calculation.whatToBuyWithMyMoney(p));
		return "dashboard/addiction/money";
	}

	@GetMapping("height")
	public String toHeightJsp(Model m, HttpSession session) {
		Problem p = problemDao.findById((long) session.getAttribute("idPb"));
		m.addAttribute("height", Calculation.heightNotSmokeGeek(p));
		return "dashboard/addiction/tabac/cigarette-size";
	}

	public void actionAtReductionEnd(Problem p, Objective o) {
		if (p.getStartQuantity() > Calculation.finalAverageSmoke(o)) {
			o.setFinalQuantity(Calculation.finalAverageSmoke(o));
		} else
			o.setFinalQuantity(p.getStartQuantity());

		if (Calculation.reducMoneySave(p) > 0)
			o.setMoneySave(Calculation.reducMoneySave(p));
		else
			o.setMoneySave(0);

		o.setQuantityNotSmoke(Calculation.quantityNotSmoke(p));
		o.setStatus(ObjectiveStatus.COMPLETED);
		p.setStartQuantity(o.getFinalQuantity());
		problemDao.Update(p);
		objectiveDao.Update(o);
	}

	private Advice adviceDaily(ProblemType pbtype, List<Advice> advices) {
		List<Advice> advicesTemp = new ArrayList<>();
		Advice advice = null;
		Date today = new Date();

		// Tri advice par Pbtype
		for (Advice a : advices) {
			List<ProblemType> li = a.getLiProblemType();
			for (ProblemType pt : li) {
				if (pt.getIdProblemTypes() == pbtype.getIdProblemTypes())
					;
				advicesTemp.add(a);
			}
		}
		// Recherche si un advice a déjà été utilisé aujourd'hui
		List<Advice> liAdDate = advicesTemp.stream().filter(x -> x.getUseDate() != null).collect(Collectors.toList());
		if (!liAdDate.isEmpty()) {
			for (Advice ad : liAdDate) {
				if (ad.getUseDate().getDate() == today.getDate() && ad.getUseDate().getMonth() == today.getMonth()) {
					advice = ad;
					break;
				}
			}
		}
		// Recherche d'un advice n'ayant pas de date
		List<Advice> liAdNonDate = new ArrayList<Advice>();
		if (advice == null) {
			liAdNonDate = advicesTemp.stream().filter(y -> y.getUseDate() == null).collect(Collectors.toList());
			// Si un n'a pas de date on prends le premier
			if (!liAdNonDate.isEmpty())
				advice = liAdNonDate.get(0);

			// Sinon on remet les dates a 0
			else {
				advicesTemp.stream().forEach(x -> {
					x.setUseDate(null);
					adviceDao.Update(x);
				});
				advice = advicesTemp.get(0);
			}
			// On met la date du jour a l'advice choisi et on update
			advice.setUseDate(today);
			adviceDao.Update(advice);
		}
		return advice;
	}
}
