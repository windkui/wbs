package com.wbs.entity.system;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Sets;
import com.springside.zsword.modules.data.TreeNode;
import com.wbs.entity.common.StringIdEntity;

/**
 * 系统菜单
 */
@Entity
@Table(name = "ACCT_MENU")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu extends StringIdEntity implements TreeNode<Menu> {

	private String name;
	private String command;
	private String icon;
	private Set<Role> roles = Sets.newLinkedHashSet();
	private Menu parent;
	private Set<Menu> subMenus = Sets.newLinkedHashSet();

	public Menu() {
	}

	public Menu(String id, String name, String command) {
	}

	/**
	 * 菜单名
	 * 
	 * @return
	 */
	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 菜单命令
	 * 
	 * @return
	 */
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 菜单图标
	 * 
	 * @return
	 */
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 菜单关联的角色集合
	 * 
	 * @return
	 */
	@ManyToMany
	@JoinTable(name = "ACCT_ROLE_MENU", joinColumns = { @JoinColumn(name = "MENU_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("name")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JSON(serialize = false)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 下级菜单集合
	 * 
	 * @return
	 */
	@OneToMany
	@JoinColumn(name = "PARENT_ID")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("name")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(Set<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	/**
	 * 上级菜单
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	@Transient
	public String getParentName() {
		return (parent == null) ? "" : parent.getName();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Transient
	public Object getNodeId() {
		return getId();
	}

	@Transient
	public String getText() {
		return getName();
	}

	@Transient
	public Collection<Menu> getChildren() {
		return getSubMenus();
	}
}
