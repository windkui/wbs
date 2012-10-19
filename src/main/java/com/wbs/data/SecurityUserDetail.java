package com.wbs.data;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 安全系统用户信息类
 */
public class SecurityUserDetail extends User implements UserDetails {
	private static final long serialVersionUID = -6725970197543136137L;
	private com.wbs.entity.system.User appUser;

	public SecurityUserDetail(
			com.wbs.entity.system.User appUser, String username,
			String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.appUser = appUser;
	}

	public String getId() {
		return appUser.getId();
	}

	public com.wbs.entity.system.User getAppUser() {
		return appUser;
	}
}
