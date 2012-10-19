<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="dataTable" uri="http://www.dhcc.com.cn/jsp/ui/datatable" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${appTitle} 系统权限管理-列表</title>
	<%@ include file="/common/meta.jsp" %>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/general.css" />
	<script type="text/javascript" src="${ctx}/js/sea.min.js" data-main="${ctx}/js/init-system"></script>				
</head>

<body>
	<div id="message" class="ui-state-hover" align="center">${successMessage}</div>
	<div id="errorMessage" class="ui-state-error" align="center">${errorMessage}</div>
<form action="${ctx}/account/authority!delete.action" method="post" id="listForm">
	<table style="width: 90%;" width="90%" border="0" align="center" cellpadding="0" cellspacing="1" class="listTable tableBorder">
	<thead>
	<tr>
		<th class="ui-state-hover ui-corner-all" height="25" colspan="6">查询结果<span class="ui-icon ui-icon-search" style="display: inline-block;" /></th>
	</tr>
	<dataTable:header orderableValues="true,true,false" 
			fieldValues="id,name,-"
			labelValues="编号,权限名,操作"></dataTable:header>
	</thead>
	<tbody>
	<c:forEach var="dto" items="${page.result}" varStatus="vs">
	<tr>
		<td class="ui-state-default" width="80" align="right">
			${page.first+vs.index}
		</td>
		<td class="first-cell ui-state-default" width="20%" align="left">
			<div class="ui-button ui-state-active ui-corner-all ui-button-text-only">
				<span class="ui-button-text">${dto.name}</span>
			</div>
		</td>
		<td class="ui-state-default" align="center" width="120">
			<div class="ui-buttonset">
     		<a class="edit-button" style="padding: 0px;" href="${ctx}/account/authority!input.action?id=${dto.id}" title="编辑">
     			<img src="${ctx}/images/icon_edit.gif" border="0" width="16" height="16" />
     		</a>
     		<a class="delete-button" style="padding: 0px;" href="${ctx}/account/authority!delete.action?id=${dto.id}" title="删除">
     			<img src="${ctx}/images/icon_trash.gif" border="0" width="16" height="16" />
     		</a>
     		</div>
    	</td>
	</tr>
	</c:forEach>
	</tbody>
	<tfoot>
	<tr>
		<td colspan="6" class="ui-widget-header ui-corner-all" align="center">		
			<dataTable:pager showNumberList="true" />						
		</td>
	</tr>
	</tfoot>
</table>
</form>
<script type="text/javascript">
$(window).initListPage();
</script>
</body>
</html>