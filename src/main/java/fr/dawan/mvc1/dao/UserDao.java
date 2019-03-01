package fr.dawan.mvc1.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.mvc1.beans.User;

public class UserDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) hibernateTemplate.find("FROM User", null);

	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<User> findAll(int start, int nb) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM User").setFirstResult(start)
				.setMaxResults(nb).list();
	}

	@Transactional
	public Long insert(User u) {
		return (Long) hibernateTemplate.save(u);
	}

	@Transactional(readOnly = true)
	public User findById(long id) {
		return hibernateTemplate.get(User.class, id);
	}

	@Transactional
	public void Update(User u) {
		hibernateTemplate.saveOrUpdate(u);
	}

	@Transactional
	public void Remove(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<User> FindByName(String search) {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("From User u WHERE u.pseudo LIKE :search").setParameter("search", "%" + search + "%")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<User> FindByNameOrEmail(String search) {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("From User u WHERE concat(u.pseudo, u.email) LIKE :search")
				.setParameter("search", "%" + search + "%").list();
	}

	@Transactional(readOnly = true)
	public long nbUsers() {
		return (long) hibernateTemplate.find("SELECT COUNT (u.idUser) FROM User u", null).get(0);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		List<User> lu = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("FROM User u WHERE u.email= :email").setParameter("email", email).list();
		if (lu != null && lu.size() > 0)
			return lu.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public User findByToken(String token) {
		List<User> lu = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("FROM User u WHERE u.resetToken= :token").setParameter("token", token).list();
		if (lu != null && lu.size() > 0)
			return lu.get(0);
		return null;
	}
}