package com.wbs.dao.hibernate.listener;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.wbs.entity.common.audit.AuditSaveEntity;
import com.wbs.entity.common.audit.AuditUpdateEntity;
import com.wbs.entity.common.audit.AuditableEntity;

/**
 * 审计实体类处理监听器
 */
@SuppressWarnings("serial")
public class AuditableEntityListener extends DefaultSaveOrUpdateEventListener {

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) {
		Object object = event.getObject();
		if (object instanceof AuditSaveEntity
				|| object instanceof AuditUpdateEntity) {
			Serializable id = ((AuditSaveEntity) object).getId();
			String loginName = SpringSecurityUtils.getCurrentUserName();
			if (id == null) {
				if (object instanceof AuditSaveEntity) {
					AuditSaveEntity auditObject = (AuditableEntity) object;
					auditObject.setCreateBy(loginName);
					auditObject.setCreateTime(new Date());
				}
			} else {
				if (object instanceof AuditUpdateEntity) {
					AuditUpdateEntity auditObject = (AuditableEntity) object;
					auditObject.setUpdateBy(loginName);
					auditObject.setUpdateTime(new Date());
				}
			}
		}
		super.onSaveOrUpdate(event);
	}
}
