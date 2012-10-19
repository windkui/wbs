package com.wbs.serveice;

import com.wbs.dao.base.BaseEntityDao;

/**
 * String型主键数据实体Service基类
 */
public class StringIdEntityService<T, DAO extends BaseEntityDao<T, String>>
		extends BaseEntityService<T, String, DAO> {

}
