package fr.dawan.mvc1.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.mvc1.beans.ProblemType;

public class ProblemTypeDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<ProblemType> findAll() {
		return (List<ProblemType>) hibernateTemplate.find("FROM ProblemType", null);

	}

	@Transactional(readOnly = true)
	public ProblemType findById(long id) {
		// FROM ProblemType pt JOIN FETCH pt.advices ....
		return hibernateTemplate.get(ProblemType.class, id);

	}

	@Transactional
	public void Update(ProblemType pT) {
		hibernateTemplate.saveOrUpdate(pT);
	}

	@Transactional
	public void UpdateAll(List<ProblemType> pT) {
		for (ProblemType pb : pT) {
			hibernateTemplate.flush();
			hibernateTemplate.saveOrUpdate(pb);
		}
	}
}