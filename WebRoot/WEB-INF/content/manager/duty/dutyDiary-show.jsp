<%@ page language="java" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/diaryStatus.jsp"%>
<%@ taglib prefix="dataTable"
	uri="http://www.dhcc.com.cn/jsp/ui/datatable"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appTitle} 值班日志-列表</title>
<%@ include file="/common/meta.jsp"%>

<link type="text/css" rel="stylesheet" href="${ctx}/css/general.css" />
<script type="text/javascript"
	src="${ctx}/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${ctx}/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/sea.min.js"
	data-main="${ctx}/js/init-system"></script>
</head>

<body>
	<c:set var="_create" value="${empty id}" />
	<div id="message" class="ui-state-hover" align="center">${successMessage}</div>
	<div id="errorMessage" class="ui-state-error" align="center">${errorMessage}</div>


	<form id="inputForm" action="#" method="post">

		<c:if test="${!_create}">
			<input type="hidden" name="id" value="${id}" />
		</c:if>

		<table border="0" align="center" cellpadding="0" cellspacing="1"
			class="tableBorder" width="550">

			<tr>
				<th height="25" class="ui-widget-header ui-corner-all" colspan="6"><b>查看值班日志</b>
				</th>
			</tr>

			<tr>
				<td height="30" class="ui-state-hover" align="right">主题</td>
				<td align="left" height="23" class="ui-state-hover"><input disabled="disabled"
					type="text" name="title" id="title" class="required"
					maxlength="255" style="width: 50%;" value="${title}"/></td>

				<td width="20%" height="23" class="ui-state-hover" align="right">
					类型：</td>
				<td class="ui-state-default"><select name="status" id="status" disabled="disabled">
						<option value="">---请选择---</option>
						<c:forEach var="tdto" items="${DiaryStatuss}">
							<option value="${tdto.key}"
								<c:if test="${tdto.key==status}">selected="selected"</c:if>>${tdto.value}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td valign="top" height="30" class="ui-state-hover" align="right">内容</td>
				<td align="left" colspan="3" class="ui-state-hover" align="right"><textarea
						name="content" id="diaryContent" style="width:100%;height: 200px;" disabled="disabled">${content}</textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
