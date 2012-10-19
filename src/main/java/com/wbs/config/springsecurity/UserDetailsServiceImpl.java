package com.wbs.config.springsecurity;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.wbs.data.SecurityUserDetail;
import com.wbs.entity.system.Authority;
import com.wbs.entity.system.Role;
import com.wbs.entity.system.User;
import com.wbs.serveice.system.AccountManager;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private AccountManager accountManager;
	/** 已登录用户默认权限 */
	public static final String ROLE_LOGON_USER = Authority.AUTHORITY_PREFIX
			+ "登陆用户";

	/**
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		User user = accountManager.findUserByLoginName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}

		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		// -- 未用到的属性, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userdetails = new SecurityUserDetail(user,
				user.getLoginName(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuths);

		return userdetails;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		Role role = user.getRole();
		// 添加默认已登陆用户权限
		authSet.add(new GrantedAuthorityImpl(ROLE_LOGON_USER));
		for (Authority authority : role.getAuthorityList()) {
			authSet.add(new GrantedAuthorityImpl(authority.getPrefixedName()));
		}
		return authSet;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
