
<table width="100%" align="center" cellpadding="3" cellspacing="0" class="printtable2">
	<tr>
		<td align="center" colspan="12" class="onepxtd">
			<b><spring:message code="cvo.order"/>#<c:out value="${order.id}" />&nbsp;
			<c:if test="${order.emergent}">(<spring:message code="cvo.order.emergent"/>)</c:if></b>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="12" class="lrbbtd">
			<b><spring:message code="cvo.order.clientdetails"/></b>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="lrbbtd"><spring:message code="cvo.order.clientname"/>:</td>
		<td colspan="4" class="rbbtd"><c:out value="${order.user.user_tag}"/>&nbsp;</td>
		<td colspan="2" class="rbbtd"><spring:message code="cvo.order.telephone"/>:</td>
		<td colspan="4" class="rbbtd"><c:out value="${order.telephone}"/>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" class="lrbbtd"><spring:message code="cvo.order.patient"/>:</td>
		<td colspan="4" class="rbbtd"><c:out value="${order.patient}"/>&nbsp;</td>
		<td colspan="2" class="rbbtd"><spring:message code="cvo.order.tray"/>:</td>
		<td colspan="4" class="rbbtd"><c:out value="${order.tray}"/>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2" class="lrbbtd"><spring:message code="cvo.order.ordereddate"/>:</td>
		<td colspan="4" class="rbbtd">
			<fmt:formatDate value="${order.ordereddate}" pattern="yyyy/MM/dd HH:mm"/>&nbsp;
		</td>	
		<td colspan="2" class="rbbtd"><spring:message code="cvo.order.delivereddate"/>:</td>
		<td colspan="4" class="rbbtd">
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
		<td colspan="2" class="lrbbtd"><spring:message code="cvo.order.ordertype"/>:</td>
		<td colspan="4" class="rbbtd">
			<spring:message code="ordertype.${order.ordertype}"/>&nbsp;
		</td>
		<td colspan="2" class="rbbtd"><spring:message code="cvo.order.orderstatus"/>:</td>
		<c:if test="${lang.language=='zh' }">
		<td colspan="4" class="rbbtd">
			<spring:message code="orderstatus.${order.processInstance.token}"/>&nbsp;
		</td>
		</c:if>
		<c:if test="${lang.language!='zh' }">
		<td colspan="2" class="rbbtd">
			<spring:message code="orderstatus.${order.processInstance.token}"/>&nbsp;
		</td>
		<td class="rbbtd">
			<spring:message code="lens.price"/>
		</td>
		<td class="rbbtd">
			${order.price }
		</td>
		</c:if>
	</tr>
	<c:if test="${!empty order.framedetail}">
		<tr>
			<td align="center" colspan="12" class="lrbbtd">
				<b><spring:message code="cvo.order.framedetails"/></b>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="lrbbtd"><spring:message code="cvo.frame.a"/>:</td>
			<td colspan="1" class="rbbtd"><fmt:formatNumber value="${order.framedetail.rboxasize}" pattern="####.####"/>&nbsp;</td>
			<td colspan="2" class="rbbtd"><spring:message code="cvo.frame.b"/>:</td>
			<td colspan="1" class="rbbtd"><fmt:formatNumber value="${order.framedetail.rboxbsize}" pattern="####.####"/>&nbsp;</td>
			<td colspan="2" class="rbbtd"><spring:message code="cvo.frame.ed"/>:</td>
			<td colspan="1" class="rbbtd"><fmt:formatNumber value="${order.framedetail.ed}" pattern="####.####"/>&nbsp;</td>
			<td colspan="2" class="rbbtd"><spring:message code="cvo.frame.dbl"/>:</td>
			<td colspan="1" class="rbbtd"><fmt:formatNumber value="${order.framedetail.dbl}" pattern="####.####"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" class="lrbbtd"><spring:message code="cvo.frame.style"/>:</td>
			<td colspan="10" class="rbbtd">
				<c:out value="${order.framedetail.framestyle}"/>&nbsp;
				<spring:message code="frametype.${order.framedetail.frametype}"/>&nbsp;
				<spring:message code="framepattern.${order.framedetail.framepattern }" />&nbsp;
			</td>
		</tr>
	</c:if>
	<c:if test="${!empty order.lensdetail}">
		<tr>
			<td align="center" colspan="12" class="lrbbtd">
				<b><spring:message code="cvo.order.lensdetails"/></b>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="lrbbtd"><spring:message code="cvo.lens.material"/>:</td>
			<td colspan="4" class="rbbtd">
				<c:if test="${!empty order.lensdetail.material}">
					<spring:message code="lensmaterial.${order.lensdetail.material.id}"/>
				</c:if>&nbsp;
					<spring:message code="lensmaterial.${order.lensdetail.material2.id}"/>&nbsp;
			</td>
			<td colspan="2" class="rbbtd"><spring:message code="cvo.lens.type"/>:</td>
			<td colspan="4" class="rbbtd">
				<spring:message code="lenstype.${order.lensdetail.lenstype.id}"/>&nbsp;
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
			<td colspan="1" class="lrbbtd"><spring:message code="cvo.lens.tint"/>:</td>
			<td colspan="2" class="rbbtd">
				<c:if test="${!empty order.lensdetail.tinttype}">
					<spring:message code="tint.${order.lensdetail.tinttype.id}"/>
				</c:if>&nbsp;
			</td>
			<td colspan="1" class="rbbtd"><spring:message code="cvo.lens.color"/>1:</td>
			<td colspan="2" class="rbbtd">
				<c:if test="${!empty order.lensdetail.tintcolor1}">
					<spring:message code="tintcolor.${order.lensdetail.tintcolor1.id}"/>
				</c:if>&nbsp;
			</td>
			<td colspan="2" class="rbbtd"><spring:message code="cvo.lens.diameter"/>:</td>
			<td colspan="1" class="rbbtd">
				<c:out value="${order.lensdetail.diameter}"/>
				<spring:message code="diameterunit.${order.lensdetail.diameterUnit}"/>&nbsp;
			</td>
			<td colspan="2" class="rbbtd"><spring:message code="cvo.lens.quantity"/>:</td>
			<td colspan="1" class="rbbtd"><c:out value="${order.lensdetail.quantity}"/>&nbsp;</td>
		</tr>
		<tr>
			<td align="center" class="lrbbtd"><spring:message code="cvo.lens"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.sphere"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.cylinder"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.axis"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.add"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.farpd"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.nearpd"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.prism"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.bprism"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.basecurve"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.seght.short"/></td>
			<td align="center" class="rbbtd"><spring:message code="cvo.lens.ochgt.short"/></td>
		</tr>
		<tr>
			<td class="lrbbtd"><spring:message code="cvo.lens.right"/></td>
			<td align="center" class="rbbtd"><c:if test="${order.lensdetail.rsphere > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.rsphere}" pattern="0.00"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:if test="${order.lensdetail.rcylinder > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.rcylinder}" pattern="0.00"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.raxis}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:if test="${order.lensdetail.radd > 0.0}">+</c:if><c:out value="${order.lensdetail.radd}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.rfarpd}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.rnearpd}"/>&nbsp;</td>
			<td align="center" class="rbbtd">
				<c:out value="${order.lensdetail.rhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.rhpd}"/>&nbsp;
			</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.rbhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.rbhpd}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.rbasecurve}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.rsegheight}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.rochgt}"/>&nbsp;</td>
		</tr>
		<tr>
			<td class="lrbbtd"><spring:message code="cvo.lens.left"/></td>
			<td align="center" class="rbbtd"><c:if test="${order.lensdetail.lsphere > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.lsphere}" pattern="0.00"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:if test="${order.lensdetail.lcylinder > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.lcylinder}" pattern="0.00"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.laxis}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:if test="${order.lensdetail.ladd > 0.0}">+</c:if><c:out value="${order.lensdetail.ladd}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.lfarpd}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.lnearpd}"/>&nbsp;</td>
			<td align="center" class="rbbtd">
				<c:out value="${order.lensdetail.lhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.lhpd}"/>&nbsp;
			</td>
			<td align="center" class="rbbtd">
				<c:out value="${order.lensdetail.lbhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.lbhpd}"/>&nbsp;
			</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.lbasecurve}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.lsegheight}"/>&nbsp;</td>
			<td align="center" class="rbbtd"><c:out value="${order.lensdetail.lochgt}"/>&nbsp;</td>
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
			<td align="center" colspan="12" class="lrbbtd">
				<b><spring:message code="cvo.order.deliveryinfo"/></b>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="lrbbtd"><spring:message code="cvo.order.couriers"/>:</td>
			<td colspan="4" class="rbbtd">
				<c:if test="${!empty order.couriers}">
					<a href="${order.couriers.trackingUrl}" target="_blank">
						<c:out value="${order.couriers.name}"/>
					</a>
				</c:if>&nbsp;
			</td>
			<td colspan="2" class="rbbtd"><spring:message code="cvo.order.stn"/>:</td>
			<td colspan="4" class="rbbtd">
				<c:if test="${!empty order.shipmentTrackingNumber}">
					&nbsp;<c:out value="${order.shipmentTrackingNumber}"/>
				</c:if>&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2" class="lrbbtd"><spring:message code="cvo.order.remarks"/>:</td>
			<td colspan="10" class="rbbtd"><c:out value="${order.remarks}"/>&nbsp;</td>
		</tr>
	</c:if>
</table>
