<%@ page language="java" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/diaryStatus.jsp"%>
<%@ taglib prefix="dataTable"
	uri="http://www.dhcc.com.cn/jsp/ui/datatable"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appTitle} 值班日志-列表</title>
<%@ include file="/common/meta.jsp"%>

<link type="text/css" rel="stylesheet" href="${ctx}/css/general.css" />
<script type="text/javascript"
	src="${ctx}/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${ctx}/js/kindeditor/lang/zh_CN.js"></script>

<script type="text/javascript" src="${ctx}/js/sea.min.js"
	data-main="${ctx}/js/init-system"></script>
</head>
<body>
	<div id="message" class="ui-state-hover" align="center">${successMessage}</div>
	<div id="errorMessage" class="ui-state-error" align="center">${errorMessage}</div>
	<form action="${ctx}/duty/dutyDiary!delete.action" method="post"
		id="listForm">
		<dataTable:grid>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="dataTable" width="100%">
				<tbody>
					<c:forEach var="ddto" items="${page.result}" varStatus="vs">
						<table width="100%" cellspacing="0" cellpadding="0"
							bordercolor="#009999" border="0" align="center">
							<tr align="left">
								<td width="20%"><div style="width:100%;height:100px"
										class="ui-button ui-state-active ui-corner-all ui-button-text-only">
										<ul>
											<li><div align="left">
													<span class="ui-button-text">值班人员：${ddto.createBy}</span>
												</div></li>
											<li>
												<div align="left">
													<span class="ui-button-text">发布时间：<fmt:formatDate
															value="${ddto.createTime}" pattern="yyyy年MM月dd日 HH:mm" />
													</span>
												</div></li>
											<li>
												<div align="left">
													<span class="ui-button-text">最后更新：<fmt:formatDate
															value="${ddto.updateTime}" pattern="yyyy年MM月dd日 HH:mm" />
													</span>
												</div></li>
										</ul>
									</div>
								</td>
								<td><div style="width:99.9%;height:100px"
										class="ui-button ui-state-active ui-corner-all ui-button-text-only">
										<ul>
											
											<li><div align="left">
													<span class="ok_type"><c:if test="${ddto.status=='TOP' }">置顶</c:if>主题：${ddto.title} </span>
												</div></li>
												<hr/>
											<li><div align="left">
													<span class="ui-button-text">内容：${ddto.content}</span>
												</div></li>
											<c:if
												test='${ddto.createBy==sysUserInfo.loginName && ddto.theSameDay}'>
												<li><div align="right">

														<a class="button" style="padding: 0px;"
															href="javascript:void(0)"
															onclick="initFormInfo('${ddto.id}','${ddto.title }','${ddto.content }','${ddto.status }')"
															title="查看"> <img src="${ctx}/images/icon_edit.gif"
															border="0" width="16" height="16" /> </a> <a
															class="delete-button" style="padding: 0px;"
															href="${ctx}/duty/dutyDiary!delete.action?id=${ddto.id}"
															title="删除"> <img src="${ctx}/images/icon_trash.gif"
															border="0" width="16" height="16" /> </a>
													</div>
												</li>
											</c:if>
										</ul>
									</div></td>
							</tr>
						</table>
						<hr />
					</c:forEach>
				</tbody>
				<tbody>
				</tbody>
			</table>
		</dataTable:grid>
	</form>

	<form id="inputForm" action="#" method="post">

		<div id="idDiv"></div>
		<table border="0" align="center" cellpadding="0" cellspacing="1"
			class="tableBorder" width="80%">

			<tr>
				<th height="25" class="ui-widget-header ui-corner-all" colspan="6"><b>添加值班日志</b>
				</th>
			</tr>

			<tr>
				<td height="30" class="ui-state-hover" align="right">主题</td>
				<td align="left" height="23" class="ui-state-hover"><input
					type="text" name="title" id="title" class="required"
					maxlength="255" style="width: 50%;" value="" />
				</td>

				<td width="20%" height="23" class="ui-state-hover" align="right">
					类型：</td>
				<td class="ui-state-default"><select name="status" id="status"
					class="required">
						<c:forEach var="tdto" items="${DiaryStatuss}">
							<option value="${tdto.key}"
								<c:if test="${tdto.key==normal}">selected="selected"</c:if>>${tdto.value}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td valign="top" height="30" class="ui-state-hover" align="right">内容</td>
				<td align="left" colspan="3" class="ui-state-hover" align="right"><textarea
						name="content" id="content" style="width:100%;height: 200px;" class="required">${content}</textarea>
				</td>
			</tr>
			<tr>
				<td height="23" class="ui-widget-header" align="center" colspan="6">
					<button type="submit" id="submit-button">提交</button> &nbsp;
					<button type="reset" id="reset-button">重置</button>
				</td>
			</tr>
		</table>
	</form>

	<script type="text/javascript">
		$(window).initInputPage({
			saveAction : '${ctx}/duty/dutyDiary!save.action'
		});
		$(window).initListPage();

		function initFormInfo(id, title, content, status) {
			//alert(id + "  " + title + "   " + content + "  " + status);
			$("#idDiv").append(
					'<input type="hidden" name="id" value="${id}" id="id"/>');
			$("#title").val(title);
			$("#content").val(content);
			$("#status").val(status);
			$("#id").val(id);
			//editor.text(content);
			$("#submit-button").focus();
		}
		/*editor = KindEditor.create('textarea[id="content"]', {
			basePath : '${ctx}/js/kindeditor/',
			newlineTag : 'br',
			themeType : 'simple',
			resizeType : 1,
			id:"editor",
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]
		});
		
		function setValue(a){
			$("#content").val(editor.text());
			//alert($("#content").val());
		}*/
	</script>
</body>
</html>
