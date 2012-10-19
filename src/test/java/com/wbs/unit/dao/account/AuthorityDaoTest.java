package com.wbs.unit.dao.account;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wbs.dao.system.AuthorityDao;
import com.wbs.entity.system.Authority;
import com.wbs.unit.BaseSpringTest;

public class AuthorityDaoTest extends BaseSpringTest {
	@Autowired
	private AuthorityDao authorityDao;

	@Test
	public void testCrud() {
		List<Authority> authorityList = authorityDao.getAll("name", true);
		System.out.println(authorityList.size());
	}
}
