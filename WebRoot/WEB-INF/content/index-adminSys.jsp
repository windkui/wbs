<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appTitle} - 系统管理</title>
<%@ include file="/common/meta.jsp" %>	
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type="text/javascript" src="${ctx}/js/sea.min.js" data-main="${ctx}/js/init-systemUI.js"></script>
<style type="text/css">
body {
	font-size: 12px;
	background: gray;
	with: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
}
html {
	width: 100%;
	height: 100%;
}
.subMenu {
	display: none;
	z-index: 101;
}
</style>
</head>

<body>
<div id="container">
	<div id="north-panel" style="margin-bottom:-5px;display: none;">
		<%@include file="/layout/header.jsp" %>
	</div>
	<div id="center-panel">
		<div id="centerContainer" style="display: none;">
			<dl id="ajaxLoading" class="lightbox ui-corner-all ui-state-default" style="display: none;">
				<dt id="loadingTitle" class="ui-widget-header">Loading...</dt>
			  	<dt><img src="${ctx}/images/ajax-loader.gif" /></dt>
			  	<dt id="loadingMessage" class="ui-state-hover">Loading... ...</dt>
			</dl>
			<%-- div id="menuContainer">
				<%@ include file="system-menus.jsp" %>
			</div --%>
		</div>
		<div id="mainContainer">
			<ul id="mainTabs">
				<li><a href="${ctx}/manager/index.action">管理首页</a></li>
				<%-- li><a href="${ctx}/account/role.action">系统角色管理</a></li>
				<li><a href="${ctx}/account/menu.action">系统菜单管理</a></li>
				<li><a href="${ctx}/account/admin.action">管理员管理</a></li --%>					
			</ul>
		</div>
	</div>
</div>
</body>
</html>