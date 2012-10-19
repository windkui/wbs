<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${appTitle} 帮助</title>
<%@ include file="/common/meta.jsp" %>
</head>

<body>
<div id="helpShow">
<iframe id="helpContent" marginheight="0" marginwidth="0" height="1000" width="100%" src="${ctx}/help/index.html"></iframe>
</div>
<script type="text/javascript">
if(window.jQuery) {
	$(function() {
		var showParent = $('#helpShow').parent();
		var pTop = showParent.offset().top;
		var wHeight = $(window).height()-32;
		$('#helpContent').height(wHeight-pTop);
	});
}
</script>
</body>
</html>