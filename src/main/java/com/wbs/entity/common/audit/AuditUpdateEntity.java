package com.wbs.entity.common.audit;

import java.util.Date;

/**
 * 支持更新时审计的实体类接口
 */
public interface AuditUpdateEntity {
	/**
	 * 更新人的登陆名
	 * 
	 * @return
	 */
	abstract String getUpdateBy();

	abstract void setUpdateTime(Date updateTime);

	/**
	 * 更新时间
	 * 
	 * @return
	 */
	abstract Date getUpdateTime();

	abstract void setUpdateBy(String updateBy);
}
