package fr.dawan.mvc1.controlers.usercontrolers;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.beans.User.StatusUser;
import fr.dawan.mvc1.dao.UserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserDao userDao;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/")
	public String home(Locale locale, Model model, HttpServletRequest request) {
		return "home";
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping("/test/insert-data")
	public String insterData() {
		User u = new User(null, "toto", "homme", "toto@gmail.com", "toto59", StatusUser.ADMIN, new Date(), 0, null);
		u.setPassword("toto59");
		userDao.insert(u);
		userDao.getHibernateTemplate().evict(u);
		return "home";
	}

	@GetMapping("/disconnect")
	public String deconnexion(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/about-us")
	public String aboutUs() {
		return "compagny-content/about-us";
	}

	@GetMapping("/privacy-policy")
	public String privacyPolicy() {
		return "compagny-content/privacy-policy";
	}

	@GetMapping("/contact-failure")
	public String contactFailure() {
		return "error/contact-failure";
	}

}
