package com.wbs.entity.system;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.reflection.ConvertUtils;

import com.google.common.collect.Lists;
import com.wbs.entity.common.StringIdEntity;

/**
 * 系统角色
 */
@Entity
@Table(name = "ACCT_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends StringIdEntity implements Serializable {

	private static final long serialVersionUID = 1640428848178322309L;
	private String name;
	private List<Authority> authorityList = Lists.newArrayList();
	private List<Menu> menuList = Lists.newArrayList();
	private String subSys;

	public Role() {
	}

	public Role(String id, String name) {
		this.setId(id);
		this.name = name;
	}

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "ACCT_ROLE_AUTHORITY", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("name")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	@ManyToMany
	@JoinTable(name = "ACCT_ROLE_MENU", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "MENU_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("name")
	@Cache(usage = CacheConcurrencyStrategy.NONE)
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	/**
	 * 所属子系统
	 * 
	 * @return
	 */
	public String getSubSys() {
		return subSys;
	}

	public void setSubSys(String subSys) {
		this.subSys = subSys;
	}

	@Transient
	public String getAuthNames() {
		return ConvertUtils.convertElementPropertyToString(authorityList,
				"name", ", ");
	}

	@Transient
	@SuppressWarnings("unchecked")
	public List<String> getAuthIds() {
		return ConvertUtils.convertElementPropertyToList(authorityList, "id");
	}

	@Transient
	public String getMenuNames() {
		return ConvertUtils.convertElementPropertyToString(menuList, "name",
				", ");
	}

	@Transient
	@SuppressWarnings("unchecked")
	public List<String> getMenuIds() {
		return ConvertUtils.convertElementPropertyToList(menuList, "id");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
