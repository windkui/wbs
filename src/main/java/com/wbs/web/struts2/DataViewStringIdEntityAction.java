package com.wbs.web.struts2;

import com.springside.zsword.modules.web.JsonViewSupport;
import com.wbs.serveice.StringIdEntityService;

/**
 * 支持多视图的String型主键数据实体Action基类
 */
@SuppressWarnings("serial")
public abstract class DataViewStringIdEntityAction<T, SERV extends StringIdEntityService<T, ?>>
		extends StringIdEntityAction<T, SERV> implements JsonViewSupport {

	private static final String JSON_ACTION_EXTENSION = "sjson";

	public DataViewStringIdEntityAction() {
		super();
		this.pathExtension = JSON_ACTION_EXTENSION;
		this.isAjaxDelete = true;
	}

	public String toView(String defaultView) {
		return super.toView(defaultView);
	}

	/**
	 * 默认JSON数据列表视图要排除序列化的属性配置
	 */
	protected abstract String[] defaultListJsonExcludeProperties();
}
