package com.wbs.serveice;

import java.io.Serializable;

import com.springside.zsword.modules.service.hibernate.BaseHibernateService;
import com.wbs.dao.base.BaseEntityDao;

/**
 * 数据实体最底层Service基类
 * 
 * 可扩展添加通用Service方法,供子类调用
 */
public abstract class BaseEntityService<T, PK extends Serializable, DAO extends BaseEntityDao<T, PK>>
		extends BaseHibernateService<T, PK, DAO> {
}
