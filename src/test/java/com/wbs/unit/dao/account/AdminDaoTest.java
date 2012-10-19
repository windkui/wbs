package com.wbs.unit.dao.account;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.orm.PropertyFilter;

import com.springside.zsword.modules.orm.ExtPropertyFilter;
import com.springside.zsword.modules.orm.ExtPropertyFilter.ExtMatchType;
import com.springside.zsword.modules.orm.ExtPropertyFilter.ExtPropertyType;
import com.wbs.dao.system.UserDao;
import com.wbs.entity.system.User;
import com.wbs.unit.BaseSpringTest;

public class AdminDaoTest extends BaseSpringTest {

	@Autowired
	private UserDao userDao;

	@Test
	@Rollback(false)
	public void testSearch() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

		PropertyFilter filter = new ExtPropertyFilter(ExtMatchType.NOTNULL,
				ExtPropertyType.S, "role.id", null);
		filters.add(filter);
		try {
			List<User> adminList = userDao.find(filters);
			System.out.println(adminList.size());
			for (int i = 0; i < 1000; i++) {
				User newUser = new User();
				newUser.setEmail("test" + i + "@test.com");
				newUser.setPassword("test");
				newUser.setLoginName("TestUser-" + i);
				userDao.save(newUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testNativeSearch() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		/*
		 * PropertyFilter filter = new ExtPropertyFilter(ExtMatchType.ISNULL,
		 * PropertyType.S, "role.id", null); filters.add(filter);
		 */
		Criteria criteria = userDao.getSession().createCriteria(User.class);
		// criteria.createAlias("role", "role");
		String associationPath = "role.name";
		String aliasName = "roleName";
		Criterion criterion = Restrictions.eq(associationPath, "1");
		criteria = criteria.add(criterion);
		try {
			List<User> adminList = userDao.find(filters);
			adminList = criteria.list();
			System.out.println(adminList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
