package fr.dawan.mvc1.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.mvc1.beans.Problem;

public class ProblemDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Problem> findAll() {
		return (List<Problem>) hibernateTemplate.find("FROM Problem", null);

	}

	@Transactional
	public Long insert(Problem pb) {
		return (Long) hibernateTemplate.save(pb);
	}

	@Transactional
	public void Update(Problem pb) {
		hibernateTemplate.saveOrUpdate(pb);
	}

	@Transactional
	public void Remove(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@Transactional(readOnly = true)
	public Problem findById(Long id) {
		return hibernateTemplate.get(Problem.class, id);
	}

}
