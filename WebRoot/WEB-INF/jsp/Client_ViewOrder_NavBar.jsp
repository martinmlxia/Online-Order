
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td align="center">
			<input type="button" class="button" 
				onclick="javascript:window.location.href='<c:url value="${returnUrl}"/>'" 
				value="<spring:message code="operation.back"/>"/>
			&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" name="orderprint"
				onclick="printOrder();" value="<spring:message code="order.print"/>"/>
			&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" name="dblorderprint"
				onclick="printDoubleOrder();" value="<spring:message code="order.printdouble"/>"/>			
		</td>
	</tr>
</table>
