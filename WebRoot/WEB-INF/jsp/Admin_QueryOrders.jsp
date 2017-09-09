
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
			var elmt = form.elements[i];
			if(elmt.type == "checkbox" && elmt.name != "selectAll")
			{
				elmt.checked = checked;
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
				var decision = false;
				if(operation == 1)
				{
					decision = window.confirm('<spring:message code="delete.confirm"/>');
				}
				else if(operation == 2)
				{
					decision = window.confirm('<spring:message code="commit.confirm"/>');
				}
				else if(operation == 3)
				{
					decision = window.confirm('<spring:message code="modify.confirm"/>');
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

<table width="99%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td class="table1">
			<img src="<c:url value="/resources/images/arrow.gif"/>"
				width="20" height="18" hspace="4" align="absmiddle">
			<spring:message code="order.list"/>
		</td>
	</tr>
	<tr>
		<td>
			<form name="orderQuerier" id="orderQuerier"
				action="<c:url value="/adminOrders.ord"/>" method="post">
				<table width="100%" align="left">
					<tr>
						<td>
						<table class="table9" width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolor="#2268A6" bordercolordark="ffffff">
							<tr>
								<td align="left" width="25%" class="table8">
									&nbsp;<spring:message code="order.orderstatus"/>
								</td>
								<td align="left" width="10%">
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
								<td align="left" width="25%" class="table8">
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
								<td align="left" width="10%">
									<spring:bind path="orderQuerier.start_ordereddate">
										<input type="text" name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.displayValue}"/>"
											onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
										<c:if test="${status.error}">
											<font class="red">${status.errorMessage}</font>
										</c:if>					
									</spring:bind>
								</td>
								<td align="left" width="25%" class="table8">&nbsp;<spring:message code="range.to"/></td>
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
								<td align="left" width="10%">
									<spring:bind path="orderQuerier.start_delivereddate">
										<input type="text" name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.displayValue}"/>"
											onFocus="WdatePicker({lang:'<spring:message code="calendar.lang"/>',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
										<c:if test="${status.error}">
											<font class="red">${status.errorMessage}</font>
										</c:if>					
									</spring:bind>
								</td>
								<td align="left" width="25%" class="table8">&nbsp;<spring:message code="range.to"/></td>
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
						<td align="center" colspan="2">
							<input type="submit" width="74" height="22" 
								align="absmiddle" class="button" value="<spring:message code="operation.query"/>"/>
						</td>						
					</tr>				
				</table>
			</form>
		</td>
	</tr>
	<tr>
		<td>
		<form name="batchCommandForm" id="batchCommandForm"
			action="<c:url value="/adminBatchCommand.ord"/>" method="post"
			onsubmit="return checkSelected()">
		<table width="99%" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="fy">
				<td width="60%" align="left">
					&nbsp;<label><input name="selectAll" type="checkbox" value="on" onclick="checkAll(this.form, this.checked)"/><spring:message code="operation.selectAll"/></label>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.batchDelete"/>" onclick="setOperation(1)"/>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.batchCommit"/>" onclick="setOperation(2)"/>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.batchModify"/>" onclick="setOperation(3)"/>
					&nbsp;<input type="button" class="button" onclick="javascript:window.location.href='<c:url value="/adminExportOrders.ord"/>'" 
						value="<spring:message code="operation.export"/>"/>
				</td>
				<td width="20%" align="left" valign="middle">
					<spring:message code="nav.records"/>:&nbsp;<c:out value="${orderQuerier.recordCount}"/>
					&nbsp;<spring:message code="nav.current"/>&nbsp;<c:out value="${orderQuerier.pageNo + 1}"/>/<c:out value="${orderQuerier.pageCount}"/>&nbsp;<spring:message code="nav.page"/>
				</td>
				<td width="20%" align="center">
					<a href="<c:url value="/adminOrders.ord"><c:param name="pageNo" value="0"/></c:url>">
						<b><spring:message code="nav.first"/></b>
					</a>
					<a href="<c:url value="/adminOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo - 1) > 0) ? (orderQuerier.pageNo - 1) : 0}"/></c:url>">
						<b><spring:message code="nav.prev"/></b>
					</a>
					<a href="<c:url value="/adminOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo + 1) < (orderQuerier.pageCount - 1)) ? (orderQuerier.pageNo + 1) : (orderQuerier.pageCount - 1)}"/></c:url>">
						<b><spring:message code="nav.next"/></b>
					</a>
					<a href="<c:url value="/adminOrders.ord"><c:param name="pageNo" value="${orderQuerier.pageCount - 1}"/></c:url>">
						<b><spring:message code="nav.last"/></b>
					</a>
				</td>
			</tr>
		</table>
		<table width="99%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolor="#2268A6" bordercolordark="ffffff">
			<tr class="table7">
				<td width="3%">&nbsp;</td>
				<td width="10%"><spring:message code="order.id"/></td>
				<td width="10%"><spring:message code="order.clientname"/></td>
				<td width="10%"><spring:message code="order.ordertype"/></td>
				<td width="10%"><spring:message code="order.orderstatus"/></td>
				<td width="15%"><spring:message code="order.ordereddate"/></td>
				<td width="15%"><spring:message code="order.delivereddate"/></td>
				<td width="20%"><spring:message code="order.operation"/></td>
			</tr>
			<c:forEach var="order" items="${orderQuerier.listOrder}" varStatus="itemStatus">
				<c:choose>
					<c:when
						test="${itemStatus.index % 2 == 0}">
							<c:choose>
								<c:when test="${order.processInstance.withdrawal}">
									<tr class="table5r">
								</c:when>
								<c:otherwise> 
									<c:if test="${order.emergent}">
										<tr class="table5b">
									</c:if>
									<c:if test="${!order.emergent}">
										<tr class="table5">
									</c:if>
								</c:otherwise>
							</c:choose>
					</c:when>
					<c:when
						test="${itemStatus.index % 2 != 0}">
							<c:choose>
								<c:when test="${order.processInstance.withdrawal}">
									<tr class="table3r">
								</c:when>
								<c:otherwise> 
									<c:if test="${order.emergent}">
										<tr class="table3b">
									</c:if>
									<c:if test="${!order.emergent}">
										<tr class="table3">
									</c:if>
								</c:otherwise>
							</c:choose>
					</c:when>
				</c:choose>
				<td>
					<input name="orderIds" type="checkbox" value="<c:out value="${order.id}"/>"/>
				</td>
				<td><c:out value="${order.id}"/></td>
				<td><c:out value="${order.user.user_name}"/></td>
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
					<fmt:formatDate value="${order.ordereddate}"
					pattern="yyyy/MM/dd HH:mm" />
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
								pattern="yyyy/MM/dd HH:mm" />&nbsp;
							</c:if>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="<c:url value="/adminViewOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>">
					<b><spring:message code="operation.view"/></b>
					</a>
					<c:if test="${order.processInstance.token < 2}">
					&nbsp;<a href="<c:url value="/adminModifyFrameLensOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>">
					<b><spring:message code="operation.modify"/></b>
					</a>
					</c:if>
					<c:if test="${(order.processInstance.token < 2) || (order.processInstance.token > 3)}">&nbsp;
					<a href="<c:url value="/orderHandover.ord"><c:param name="processId" value="${order.processInstance.id}"/><c:param name="returnUrl" value="${returnUrl}"/></c:url>">
					<b><spring:message code="operation.handle"/></b>
					</a>
					</c:if>
				</td>
				</tr>
			</c:forEach>
		</table>
		<table width="99%" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="fy">
				<td width="60%" align="left">
					&nbsp;<label><input name="selectAll" type="checkbox" value="on" onclick="checkAll(this.form, this.checked)"/><spring:message code="operation.selectAll"/></label>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.batchDelete"/>" onclick="setOperation(1)"/>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.batchCommit"/>" onclick="setOperation(2)"/>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.batchModify"/>" onclick="setOperation(3)"/>
					&nbsp;<input type="button" class="button" onclick="javascript:window.location.href='<c:url value="/adminExportOrders.ord"/>'" 
						value="<spring:message code="operation.export"/>"/>
				</td>
				<td width="20%" align="left" valign="middle">
					<spring:message code="nav.records"/>:&nbsp;<c:out value="${orderQuerier.recordCount}"/>
					&nbsp;<spring:message code="nav.current"/>&nbsp;<c:out value="${orderQuerier.pageNo + 1}"/>/<c:out value="${orderQuerier.pageCount}"/>&nbsp;<spring:message code="nav.page"/>
				</td>
				<td width="20%" align="center">
					<a href="<c:url value="/adminOrders.ord"><c:param name="pageNo" value="0"/></c:url>">
						<b><spring:message code="nav.first"/></b>
					</a>
					<a href="<c:url value="/adminOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo - 1) > 0) ? (orderQuerier.pageNo - 1) : 0}"/></c:url>">
						<b><spring:message code="nav.prev"/></b>
					</a>
					<a href="<c:url value="/adminOrders.ord"><c:param name="pageNo" value="${((orderQuerier.pageNo + 1) < (orderQuerier.pageCount - 1)) ? (orderQuerier.pageNo + 1) : (orderQuerier.pageCount - 1)}"/></c:url>">
						<b><spring:message code="nav.next"/></b>
					</a>
					<a href="<c:url value="/adminOrders.ord"><c:param name="pageNo" value="${orderQuerier.pageCount - 1}"/></c:url>">
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
