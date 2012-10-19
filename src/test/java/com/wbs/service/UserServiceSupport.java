package com.wbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.dao.UserDaoSupport;
import com.wbs.entity.system.User;

@Service
@Transactional(readOnly = true)
public class UserServiceSupport {

	private UserDaoSupport daoSupport;

	public List<User> getAll() {
		return daoSupport.getAll();
	}

	@Transactional(readOnly = true)
	public void save(User entity) {
		daoSupport.save(entity);
	}

	@Autowired
	public void setDaoSupport(UserDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}
