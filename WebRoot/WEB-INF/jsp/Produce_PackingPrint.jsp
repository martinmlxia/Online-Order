<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="conant.ico"/>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css" rel="stylesheet"/>
<title><spring:message code="order.print"/></title>
</head>
<style>
   .print_class{
	   font-size:12px;
	   width:330px;
	   height:330px;
	   border:#333 solid 1px;
	   border-collapse:collapse;
	   }
   .print_class td{
	   border:#333 solid 1px;
	   padding: 5px;
	   vertical-align:top;
	   }
</style>
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
<body>
<table align="center" width="50%">
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
<div id="printcontent" style="text-align:center;">
   <%@ include file="ProducePackingPrint.jsp" %>
 </div>
   <br/>
<table align="center" width="50%">
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

