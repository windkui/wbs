<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${appTitle} 平台首页</title>
<%@ include file="/common/meta.jsp" %>	

<link type="text/css" rel="stylesheet" href="${ctx}/css/style.css" />
<script type="text/javascript" src="${ctx}/js/sea.min.js" data-main="${ctx}/js/init-systemUI.js"></script>
</head>

<body>
<div class="personal">
   <div class="title"><span ><img src="${ctx}/images/more.gif" width="33" height="11" /></span><strong>个人信息</strong></div>
   <div class="personal_content">
     <ol>
       <li>登录名：<span>${user.loginName}</span></li>
       <li>姓名：<span>${user.name}</span></li>
       <li>岗位：<span>${(empty user.departmentName)? '无':(user.departmentName)}</span></li>
       <li>职责：<span>${user.roleName}</span></li>
       <li>当前日期：<span><fmt:formatDate value="<%=new Date()%>" pattern="yyyy年MM月hh日" /></span></li>
     </ol>
   </div>
</div>
<div class="bar news">
   <div class="title"><span ><img src="${ctx}/images/more.gif" width="33" height="11" /></span><strong>消息提醒</strong></div>
   <c:set var="msgCount" value="${fn:length(messages)}" />
   <div class="bar_content" style="padding:3px;">
     <div class="news_con">
       <div class="new_title">
         <div class="new_title_left">您一共有<font>[ ${msgCount} ]</font>条<span>新消息</span></div>         
         <div class="new_title_right">&nbsp;</div>
       </div>
       <div class="com">
         <ul>
         	<c:if test="${msgCount==0}">
         	<li><a><b class="errorMessage">对不起，暂无最新消息！</b></a></li>
         	</c:if>
         	<c:forEach var="mdto" items="${messages}">
         	<c:if test="${mdto.status==0}">
         		<li class="red" title="未读">
         	</c:if>
         	<c:if test="${mdto.status==1}">
         		<li class="blue" title="已读">
         	</c:if>
           		<div class="inlineText" style="width: 70%;"><a href="#" title="${mdto.title} - ${mdto.content}">${mdto.title} - ${mdto.content}</a></div><span><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${mdto.sendTime}" /></span></li>
           	</c:forEach>
         </ul>
         &nbsp;
       </div>
     </div>
   </div>
   <div class="bot"><span ></span><strong></strong></div>
</div>
<div class="weather">       
   <div class="title"><span ><img src="${ctx}/images/more.gif" width="33" height="11" /></span><strong>天气预报</strong></div>
   <div class="weather_content">
     <ul>
       <li style="font-weight:bold;"><span>今天(周五)</span><span><img src="${ctx}/images/weather1.jpg" width="48" height="47" /><img src="${ctx}/images/weather2.jpg" width="48" height="47" /></span><span><font>32～24℃</font><font>多云转阴</font><font>微风</font></span></li>
       <li style="font-weight:bold;"><span>今天(周五)</span><span><img src="${ctx}/images/weather1.jpg" width="48" height="47" /><img src="${ctx}/images/weather2.jpg" width="48" height="47" /></span><span><font>32～24℃</font><font>多云转阴</font><font>微风</font></span></li>
       <li style="font-weight:bold;"><span>今天(周五)</span><span><img src="${ctx}/images/weather1.jpg" width="48" height="47" /><img src="${ctx}/images/weather2.jpg" width="48" height="47" /></span><span><font>32～24℃</font><font>多云转阴</font><font>微风</font></span></li>
     </ul>
     <div class="fabu">2012年05月30日12时发布 <span><a href="#">7天预报</a> </span>|<span><a href="#">更多城市</a> </span></div>
   </div>
</div>
<div class="bar matter">
   <div class="title"><span ><img src="${ctx}/images/more.gif" width="33" height="11" /></span><strong>值班日记</strong></div>
   <div class="bar_content matter_cot">
     <ul>
     <c:if test="${fn:length(dutyDiaryList)==0}">
     	<li><div style="height: 100px;" align="center"><a><b class="errorMessage">对不起，暂无最新日记！</b></a></div></li>
     </c:if>
	 <c:forEach var="ddto" items="${dutyDiaryList}">
   		<li><a href="#"><strong>【${ddto.title}】</strong>${ddto.content}<span>[<font><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${ddto.createTime}" /></font>]</span></a></li>     	
   	 </c:forEach>       
     </ul>
   </div>
   <div class="bot"><span ></span><strong></strong></div>
</div>
</body>
</html>