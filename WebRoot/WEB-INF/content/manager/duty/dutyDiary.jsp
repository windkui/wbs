<%@ page language="java" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/dataFileTypes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appTitle} 值班日志</title>
<%@ include file="/common/meta.jsp"%>

<link type="text/css" rel="stylesheet" href="${ctx}/css/general.css" />
<script type="text/javascript" src="${ctx}/js/sea.min.js"
	data-main="${ctx}/js/init-system"></script>
</head>

<body>
	<div id="mainPanel" class="mainPanel">
		<div class="ui-widget-header ui-corner-all" style="padding: 2px;">
			<button id="refresh-button">值班日志</button>
			<button id="toolbar-button">工具栏</button>
		</div>
		<div id="toolbarPanel" class="ui-corner-all ui-state-default">
			<form id="searchForm" name="searchForm"
				action="${ctx}/manager/duty/dutyDiary!list.action" method="post">
				<%@ include file="/common/searchform-tile.jsp"%>
				<table style="width:auto;" border="0" align="center" cellpadding="0"
					cellspacing="1" class="tableBorder">
					<tbody>
						<tr>
							<th class="ui-widget-header" height="25" colspan="6">查询条件</th>
						</tr>
						<tr>
							<td width="20%" height="23" class="ui-state-hover" align="right">
								标题：</td>
							<td class="ui-state-default"><input type="text"
								maxlength="20" class="text" name="flt_LIKES_title" />
							</td>
							<td width="20%" height="23" class="ui-state-hover" align="right">
								类型：</td>
							<td class="ui-state-default"><select name="flt_EQS_status">
									<option value="">---请选择---</option>
									<c:forEach var="tdto" items="${DiaryStatuss}">
										<option value="${tdto.key}">${tdto.value}</option>
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td height="23" class="ui-state-hover" align="right">部门：</td>
							<td class="ui-state-default"><select
								name="flt_EQS_department_P_id" class="text">
									<option value="">请选择</option>
									<c:forEach var="ddto" items="${allDepartmentList}">
										<option value="${ddto.id}"
											<c:if test="${ddto.id==department.id}">selected="selected"</c:if>>${ddto.name}</option>
									</c:forEach>
							</select>
							</td>
							<td height="23" class="ui-state-hover" align="right">值班人：</td>
							<td class="ui-state-default"><input type="text"
								maxlength="20" class="text" name="flt_LIKES_createBy"
								value="${sysUserInfo.loginName}" />
							</td>
						</tr>

						<tr>
							<td height="23" class="ui-state-hover" align="right">生成时间：</td>
							<td class="ui-state-default" colspan="3"><input type="text"
								maxlength="50" class="text serDatePicker"
								name="flt_GED_createTime" readonly="readonly" /> 至 <input
								type="text" maxlength="50" class="text serDatePicker"
								name="flt_LED_createTime" readonly="readonly" /></td>
						</tr>
						<tr>
							<td height="23" class="ui-state-hover" align="right">操作：</td>
							<td class="ui-state-hover ui-corner-all" colspan="6">
								<button id="search-button">查询</button>
								<button type="reset" id="reset-button">重置</button>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div id="dataPanel" class="dataPanel" align="center">
			<div id="inputDialog" title="查看日志">
				<div id="inputPanel" class="inputPanel" title="查看日志" align="center">
				</div>
			</div>
			<div id="listPanel" class="listPanel ui-corner-all">
				<!-- %@include file="dutyDiary-list.jsp" % -->
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function initPage() {
			jQuery(window).initDataPager();
			$('.serDatePicker').datepicker();
		}
		if (window.jQuery) {
			initPage();
		}
	</script>
</body>
</html>
