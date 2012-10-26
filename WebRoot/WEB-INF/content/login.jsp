<%@ page language="java" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/subSysNames.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appTitle} 登录</title>
<link href="newlogin/style.css" rel="stylesheet" type="text/css" />
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/validate/messages_cn.js"></script>
<script>
	if (window != window.top) {
		window.top.location = window.location;
	}
	jQuery(function() {
		$('#loginName').focus();
		$('#loginForm').validate();
		$("div").not("#login,#tot1,#content").click(function() {
				if ($('#login').css("display") == "none") {
					$('#login').css("display", "block");
				}
			});
		if(${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}){
			$('#login').css("display", "block");
		}
		$('#close').click(function(){
			$('#login').css("display", "none");
		});
	});
</script>
</head>

<body>

	<div class="wrap"
		id="wrap">
			<div class="top">
				<ul class="operating">
					<li><a href="#"><span class="icon help"></span><span>帮助</span>
					</a></li>
					<li><a href="#"><span class="icon set-up"></span><span>设置</span>
					</a></li>
					<li><a href="#"><span class="icon closed"></span><span>退出</span>
					</a></li>
				</ul>
			</div>
			<div class="big">
				<div class="main">
					<div class="data"></div>
					<div class="duty"></div>
					<div class="product"></div>
					<div class="client"></div>
					<div class="business"></div>
					<div class="system"></div>
				</div>
			</div>
			<div class="news">
				<span>公共信息内容竖向滚动显示，公共信息内容竖向滚动显示，公共信息内容竖向滚动显示，公共信息内容竖向滚动显示......</span>
			</div>
			<div class="copy">© 2008 Beijing DHC Digital Techology Co.,Ltd,
				All Rights Reserved
			</div>
		</div>
		<form id="loginForm" action="${ctx}/j_spring_security_check"
		method="post" title="欢迎使用“${appTitle}”">
			<div class="login" id="login">
				<div class="tot1" id="tot1">
					<span class="close" id="close"> </span>
				</div>
				<div class="content">
					<ul class="til">
						<li><span class="close"><c:if
									test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
									<b style="color: #ff0000;">登陆信息错误!请重新登陆!</b>
								</c:if> </span>
						</li>
						<li><input type="text" class="name" id="loginName"
							name="j_username" value="admin" /></li>
						<li><input type="password" class="input password"
							id="password" name="j_password" value="admin" /></li>
						<li><span><input type="submit" class="true"
								name="button" id="button" value="" /> </span></li>
					</ul>
				</div>
			</div>
		</form>
</body>
</html>

