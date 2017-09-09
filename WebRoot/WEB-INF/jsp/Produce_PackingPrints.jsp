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
		// 获取订单id列表
		var ordertables = document.getElementsByTagName('table');
		var i, el;
		var orderIds = "";
		for(i = 0; i < ordertables.length; i++)
		{
			el = ordertables[i];
			if(el.name == 'ordertable')
			{
				orderIds = orderIds + el.id + ",";
			}
		}
		var htmlcode = document.body.innerHTML;
		var printDiv = document.getElementById("printcontent"); 
		var printhtml = printDiv.innerHTML;
		window.document.body.innerHTML= printhtml;
		window.print();
		window.document.body.innerHTML = htmlcode;
		window.location.href='<c:url value="/producePrintCounter.ord?orderIds=' + orderIds + '"/>';
	}
</script>
</head>
<style>
   .print_class{
	   font-size:12px;
	   width:300px;
	   height:300px;
	   border:#333 solid 1px;
	   border-collapse:collapse;
	   }
   .print_class td{
	   border:#333 solid 1px;
	   padding: 5px;
	   vertical-align:top;
	   }
</style>
<body>
<table align="center" width="99%">
	<tr>
		<td align="right">
			<input type="button" class="button"
				onclick="javascript:window.location.href='<c:url value="/produceOrders.ord"/>'" 
				value="<spring:message code="operation.back"/>"/>
		</td>
		<td align="left">
			<input name="printOrder" type="button" class="button"
				onclick="printOrder();" value="<spring:message code="order.print"/>"/>
		</td>
	</tr>
</table>
<br/>
<div id="printcontent" style="text-align:center;">
<c:forEach var="order" items="${listOrder}" varStatus="itemStatus">
	<%@ include file="ProducePackingPrint.jsp" %>
	<c:if test="${!itemStatus.last}">
		<div style="height:10px;"></div>
	</c:if>
</c:forEach>
</div>
<br/>
<table align="center" width="99%">
	<tr>
		<td align="right">
			<input type="button" class="button"
				onclick="javascript:window.location.href='<c:url value="/produceOrders.ord"/>'" 
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

