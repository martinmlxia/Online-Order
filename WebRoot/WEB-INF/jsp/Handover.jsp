
<spring:bind path="handover.processInstance.id">
	<input type="hidden" name="<c:out value="${status.expression}"/>"
		value="<c:out value="${status.value}"/>" />
</spring:bind>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr class="table7">
		<td width="20%"><spring:message code="handover.prevnode"/></td>
		<td width="20%"><spring:message code="handover.currentnode"/></td>
		<td width="20%"><spring:message code="handover.nextnode"/></td>
	</tr>
	<tr class="table5">
		<td>
		<spring:bind path="handover.previousNode">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
			<c:out value="${status.value}" />
		</spring:bind>
		</td>
		<td>
		<spring:bind path="handover.currentNode">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
			<c:out value="${status.value}" />
		</spring:bind>
		</td>
		<td>
		<spring:bind path="handover.nextNode">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
			<c:out value="${status.value}" />
		</spring:bind>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center">
			<table border="1" width="75%" cellpadding="0" cellspacing="0" bordercolor="#2268A6" bordercolordark="ffffff">
				<c:forEach var="logItem" items="${handover.processInstance.processLogs}" varStatus="itemStatus">
					<c:if test="${itemStatus.index == 0}">
						<tr  class="table7">
							<td colspan="4" align="center" valign="middle">
								<spring:message code="handover.history"/>
							</td>
						</tr>
						<tr>
							<td><spring:message code="handover.time"/></td>
							<td><spring:message code="handover.node"/></td>
							<td><spring:message code="handover.user"/></td>
							<td><spring:message code="handover.message"/></td>
						</tr>
					</c:if>
					<tr>
						<td>
							<fmt:formatDate value="${logItem.processdate}" pattern="yyyy/MM/dd HH:mm" />
						</td>
						<td><spring:message code="orderstatus.${logItem.node}"/></td>
						<td><c:out value="${logItem.user}"/></td>
						<td><c:out value="${logItem.message}"/>&nbsp;</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="center">
			<spring:message code="handover.message"/>
			<spring:bind path="handover.message"><textarea cols="50" rows="3" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}"/></textarea></spring:bind>
		</td>
	</tr>
	<tr>
		<td align="center">
			<c:if test="${handover.currentNode > 1}">
				<input type="submit" class="button" name="prev" value="<spring:message code="handover.backward"/>" />
			</c:if>
		</td>
		<td>&nbsp;</td>
		<td align="center">
			<c:if test="${handover.currentNode < 5}">
				<input type="submit" class="button" name="next" value="<spring:message code="handover.forward"/>" />
			</c:if>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="3">
			<input type="button" class="button" name="back" value="<spring:message code="operation.back"/>" 
				onclick="javascript:location.href='<c:url value="${returnUrl}"/>'" />
		</td>
	</tr>
</table>
