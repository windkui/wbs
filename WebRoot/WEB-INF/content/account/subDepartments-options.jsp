<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<c:set var="departmentParent" value="${requestScope.parentDepartment}" />
<c:set var="level" value="${requestScope.departLevel}" />
<c:forEach var="ddto" items="${departmentParent.subDepartments}">
<c:if test="${id!=ddto.id}">
	<option value="${ddto.id}" <c:if test="${department.id==ddto.id}">selected="selected"</c:if>>├<c:forEach begin="1" end="${level-1}">--</c:forEach>${ddto.name}</option>
	<c:if test="${not empty ddto.subDepartments}">
		<c:set var="parentDepartment" value="${ddto}" scope="request"/>
		<c:set var="departLevel" value="${level+1}" scope="request" />
		<c:import url="subDepartments-options.jsp" />
	</c:if>
</c:if>
</c:forEach>