package fr.dawan.mvc1.controlers.usercontrolers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.mvc1.beans.User;
import fr.dawan.mvc1.dao.UserDao;

@RestController
@RequestMapping("api")
public class UserRestController {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public List<User> findAll() {
		return userDao.findAll();
	}

	@RequestMapping(value = "/users/xml", method = RequestMethod.GET, produces = "application/xml")
	public List<User> findAllXml() {
		return userDao.findAll();
	}
}
