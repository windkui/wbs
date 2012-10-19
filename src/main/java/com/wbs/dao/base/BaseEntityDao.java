package com.wbs.dao.base;

import java.io.Serializable;

import com.springside.zsword.modules.dao.hibernate.ExtendHibernateDao;

/**
 * 数据实体最底层DAO基类
 * 
 * 可以扩展添加通用方法,供子类调用
 */
public abstract class BaseEntityDao<T, PK extends Serializable> extends
		ExtendHibernateDao<T, PK> {
}
