package com.wbs.data;

import java.util.List;
import java.util.UUID;

import org.springside.modules.test.utils.DataUtils;

import com.google.common.collect.Lists;
import com.wbs.entity.system.Authority;
import com.wbs.entity.system.Menu;
import com.wbs.entity.system.Role;
import com.wbs.entity.system.User;

/**
 * Account相关实体测试数据生成.
 * 
 */
public class AccountData {

	public static final String DEFAULT_PASSWORD = "123456";

	private static List<Menu> defaultMenuList = null;

	private static List<Role> defaultRoleList = null;

	private static List<Authority> defaultAuthorityList = null;

	private static final String STRINGID_SOURCE = "0123456789abcdefg";

	protected static String randomStringId() {
		return UUID.fromString(STRINGID_SOURCE).toString();
	}

	public static User getRandomUser() {
		String userName = DataUtils.randomName("User");

		User user = new User();
		user.setLoginName(userName);
		user.setName(userName);
		user.setPassword(DEFAULT_PASSWORD);
		user.setEmail(userName + "@springside.org.cn");

		return user;
	}

	public static User getRandomUserWithRole() {
		User user = getRandomUser();
		user.setRole(getRandomDefaultRole());
		return user;
	}

	public static Role getRandomRole() {
		Role role = new Role();
		role.setName(DataUtils.randomName("Role"));

		return role;
	}

	public static Role getRandomRoleWithAuthority() {
		Role role = getRandomRole();
		role.getAuthorityList().addAll(getRandomDefaultAuthorityList());
		return role;
	}

	public static Role getRandomRoleWithMenu() {
		Role role = getRandomRole();
		role.getMenuList().addAll(getRandomDefaultMenuList());
		return role;
	}

	public static List<Role> getDefaultRoleList() {
		if (defaultRoleList == null) {
			defaultRoleList = Lists.newArrayList();
			defaultRoleList.add(new Role(randomStringId(), "管理员"));
			defaultRoleList.add(new Role(randomStringId(), "用户"));
		}
		return defaultRoleList;
	}

	public static Role getRandomDefaultRole() {
		return DataUtils.randomOne(getDefaultRoleList());
	}

	public static Menu getRandomMenu() {
		Menu menu = new Menu();
		menu.setName(DataUtils.randomName("Menu"));

		return menu;
	}

	public static Authority getRandomAuthority() {
		String authName = DataUtils.randomName("Authority");

		Authority authority = new Authority();
		authority.setName(authName);

		return authority;
	}

	public static List<Menu> getDefaultMenuList() {
		if (defaultMenuList == null) {
			defaultMenuList = Lists.newArrayList();
			defaultMenuList.add(new Menu("1", "系统管理", null));
			defaultMenuList.add(new Menu("2", "用户管理", null));
			defaultMenuList.add(new Menu("3", "权限管理", null));
			defaultMenuList.add(new Menu("4", "角色管理", null));
			defaultMenuList.add(new Menu("5", "菜单管理", null));
		}
		return defaultMenuList;
	}

	public static List<Authority> getDefaultAuthorityList() {
		if (defaultAuthorityList == null) {
			defaultAuthorityList = Lists.newArrayList();
			defaultAuthorityList.add(new Authority("1", "浏览用户"));
			defaultAuthorityList.add(new Authority("2", "修改用户"));
			defaultAuthorityList.add(new Authority("3", "浏览角色"));
			defaultAuthorityList.add(new Authority("4", "修改角色"));
		}
		return defaultAuthorityList;
	}

	public static List<Menu> getRandomDefaultMenuList() {
		return DataUtils.randomSome(getDefaultMenuList());
	}

	public static List<Authority> getRandomDefaultAuthorityList() {
		return DataUtils.randomSome(getDefaultAuthorityList());
	}
}
