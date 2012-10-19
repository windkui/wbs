package com.wbs.unit.service.account;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.wbs.data.AccountData;
import com.wbs.entity.system.User;
import com.wbs.service.UserServiceSupport;
import com.wbs.unit.BaseSpringTest;

public class UserSupportServiceTest extends BaseSpringTest {

	@Autowired
	private UserServiceSupport supportService;

	@Test
	@Rollback(false)
	@Transactional(readOnly = true)
	public void testCrud() {
		User user = AccountData.getRandomUser();
		supportService.save(user);
	}
}
