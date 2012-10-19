<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${appTitle} 系统菜单管理-保存</title>
	<%@ include file="/common/meta.jsp" %>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/general.css" />
	<script type="text/javascript" src="${ctx}/js/sea.min.js" data-main="${ctx}/js/init-systemUI"></script>		
</head>

<body>
<c:set var="_create" value="${empty id}" />
	<div id="message" class="ui-state-hover" align="center">${message}</div>
	<div id="errorMessage" class="ui-state-error" align="center">${errorMessage}</div>
<form id="inputForm" action="#" method="post" enctype="multipart/form-data">
	<c:if test="${!_create}">
		<input type="hidden" name="id" value="${id}" />
	</c:if>
  	<table border="0" align="center" cellpadding="0" cellspacing="1" class="tableBorder" width="500">
    <tr>
    	<th height="25" class="ui-widget-header ui-corner-all" colspan="6"><b><c:out value="${_create? '增加':'修改'}" />系统菜单</b></th>
    </tr>
    <tr>
		<td width="30%" height="23" class="ui-state-hover" align="right">			
			菜单名：<b class="requiredStar">*</b>
		</td>
		<td width="40%" class="ui-state-default">
           	<input type="text" name="name" id="name" value="${name}" class="text required" size="30" maxlength="40" />
		</td>
		<td class="ui-state-default"></td>
	</tr>
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			菜单命令：<b class="requiredStar">*</b>
		</td>
        <td class="ui-state-default">
           	<input type="text" name="command" id="command" value="${command}" class="text" size="30" maxlength="255" />
        </td>
		<td class="ui-state-default"></td>
	</tr>
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			上级菜单：<b class="requiredStar"></b>
		</td>
		<td class="ui-state-default">		
			<select name="parentId">
				<option value="">--请选择--</option>
           	<c:forEach var="mdto" items="${topMenuList}">           	
           		<c:if test="${mdto.id!=id}">
           			<option value="${mdto.id}" <c:if test="${parent.id==mdto.id}">selected="selected"</c:if>>${mdto.name}</option>
           			<c:if test="${not empty mdto.subMenus}">
           				<c:set var="parentMenu" value="${mdto}" scope="request" />
           				<c:set var="menuLevel" value="1" scope="request" />
           				<c:import url="subMenus-options.jsp" />
           			</c:if>
           		</c:if>           		
           	</c:forEach>
			</select>
           </td>
           <td id="error_parent_id" class="ui-state-default"></td>
    </tr>
    <tr>
      <td height="23" class="ui-widget-header" align="center" colspan="6">
      	<button type="submit" id="submit-button">提交</button>
		&nbsp;
      	<button type="reset" id="reset-button">重置</button>
      </td>
    </tr>
  </table>
</form>
<script type="text/javascript">
function initPageUI() {
	$(window).initInputPageUI({
		saveAction: '${ctx}/account/menu!save.action',
		validations: {
			onfocusout: false,
			rules: {
				name: {remoteValid: {url:"${ctx}/account/menu!checkName.action", data:{oldName:'${name}'}}}
			},
			messages: {
				name: {remoteValid: "该菜单名已被使用!"}
			}
		}
	});
}
if(window.jQuery) {
	initPageUI();
}
</script>
</body>
</html>
