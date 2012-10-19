<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.ToolTypes}">
<jsp:useBean id="ToolTypes" class="java.util.LinkedHashMap" scope="session">
	<c:set target="${ToolTypes}" property="EXE" value="无界面EXE" />
	<c:set target="${ToolTypes}" property="BAT" value="BAT" />
	<c:set target="${ToolTypes}" property="GUI" value="界面EXE" />
	<c:set target="${ToolTypes}" property="NATIVE" value="系统原生" />
</jsp:useBean>
</c:if>