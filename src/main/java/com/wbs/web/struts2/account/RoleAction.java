package com.wbs.web.struts2.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.springside.zsword.modules.orm.ExtPropertyFilter;
import com.springside.zsword.modules.orm.hibernate.HibernateUtils;
import com.springside.zsword.modules.utils.json.Struts2JSONConvertor;
import com.springside.zsword.modules.web.struts2.BaseCrudActionSupport;
import com.wbs.entity.system.Authority;
import com.wbs.entity.system.Menu;
import com.wbs.entity.system.Role;
import com.wbs.serveice.system.AuthorityService;
import com.wbs.serveice.system.MenuService;
import com.wbs.serveice.system.RoleService;
import com.wbs.web.struts2.DataViewStringIdEntityAction;

/**
 * 角色管理Action.
 * 
 * 演示不分页的简单管理界面.
 */
@SuppressWarnings("serial")
@Namespace("/account")
@Results({ @Result(name = BaseCrudActionSupport.RELOAD, location = "role.action", type = "redirect") })
public class RoleAction extends DataViewStringIdEntityAction<Role, RoleService> {

	private static final String[] LIST_JSON_EXCLUDES = {};

	private AuthorityService authorityService;
	private MenuService menuService;
	private List<String> checkedAuthIds;// 页面中勾选的权限id列表
	private List<String> checkedMenuIds;// 页面中勾选的菜单id列表

	@Override
	protected String inputData() {
		if (entity != null) {
			checkedAuthIds = entity.getAuthIds();
			checkedMenuIds = entity.getMenuIds();
		}
		return super.inputData();
	}

	@Override
	protected String saveData() {
		// 根据页面上的checkbox 整合Role的Authorities Set.
		HibernateUtils.mergeByCheckedIds(entity.getAuthorityList(),
				checkedAuthIds, Authority.class);
		// 根据页面上的checkbox 整合Role的Menus Set.
		HibernateUtils.mergeByCheckedIds(entity.getMenuList(), checkedMenuIds,
				Menu.class);
		return super.saveData();
	}

	@Override
	protected void defaultPageSorter() {
		page.setOrderBy("name");
		page.setOrder(Page.ASC);
	}

	/**
	 * 支持使用Jquery.validate AJAX检验用户名是否重复.
	 * 
	 * @throws Exception
	 */
	public void checkName() throws Exception {
		super.checkWebPropertyValueUnique("name");
	}

	/**
	 * input页面显示所有授权列表.
	 */
	public List<Authority> getAllAuthorityList() {
		return authorityService.getAll("name", true);
	}

	/**
	 * input页面显示所有菜单列表
	 * 
	 * @return
	 */
	public List<Menu> getAllMenuList() {
		return menuService.getAll("name", true);
	}

	/**
	 * 获取菜单树JSON数据
	 * 
	 * @return
	 */
	public String getJsonMenuTree() {
		List<Menu> topMenus = menuService.getAll();
		String json = null;
		try {
			List<Map<String, Object>> menusTree = new ArrayList<Map<String, Object>>();
			for (Menu menu : topMenus) {
				if (menu.getParent() != null) {
					continue;
				}
				menusTree = buildJsonMenuTree(menusTree, menu, topMenus);
			}
			json = Struts2JSONConvertor.convertToString(menusTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 构建单个menu数节点
	 * 
	 * @param menuTree
	 * @param menu
	 * @return
	 */
	private List<Map<String, Object>> buildJsonMenuTree(
			List<Map<String, Object>> menuTree, Menu menu, List<Menu> allMenus) {
		Map<String, Object> menuNode = new HashMap<String, Object>();
		menuNode.put("id", menu.getId());
		menuNode.put("text", menu.getName());
		List<Map<String, Object>> subNodes = new ArrayList<Map<String, Object>>();
		for (Menu sub : menu.getSubMenus()) {
			subNodes = buildJsonMenuTree(subNodes, sub, allMenus);
		}
		menuNode.put("children", subNodes);
		menuTree.add(menuNode);
		return menuTree;
	}

	/**
	 * 返回选中的菜单ID集合JSON字串
	 * 
	 * @return
	 */
	public String getJsonCheckedMenuIds() {
		String json = null;
		try {
			List<String> menuIds = getCheckedMenuIds();
			json = Struts2JSONConvertor.convertToString(menuIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * input页面显示所有顶级菜单
	 * 
	 * @return
	 */
	public List<Menu> getTopMenuList() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new ExtPropertyFilter("ISNULLS_parent.id", null);
		filters.add(filter);
		return menuService.find(filters);
	}

	/**
	 * input页面显示角色拥有的授权.
	 */
	public List<String> getCheckedAuthIds() {
		return checkedAuthIds;
	}

	/**
	 * input页面提交角色拥有的授权.
	 */
	public void setCheckedAuthIds(List<String> checkedAuthIds) {
		this.checkedAuthIds = checkedAuthIds;
	}

	/**
	 * input页面显示角色拥有的菜单.
	 * 
	 * @return
	 */
	public List<String> getCheckedMenuIds() {
		return checkedMenuIds;
	}

	/**
	 * input页面提交角色拥有的菜单
	 * 
	 * @param checkedMenuIds
	 */
	public void setCheckedMenuIds(List<String> checkedMenuIds) {
		this.checkedMenuIds = checkedMenuIds;
	}

	@Autowired
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@Override
	protected String[] defaultListJsonExcludeProperties() {
		return LIST_JSON_EXCLUDES;
	}
}