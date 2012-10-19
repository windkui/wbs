<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${appTitle} 头部</title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
</head>

<body>
<div class="top_xtgl">
  	<div class="logo_xtgl"><font>--系统管理子系统</font></div>
	<div class="icon_xtgl">
		<%@ include file="system-menus.jsp" %>
	</div>
</div>
</body>
</html>