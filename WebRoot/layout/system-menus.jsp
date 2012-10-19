<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
<title>${appTitle} 主体页</title>
<link type="text/css" rel="stylesheet" href="${ctx}/js/operamasks-ui/css/default/om-default.css" id="omTheme" />
<link type="text/css" rel="stylesheet" href="${ctx}/js/jquery-ui/theme/${appTheme}/jquery-ui.css" id="uiTheme" />
</head>
<body>
    <ul id="mainMenu" class="topMenus">
    <c:forEach var="topMenu" items="${userMenus}">
    	<li class="menuBtn">
    		<a tabindex="0" id="menu-${topMenu.id}" href="#sub-${topMenu.id}" class="topMenu">
    			<span><img src="${ctx}/${(empty topMenu.icon)? 'images/ico7.png':(topMenu.icon)}"  width="30" height="28" border="0"/></span>
    			<dl>${topMenu.name}</dl>
    		</a>
			<div id="sub-${topMenu.id}" class="subMenu">
				<c:set var="parentMenu" value="${topMenu}" scope="request"/>
				<c:import url="./sub-menus.jsp"></c:import>
			</div>    		
    	</li>
    </c:forEach>
    	<li class="menuBtn">
			<a tabindex="0" href="#sub-help" id="menu-help" class="topMenu">
    			<span><img src="${ctx}/images/ico5.png"  width="30" height="28" border="0"/></span>
    			<dl>帮助</dl>
			</a>
			<div id="sub-help" class="subMenu" style="margin-top: 2px;">			
				<ul>
					<li><a href="#" onclick="executeCommand('tab-000', '平台首页', '${ctx}/manager/index.action')">
						<img src="${ctx}/images/calendar.gif" height="16" width="16" class="om-menu-icon" />
						<span>平台首页</span></a>
					</li>				
					<li><a href="#" onclick="executeCommand('tab-001', '帮助首页', '${ctx}/help/help.jsp')">
						<img src="${ctx}/images/calendar.gif" height="16" width="16" class="om-menu-icon" />
						<span>平台使用手册</span></a>
					</li>
					<li><a href="#" id="aboutMenu" onclick="showAbout()">
						<img src="${ctx}/images/calendar.gif" height="16" width="16" class="om-menu-icon" />
						<span>关于综合业务平台</span></a>
					</li>
				</ul>
			</div>    	
    	</li>
  	</ul>
	<div class="quit ui-button"><b>
		<a href="${ctx}/j_spring_security_logout" target="_top"><span>退出登录</span>
		</a></b>
  	</div><div class="xinxi"><b>欢迎您：${sysUserInfo.loginName}&nbsp;&nbsp;&nbsp;&nbsp;所属岗位：${sysUserInfo.department.name}</b></div>
  	<br/><b class="xinxi">风格：<select onchange="changeOmTheme(this.value)">
  		<option value="default">Default</option>
  		<option value="apusic">Apusic</option>
  	</select></b>  	
<div id="aboutPanel">
	<div class="ui-state-hover ui-corner-all" style="padding: 4px;">
	<b>
		<h3>综合业务平台</h3>
		<span>copyright © 2012 powered by  
		<br/>【版权所有】</span>
	</b>
	</div>
</div>	
</body>
</html>