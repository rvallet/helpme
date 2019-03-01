package fr.dawan.mvc1.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.mvc1.beans.Advice;

public class AdviceDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Advice> findAll() {
		return (List<Advice>) hibernateTemplate.find("FROM Advice", null);

	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Advice> findAll(int start, int nb) {
		return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("FROM Advice")
				.setFirstResult(start).setMaxResults(nb).list();
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Advice> FindByTitleOrDesc(String search) {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("From Advice adv WHERE adv.title LIKE :search OR adv.content like :search")
				.setParameter("search", "%" + search + "%").list();
	}

	@Transactional
	public Long insert(Advice a) {
		return (Long) hibernateTemplate.save(a);
	}

	@Transactional(readOnly = true)
	public Advice findById(long id) {
		return hibernateTemplate.get(Advice.class, id);
	}

	@Transactional
	public void Update(Advice a) {
		hibernateTemplate.saveOrUpdate(a);
	}

	@Transactional
	public void Remove(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@Transactional(readOnly = true)
	public long nbAdvice() {
		return (long) hibernateTemplate.find("SELECT COUNT (a.idAdvice) FROM Advice a", null).get(0);
	}

}