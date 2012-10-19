<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${appTitle} 头部</title>
<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
</head>

<body>
<div class="top">
	<div class="logo"></div>
	<div class="icon">
		<%@ include file="system-menus.jsp" %>
	</div>	
	<div class="quit ui-button"><b>
		<a href="${ctx}/j_spring_security_logout" target="_top"><span>退出登录</span>
		</a></b>
  	</div><div class="xinxi"><b>欢迎您：${sysUserInfo.loginName}&nbsp;&nbsp;&nbsp;&nbsp;所属岗位：${sysUserInfo.department.name}</b></div>
</div>
</body>
</html>