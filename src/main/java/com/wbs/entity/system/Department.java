package com.wbs.entity.system;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wbs.entity.common.StringIdEntity;

/**
 * 部门
 */
@Entity
@Table(name = "ACCT_DEPARTMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department extends StringIdEntity {
	private String name;
	private String remark;

	/**
	 * 部门名称
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 部门备注
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
