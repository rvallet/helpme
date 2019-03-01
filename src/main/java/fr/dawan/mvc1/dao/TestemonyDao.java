package fr.dawan.mvc1.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.mvc1.beans.Testemony;

public class TestemonyDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Testemony> findAll() {
		return (List<Testemony>) hibernateTemplate.find("FROM Testemony", null);

	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Testemony> findAll(int start, int nb) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Testemony")
				.setFirstResult(start).setMaxResults(nb).list();
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Testemony> findAllPublished() {
		return (List<Testemony>) hibernateTemplate.find("FROM Testemony test WHERE test.status LIKE 'PUBLISHED'", null);
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Testemony> FindByTitleOrDesc(String search) {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("From Testemony test WHERE test.title LIKE :search OR test.content LIKE :search")
				.setParameter("search", "%" + search + "%").list();
	}

	@Transactional
	public Long insert(Testemony t) {
		return (Long) hibernateTemplate.save(t);
	}

	@Transactional(readOnly = true)
	public Testemony findById(long id) {
		return hibernateTemplate.get(Testemony.class, id);
	}

	@Transactional
	public void Update(Testemony a) {
		hibernateTemplate.saveOrUpdate(a);
	}

	@Transactional
	public void Remove(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@Transactional(readOnly = true)
	public long nbAdvice() {
		return (long) hibernateTemplate.find("SELECT COUNT (a.idTemoignage) FROM Testemony a", null).get(0);
	}

}