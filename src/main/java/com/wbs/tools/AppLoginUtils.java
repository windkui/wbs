package com.wbs.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.springside.zsword.modules.utils.data.DataValidator;
import com.wbs.data.SecurityUserDetail;
import com.wbs.entity.system.Department;
import com.wbs.entity.system.User;
import com.wbs.serveice.system.AccountManager;

/**
 * 系统登陆信息工具类
 */
public abstract class AppLoginUtils {

	private static final String SYS_USER_INFO = "sysUserInfo";
	private static final String SUB_SYS = "subSys";
	private static AccountManager accountManager;

	private AppLoginUtils() {
	}

	public static String getLoginUserName() {
		return SpringSecurityUtils.getCurrentUserName();
	}

	protected static SecurityUserDetail getSecurityUser() {
		return SpringSecurityUtils.getCurrentUser();
	}

	public static String getLoginUserId() {
		SecurityUserDetail securityUser = getSecurityUser();
		return (securityUser == null) ? null : securityUser.getId();
	}

	public static User getLoginUser() {
		String id = getLoginUserId();
		if (DataValidator.isBlank(id)) {
			return null;
		}
		return accountManager.getUser(id);
	}

	/**
	 * 获取用户所属部门ID
	 * 
	 * @return
	 */
	public static String getDepartmentId() {
		Department department = getSecurityUser().getAppUser().getDepartment();
		return department == null ? null : department.getId();
	}

	/**
	 * 获取同步后用户所属部门ID
	 * 
	 * @return
	 */
	public static String getSyncDepartmentId() {
		User user = getLoginUser();
		if (user == null) {
			return null;
		}
		Department department = user.getDepartment();
		return department == null ? null : department.getId();
	}

	/**
	 * 保存用户信息至HttpSession
	 * 
	 * @param session
	 * @param user
	 */
	public static void saveUserToSession(HttpSession session, User user) {
		session.setAttribute(SYS_USER_INFO, user);
	}

	/**
	 * 保存子系统名
	 * 
	 * @param request
	 * @param subSys
	 */
	public static void saveSubSys(HttpServletRequest request, String subSys) {
		request.getSession(false).setAttribute(SUB_SYS, subSys);
	}

	public static String getUserSubSys() {
		System.out.println(getLoginUser().getRole().getSubSys());
		return getLoginUser().getRole().getSubSys();
	}

	/**
	 * 获取子系统名
	 * 
	 * @param request
	 * @return
	 */
	public static String getSubSys(HttpServletRequest request) {
		return (String) request.getSession(false).getAttribute(SUB_SYS);
	}

	public static void setAccountManager(AccountManager accountManager) {
		AppLoginUtils.accountManager = accountManager;
	}
}
