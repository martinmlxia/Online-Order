
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="shortcut icon" href="conant.ico"/>
		<title>Batch Modify Orders</title>
		<link href="<c:url value="/resources/style/style.css" />" type="text/css" rel="stylesheet"/>		
	</head>
	
	<body>

	<form name="batchModifyForm" id="batchModifyForm"
		action="<c:url value="/salesBatchModifyOrders.ord"/>" method="post">
		<table width="100%" align="center" style="display:none;">
			<c:forEach var="orderId" items="${form.orderIds}">
				<tr>
					<td>
						<input name="orderIds" type="hidden" value="${orderId}"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<table width="40%" align="center">
			<tr>
				<td colspan="2" align="center">
					<b><spring:message code="operation.batchModifyOption"/></b>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="right" width="50%">
					<b><spring:message code="order.emergent"/></b>
				</td>
				<td align="left" width="50%">
					<input name="emergent" 
						type="checkbox" <c:if test="${!form.emergent}">checked</c:if>/>
				</td>
			</tr>
		</table>
		<br/>
		<table width="40%" align="center">
			<tr>
				<td align="right">
					<input type="button" class="button" 
						onclick="javascript:window.location.href='<c:url value="${returnUrl}"/>'" 
						value="<spring:message code="operation.back"/>"/>
				</td>
				<td align="left">
					<input name="modify" type="submit" class="button" align="middle" value="<spring:message code="operation.modify"/>"/>
				</td>
			</tr>
		</table>
	</form>

	</body>
	
</html>
