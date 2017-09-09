
<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<title>print orders</title>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css" rel="stylesheet"/>
<script language="javascript" type="text/javascript">
	function printOrder() 
	{
		var htmlcode = document.body.innerHTML;
		var printDiv = document.getElementById("printcontent"); 
		var printhtml = printDiv.innerHTML;
		window.document.body.innerHTML= printhtml;
		window.print();
		window.document.body.innerHTML = htmlcode;
	}
</script>
</head>
<body>
<table align="center" width="99%">
	<tr>
		<td align="right">
			<input type="button" class="button"
				onclick="javascript:window.location.href='<c:url value="${returnUrl}"/>'" 
				value="<spring:message code="operation.back"/>"/>
		</td>
		<td align="left">
			<input name="printOrder" type="button" class="button"
				onclick="printOrder();" value="<spring:message code="order.print"/>"/>
		</td>
	</tr>
</table>
<br/>
<div id="printcontent">
	<c:forEach var="order" items="${listOrder}" varStatus="itemStatus">
		<%@ include file="ViewOrder.jsp" %>
		<c:if test="${doubleMode}"><br/><%@ include file="ViewOrder.jsp" %></c:if>
		<c:if test="${!itemStatus.last}"><table style="PAGE-BREAK-AFTER: always"></table></c:if>
	</c:forEach>
</div>
<br/>
<table align="center" width="99%">
	<tr>
		<td align="right">
			<input type="button" class="button"
				onclick="javascript:window.location.href='<c:url value="${returnUrl}"/>'" 
				value="<spring:message code="operation.back"/>"/>
		</td>
		<td align="left">
			<input name="printOrder" type="button" class="button"
				onclick="printOrder();" value="<spring:message code="order.print"/>"/>
		</td>
	</tr>
</table>

</body>
</html>
