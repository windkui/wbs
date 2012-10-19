<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${appTitle} 系统用户管理-保存</title>
	<%@ include file="/common/meta.jsp" %>	
	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/general.css" />
	<script type="text/javascript" src="${ctx}/js/sea.min.js" data-main="${ctx}/js/init-systemUI"></script>
</head>

<body>
<c:set var="_create" value="${empty id}" />
	<div id="message" class="ui-state-hover" align="center">${message}</div>
	<div id="errorMessage" class="ui-state-error" align="center">${errorMessage}</div>
<form id="inputForm" action="#" method="post">
	<c:if test="${!_create}">
		<input type="hidden" name="id" value="${id}" />
	</c:if>
  	<table border="0" align="center" cellpadding="0" cellspacing="1" class="tableBorder" width="550">
    <tr>
    	<th height="25" class="ui-widget-header ui-corner-all" colspan="6"><b><c:out value="${_create? '增加':'修改'}" />系统用户</b></th>
    </tr>
    <tr>
		<td width="30%" height="23" class="ui-state-hover" align="right">			
			登录名：<b class="requiredStar">*</b>
		</td>
		<td width="40%" class="ui-state-default">
           	<input type="text" name="loginName" id="loginName" value="${loginName}" class="text required" size="30" maxlength="40" <c:if test="${!_create}">disabled="disabled"</c:if> />
		</td>
		<td class="ui-state-default"></td>
	</tr>
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			登录密码：<b class="requiredStar">*</b>			
		</td>
        <td class="ui-state-default">
           	<input type="password" name="password" id="password" value="${password}" class="text" size="30" maxlength="40" />
        </td>
		<td class="ui-state-default"></td>
	</tr>
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			密码确认：<b class="requiredStar">*</b>
		</td>
		<td class="ui-state-default">
           	<input type="password" name="repasswd" id="repasswd" value="${password}" class="text required" size="30" maxlength="40"/>
		</td>
		<td class="ui-state-default"></td>
	</tr>
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			用户姓名：<b class="requiredStar">*</b>
		</td>
		<td class="ui-state-default">
			<input type="text" name="name" id="name" value="${name}" class="text required" size="30" maxlength="40"/>
		</td>
		<td class="ui-state-default"></td>
	</tr>
	<%-- tr>
		<td height="23" class="ui-state-hover" align="right">
			用户邮箱：<b class="requiredStar">*</b>
		</td>
		<td class="ui-state-default">
			<input type="text" name="email" value="${email}" class="text required email" size="30" maxlength="40" />               
		</td>
		<td class="ui-state-default"></td>
	</tr --%>
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			用户角色：<b class="requiredStar">*</b>
		</td>
		<td class="ui-state-default">
           	<span class="forumRowHighlight" id="roleset">
           	<c:forEach var="rdto" items="${allRoleList}">
           		<input type="radio" name="roleId" value="${rdto.id}" id="role_${rdto.id}" class="required" <c:if test="${role.id==rdto.id}">checked="checked"</c:if> />
           		<label for="role_${rdto.id}">${rdto.name}</label>
           	</c:forEach>
           	</span>
           </td>
           <td id="error_role_id" class="ui-state-default"></td>
    </tr>
	<tr>
		<td height="23" class="ui-state-hover" align="right">
			所属部门：<b class="requiredStar">&nbsp;&nbsp;</b>
		</td>
		<td class="ui-state-default">
			<c:set var="departId" value="${department.id}" />
			<select name="departmentInput.id">
				<option value="">-不指定-</option>
			<c:forEach var="ddto" items="${allDepartmentList}">			
				<option value="${ddto.id}" <c:if test="${departId==ddto.id}">selected="selected"</c:if>>${ddto.name}</option>
			</c:forEach>
			</select>
           </td>
           <td id="error_role_id" class="ui-state-default"></td>
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
		saveAction: '${ctx}/account/user!save.action',
		validations: {
			onkeyup: false,
			rules: {
				loginName: {remoteValid: {url:"${ctx}/account/user!checkLoginName.action", data:{oldLoginName:'${loginName}'}}},
				password: 'strengthPassword',
				repasswd: {equalTo: '#password'}
			},
			messages: {
				loginName: {remoteValid: "该登录名已被使用!"},
				password: {strengthPassword: '请输入介于6至20位的有效密码!'},
				repasswd: {equalTo: '确认密码与密码不符!'}
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
