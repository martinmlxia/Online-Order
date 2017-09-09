<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="order.print"/></title>
<link rel="shortcut icon" href="conant.ico"/>
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
		window.location.href='<c:url value="/producePrintCounter.ord"><c:param name="orderIds" value="${order.id}"/></c:url>';
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
	<%@ include file="ProducePrintOrder.jsp" %>
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
