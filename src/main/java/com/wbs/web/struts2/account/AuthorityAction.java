package com.wbs.web.struts2.account;

import org.springside.modules.orm.Page;

import com.wbs.entity.system.Authority;
import com.wbs.serveice.system.AuthorityService;
import com.wbs.web.struts2.DataViewStringIdEntityAction;

/**
 * 权限管理Action
 */
@SuppressWarnings("serial")
public class AuthorityAction extends
		DataViewStringIdEntityAction<Authority, AuthorityService> {

	private static final String LIST_JSON_EXCLUDES[] = {};

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
		this.checkWebPropertyValueUnique("name");
	}

	@Override
	protected String[] defaultListJsonExcludeProperties() {
		return LIST_JSON_EXCLUDES;
	}
}
