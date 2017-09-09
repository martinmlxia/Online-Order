
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="conant.ico"/>
<title>Query Orders</title>
<link href="<c:url value="/resources/style/style.css" />"
	type="text/css" rel="stylesheet" />
<script language="javascript"
	src="<c:url value="/resources/calendar/WdatePicker.js"/>"
	type="text/javascript">
</script>
<script language="javascript" type="text/javascript">
	// 1: batchDelete 
	// 2: batchCommit
	// 3: batchModify
	var operation = 0;
	
	function setOperation(target)
	{
		operation = target;
	}
	
	function checkAll(form, checked) 
	{
		for(var i = 0; i < form.elements.length; i++)
		{
			var e = form.elements[i];
			if(e.type == "checkbox" && e.name == "orderIds")
			{
				e.checked = checked;
			}
		}
	}

	function checkSelected()
	{
		var allcheck = document.getElementsByTagName("input");
		for(var i = 0; i < allcheck.length; i++)
		{
			var elmt = allcheck[i];
			if(elmt.type == "checkbox" && elmt.name != "selectAll" && elmt.checked)
			{
				var decision = true;
				if(operation == 1)
				{
					decision = window.confirm('<spring:message code="delete.confirm"/>');
				}
				return (decision == true);
			}
		}
		window.alert('<spring:message code="operation.select"/>');
		return false;
	}
</script>
</head>

<body>

