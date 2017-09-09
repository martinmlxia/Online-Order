<%-- 
    Document   : AddLensOrder
    Created on : 2008-11-16, 23:19:30
    Author     : Administrator
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>lens order</title>
<link rel="shortcut icon" href="conant.ico"/>
<link href="<c:url value="/resources/style/style.css" />"
	type="text/css" rel="stylesheet">
<script language="javascript" 
	src="<c:url value="/resources/js/calendar.js"/>" type="text/javascript">
</script>
</head>

<body>
<c:if test="${empty order.id}">
	<form action="<c:url value="/addLensOrder.ord"/>" method="post">
</c:if>
<c:if test="${!empty order.id}">
	<form action="<c:url value="/salesModifyOrder.ord"/>" method="post">
</c:if>

<%@ include file="SalesEditOrder.jsp"%>
<br/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
			<c:if test="${empty order.id}">
				<input name="submitok" type="submit" class="button" value="<spring:message code="operation.add"/>"/>
			</c:if>
			<c:if test="${!empty order.id}">
				<input name="submitok" type="submit" class="button" value="<spring:message code="operation.save"/>"/> 
			</c:if>
			&nbsp;&nbsp;<input type="button" class="button" 
				value="<spring:message code="operation.back"/>"
				onclick="javascript:window.location.href='<c:url value="${returnUrl}"/>'"/>			
		</td>
	</tr>
</table>

</form>
</body>
</html>
