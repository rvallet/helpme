package fr.dawan.mvc1.controlers.usercontrolers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.dawan.mvc1.beans.Problem;
import fr.dawan.mvc1.beans.ProblemType;
import fr.dawan.mvc1.beans.Testemony;
import fr.dawan.mvc1.beans.Testemony.TestemonysStatus;
import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.ProblemDao;
import fr.dawan.mvc1.dao.ProblemTypeDao;
import fr.dawan.mvc1.dao.TestemonyDao;
import fr.dawan.mvc1.tools.Calculation;

@Controller
public class TestemonyControler {

	@Autowired
	TestemonyDao testemonyDao;

	public void setTestemonyDao(TestemonyDao testemonyDao) {
		this.testemonyDao = testemonyDao;
	}

	@Autowired
	ProblemTypeDao problemTypeDao;

	@Autowired
	ProblemDao problemDao;

	public void setProblemTypeDao(ProblemTypeDao problemTypeDao) {
		this.problemTypeDao = problemTypeDao;
	}

	@GetMapping("/testemony")
	public String addTestemony(Model m) {
		m.addAttribute("testemony", new Testemony());
		return "/dashboard/addiction/testemony-form";
	}

	@PostMapping("/testemony/save")
	public String saveTestemony(Model m, @Valid @ModelAttribute("testemony") Testemony testemony, BindingResult result,
			HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		testemony.setPseudo(u.getPseudo());
		testemony.setDateWrite(new Date());
		testemony.setStatus(TestemonysStatus.CREATED);
		testemony.setProblemTypes((ProblemType) req.getSession().getAttribute("pbType"));
		testemonyDao.insert(testemony);
		return "/dashboard/addiction/testemony-success";
	}

	@GetMapping("/testemonies")
	public String randomTestemony(Model m, HttpServletRequest req) {
		Long idProblem = (Long) req.getSession().getAttribute("idPb");
		Problem problem = problemDao.findById(idProblem);
		Long abstinence = Calculation.absTimeSec(problem);
		// TODO : Remove static boolean true for prod env (only one month abstinent user
		// can post a testemony)
		boolean canSpeak = true;
//		boolean canSpeak = abstinence > 2628000;
		List<Testemony> testemonies = testemonyDao.findAllPublished();
		Random random = new Random();
		int index = random.nextInt(testemonies.size());
		SimpleDateFormat formater = new SimpleDateFormat("'le' dd MMMM yyyy 'à' hh:mm:ss");
		m.addAttribute("selectedTestemonyContent", testemonies.get(index).getContent());
		m.addAttribute("selectedTestemonyTitle", testemonies.get(index).getTitle());
		m.addAttribute("selectedTestemonyPseudo", testemonies.get(index).getPseudo());
		m.addAttribute("selectedTestemonyDate", formater.format(testemonies.get(index).getDateWrite()));
		m.addAttribute("canSpeak", canSpeak);
		return "/dashboard/addiction/testemonies";
	}
}
