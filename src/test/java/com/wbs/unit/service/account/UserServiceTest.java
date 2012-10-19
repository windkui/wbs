package com.wbs.unit.service.account;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.wbs.data.AccountData;
import com.wbs.entity.system.User;
import com.wbs.serveice.system.AccountManager;
import com.wbs.serveice.system.UserService;
import com.wbs.unit.BaseSpringTest;

public class UserServiceTest extends BaseSpringTest {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountManager accountManager;

	@Test
	@Rollback(false)
	public void testCrud() {
		User user = null;
		List<User> userList = userService.getAll();
		if (userList.size() > 0) {
			user = userList.get(0);
		} else {
			user = AccountData.getRandomUser();
		}
		try {
			user = userService.load("402881b836b4f16a0136b4fea85d0003");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		user.setName("Admin-Test:" + new Date());
		// userService.save(user);
		userList = userService.getAll();
		if (!userList.isEmpty()) {
			user = userList.get(0);
			user.setName("Admin-Test:");
			// userService.save(user);
		}
	}
}
