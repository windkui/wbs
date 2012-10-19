<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<%@include file="/common/subSysNames.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${appTitle} 系统角色管理</title>
	<%@ include file="/common/meta.jsp" %>	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/general.css" />	
	<script type="text/javascript" src="${ctx}/js/sea.min.js" data-main="${ctx}/js/init-systemUI"></script>
</head>

<body>
<div class="pager">
<div id="mainPanel" class="uiMainPanel">
<div class="ui-widget-header ui-corner-all" style="padding: 2px;">
   <button id="refresh-button">系统角色管理</button>
   <button id="toolbar-button">工具栏</button>   
</div>
<div id="toolbarPanel" class="ui-corner-all ui-state-default">		
	<form id="searchForm" name="searchForm" action="${ctx}/account/role!list.action" method="post">	
  		<table style="width:auto;" border="0" align="center" cellpadding="0" cellspacing="1" class="tableBorder">
  		<tbody>
		<tr>
			<th class="ui-widget-header" height="25" colspan="6">查询条件</th>
		</tr>
		<tr>
			<td width="30%" height="23" class="ui-state-hover" align="right">
				角色名：
			</td>
			<td class="ui-state-default">
				<input type="text" maxlength="20" class="text" name="flt_LIKES_name" />
			</td>
			<td width="20%" height="23" class="ui-state-hover" align="right">
				&nbsp;
			</td>
			<td class="ui-state-default">
				&nbsp;
			</td>			
		</tr>
		<tr>
			<td height="23" class="ui-state-hover" align="right">
				操作：
			</td>
			<td class="ui-state-hover ui-corner-all" colspan="6">
				<button id="search-button">查询</button>
				<button type="reset" id="reset-button">重置</button>
				<button type="button" id="add-button" value="${ctx}/account/role!input.action">添加</button>
			</td>
		</tr>	
		</tbody>
		</table>
	</form>
</div>
<div id="dataPanel" class="dataPanel" align="center">
	<div id="inputDialog" title="系统角色" style="background: transparent;">
		<div id="inputPanel" class="inputPanel" style="background: transparent;">
		</div>
	</div>	
	<table id="listDataTable" align="left">
	</table>
</div>
</div>
<script type="text/javascript">
function initPageUI() {
	window.SubSysJSON = window.SubSysJSON||eval('${SubSysJSON}');
	function renderSubSysName(sysKey) {
		if(!sysKey) return "";
		return SubSysJSON[sysKey];
	}
	jQuery(window).initDataPagerUI({
		gridConfig: {
			colModel: [
				{header:'角色名', name: 'name', width: 100, sort:'clientSide'},
				{header:'角色权限', name: 'authNames', width: 200, wrap:false, sort:'clientSide'},
				{header:'角色菜单', name: 'menuNames', width: 200, wrap:false, sort:'clientSide'},
				{header:'子系统', name: 'subSys', width: 200, wrap:true, sort:'clientSide', renderer:renderSubSysName},
				{header:'操作', name:'id', renderer: uiRenderOptCol, width: 200}
			]
		}
	});
}
if(window.jQuery) {
	initPageUI();
}
</script>
</div>	
</body>
</html>
