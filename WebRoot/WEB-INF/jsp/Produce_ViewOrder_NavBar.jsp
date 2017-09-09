
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td align="center">
			<input type="button" class="button" 
				onclick="javascript:window.location.href='<c:url value="${returnUrl}"/>'" 
				value="<spring:message code="operation.back"/>"/>&nbsp;&nbsp;&nbsp;
			<input type="button" class="button" 
				onclick="javascript:window.location.href='<c:url value="/producePrintOrder.ord"><c:param name="orderId" value="${order.id}"/></c:url>'" 
				value="<spring:message code="operation.print"/>"/>
			<c:if test="${(order.processInstance.token >= 2) && (order.processInstance.token <= 3)}">
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="button" 
				onclick="javascript:window.location.href='<c:url value="/orderHandover.ord"><c:param name="processId" value="${order.processInstance.id}"/><c:param name="returnUrl" value="${returnUrl}"/></c:url>'" 
				value="<spring:message code="operation.handover"/>"/>
			</c:if>
		</td>
	</tr>
</table>
