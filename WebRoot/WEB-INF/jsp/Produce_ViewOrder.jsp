
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<title>view order</title>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css"
	rel="stylesheet" />
</head>
<body>

<%@ include file="Produce_ViewOrder_NavBar.jsp"%>
<br/>
<%@ include file="ProduceViewOrder.jsp"%>
<br/>
<table width="95%" align="center" border="1" cellpadding="3"
	cellspacing="1" bordercolor="#2268A6" bordercolordark="ffffff">
	<c:forEach var="logItem" items="${order.processInstance.processLogs}" varStatus="itemStatus">
		<c:if test="${itemStatus.index == 0}">
			<tr  class="table7">
				<td colspan="4" align="center" valign="middle">
					<spring:message code="handover.history"/>
				</td>
			</tr>
			<tr>
				<td align="center"><spring:message code="handover.time"/></td>
				<td align="center"><spring:message code="handover.node"/></td>
				<td align="center"><spring:message code="handover.user"/></td>
				<td align="center"><spring:message code="handover.message"/></td>
			</tr>
		</c:if>
		<tr>
			<td align="center">
				<fmt:formatDate value="${logItem.processdate}" pattern="yyyy/MM/dd HH:mm" />
			</td>
			<td align="center"><spring:message code="orderstatus.${logItem.node}"/></td>
			<td align="center"><c:out value="${logItem.user}"/></td>
			<td align="center"><c:out value="${logItem.message}"/>&nbsp;</td>
		</tr>
	</c:forEach>
</table>
<br/>
<%@ include file="Produce_ViewOrder_NavBar.jsp"%>

</body>
</html>
