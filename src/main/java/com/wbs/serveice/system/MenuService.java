package com.wbs.serveice.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.wbs.dao.system.MenuDao;
import com.wbs.entity.system.Menu;
import com.wbs.serveice.StringIdEntityService;

/**
 * 系统菜单Service
 * 
 */
@Service
public class MenuService extends StringIdEntityService<Menu, MenuDao> {

	/**
	 * 获取顶级菜单列表，并构建子菜单集合
	 * 
	 * @return
	 */
	public List<Menu> getTopMenuList() {
		List<Menu> allMenus = entityDao.getAll("name", true);
		List<Menu> topMenus = new ArrayList<Menu>();
		Iterator<Menu> menuIt = allMenus.iterator();
		while (menuIt.hasNext()) {
			Menu menu = menuIt.next();
			if (menu.getParent() != null) {
				continue;
			}
			menuIt.remove();
			try {
				menu = buildToolMenu(menu, allMenus);
			} catch (Exception e) {
				e.printStackTrace();
			}
			topMenus.add(menu);
			menuIt = allMenus.iterator();
		}
		return topMenus;
	}

	private Menu buildToolMenu(Menu parent, List<Menu> menus) {
		Set<Menu> subMenus = new HashSet<Menu>();
		Iterator<Menu> menuIt = menus.iterator();
		while (menuIt.hasNext()) {
			Menu menu = menuIt.next();
			if (menu.getParent() == null) {
				continue;
			}
			if (parent.getId().equals(menu.getParent().getId())) {
				menuIt.remove();
				menu = buildToolMenu(menu, menus);
				subMenus.add(menu);
				menuIt = menus.iterator();
			}
		}
		parent.setSubMenus(subMenus);
		return parent;
	}
}
