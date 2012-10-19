<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<c:set var="menuParent" value="${requestScope.parentMenu}" />
<c:set var="level" value="${requestScope.menuLevel}" />
<c:forEach var="mdto" items="${menuParent.subMenus}">
<c:if test="${id!=mdto.id}">
	<option value="${mdto.id}" <c:if test="${parent.id==mdto.id}">selected="selected"</c:if>>├<c:forEach begin="1" end="${level-1}">--</c:forEach>${mdto.name}</option>
	<c:if test="${not empty mdto.subMenus}">
		<c:set var="parentMenu" value="${mdto}" scope="request"/>
		<c:set var="menuLevel" value="${level+1}" scope="request" />
		<c:import url="subMenus-options.jsp" />
	</c:if>
</c:if>
</c:forEach>