<table width="99%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td class="table1">
			<img src="<c:url value="/resources/images/arrow.gif"/>"
				width="20" height="18" hspace="4" align="absmiddle">
			<spring:message code="order.list"/>
		</td>
	</tr>
	<tr>
		<td align="left">
		<form name="orderQuerier" id="orderQuerier" action="<c:url value="/myOrders.ord"/>" method="post">
		<table align="left" width="100%">
			<tr>
				<td class="table8">
				<table class="table9" width="100%" border="1"
					cellpadding="0" cellspacing="0" bordercolor="#2268A6"
					bordercolordark="ffffff">
					<tr>
						<td align="left" width="5%" class="table8">&nbsp;<spring:message code="order.orderstatus"/>&nbsp;</td>
						<td align="left" width="5%">
							<spring:bind path="orderQuerier.orderstatus">
							<select class="input"
								name='<c:out value="${status.expression}"/>'>
								<c:forEach var="order_status" items="${listOrderstatus}">
									<option
										<c:if test="${order_status == status.value}">selected</c:if>=""
										value='<c:out value="${order_status}"/>'><c:out
										value="${order_status}" /></option>
								</c:forEach>
							</select>
							</spring:bind>
						</td>
						<td align="left" width="5%" class="table8">&nbsp;<spring:message code="order.id"/>&nbsp;</td>
						<td align="left" width="5%">
						<spring:bind path="orderQuerier.id">
							<input type="text" name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.value}"/>'>
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>
						</spring:bind>
						</td>
					</tr>
					<tr>
						<td align="left" width="5%" class="table8">&nbsp;<spring:message code="order.ordereddate"/>&nbsp;<spring:message code="range.from"/></td>
						<td align="left" width="5%">
						<spring:bind path="orderQuerier.start_ordereddate">
							<input type="text" name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.displayValue}"/>'
								onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>
						</spring:bind>
						</td>
						<td align="left" width="5%" class="table8">&nbsp;<spring:message code="range.to"/>&nbsp;</td>
						<td>
						<spring:bind path="orderQuerier.end_ordereddate">
							<input type="text" name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.displayValue}"/>'
								onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>
						</spring:bind>
						</td>
					</tr>
					<tr>
						<td align="left" width="5%" class="table8">&nbsp;<spring:message code="order.patient"/>&nbsp;</td>
						<td align="left" width="5%">
							<spring:bind path="orderQuerier.patient">
								<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>">
							</spring:bind>
						</td>
						<td align="left" width="5%" class="table8">&nbsp;<spring:message code="order.tray"/>&nbsp;</td>
						<td align="left" width="5%">
						<spring:bind path="orderQuerier.tray">
							<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>">
						</spring:bind>
						</td>
					</tr>
					<tr>
						<td align="left" width="5%" class="table8">&nbsp;<spring:message code="order.delivereddate"/>&nbsp;<spring:message code="range.from"/></td>
						<td align="left" width="5%">
						<spring:bind path="orderQuerier.start_delivereddate">
							<input type="text" name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.displayValue}"/>'
								onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>
						</spring:bind>
						</td>
						<td align="left" width="5%" class="table8">&nbsp;<spring:message code="range.to"/>&nbsp;</td>
						<td>
						<spring:bind path="orderQuerier.end_delivereddate">
							<input type="text" name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.displayValue}"/>'
								onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							<c:if test="${status.error}">
								<font class="red">${status.errorMessage}</font>
							</c:if>
						</spring:bind>
						</td>
					</tr>
				</table>
				</td>
				<td>
				<table>
					<tr>
						<td>
						<input type="submit" width="74" height="22" 
							align="middle" class="button" value="<spring:message code="operation.query"/>">
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
	<tr>
		<td align="left">
		<form name="batchCommandForm" id="batchCommandForm"
			action="<c:url value="/myBatchCommand.ord"/>" method="post"
			onsubmit="return checkSelected()">
		<table width="99%" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="fy">
				<td width="60%" align="left" valign="middle">
					<label><input name="selectAll" type="checkbox" value="on" align="middle" onclick="javascript:checkAll(this.form, this.checked);"/><spring:message code="operation.selectAll"/></label>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.batchPrint"/>"/>
				</td>
				<td width="20%" align="center" valign="middle">
					<spring:message code="nav.records"/>:&nbsp;<c:out value="${orderQuerier.recordCount}"/>
					&nbsp;<spring:message code="nav.page"/>&nbsp;<c:out value="${orderQuerier.pageNo + 1}"/>/<c:out value="${orderQuerier.pageCount}"/>
				</td>
				<td width="20%" align="center">
				<a href="<c:url value="/myOrders.ord"><c:param name="pageNo" value="0"/></c:url>">
					<b><spring:message code="nav.first"/></b>
				</a>
				<a href="<c:url value="/myOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo - 1) > 0) ? (orderQuerier.pageNo - 1) : 0}"/></c:url>">
					<b><spring:message code="nav.prev"/></b>
				</a>
				<a href="<c:url value="/myOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo + 1) < (orderQuerier.pageCount - 1)) ? (orderQuerier.pageNo + 1) : (orderQuerier.pageCount - 1)}"/></c:url>">
					<b><spring:message code="nav.next"/></b>
				</a>
				<a href="<c:url value="/myOrders.ord"><c:param name="pageNo" value="${orderQuerier.pageCount - 1}"/></c:url>">
					<b><spring:message code="nav.last"/></b>
				</a>
				</td>
			</tr>
		</table>
		<table width="99%" border="1" cellpadding="0"
			cellspacing="0" bordercolor="#2268A6" bordercolordark="ffffff">
			<tr class=table7>
				<td width="3%">&nbsp;</td>
				<td width="8%"><spring:message code="order.id"/></td>
				<td width="7%"><spring:message code="order.patient"/></td>
				<td width="10%"><spring:message code="order.ordertype"/></td>
				<c:if test="${lang.language != 'zh'}">
				<td width="10%"><spring:message code="order.orderstatus"/></td>
				<td width="10%"><spring:message code="order.ordereddate"/></td>
				<td width="10%"><spring:message code="order.delivereddate"/></td>
				<td width="10%"><spring:message code="lens.price"/></td>
				<td width="10%"><spring:message code="order.invoiceid"/></td>
				</c:if>
				<c:if test="${lang.language == 'zh'}">
				<td width="15%"><spring:message code="order.orderstatus"/></td>
				<td width="15%"><spring:message code="order.ordereddate"/></td>
				<td width="15%"><spring:message code="order.delivereddate"/></td>
				</c:if>
				<td width="20%"><spring:message code="order.operation"/></td>
			</tr>
			<c:forEach var="order" items="${orderQuerier.listOrder}"
				varStatus="itemStatus">
				<c:choose>
					<c:when test="${(itemStatus.index % 2 == 0) && (order.processInstance.withdrawal == true)}">
						<tr class="table5r">
					</c:when>
					<c:when test="${(itemStatus.index % 2 == 0) && (order.processInstance.withdrawal == false)}">
						<tr class="table5">
					</c:when>
					<c:when test="${(itemStatus.index % 2 != 0) && (order.processInstance.withdrawal == true)}">
						<tr class="table3r">
					</c:when>
					<c:when test="${(itemStatus.index % 2 != 0) && (order.processInstance.withdrawal == false)}">
						<tr class="table3">
					</c:when>
				</c:choose>
				<td>
					<input name="orderIds" type="checkbox" value="<c:out value="${order.id}"/>"/>
				</td>
				<td>
					<c:out value="${order.id}"/>
				</td>
				<td>
				   ${order.patient}
				</td>
				<td>
					<spring:bind path="orderQuerier.listOrder[${itemStatus.index}].ordertype">
						<input type="hidden" name="<c:out value="${status.expression}"/>"
							value="<c:out value="${status.value}"/>"/>
						<c:out value="${status.value}"/>
					</spring:bind>
				</td>
				<td>
					<spring:bind path="orderQuerier.listOrder[${itemStatus.index}].processInstance.token">
						<input type="hidden" name="<c:out value="${status.expression}"/>"
							value="<c:out value="${status.value}"/>"/>
						<c:out value="${status.value}"/>
					</spring:bind>
				</td>				
				<td>
					<fmt:formatDate	value="${order.ordereddate}" pattern="yyyy/MM/dd HH:mm"/>
				</td>
				<td>
					<c:choose>
					<c:when test="${order.processInstance.token < 3}">
						--
					</c:when>
					<c:otherwise>
						<c:forEach var="node" items="${order.processInstance.nodeInstances}" varStatus="nodeStatus">
							<c:if test="${nodeStatus.index == 2}">
								<fmt:formatDate value="${node.leavedate}"
								pattern="yyyy/MM/dd HH:mm"/>&nbsp;
							</c:if>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</td>
				<c:if test="${lang.language != 'zh'}">
				<td>
				  ${order.price }
				</td>
				<td>
				  ${fn:replace(order.invoiceid, "¡ù", "")}
				</td>
				</c:if>
				<td>
				<a href="<c:url value="/myViewOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>">
					<b><spring:message code="operation.view"/></b>
				</a>
				<c:if test="${order.processInstance.token < 2}">&nbsp;
				<a href="<c:url value="/myModifyFrameLensOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>">
					<b><spring:message code="operation.modify"/></b>
				</a>
				</c:if>
				&nbsp;<a href="<c:url value="/myViewOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>">
					<b><spring:message code="operation.print"/></b>
				</a>
				</td>
				</tr>
			</c:forEach>
		</table>
		<table width="100%" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="fy">
				<td width="60%" align="left" valign="middle">
					<label><input name="selectAll" type="checkbox" value="on" align="middle" onclick="javascript:checkAll(this.form, this.checked);"/><spring:message code="operation.selectAll"/></label>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.batchPrint"/>"/>
					&nbsp;<input type="button" class="button" onclick="javascript:window.location.href='<c:url value="/myExportOrders.ord"/>'" value="<spring:message code="operation.export"/>"/>
				</td>
				<td width="20%" align="center" valign="middle">
					<spring:message code="nav.records"/>:&nbsp;<c:out value="${orderQuerier.recordCount}"/>
					&nbsp;<spring:message code="nav.page"/>&nbsp;<c:out value="${orderQuerier.pageNo + 1}"/>/<c:out value="${orderQuerier.pageCount}"/>
				</td>
				<td width="20%" align="center">
				<a href="<c:url value="/myOrders.ord"><c:param name="pageNo" value="0"/></c:url>">
					<b><spring:message code="nav.first"/></b>
				</a>
				<a href="<c:url value="/myOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo - 1) > 0) ? (orderQuerier.pageNo - 1) : 0}"/></c:url>">
					<b><spring:message code="nav.prev"/></b>
				</a>
				<a href="<c:url value="/myOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo + 1) < (orderQuerier.pageCount - 1)) ? (orderQuerier.pageNo + 1) : (orderQuerier.pageCount - 1)}"/></c:url>">
					<b><spring:message code="nav.next"/></b>
				</a>
				<a href="<c:url value="/myOrders.ord"><c:param name="pageNo" value="${orderQuerier.pageCount - 1}"/></c:url>">
					<b><spring:message code="nav.last"/></b>
				</a>
				</td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>
</body>

</html>
