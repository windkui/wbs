package com.wbs.web.struts2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.quartz.SchedulerException;

import com.ckfinder.connector.configuration.ConfigurationFactory;
import com.springside.zsword.modules.web.struts2.SimpleActionSupport;
import com.wbs.entity.system.Menu;
import com.wbs.entity.system.Role;
import com.wbs.entity.system.User;
import com.wbs.tools.AppLoginUtils;
import com.wbs.tools.job.SendMailJob;
import com.wbs.tools.job.test.JobEntity;
import com.wbs.tools.job.test.JobScheduler;

/**
 * 系统主页Action
 */
@SuppressWarnings("serial")
public class MainAction extends SimpleActionSupport {
	private List<Menu> userMenus;
	private static String CKFINDER_SESSIONID;
	private static final String CKFINDER_TOOLS_ROLE = "ToolApps";

	static {
		try {
			CKFINDER_SESSIONID = ConfigurationFactory.getInstace()
					.getConfiguration().getUserRoleName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String execute() {
		User user = AppLoginUtils.getLoginUser();
		if (user == null) {
			return LOGIN;
		}
		JobScheduler.startJob();
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		Role role = user.getRole();
		if (role.getAuthNames().indexOf("保存气象工具") != -1) {
			session.setMaxInactiveInterval(-1);
			session.setAttribute(CKFINDER_SESSIONID, CKFINDER_TOOLS_ROLE);
		}
		AppLoginUtils.saveUserToSession(session, user);
		List<Menu> menuList = role.getMenuList();
		this.userMenus = this.loadUserMenus(menuList);
		String subSys = AppLoginUtils.getSubSys(getRequest());
		if (notValueBlank(subSys)) {
			return subSys;
		}
		return SUCCESS;
	}

	private List<Menu> loadUserMenus(List<Menu> menusList) {
		List<Menu> userMenus = new ArrayList<Menu>();
		for (Menu menu : menusList) {
			if (menu.getParent() == null) {
				Iterator<Menu> subsIt = menu.getSubMenus().iterator();
				while (subsIt.hasNext()) {
					Menu sub = subsIt.next();
					if (!menusList.contains(sub)) {
						subsIt.remove();
					}
				}
				userMenus.add(menu);
			}
		}
		return userMenus;
	}

	public List<Menu> getUserMenus() {
		return userMenus;
	}
}
