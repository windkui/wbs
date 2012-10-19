package com.wbs.config.springsecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.wbs.tools.AppLoginUtils;

/**
 * 根据登录信息跳转到不同的子系统
 */
public class SubSystemAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	private static final String SUB_SYS = "subSys";
	private String subSysParam = SUB_SYS;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String sysName = request.getParameter(subSysParam);
		String userRoleSysName = AppLoginUtils.getUserSubSys();
		if (!sysName.equals(userRoleSysName)) {
			this.clearAuthenticationAttributes(request);
			AuthenticationException exception = new BadCredentialsException(
					"权限不足,无法登录系统!");
			this.saveException(request, exception);
			this.getRedirectStrategy().sendRedirect(request, response,
					"/login.action?error=true");
			return;
		}
		AppLoginUtils.saveSubSys(request, sysName);
		super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * Caches the {@code AuthenticationException} for use in view rendering.
	 * <p>
	 * If {@code forwardToDestination} is set to true, request scope will be
	 * used, otherwise it will attempt to store the exception in the session. If
	 * there is no session and {@code allowSessionCreation} is {@code true} a
	 * session will be created. Otherwise the exception will not be stored.
	 */
	protected final void saveException(HttpServletRequest request,
			AuthenticationException exception) {
		HttpSession session = request.getSession(false);
		session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
	}

	public void setSubSysParam(String subSysParam) {
		this.subSysParam = subSysParam;
	}
}
