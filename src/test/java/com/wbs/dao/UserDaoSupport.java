package com.wbs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wbs.entity.system.User;

@Repository
public class UserDaoSupport extends HibernateDaoSupport {

	@Autowired
	public void setConfigHibernateTemplate(HibernateTemplate hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
	}

	public List<User> getAll() {
		return this.getHibernateTemplate().loadAll(User.class);
	}

	public void save(User entity) {
		this.getHibernateTemplate().save(entity);
	}
}
