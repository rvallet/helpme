package fr.dawan.mvc1.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import fr.dawan.mvc1.beans.Objective;

public class ObjectiveDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public List<Objective> findAll() {
		return (List<Objective>) hibernateTemplate.find("FROM Objective", null);

	}

	@Transactional
	public Long insert(Objective obj) {
		return (Long) hibernateTemplate.save(obj);
	}

	@Transactional
	public void Update(Objective obj) {
		hibernateTemplate.saveOrUpdate(obj);
	}

	@Transactional
	public void Remove(long id) {
		hibernateTemplate.delete(findById(id));
	}

	@Transactional(readOnly = true)
	public Objective findById(long id) {
		return hibernateTemplate.get(Objective.class, id);
	}

}
