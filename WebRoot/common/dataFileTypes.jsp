<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.DataFileTypes}">
<jsp:useBean id="DataFileTypes" class="java.util.LinkedHashMap" scope="session">
	<c:set target="${DataFileTypes}" property="GROUND" value="地面" />
	<c:set target="${DataFileTypes}" property="AIR" value="高空" />
	<c:set target="${DataFileTypes}" property="AUTOSTATION" value="自动气象站" />
	<c:set target="${DataFileTypes}" property="CLOUD" value="云图" />
	<c:set target="${DataFileTypes}" property="RADAR" value="雷达" />	
</jsp:useBean>
<jsp:useBean id="DataFileFormatTypes" class="java.util.LinkedHashMap" scope="session">
	<c:set target="${DataFileFormatTypes}" property="TXT" value="TXT" />
	<c:set target="${DataFileFormatTypes}" property="DOC" value="DOC" />
	<c:set target="${DataFileFormatTypes}" property="XML" value="XML" />
	<c:set target="${DataFileFormatTypes}" property="XSL" value="XSL" />
	<c:set target="${DataFileFormatTypes}" property="DAT" value="DAT" />
	<c:set target="${DataFileFormatTypes}" property="JPG" value="JPG" />
	<c:set target="${DataFileFormatTypes}" property="PDF" value="PDF" />	
</jsp:useBean>
<jsp:useBean id="DataFileTimeTypes" class="java.util.HashMap" scope="session">
	<c:set target="${DataFileTimeTypes}" property="SHORT" value="短期" />
	<c:set target="${DataFileTimeTypes}" property="MEDIUM" value="中期" />
	<c:set target="${DataFileTimeTypes}" property="LONG" value="长期" />
</jsp:useBean>
<jsp:useBean id="DataSourceTypes" class="java.util.HashMap" scope="session">
	<c:set target="${DataSourceTypes}" property="FORECAST" value="预报" />
	<c:set target="${DataSourceTypes}" property="LIVE" value="实况" />
	<c:set target="${DataSourceTypes}" property="OTHER" value="其它" />
</jsp:useBean>
</c:if>