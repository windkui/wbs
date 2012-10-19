package com.wbs.web.struts2.account;

import com.wbs.entity.system.Department;
import com.wbs.serveice.system.DepartmentService;
import com.wbs.web.struts2.DataViewStringIdEntityAction;

@SuppressWarnings("serial")
public class DepartmentAction extends
		DataViewStringIdEntityAction<Department, DepartmentService> {

	private static final String[] LIST_JSON_EXCLUDES = { ".*\\.(dutyFlows)" };

	/**
	 * AJAX验证名称是否唯一
	 */
	public void checkName() {
		checkWebPropertyValueUnique("name");
	}

	@Override
	protected String[] defaultListJsonExcludeProperties() {
		return LIST_JSON_EXCLUDES;
	}
}
