<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${appTitle}</title>
	<%@ include file="/common/meta.jsp" %>	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/general.css" />	
</head>
<body>
<div class="ui-state-default ui-corner-all" style="border:1px solid;padding: 4px;width: 600px;">
	<b>操作失败：<div class="ui-state-error ui-corner-all" style="padding: 2px;"><b>${errorMessage}</b></div></b>
</div>
</body>
</html>