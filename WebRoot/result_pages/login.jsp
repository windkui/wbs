<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/subSysNames.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appTitle} 登录</title>
<%@ include file="/common/meta.jsp" %>
<link type="text/css" rel="stylesheet" href="${ctx}/js/jquery/validate/jquery.validate.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/validate/messages_cn.js"></script>
<script>
if(window!=window.top) {
	window.top.location = window.location;
}
jQuery(function() {
	$('#loginName').focus();
	$('#loginForm').validate();
});
</script>
<style type="text/css">
<!--
body { margin:0 auto; padding:0; font-size:12px; background:url(${ctx}/images/big_bg.png) top repeat-x #49a1d0;}
.lg_bg{ background:url(${ctx}/images/bg.png) no-repeat center; margin:0 auto;height:514px;}
.login{width:250px;height:130px;border:none;padding:10px 0;}
.input{ width:130px; height:20px; text-align:left; color:#000000;}
.text{color:#1e6586; font-size:14px; font-weight:bold; text-align:right; padding-right:5px; line-height:35px;}
.denglu{ text-align:center; margin:10px;}
.button{ background:url(${ctx}/images/but.png) no-repeat center; width:102px; height:37px; margin:0 auto; border:none; line-height:37px; cursor:pointer;}
.big{width:500px; height:330px; margin-top:50px; border:none; }
-->
</style>
</head>

<body>
<form id="loginForm" action="${ctx}/j_spring_security_check" method="post" title="欢迎使用“${appTitle}”">
<table width="100%" cellpadding="0" cellspacing="0" align="center" class="lg_bg">
<tr>
    <td align="center">
    <table class="big">
    <tr>
    	<td align="right">
    	<table width="45%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    		<th colspan="4">
			<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
				<b style="color:#ff0000;">登陆信息错误!请重新登陆!</b>
			</c:if>      	    		
    		</th>
    	</tr>
      	<tr>
        	<td width="34%" class="text">用户名：</td>
        	<td width="66%"><input type="text" class="input required" id="loginName" name="j_username" value="admin" /></td>
      	</tr>
		<tr>
		  <td class="text">密码：</td>
		  <td><input type="password" class="input required" id="password" name="j_password" value="admin" /></td>
		</tr>
		<tr>
		  <td class="text">子系统：</td>
		  <td>
		  	<select name="subSys" class="required">
		  	<c:forEach var="sdto" items="${SubSysNameMap}">
		  		<option value="${sdto.key}">${sdto.value}</option>		  		
		  	</c:forEach>
		  	</select>
		  </td>
		</tr>
    	</table>
    	<table width="45%"  border="0" cellpadding="0" cellspacing="0"  class="denglu">
    	<tr>
        	<td  align="center"><input type="submit" class="button" name="button" id="button" value="" /></td>
    	</tr>
    	</table>
    	</td>
	</tr>
	</table>    
	</td>
</tr> 
</table>
</form>
</div>
</body>
</html>

