package com.wbs.entity.common.audit;

import java.io.Serializable;
import java.util.Date;

/**
 * 支持新增时审计的实体类接口
 */
public interface AuditSaveEntity {
	abstract Serializable getId();

	/**
	 * 创建人的登陆名
	 * 
	 * @param createBy
	 */
	abstract String getCreateBy();

	abstract void setCreateBy(String createBy);

	/**
	 * 创建时间
	 * 
	 * @return
	 */
	abstract Date getCreateTime();

	abstract void setCreateTime(Date createTime);
}
