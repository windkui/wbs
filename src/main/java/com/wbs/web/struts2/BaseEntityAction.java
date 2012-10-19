package com.wbs.web.struts2;

import java.io.Serializable;

import com.opensymphony.xwork2.Action;
import com.springside.zsword.modules.web.exception.WebRequestException;
import com.wbs.serveice.BaseEntityService;

/**
 * 数据实体最底层Action基类
 * 
 * 可扩展添加通用方法,供子类调用
 */
@SuppressWarnings("serial")
public class BaseEntityAction<T, PK extends Serializable, SERV extends BaseEntityService<T, PK, ?>>
		extends EntityActionSupport<T, PK, SERV> {

	/** 操作成功结果名 */
	protected static final String OPERATION_SUCCESS = "operationSuccess";
	/** 是否通过AJAX删除数据，设为true,会直接输出操作结果，而且不会返回list视图 */
	protected boolean isAjaxSave = true;
	protected boolean isAjaxDelete = false;
	protected boolean isAjaxInput = true;

	@Override
	public String list() throws Exception {
		return super.list();
	}

	@Override
	public String input() throws Exception {
		try {
			return super.input();
		} catch (Exception e) {
			String resultMessage = "数据获取失败-";
			if (isAjaxInput) {
				renderErrorMessage(resultMessage, e);
				return Action.NONE;
			}
			throw throwActionError(resultMessage, e);
		}
	}

	@Override
	public String save() throws Exception {
		try {
			String result = super.save();
			String resultMessage = "数据保存成功!";
			return handleSaveSuccess(result, resultMessage);
		} catch (Exception e) {
			String resultMessage = "数据保存失败-";
			return handleSaveFailed(resultMessage, e);
		}
	}

	protected String handleSaveSuccess(String result, String resultMessage) {
		return handleSaveSuccess(result, resultMessage, isAjaxSave);
	}

	protected String handleSaveSuccess(String result, String resultMessage,
			boolean isAjax) {
		if (isAjax) {
			renderSuccessMessage(resultMessage);
			return Action.NONE;
		}
		setRequestMessage(resultMessage);
		return result;
	}

	protected String handleSaveFailed(String resultMessage, Exception e)
			throws Exception {
		return handleSaveFailed(resultMessage, e, isAjaxSave);
	}

	protected String handleSaveFailed(String resultMessage, Exception e,
			boolean isAjax) throws Exception {
		logger.error(e.getMessage(), e);
		if (isAjax) {
			renderErrorMessage(resultMessage, e);
			return Action.NONE;
		}
		setRequestErrorMessage(resultMessage, e);
		return input();
	}

	/**
	 * 处理操作成功结果
	 * 
	 * @param resultCode
	 * @param resultMessage
	 * @return
	 */
	protected String handleOperationSuccess(String resultCode,
			String resultMessage) {
		if (resultCode == null) {
			renderSuccessMessage(resultMessage);
			return Action.NONE;
		}
		setRequestMessage(resultMessage);
		return resultCode;
	}

	/**
	 * 处理操作出错结果
	 * 
	 * @param resultCode
	 * @param resultMessage
	 * @param e
	 * @return
	 */
	protected String handleOperationError(String resultCode,
			String resultMessage, Exception e) {
		if (resultCode == null) {
			renderErrorMessage(resultMessage, e);
			return Action.NONE;
		}
		setRequestErrorMessage(resultMessage, e);
		return resultCode;
	}

	protected WebRequestException throwOperationException(String info,
			Exception e) {
		WebRequestException webException = null;
		if (!(e instanceof WebRequestException)) {
			webException = new WebRequestException(e.getMessage(), e.getCause());
		} else {
			webException = (WebRequestException) e;
		}
		setRequestErrorMessage(info, e);
		throw webException;
	}

	@Override
	public String delete() throws Exception {
		try {
			super.delete();
			String resultMessage = "数据删除成功!";
			if (isAjaxDelete) {
				renderSuccessMessage(resultMessage);
				return Action.NONE;
			}
			setRequestMessage(resultMessage);
		} catch (Exception e) {
			String resultMessage = "删除失败-";
			if (isAjaxDelete) {
				renderErrorMessage(resultMessage, e);
				return Action.NONE;
			}
			setRequestErrorMessage(resultMessage, e);
		}
		return list();
	}

}
