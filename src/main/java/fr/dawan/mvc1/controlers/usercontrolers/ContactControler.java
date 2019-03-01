package fr.dawan.mvc1.controlers.usercontrolers;

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

import fr.dawan.mvc1.dao.UserDao;
import fr.dawan.mvc1.formsbeans.ContactForm;
import fr.dawan.mvc1.formsbeans.ContactForm.TypesDemandes;

@Controller
public class ContactControler {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping("contact-us")
	public String contactForm(Model model) {
		model.addAttribute("contact-form", new ContactForm());
		model.addAttribute("TypeDemande", TypesDemandes.values());
		return "compagny-content/contact-us";
	}

	@PostMapping("send")
	public String sendEmail(Model model, @Valid @ModelAttribute ContactForm form, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			model.addAttribute("contact-form", form);
			return "compagny-content/contact-us";
		}
		try {
			Email email = new SimpleEmail();

			/*
			 * --StartOf - GoogleMail SMTP - Uncomment to use && comment entire Fake SMTP
			 * part. Limited @ 100 email send per month
			 */
//			email.setHostName("smtp.googlemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("r.letesteur@gmail.com", "AqwZsxEdc"));
//			email.setSSLOnConnect(true);
			/* --EndOf - GoogleMail SMTP */

			/*
			 * --StartOf - Fake SMTP - Uncomment to use && comment entire GoogleMail SMTP
			 * part (http://nilhcem.com/FakeSMTP/download.html)
			 */
			email.setHostName("127.0.0.1");
			email.setSmtpPort(25);
			/* --EndOf - Fake SMTP */

			email.setFrom(form.getEmail());
			email.setSubject(form.getType().toString());
			email.setMsg(form.getMessage());
			email.addTo("r.letesteur@gmail.com");
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msgRetour", e.getMessage());
			model.addAttribute("contact-form", form);
//			return "contact-failure";
		}
		String msg = "Votre message à bien été envoyé";
		model.addAttribute("contact-success", msg);
		model.addAttribute("contact-form", new ContactForm());
		model.addAttribute("TypeDemande", TypesDemandes.values());
		return "error/contact-success";
	}
}
