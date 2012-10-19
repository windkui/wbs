package com.wbs.unit.service.account;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.wbs.data.AccountData;
import com.wbs.entity.system.Menu;
import com.wbs.entity.system.Role;
import com.wbs.entity.system.User;
import com.wbs.serveice.system.MenuService;
import com.wbs.serveice.system.RoleService;
import com.wbs.serveice.system.UserService;
import com.wbs.unit.BaseSpringTest;

public class RoleServiceTest extends BaseSpringTest {

	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	public RoleServiceTest() {
		loadDefaultData = false;
	}

	@Test
	@Rollback(false)
	public void testCrud() throws Exception {
		try {
			List<User> userList = userService.getAll();
			for (User user : userList) {
				userService.delete(user);
			}
			List<Role> roleList = roleService.getAll();
			for (Role role : roleList) {
				roleService.delete(role);
			}
			Role role = AccountData.getRandomRole();
			Menu menu = AccountData.getRandomMenu();
			Menu subMenu = AccountData.getRandomMenu();
			menuService.save(menu);
			subMenu.setParent(menu);
			menuService.save(subMenu);
			role.getMenuList().add(menu);
			roleService.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Rollback(false)
	public void testDeleteMenu() {
		Menu menu = menuService.getAll().get(0);
		menuService.delete(menu);
	}

	@Test
	@Rollback(false)
	public void testCrudWithToMany() {
		Role role = roleService.getAll().get(0);
		System.out.println(roleService.getAll().size());
		roleService.delete(role);
	}
}
