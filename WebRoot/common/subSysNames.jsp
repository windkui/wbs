<%@page import="com.springside.zsword.modules.utils.json.Struts2JSONConvertor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.SubSysNameMap}">
<jsp:useBean id="SubSysNameMap" class="java.util.LinkedHashMap" scope="session">	
	<c:set target="${SubSysNameMap}" property="adminSys" value="系统管理" />
	<c:set target="${SubSysNameMap}" property="pipeDistSys" value="编审分发" />
	<c:set target="${SubSysNameMap}" property="productSys" value="产品加工" />
	<c:set target="${SubSysNameMap}" property="weatherServSys" value="气象服务" />
	<c:set target="${SubSysNameMap}" property="dataCenterSys" value="数据中心" />
	<c:set target="${SubSysNameMap}" property="infoQuerySys" value="信息查询" />
	<c:set target="${SubSysNameMap}" property="businessSys" value="用户管理" />
</jsp:useBean>
<c:set var="SubSysJSON" value="<%='('+Struts2JSONConvertor.convertToString(SubSysNameMap)+')' %>" scope="session" />
</c:if>