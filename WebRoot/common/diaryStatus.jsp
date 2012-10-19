<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.DiaryStatuss}">
<jsp:useBean id="DiaryStatuss" class="java.util.HashMap" scope="session">
	<c:set target="${DiaryStatuss}" property="TOP" value="置顶" />
	<c:set target="${DiaryStatuss}" property="NORMAL" value="普通" />
</jsp:useBean>
</c:if>