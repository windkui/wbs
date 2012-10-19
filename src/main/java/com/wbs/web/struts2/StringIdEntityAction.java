package com.wbs.web.struts2;

import com.wbs.serveice.StringIdEntityService;

/**
 * 基于Struts2的String型主键数据实体Action基类
 */
@SuppressWarnings("serial")
public abstract class StringIdEntityAction<T, SERV extends StringIdEntityService<T, ?>>
		extends BaseEntityAction<T, String, SERV> {

	@Override
	public void setId(String id) {
		super.setId(id);
	}
}
