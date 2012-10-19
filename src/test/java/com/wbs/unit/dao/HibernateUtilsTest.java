package com.wbs.unit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.springside.zsword.modules.orm.hibernate.HibernateUtils;
import com.wbs.entity.system.User;

public class HibernateUtilsTest {

	@Test
	public void mergeByCheckedIds() {
		User a = new User();
		a.setId("1");

		User b = new User();
		b.setId("1");

		List<User> srcList = Lists.newArrayList(a, b);
		List<String> idList = Lists.newArrayList("1", "3");

		HibernateUtils.mergeByCheckedIds(srcList, idList, User.class);

		assertEquals(2, srcList.size());
		assertTrue("1".equals(srcList.get(0).getId()));
		assertTrue("3".equals(srcList.get(1).getId()));
	}

}
