package com.wbs.unit.dao.account;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.orm.PropertyFilter;

import com.wbs.dao.system.MenuDao;
import com.wbs.data.AccountData;
import com.wbs.entity.system.Menu;
import com.wbs.unit.BaseSpringTest;

public class MenuDaoTest extends BaseSpringTest {

	private static String menuId = null;

	@Autowired
	private MenuDao menuDao;

	@Test
	@Rollback(false)
	public void testCrud() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		List<Menu> topMenus = menuDao.find(filters);
		Menu parent = topMenus.get(0);
		System.out.println(parent.getSubMenus().size());
		Menu menu = AccountData.getRandomMenu();
		menu.setParent(parent);
		// menuDao.save(menu);
		menuId = menu.getId();
		for (Menu m : parent.getSubMenus()) {
			menuId = m.getId();
			if (menuId.length() > 2) {
				menuDao.delete(m);
				System.out.println(m.getName());
				break;
			}
		}
		// menuDao.evictCollectionRegion("subMenus");
	}

	@Test
	public void testEvictedCache() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		List<Menu> topMenus = menuDao.find(filters);
		Menu parent = topMenus.get(0);
		System.out.println(parent.getSubMenus().size());
	}

	@Test
	@Rollback(false)
	public void testCache() {
		Menu parent = menuDao.get("1");
		System.out.println(parent.getSubMenus().size());
		for (Menu m : parent.getSubMenus()) {
			if (m.getId().equals(menuId)) {
				System.out.println(m.getName());
				break;
			}
		}
	}
}
