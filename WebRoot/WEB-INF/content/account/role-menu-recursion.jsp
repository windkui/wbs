<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<c:set var="menu_parent" value="${requestScope.parent_menu}"/>
<c:if test="${not empty menu_parent.subMenus}">
<ul>
	<c:forEach var="sub_menu" items="${menu_parent.subMenus}">
	<li class="middle-all">
		<input type="checkbox" name="checkedMenuIds" value="${sub_menu.id}" id="roleMenu_${sub_menu.id}" 
			<c:if test="${fn:contains(checkedMenuIds, sub_menu.id)}">checked="checked"</c:if> />
		<label for="roleMenu_${sub_menu.id}" class="ui-state-hover ui-corner-all">${sub_menu.name}</label>
    <c:if test="${not empty sub_menu.subMenus}">
		<c:set var="parent_menu" value="${sub_menu}" scope="request"/>
		<c:import url="role-menu-recursion.jsp"/>
	</c:if>
	</li>
	</c:forEach>
</ul>
</c:if>
<c:set var="parent_menu" value="${menu_parent}" scope="request" />