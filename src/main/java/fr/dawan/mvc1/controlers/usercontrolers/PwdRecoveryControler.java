package fr.dawan.mvc1.controlers.usercontrolers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.UserDao;
import fr.dawan.mvc1.formsbeans.ContactForm;
import fr.dawan.mvc1.formsbeans.PwdRecoveryForm;

@Controller
public class PwdRecoveryControler {

	@Autowired
	private UserDao userDao;

	@GetMapping("password-recovery")
	public String showRecovery(Model model) {
		PwdRecoveryForm prf = new PwdRecoveryForm(null);
		model.addAttribute("prf-recovery", prf);
		return "error/pwd-recovery";
	}

	@PostMapping("sendpwd")
	public String checkEmail(HttpServletRequest request, Model model,
			@Valid @ModelAttribute("prf-recovery") PwdRecoveryForm form, BindingResult result) {
		String cible = "redirect:/authenticate";
		if (result.hasErrors()) {
			model.addAttribute("errors", result);
			model.addAttribute("prf-recovery", form);
			return "error/pwd-recovery";
		}
		User u = userDao.findByEmail(form.getEmail());
		if (u != null && form.getEmail().equals(u.getEmail())) {
			request.getSession().setAttribute("email", u.getEmail());
			request.getSession().setAttribute("user", u);
			/*
			 * Add a random token to the User table. This token is crashed if a new request
			 * is made. It expires when the password is changed
			 * 
			 */
			u.setResetToken(UUID.randomUUID().toString());
			userDao.Update(u);
			String appUrl = request.getScheme() + "://" + request.getServerName() + ":8080/HelpMe";
			try {
				Email email = new SimpleEmail();

				/*
				 * --StartOf - GoogleMail SMTP - Uncomment to use && comment entire Fake SMTP
				 * part. (Limited @ 100 email send per month)
				 */
//				email.setHostName("smtp.googlemail.com");
//				email.setSmtpPort(465);
//				email.setAuthenticator(new DefaultAuthenticator("r.letesteur@gmail.com", "AqwZsxEdc"));
//				email.setSSLOnConnect(true);
				/* --EndOf - GoogleMail SMTP */

				/*
				 * --StartOf - Fake SMTP - Uncomment to use && comment entire GoogleMail SMTP
				 * part (http://nilhcem.com/FakeSMTP/download.html)
				 */
				email.setHostName("127.0.0.1");
				email.setSmtpPort(25);
				/* --EndOf - Fake SMTP */

				email.setFrom("r.letesteur@gmail.com");
				email.setSubject("Réinitialisation de votre mot de passe");
				email.setMsg("Pour enregistrer un nouveau mot de passe, cliquez sur le lien suivant : \n" + appUrl
						+ "/password-reset?token=" + u.getResetToken());
				email.addTo(u.getEmail());
				email.send();
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msgRetour", e.getMessage());
				model.addAttribute("contact-form", form);
			}
			String msg = "Votre message à bien été envoyé";
			model.addAttribute("newpwdrequest-success", msg);
			model.addAttribute("contact-form", new ContactForm());
			cible = "error/recoverysent-success";
			return cible;
		} else {
			model.addAttribute("msg",
					"L'email indiqué n'a pas été reconnu.\n Renseignez l'email utilisé lors de votre création de compte ou créez un nouveau compte");
			model.addAttribute("prf-recovery", form);
			cible = "error/pwd-recovery";
		}
		return cible;
	}
}
