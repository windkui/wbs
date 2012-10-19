package com.wbs.web.struts2.account;

import java.util.List;

import org.springside.modules.orm.Page;

import com.wbs.entity.system.Menu;
import com.wbs.serveice.system.MenuService;
import com.wbs.web.struts2.DataViewStringIdEntityAction;

@SuppressWarnings("serial")
public class MenuAction extends DataViewStringIdEntityAction<Menu, MenuService> {

	private static final String LIST_JSON_EXCLUDES[] = { ".*.(icon|parent|subMenus)" };
	private String parentId;

	/**
	 * 保存菜单数据,添加上级菜单设置
	 */
	@Override
	protected String saveData() {
		Menu parent = null;
		if (notValueBlank(parentId)) {
			parent = new Menu();
			parent.setId(parentId);
		}
		entity.setParent(parent);
		return super.saveData();
	}

	/**
	 * 设置默认分页条件
	 */
	@Override
	protected void defaultPageSorter() {
		page.setOrderBy("parent");
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
	 * input页面获取顶级菜单集合
	 * 
	 * @return
	 */
	public List<Menu> getTopMenuList() {
		return entityService.getTopMenuList();
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	protected String[] defaultListJsonExcludeProperties() {
		return LIST_JSON_EXCLUDES;
	}
}
