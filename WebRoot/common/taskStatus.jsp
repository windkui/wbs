<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.TaskHistoryStatus}">
<jsp:useBean id="TaskHistoryStatus" class="java.util.LinkedHashMap" scope="session">	
	<c:set target="${TaskHistoryStatus}" property="ERROR" value="未知错误" />
	<c:set target="${TaskHistoryStatus}" property="GET_ERROR" value="采集错误" />
	<c:set target="${TaskHistoryStatus}" property="PARSE_ERROR" value="解析错误" />
	<c:set target="${TaskHistoryStatus}" property="DB_ERROR" value="入库错误" />
	<c:set target="${TaskHistoryStatus}" property="WARNING" value="警告" />
	<c:set target="${TaskHistoryStatus}" property="SUCCESS" value="成功" />
</jsp:useBean>
</c:if>