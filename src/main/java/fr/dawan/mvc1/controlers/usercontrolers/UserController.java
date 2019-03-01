package fr.dawan.mvc1.controlers.usercontrolers;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.AdviceDao;
import fr.dawan.mvc1.dao.ObjectiveDao;
import fr.dawan.mvc1.dao.ProblemDao;
import fr.dawan.mvc1.dao.ProblemTypeDao;
import fr.dawan.mvc1.dao.UserDao;
import fr.dawan.mvc1.tools.PasswordEncoderArgon2;

@Controller
public class UserController {

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

	@PostMapping("/account-creation/save-user")
	public String sauvegarde(@Valid @ModelAttribute("u") User u, BindingResult result, Model m,
			HttpServletRequest request, @RequestParam("verifpwd") String pwd) {
		String cible = "redirect:../";
		if (result.hasErrors()) {
			m.addAttribute("u", u);
			m.addAttribute("errors", result.getAllErrors());
			cible = "account/account-creation";
			return cible;
		} else if (!pwd.equals(u.getPassword())) {
			m.addAttribute("u", u);
			String msg = "Les mot de passe saisis ne sont pas identiques";
			m.addAttribute("pwdnotmatch", msg);
			cible = "account/account-creation";
			return cible;
		} else if (userDao.findByEmail(u.getEmail()) == null) {
			u.setStatut(User.StatusUser.USER);
			CharSequence pwdCharSeq = new StringBuilder(u.getPassword());
			u.setPassword(new PasswordEncoderArgon2().encode(pwdCharSeq));
			u.setLiProblem(new ArrayList<>());
			u.setCreationDate(new Date());
			userDao.insert(u);
			userDao.getHibernateTemplate().refresh(u);
			request.getSession().setAttribute("email", u.getEmail());
			request.getSession().setAttribute("user_id", u.getIdUser());
			request.getSession().setAttribute("user", u);
			request.getSession().setAttribute("status", u.getStatusStr());
			request.getSession().setAttribute("isAuth", true);
			return "home";
		} else if (u.getEmail().equalsIgnoreCase(userDao.findByEmail(u.getEmail()).getEmail())) {
			request.getSession().setAttribute("email", u.getEmail());
			m.addAttribute("msg",
					"Cet eMail existe déjà, souhaitez-vous récupérer votre mot de passe ou vous authentifier ?");
			m.addAttribute("account-creation", u);
			return "/account/account-creation";
		} else
			return cible;
	}

	@GetMapping("/account-creation")
	public String addUser(Model m) {
		m.addAttribute("u", new User());
		return "/account/account-creation";
	}

	/**
	 * Recherche des différents types de problème depuis la base de donnée et on
	 * l'envoie dans le modèle de la page jsp vers laquelle on redirige
	 * l'utilisateur
	 * 
	 * @param m
	 * @return
	 */

}
