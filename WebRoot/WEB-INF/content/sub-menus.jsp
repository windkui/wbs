<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<c:set var="menuParent" value="${requestScope.parentMenu}"/>
<c:if test="${not empty menuParent.subMenus}">			
<ul>
<c:forEach var="subMenu" items="${menuParent.subMenus}">
	<li>
		<a href="#" class="menuItem" <c:if test="${not empty subMenu.command}">
			onclick="executeCommand('tab-${subMenu.id}', '${subMenu.name}', '${ctx}${subMenu.command}')"</c:if> title="${subMenu.name}" rel="${ctx}${subMenu.command}">
			<img src="${ctx}/images/calendar.gif" class="om-menu-icon" />
			<span>${subMenu.name}</span>
		<c:if test="${not empty subMenu.subMenus}">
			<span role="popup"></span>
		</c:if>
		</a>
		<c:set var="parentMenu" value="${subMenu}" scope="request"/>
		<c:import url="sub-menus.jsp" />
	</li>
</c:forEach>
</ul>
</c:if>