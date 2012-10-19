package com.wbs.web.struts2;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.springside.zsword.modules.orm.PageBean;
import com.springside.zsword.modules.web.exception.WebRequestException;
import com.springside.zsword.modules.web.struts2.DataViewCrudActionSupport;
import com.wbs.entity.Log;
import com.wbs.serveice.BaseEntityService;
import com.wbs.serveice.LogService;
import com.wbs.tools.LogType;

/**
 * 数据实体ActionSupport基类
 * 
 */
@SuppressWarnings("serial")
public class EntityActionSupport<T, PK extends Serializable, SERV extends BaseEntityService<T, PK, ?>>
		extends DataViewCrudActionSupport<T, PK, SERV> {

	public static final String OLD_PREFIX = "old";

	protected boolean debug = true;

	private LogService logService;

	public String execute() throws Exception {
		if (debug) {
			System.err.println("ExecuteRequest: " + getRequestURL());
		}
		return super.execute();
	}

	protected String list() throws Exception {
		if (debug) {
			System.err.println("ListRequest: " + getRequestURL());
		}
		try {
			beforeReturnList();
			return super.list();
		} catch (WebRequestException wre) {
			logger.error(wre.toString());
			throw throwActionError("查询失败-", wre);
		}
	}

	/**
	 * Action方法,创建数据查询分页对象. (DEBUG用，正式版可以删除)
	 * 
	 * @return
	 */
	protected PageBean<T> buildPage() {
		this.page = super.buildPage();
		return page;
	}

	/**
	 * Action方法,创建数据查询Filter集合.(DEBUG用，正式版可以删除)
	 * 
	 * @return
	 */
	@Override
	protected List<PropertyFilter> buildFilters() {
		List<PropertyFilter> filters = super.buildFilters();
		return filters;
	}

	@Override
	public String input() throws Exception {
		if (debug) {
			System.err.println("InputRequest: " + getRequestURL());
		}
		try {
			try {
				if (notValueBlank(id) && isValueBlank(entity)) {
					throw new WebRequestException("Data not found!" + id);
				}
			} catch (RuntimeException re) {
				throw re;
			}
			beforeReturnInput();
			return super.input();
		} catch (WebRequestException ope) {
			logger.error(ope.toString());
			throw throwActionError("操作失败-", ope);
		}
	}

	@Override
	protected String save() throws Exception {
		if (debug) {
			System.err.println("SaveRequest: " + getRequestURL());
		}

		if (this.id == null) {
			setAddLogInfo(this.entity.getClass() + "#" + id);
		}
		return super.save();
	}

	@Override
	protected String delete() throws Exception {
		if (debug) {
			System.err.println("DeleteRequest: " + getRequestURL());
		}

		this.prepareModel();
		setDeleteLogInfo(this.entity.getClass() + "#" + id);

		return super.delete();
	}

	/**
	 * list执行并方法返回前，执行此方法
	 */
	protected void beforeReturnList() {
	}

	/**
	 * input方法执行并返回前，执行此方法
	 */
	protected void beforeReturnInput() {
	}

	/**
	 * 验证Web传递的属性值是否唯一,并输出true/false至response
	 * 
	 * @param propertyName
	 */
	protected void checkWebPropertyValueUnique(String propertyName) {
		String oldParam = OLD_PREFIX
				+ propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1);
		String oldValue = getParameter(oldParam);
		String newValue = getParameter(propertyName);
		super.checkPropValueUnique(propertyName, newValue, oldValue);
	}

	private static final String[] JSON_EXCLUDES = { ".*" };

	@Override
	protected String[] defaultListJsonExcludeProperties() {
		return JSON_EXCLUDES;
	}

	@Override
	protected String[] defaultJsonExcludeProperties() {
		return JSON_EXCLUDES;
	}

	protected void checkIdModel(boolean create) throws Exception {
		if (isValueBlank(id)) {
			throw new WebRequestException("数据不存在!" + id);
		}
		this.prepareModel(create);
		if (isValueBlank(entity)) {
			throw new WebRequestException("数据不存在!" + id);
		}
	}

	@Autowired
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Log setLogInfo(String logType, String content) {

		Log log = new Log();
		log.setId(null);
		log.setLogType(logType);
		log.setContent(content);
		log.setCreateTime(new Date());
		// 设置操作用户姓名
		log.setUserName(SpringSecurityUtils.getCurrentUserName());

		return log;
	}

	public void setDeleteLogInfo(String content) {
		Log log = setLogInfo(LogType.DELETE, "删除了 " + content);
		logService.save(log);
	}

	public void setAddLogInfo(String content) {
		Log log = setLogInfo(LogType.ADD, "增加了 " + content);
		logService.save(log);
	}

	public void setModifyLogInfo(String content) {
		Log log = setLogInfo(LogType.MODIFY, "修改了 " + content);
		logService.save(log);
	}

	public void setStartLogInfo(String content) {
		Log log = setLogInfo(LogType.START, "启动了  " + content);
		logService.save(log);
	}

	public void setStopLogInfo(String content) {
		Log log = setLogInfo(LogType.STOP, "停止了 " + content);
		logService.save(log);
	}
}
