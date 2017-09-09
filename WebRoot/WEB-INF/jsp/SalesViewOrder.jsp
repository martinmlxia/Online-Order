
<table width="95%" align="center" border="1" cellpadding="3"
	cellspacing="1" bordercolor="#2268A6" bordercolordark="ffffff">
	<tr>
		<td align="center" colspan="12">
			<b><spring:message code="order"/>#<c:out value="${order.id}" /></b>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="12">
			<b><spring:message code="order.clientdetails"/></b>
		</td>
	</tr>
	<tr>
		<td colspan="2"><spring:message code="order.clientname"/>:</td>
		<td colspan="4"><c:out value="${order.user.user_tag}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="order.telephone"/>:</td>
		<td colspan="4"><c:out value="${order.telephone}"/>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2"><spring:message code="order.patient"/>:</td>
		<td colspan="4"><c:out value="${order.patient}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="order.tray"/>:</td>
		<td colspan="4"><c:out value="${order.tray}"/>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2"><spring:message code="order.ordereddate"/>:</td>
		<td colspan="4">
			<fmt:formatDate value="${order.ordereddate}" pattern="yyyy/MM/dd HH:mm"/>&nbsp;
		</td>	
		<td colspan="2"><spring:message code="order.delivereddate"/>:</td>
		<td colspan="4">
			<c:choose>
			<c:when test="${order.processInstance.token < 3}">--</c:when>
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
	</tr>
	<tr>
		<td colspan="2"><spring:message code="order.ordertype"/>:</td>
		<td colspan="4">
			<spring:bind path="order.ordertype">
				<input type="hidden" name="<c:out value="${status.expression}"/>"
							value="<c:out value="${status.value}"/>"/>
				<c:out value="${status.value}"/>&nbsp;
			</spring:bind>
		</td>
		<td colspan="2"><spring:message code="order.orderstatus"/>:</td>
		<td colspan="2" class="rbbtd">
			<spring:message code="orderstatus.${order.processInstance.token}"/>&nbsp;
		</td>
		<td class="rbbtd">
			<spring:message code="lens.price"/>
		</td>
		<td class="rbbtd">
			${order.price }
		</td>		
	</tr>
	<c:if test="${!empty order.framedetail}">
		<tr>
			<td align="center" colspan="12">
				<b><spring:message code="order.framedetails"/></b>
			</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="frame.a"/>:</td>
			<td colspan="4"><fmt:formatNumber value="${order.framedetail.rboxasize}" pattern="####.####"/>&nbsp;</td>
			<td colspan="2"><spring:message code="frame.b"/>:</td>
			<td colspan="4"><fmt:formatNumber value="${order.framedetail.rboxbsize}" pattern="####.####"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="frame.ed"/>:</td>
			<td colspan="4"><fmt:formatNumber value="${order.framedetail.ed}" pattern="####.####"/>&nbsp;</td>
			<td colspan="2"><spring:message code="frame.dbl"/>:</td>
			<td colspan="4"><fmt:formatNumber value="${order.framedetail.dbl}" pattern="####.####"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="frame.style"/>:</td>
			<td colspan="10">
				<c:out value="${order.framedetail.framestyle}"/>&nbsp;
				<spring:message code="frametype.${order.framedetail.frametype}"/>&nbsp;	
				<spring:message code="framepattern.${order.framedetail.framepattern }" />&nbsp;			
			</td>
		</tr>		
	</c:if>
	<c:if test="${!empty order.lensdetail}">
		<tr>
			<td align="center" colspan="12">
				<b><spring:message code="order.lensdetails"/></b>
			</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="lens.material"/>:</td>
			<td colspan="4">
				<c:if test="${!empty order.lensdetail.material}">
					<spring:message code="lensmaterial.${order.lensdetail.material.id}"/>
				</c:if>&nbsp;
				<spring:message code="lensmaterial.${order.lensdetail.material2.id}"/>&nbsp;
			</td>
			<td colspan="2"><spring:message code="lens.type"/>:</td>
			<td colspan="4">
				<spring:message code="lenstype.${order.lensdetail.lenstype.id}"/>&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="lens.diameter"/>:</td>
			<td colspan="10">
				<c:out value="${order.lensdetail.diameter}"/>
				<spring:message code="diameterunit.${order.lensdetail.diameterUnit}"/>&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2" class="lrbbtd"><spring:message code="lens.treatment"/>:</td>
			<td colspan="10" class="rbbtd">
				&nbsp;<spring:message code="treatment.treat.${order.lensdetail.treat}"/>			
			    <c:if test="${order.lensdetail.uv}">&nbsp;<spring:message code="treatment.uv"/></c:if>
			</td>
		</tr>
		<tr>
			<td colspan="1"><spring:message code="lens.tint"/>:</td>
			<td colspan="2">
				<c:if test="${!empty order.lensdetail.tinttype}">
					<spring:message code="tint.${order.lensdetail.tinttype.id}"/>&nbsp;
				</c:if>&nbsp;
			</td>
			<td colspan="1"><spring:message code="lens.color"/>1:</td>
			<td colspan="2">
				<c:if test="${!empty order.lensdetail.tintcolor1}">
					<spring:message code="tintcolor.${order.lensdetail.tintcolor1.id}"/>
				</c:if>&nbsp;
			</td>
			<td colspan="1"><spring:message code="lens.color"/>2:</td>
			<td colspan="2">
				<c:if test="${!empty order.lensdetail.tintcolor2}">
					<spring:message code="tintcolor.${order.lensdetail.tintcolor2.id}"/>
				</c:if>&nbsp;
			</td>
			<td colspan="1"><spring:message code="lens.quantity"/>:</td>
			<td colspan="2"><c:out value="${order.lensdetail.quantity}"/>&nbsp;</td>
		</tr>
		<tr>
			<td align="center"><spring:message code="lens"/></td>
			<td align="center"><spring:message code="lens.sphere"/></td>
			<td align="center"><spring:message code="lens.cylinder"/></td>
			<td align="center"><spring:message code="lens.axis"/></td>
			<td align="center"><spring:message code="lens.add"/></td>
			<td align="center"><spring:message code="lens.farpd"/></td>
			<td align="center"><spring:message code="lens.nearpd"/></td>
			<td align="center"><spring:message code="lens.prism"/></td>
			<td align="center"><spring:message code="lens.prism.bshort"/></td>
			<td align="center"><spring:message code="lens.basecurve"/></td>
			<td align="center"><spring:message code="lens.seght"/></td>
			<td align="center"><spring:message code="lens.ochgt"/></td>
		</tr>
		<tr>
			<td><spring:message code="lens.right"/></td>
			<td align="center"><c:if test="${order.lensdetail.rsphere > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.rsphere}" pattern="0.00"/>&nbsp;</td>
			<td align="center"><c:if test="${order.lensdetail.rcylinder > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.rcylinder}" pattern="0.00"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.raxis}"/>&nbsp;</td>
			<td align="center"><c:if test="${order.lensdetail.radd > 0.0}">+</c:if><c:out value="${order.lensdetail.radd}"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.rfarpd}"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.rnearpd}"/>&nbsp;</td>
			<td align="center">
				<c:out value="${order.lensdetail.rhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.rhpd}"/>&nbsp;
			</td>
			<td align="center">
				<c:out value="${order.lensdetail.rbhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.rbhpd}"/>&nbsp;
			</td>
			<td align="center"><c:out value="${order.lensdetail.rbasecurve}"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.rsegheight}"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.rochgt}"/>&nbsp;</td>
		</tr>
		<tr>
			<td><spring:message code="lens.left"/></td>
			<td align="center"><c:if test="${order.lensdetail.lsphere > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.lsphere}" pattern="0.00"/>&nbsp;</td>
			<td align="center"><c:if test="${order.lensdetail.lcylinder > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.lcylinder}" pattern="0.00"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.laxis}"/>&nbsp;</td>
			<td align="center"><c:if test="${order.lensdetail.ladd > 0.0}">+</c:if><c:out value="${order.lensdetail.ladd}"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.lfarpd}"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.lnearpd}"/>&nbsp;</td>
			<td align="center">
				<c:out value="${order.lensdetail.lhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.lhpd}"/>&nbsp;
			</td>
			<td align="center">
				<c:out value="${order.lensdetail.lbhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.lbhpd}"/>&nbsp;
			</td>
			<td align="center"><c:out value="${order.lensdetail.lbasecurve}"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.lsegheight}"/>&nbsp;</td>
			<td align="center"><c:out value="${order.lensdetail.lochgt}"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="order.emergent"/>:</td>
			<td colspan="10">
				<c:if test="${order.emergent}"><spring:message code="boolean.true"/></c:if>
				<c:if test="${!order.emergent}"><spring:message code="boolean.false"/></c:if>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="12" class="lrbbtd">
				<b><spring:message code="lens.pow"/></b>
			</td>
		</tr>
		<tr>
		 <td colspan="2" class="lrbbtd"><spring:message code="lens.verdis"/></td>
		 <td colspan="2" class="rbbtd">${order.lensdetail.verdis}</td>
		 <td colspan="2" class="rbbtd"><spring:message code="lens.pantilt"/></td>
		 <td colspan="2" class="rbbtd">${order.lensdetail.pantilt}</td>
		 <td colspan="2" class="rbbtd"><spring:message code="lens.wrapangle"/></td>
		 <td colspan="2" class="rbbtd">${order.lensdetail.wrapangle}</td>
		</tr>
		<tr>
			<td align="center" colspan="12">
				<b><spring:message code="order.deliveryinfo"/></b>
			</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="order.couriers"/>:</td>
			<td colspan="4">
				<c:if test="${!empty order.couriers}">
					<a href="${order.couriers.trackingUrl}" target="_blank">
						<c:out value="${order.couriers.name}"/>
					</a>
				</c:if>&nbsp;
			</td>
			<td colspan="2"><spring:message code="order.stn"/>:</td>
			<td colspan="4">
				<c:if test="${!empty order.shipmentTrackingNumber}">
					<c:out value="${order.shipmentTrackingNumber}"/>
				</c:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="order.remarks"/>:</td>
			<td colspan="10"><c:out value="${order.remarks}"/>&nbsp;</td>
		</tr>
	</c:if>
</table>
