package fr.dawan.mvc1.controlers.usercontrolers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.UserDao;
import fr.dawan.mvc1.tools.PasswordEncoderArgon2;

@Controller
public class PwdResetController {
	@Autowired
	private UserDao userDao;

	@GetMapping("/password-reset")
	public String pwdChange(Model model, @RequestParam("token") String token, HttpServletRequest req) {
		User u = userDao.findByToken(token);
		System.out.println(u);
		if (u != null) {
			model.addAttribute("resetToken", token);
		} else {
			req.getSession().setAttribute("errorMessage",
					"Le lien de réinitialisation de mot de passe utilisé n'est plus valide.\n Merci de renouveller votre demande");
			return "redirect:/password-recovery";
		}
		return "error/pwd-reset";
	}

	@PostMapping("password-changed")
	public String pwdUpdate(Model model, @RequestParam("resetToken") String token, HttpServletRequest req,
			@RequestParam("password") String password) {
		User u = userDao.findByToken(token);
		// TODO: Double sasie du mot de passe sur une demande de reset-password
//		if (u != null && !pwd.equals(u.getPassword())) {
//			model.addAttribute("u", u);
//			String msg = "Les mot de passe saisis ne sont pas identiques";
//			model.addAttribute("pwdnotmatch", msg);
//			String cible = "password-reset?token=" + token;
//			return cible;
//		} else 
		if (u != null) {
			CharSequence pwdCharSeq = new StringBuilder(password);
			u.setPassword(new PasswordEncoderArgon2().encode(pwdCharSeq));
			u.setResetToken(null);
			userDao.Update(u);
			req.getSession().setAttribute("success-resetpwd", "Votre nouveau mot de passe à été mis à jour");
			return "error/pwd-resetsuccess";
		} else {
			req.getSession().setAttribute("error-resetpwd",
					"La modification n'a pu être effectuée. Effectuez une nouvelle demande");
			return "error/pwd-reset";
		}
//		return "redirect:/authenticate";
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:authenticate");
	}
}
