package com.wbs.web.struts2.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import com.springside.zsword.modules.web.struts2.BaseCrudActionSupport;
import com.wbs.entity.system.Department;
import com.wbs.entity.system.Role;
import com.wbs.entity.system.User;
import com.wbs.serveice.system.DepartmentService;
import com.wbs.serveice.system.RoleService;
import com.wbs.serveice.system.UserService;
import com.wbs.web.struts2.DataViewStringIdEntityAction;

/**
 * 用户管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数. 演示带分页的管理界面.
 * 
 */
@SuppressWarnings("serial")
// 定义URL映射对应/account/user.action
@Namespace("/account")
// 定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results({ @Result(name = BaseCrudActionSupport.RELOAD, location = "admin.action", type = "redirect") })
public class UserAction extends DataViewStringIdEntityAction<User, UserService> {

	private RoleService roleService;
	private DepartmentService departmentService;
	private String roleId;
	private Department departmentInput = null;
	private static final String[] LIST_JSON_EXCLUDES = { ".*\\.(role|password|department)" };

	/**
	 * 批量更新操作，同时更新原始DAT数据源文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String batchUpdate() throws Exception {
		try {
			String recordsJson = getParameter("records");
			List<User> weatherElements = processBatchJson(recordsJson);
			entityService.save(weatherElements);
			renderSuccessMessage("更新数据成功!");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			renderErrorMessage("更新数据失败-", e);
		}
		return null;
	}

	/**
	 * 处理批量更新JSON请求数据
	 * 
	 * @param json
	 * @return
	 * @throws IOException
	 */
	private List<User> processBatchJson(String json) throws IOException {
		JSONArray jsonArray = jsonFromObject(json);
		ids = new ArrayList<String>();
		Map<String, JSONObject> jsonMap = new HashMap<String, JSONObject>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String id = jsonObject.getString("id");
			jsonMap.put(id, jsonObject);
			ids.add(id);
		}
		List<User> users = entityService.get(ids);
		for (int i = 0; i < jsonArray.size(); i++) {
			User user = users.get(i);
			JSONObject jsonObject = jsonMap.get(user.getId());
			jsonToBean(jsonObject, user);
			System.out.println(user.getName());
		}
		return users;
	}

	@Override
	public String list() throws Exception {
		String result = super.list();
		System.out.println(result);
		return result;
	}

	public String show() throws Exception {
		return super.showModel();
	}

	/**
	 * 设置默认分页大小
	 */
	@Override
	protected int defaultPageSize() {
		return 10;
	}

	/**
	 * 设置默认分页排序条件
	 */
	@Override
	protected void defaultPageSorter() {
		page.setOrderBy("name");
		page.setOrder(Page.ASC);
	}

	@Override
	protected String[] defaultListJsonExcludeProperties() {
		return LIST_JSON_EXCLUDES;
	}

	@Override
	protected String[] defaultJsonExcludeProperties() {
		return null;
	}

	@Override
	protected String saveData() {
		if (roleId != null) {
			Role role = new Role();
			role.setId(roleId);
			entity.setRole(role);
		}
		entity.setDepartment(departmentInput);
		return super.saveData();
	}

	/**
	 * input页面传递的部门对象
	 * 
	 * @param department
	 */
	public void setDepartmentInput(Department departmentInput) {
		if (isValueBlank(departmentInput.getId())) {
			departmentInput = null;
		}
		this.departmentInput = departmentInput;
	}

	// -- 其他Action函数 --//
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public void checkLoginName() throws Exception {
		this.checkWebPropertyValueUnique("loginName");
	}

	/**
	 * input页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return roleService.getAll("name", true);
	}

	/**
	 * input页面获取所有部门列表
	 * 
	 * @return
	 */
	public List<Department> getAllDepartmentList() {
		return departmentService.getAll("name", true);
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

}
