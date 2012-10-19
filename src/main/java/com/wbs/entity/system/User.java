package com.wbs.entity.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wbs.entity.common.StringIdEntity;

/**
 * 系统用户
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "ACCT_USER")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends StringIdEntity implements Serializable {

	private static final long serialVersionUID = -2063229479376429385L;
	private String loginName;
	private String password;// 为简化演示使用明文保存的密码
	private String name;
	private String email;
	/** 用户角色 */
	private Role role;
	/** 用户所属部门 */
	private Department department;

	// 字段非空且唯一, 用于提醒Entity使用者及生成DDL.
	@Column(nullable = false, unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * 增加获取角色名称方法,可按roleName进行排序
	 * 
	 * @return
	 */
	@Transient
	public String getRoleName() {
		if (role == null) {
			return null;
		}
		return role.getName();
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "DEPART_ID", nullable = true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * 增加获取角色名称方法,可按roleName进行排序
	 * 
	 * @return
	 */
	@Transient
	public String getDepartmentName() {
		return (department == null) ? null : department.getName();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}