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

	<form action="${ctx}/manager/duty/dutyDiary!delete.action"
		method="post" id="listForm">
		<dataTable:grid>
			<table border="0" align="center" cellpadding="0" cellspacing="1"
				class="dataTable">
				<thead>
					<dataTable:header
						orderableValues="false,true,true,true,true,true,true,true,false"
						fieldValues="status,id,createBy,department,createTime,title,content,updateTime,-"
						labelValues="类型,编号,值班人,岗位,创建时间,主题,内容,最后更新,操作"></dataTable:header>
				</thead>
				<tbody>
					<c:forEach var="ddto" items="${page.result}" varStatus="vs">

						<tr>
							<td class="ui-state-default" width="10%" align="center">
								<div
									class="ui-button ui-state-active ui-corner-all ui-button-text-only">
									<span class="ui-button-text">${DiaryStatuss[ddto.status]}</span>
								</div>
							</td>

							<td class="ui-state-default" width="80" align="center">
								<div
									class="ui-button ui-state-active ui-corner-all ui-button-text-only">
									<span class="ui-button-text">${page.first+vs.index}</span>
								</div>
							</td>
							<td class="ui-state-default" width="80" align="left">
								<div
									class="ui-button ui-state-active ui-corner-all ui-button-text-only">
									<span class="ui-button-text">${ddto.createBy}</span>
								</div>
							</td>

							<td class="ui-state-default" width="80" align="left">
								<div
									class="ui-button ui-state-active ui-corner-all ui-button-text-only">
									<span class="ui-button-text">${ddto.departmentName}</span>
								</div>
							</td>
							<td class="ui-state-default" width="80" align="left">
								<div
									class="ui-button ui-state-active ui-corner-all ui-button-text-only">
									<span class="ui-button-text">${ddto.createTime}</span>
								</div>
							</td>
							<td class="ui-state-default" width="80" align="left">
								<div
									class="ui-button ui-state-active ui-corner-all ui-button-text-only">
									<span class="ui-button-text">${ddto.title}</span>
								</div></td>
							<td class="ui-state-default" width="80" align="left">
								<div
									class="ui-button ui-state-active ui-corner-all ui-button-text-only">
									<span class="ui-button-text" id="cont"><c:choose>
											<c:when test="${fn:length(ddto.content) > 20}">
												${fn:substring(ddto.content, 0, 20)}......
											</c:when>
											<c:otherwise>
												${ddto.content}
											</c:otherwise>
										</c:choose> </span>
								</div></td>
							<td class="ui-state-default" width="80" align="left">
								<div
									class="ui-button ui-state-active ui-corner-all ui-button-text-only">
									<span class="ui-button-text">${ddto.updatePerson} 在 <fmt:formatDate
											value="${ddto.updateTime}" pattern="yyyy年MM月dd日 HH:mm" />更新</span>
								</div></td>
							<td class="ui-state-default" width="80" align="left"><a id="${ddto.id }a"
								class="show-button" style="padding: 0px;"
								href="${ctx}/manager/duty/dutyDiary!show.action?id=${ddto.id}"
								title="查看"> <img src="${ctx}/images/icon_edit.gif"
									border="0" width="16" height="16" /></a> <a class="delete-button"
								style="padding: 0px;"
								href="${ctx}/manager/duty/dutyDiary!delete.action?id=${ddto.id}"
								title="删除"> <img src="${ctx}/images/icon_trash.gif"
									border="0" width="16" height="16" /> </a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</dataTable:grid>
	</form>
	<script type="text/javascript">
		$(window).initListPage();
	</script>
</body>
</html>
