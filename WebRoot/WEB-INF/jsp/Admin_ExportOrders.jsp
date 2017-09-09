
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<title>Export Orders</title>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css" rel="stylesheet"/>
<script language="javascript"
	src="<c:url value="/resources/calendar/WdatePicker.js"/>"
	type="text/javascript">
</script>
</head>
<body>

	<form action="<c:url value="/adminExportOrders.ord"/>" method="post">
		<table width="100%" align="left">
			<tr>
				<td>
				<table class="table9" width="100%" border="1" cellpadding="0" cellspacing="0"
					bordercolor="#2268A6" bordercolordark="ffffff">
					<tr>
						<td align="left" width="25%" class="table8">
							&nbsp;<spring:message code="order.orderstatus"/>
						</td>
						<td align="left" width="15%">
						<spring:bind path="orderQuerier.orderstatus">
							<select class="input"
								name="<c:out value="${status.expression}"/>">
								<c:forEach var="order_status" items="${listOrderstatus}">
									<option <c:if test="${order_status == status.value}">selected</c:if> 
									value="<c:out value="${order_status}"/>"><c:out value="${order_status}"/>
									</option>
								</c:forEach>
							</select>
						</spring:bind>
						</td>
						<td align="left" width="20%" class="table8">
							&nbsp;<spring:message code="order.id"/>
						</td>
						<td align="left" width="10%">
							<spring:bind path="orderQuerier.id">
								<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
								<c:if test="${status.error}">
									<font class="red">${status.errorMessage}</font>
								</c:if>
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td align="left" width="20%" class="table8">&nbsp;<spring:message code="order.clientname"/></td>
						<td align="left" colspan="5">
							<spring:bind path="orderQuerier.username">
								<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>"/>
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td align="left" width="25%" class="table8">
							&nbsp;<spring:message code="order.ordereddate"/>
							&nbsp;<spring:message code="range.from"/>
						</td>
						<td align="left" width="15%">
						<spring:bind path="orderQuerier.start_ordereddate">
							<input type="text" name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.displayValue}"/>"
								onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>					
						</spring:bind>
						</td>
						<td align="left" width="20%" class="table8">&nbsp;<spring:message code="range.to"/></td>
						<td colspan="3">
						<spring:bind path="orderQuerier.end_ordereddate">
							<input type="text" name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.displayValue}"/>"
								onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>					
						</spring:bind>
						</td>
					</tr>
					<tr>
						<td align="left" width="25%" class="table8">&nbsp;<spring:message code="order.patient"/>&nbsp;</td>
						<td align="left" width="10%">
							<spring:bind path="orderQuerier.patient">
								<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>">
							</spring:bind>
						</td>
						<td align="left" width="25%" class="table8">&nbsp;<spring:message code="order.tray"/>&nbsp;</td>
						<td align="left" colspan="3">
							<spring:bind path="orderQuerier.tray">
								<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>">
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td align="left" width="25%" class="table8">
							&nbsp;<spring:message code="order.delivereddate"/>
							&nbsp;<spring:message code="range.from"/>
						</td>
						<td align="left" width="15%">
						<spring:bind path="orderQuerier.start_delivereddate">
							<input type="text" name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.displayValue}"/>"
								onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>					
						</spring:bind>
						</td>
						<td align="left" width="20%" class="table8">&nbsp;<spring:message code="range.to"/></td>
						<td colspan="3">
						<spring:bind path="orderQuerier.end_delivereddate">
							<input type="text" name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.displayValue}"/>"
								onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>
						</spring:bind>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table>
						<tr>
							<td>
								<input type="button" class="button" 
									onclick="javascript:window.location.href='<c:url value="${returnUrl}"/>'" 
									value="<spring:message code="operation.back"/>"/>
							</td>			
							<td align="center" colspan="2">
								<input type="submit" width="74" height="22" 
									align="absmiddle" class="button" value="<spring:message code="operation.queryExport"/>"/>
							</td>
						</tr>
					</table>
				</td>			
			</tr>
		</table>
	</form>

</body>
</html>
