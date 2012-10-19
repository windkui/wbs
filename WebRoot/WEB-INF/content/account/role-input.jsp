<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/subSysNames.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${appTitle} 系统角色管理-保存</title>
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
    	<th height="25" class="ui-widget-header ui-corner-all" colspan="6"><b><c:out value="${_create? '增加':'修改'}" />系统角色</b></th>
    </tr>
    <tr>
		<td width="30%" height="23" class="ui-state-hover" align="right">			
			角色名：<b class="requiredStar">*</b>
		</td>
		<td width="40%" class="ui-state-default">
           	<input type="text" name="name" id="name" value="${name}" class="text required" size="30" maxlength="40" />
		</td>
		<td class="ui-state-default"></td>
	</tr>
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			所属系统：<b class="requiredStar"></b>
		</td>
        <td class="ui-state-default" align="left">
        	<select name="subSys" class="required">
        	<c:forEach var="sys" items="${SubSysNameMap}">
        		<option value="${sys.key}" <c:if test="${sys.key==subSys}">selected="selected"</c:if>>${sys.value}</option>
        	</c:forEach>        	
        	</select>
        </td>
		<td class="ui-state-default"></td>
	</tr>	
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			角色权限：<b class="requiredStar"></b>
		</td>
        <td class="ui-state-default" align="left">
        	<button type="button" id="authoriesToggleBtn">显示/隐藏</button>
        	<div id="authoriesPanel" class="hidden">
        	<c:forEach var="adto" items="${allAuthorityList}">
        		<label class="ui-button ui-state-default ui-corner-all ui-smallest-button">
        			<input type="checkbox" name="checkedAuthIds" value="${adto.id}" <c:if test="${fn:contains(checkedAuthIds, adto.id)}">checked="checked"</c:if> />${adto.name}
        		</label>&nbsp;
        	</c:forEach>
        	</div>
        </td>
		<td class="ui-state-default"></td>
	</tr>
	<tr>	
		<td height="23" class="ui-state-hover" align="right">
			角色菜单：<b class="requiredStar"></b>
		</td>
		<td class="ui-state-default" align="left">
			<ul id="menusTree">
			</ul>
		</td>
		<td class="ui-state-default"></td>
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
	var authoriesPanel = $('#authoriesPanel');
	$('#authoriesToggleBtn').click(function() {
		authoriesPanel.toggle();
	});
    var menusData = eval('${jsonMenuTree}');
	var menusTree = $('#menusTree').fixOmTree({
		checkboxName: 'checkedMenuIds',
		dataSource: menusData,
		showCheckbox: true
	});
	var menuIds = eval('${jsonCheckedMenuIds}');
	var selectMenus = menusTree.fixOmTree('findNodesBy', function(node) {
		if(menuIds.indexOf(node.id)!=-1) {
			if(!node.children || node.children.length<1) {
				$('#menusTree').fixOmTree('check', node);
				return true;
			}
		}
		return false;
	});
	$(window).initInputPageUI({
		saveAction: '${ctx}/account/role!save.action',
		validations: {
			onfocusout: false,
			rules: {
				name: {remoteValid: {url:"${ctx}/account/role!checkName.action", data:{oldName:'${name}'}}}
			},
			messages: {
				name: {remoteValid: "该角色名已被使用!"}
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
