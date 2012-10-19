package com.wbs.entity.common.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wbs.entity.common.StringIdEntity;

/**
 * 审计String主键实体基类
 */
@MappedSuperclass
public class AuditableStringIdEntity extends StringIdEntity implements
		AuditableEntity {
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;

	@Column(updatable = false)
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(insertable = false)
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(insertable = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
