
<table width="100%" cellpadding="3" cellspacing="0" class="printtable" name="ordertable" id="${order.id}" <c:if test="${pageContext.request.locale.language != 'zh'}">style="font-style:normal;font-size:12px;"</c:if>>
	<tr>
		<td colspan="8" style="padding:0">
			<table width="100%" height="100%" cellspacing="0" cellpadding="0">
				<tr>
				<td align="left" valign="middle" class="ltbbtd">
					&nbsp;<c:if test="${!empty order.patient }">
				  	  <spring:message code="pvo.order.patient"/>:${order.patient }
					</c:if>
				</td>

				
				<td align="center" class="trbbtd">
				<img src="<c:url value="/barcode"><c:param name="data" value="${order.id}"/><c:param name="type" value="Code39"/><c:param name="drawText" value="true"/></c:url>"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="lens.rx"/><spring:message code="order"/>&nbsp;&nbsp;&nbsp;</td>
				<c:if test="${order.emergent}">
				<td align="center"  class="trbbtd">
				      <font size="4"><b><spring:message code="order.emergent.short"/></b></font>
				</td>
				</c:if>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="1" class="lrbbtd">
			<spring:message code="order.ordereddate"/>
		</td>
		<td colspan="1" class="rbbtd">
			<fmt:formatDate value="${order.ordereddate}" pattern="yyyy/MM/dd"/>&nbsp;
		</td>
		<td colspan="1" class="rbbtd">
			<spring:message code="lens.material"/>
		</td>
		<td colspan="1" class="rbbtd">
			<c:if test="${!empty order.lensdetail.material}">
				<spring:message code="lensmaterial.${order.lensdetail.material.id}"/>
			</c:if>&nbsp;
				<spring:message code="lensmaterial.${order.lensdetail.material2.id}"/>&nbsp;
		</td>
		<td colspan="1" class="rbbtd">
			<spring:message code="lens.lenstype"/>
		</td>
		<td colspan="3" class="rbbtd">
			<spring:message code="lenstype.${order.lensdetail.lenstype.id}"/>&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="1" class="lrbbtd">
			<spring:message code="order.orderNo"/>
		</td>
		<c:choose>
		<c:when test="${!empty order.lensdetail.quantity }">
		<td colspan="3" class="rbbtd">
			<c:out value="${order.id}"/>
		</td>
		<td colspan="1" class="rbbtd">
		    <spring:message code="lens.quantity"/>
		</td>
		<td colspan="3" class="rbbtd">
			<c:out value="${order.lensdetail.quantity}"/>&nbsp;
		</td>
		</c:when>
		<c:otherwise>
		  <td colspan="7" class="rbbtd">
			<c:out value="${order.id}"/>
		</td>
		</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td colspan="1" class="lrbbtd">
			<spring:message code="order.clientname"/>
		</td>
		<td colspan="1" class="rbbtd">
			<c:out value="${order.user.user_tag}"/>&nbsp;
		</td>
		<td colspan="1" class="rbbtd">
			<spring:message code="order.tray"/>
		</td>
		<c:choose>
		<c:when test="${!empty order.lensdetail.tinttype.id&&!empty order.lensdetail.tintcolor1.id}">
		<td colspan="1" class="rbbtd">
			<c:out value="${order.tray}"/>&nbsp;
		</td>
		<td colspan="1" class="rbbtd">
			<spring:message code="lens.tint.print"/>
		</td>
		<td colspan="3" class="rbbtd" style="font-size:9px;">
		<c:if test="${!empty order.lensdetail.tinttype.id }">
			<spring:message code="tint"/>:
			<spring:message code="tint.${order.lensdetail.tinttype.id}"/>&nbsp;
		</c:if>
		<c:if test="${!empty order.lensdetail.tintcolor1.id}">
			<spring:message code="lens.color"/>:
			<spring:message code="tintcolor.${order.lensdetail.tintcolor1.id}"/>&nbsp;
		</c:if>
		</td>
		</c:when>
		<c:otherwise>
		   <td colspan="5" class="rbbtd">
			<c:out value="${order.tray}"/>&nbsp;
	   	   </td>
		</c:otherwise>
		</c:choose>
	</tr>
		<tr>
		 <td colspan="1" class="lrbbtd" style="font-size:9px;"><spring:message code="lens.verdis"/></td>
		 <td colspan="1" class="rbbtd">&nbsp;${order.lensdetail.verdis}</td>
		 <td colspan="1" class="rbbtd" style="font-size:9px;"><spring:message code="lens.pantilt"/></td>
		 <td colspan="1" class="rbbtd">&nbsp;${order.lensdetail.pantilt}</td>
		 <td colspan="1" class="rbbtd" style="font-size:9px;"><spring:message code="lens.wrapangle"/></td>
		 <td colspan="3" class="rbbtd">&nbsp;${order.lensdetail.wrapangle}</td>
		</tr>
	<tr>
		<td colspan="1" class="lrbbtd">
			<spring:message code="lens.treatment"/>
		</td>
		<td colspan="7" class="rbbtd">
			&nbsp;<spring:message code="treatment.treat.${order.lensdetail.treat}"/>			
			<c:if test="${order.lensdetail.uv}">&nbsp;<spring:message code="treatment.uv"/></c:if>
		</td>
	</tr>
	<tr>
		<td colspan="1" class="lrbbtd">
			<spring:message code="order.framedetails"/>
		</td>
		<td colspan="7" class="rbbtd">
			<spring:message code="frame.style"/>:
			<c:out value="${order.framedetail.framestyle}"/>&nbsp;<spring:message code="frametype.${order.framedetail.frametype}"/>&nbsp;<spring:message code="framepattern.${order.framedetail.framepattern }" />&nbsp;
			<c:if test="${!empty order.framedetail.rboxasize}">
			   <spring:message code="frame.a"/>:
			   <fmt:formatNumber value="${order.framedetail.rboxasize}" pattern="####.####"/>&nbsp;
			</c:if>
			<c:if test="${!empty order.framedetail.rboxbsize}">
			   <spring:message code="frame.b"/>:
			   <fmt:formatNumber value="${order.framedetail.rboxbsize}" pattern="####.####"/>&nbsp;
			</c:if>
			<c:if test="${!empty order.framedetail.ed}">
			   <spring:message code="frame.ed"/>:
			   <fmt:formatNumber value="${order.framedetail.ed}" pattern="####.####"/>&nbsp;
			</c:if>
			<c:if test="${!empty order.framedetail.dbl}">
			   <spring:message code="frame.dbl"/>:
			   <fmt:formatNumber value="${order.framedetail.dbl}" pattern="####.####"/>
			</c:if>
		</td>
	</tr>
	<tr>
	<td colspan="8" style="padding:0">
		<table class="printtable1" width="100%" height="100%" cellspacing="0" cellpadding="0" <c:if test="${pageContext.request.locale.language != 'zh'}">style="font-size:14px;"</c:if>>
		<tr height="30px">
		<td class="lrbbtd" width="50px"><spring:message code="lens"/></td>
		<td class="rbbtd"><spring:message code="lens.diameter"/></td>
		<td class="rbbtd"><spring:message code="lens.sphere"/></td>
		<td class="rbbtd"><spring:message code="lens.cylinder"/></td>
		<td class="rbbtd"><spring:message code="lens.axis"/></td>
		<td class="rbbtd"><spring:message code="lens.add"/></td>
		<td class="rbbtd"><spring:message code="lens.farpd"/></td>
		<td class="rbbtd"><spring:message code="lens.nearpd"/></td>
		<td class="rbbtd"><spring:message code="lens.prism"/></td>
		<td class="rbbtd"><spring:message code="lens.prism.bshort"/></td>
		<td class="rbbtd"><spring:message code="lens.basecurve"/></td>
		<td class="rbbtd"><spring:message code="lens.seght.print"/></td>
		<td class="rbbtd"><spring:message code="lens.ochgt.print"/></td>
		</tr>
		<tr height="30px">
		<td class="lrbbtd" width="50px"><spring:message code="lens.right"/></td>
		<td class="rbbtd">
			<c:out value="${order.lensdetail.diameter}"/>
			<spring:message code="diameterunit.${order.lensdetail.diameterUnit}"/>&nbsp;
		</td>
		<td class="rbbtd"><c:if test="${order.lensdetail.rsphere > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.rsphere}" pattern="0.00"/>&nbsp;</td>
		<td class="rbbtd"><c:if test="${order.lensdetail.rcylinder > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.rcylinder}" pattern="0.00"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.raxis}"/>&nbsp;</td>
		<td class="rbbtd"><c:if test="${order.lensdetail.radd > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.radd}" pattern="0.00"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.rfarpd}"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.rnearpd}"/>&nbsp;</td>
		<td class="rbbtd">
			<c:out value="${order.lensdetail.rhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.rhpd}"/>&nbsp;
		</td>
		<td class="rbbtd">
			<c:out value="${order.lensdetail.rbhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.rbhpd}"/>&nbsp;
		</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.rbasecurve}"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.rsegheight}"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.rochgt}"/>&nbsp;</td>
		</tr>
		<tr height="30px">
		<td class="lrbbtd" width="50px"><spring:message code="lens.left"/></td>
		<td class="rbbtd">
			<c:out value="${order.lensdetail.diameter}"/>
			<c:if test="${!empty order.lensdetail.diameterUnit}">
			<spring:message code="diameterunit.${order.lensdetail.diameterUnit}"/>
			</c:if>&nbsp;
		</td>
		<td class="rbbtd"><c:if test="${order.lensdetail.lsphere > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.lsphere}" pattern="0.00"/>&nbsp;</td>
		<td class="rbbtd"><c:if test="${order.lensdetail.lcylinder > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.lcylinder}" pattern="0.00"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.laxis}"/>&nbsp;</td>
		<td class="rbbtd"><c:if test="${order.lensdetail.ladd > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.ladd}" pattern="0.00"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.lfarpd}"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.lnearpd}"/>&nbsp;</td>
		<td class="rbbtd">
			<c:out value="${order.lensdetail.lhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.lhpd}"/>&nbsp;
		</td>
		<td class="rbbtd">
			<c:out value="${order.lensdetail.lbhprism}"/><spring:message code="prism.direction.short.${order.lensdetail.lbhpd}"/>&nbsp;
		</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.lbasecurve}"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.lsegheight}"/>&nbsp;</td>
		<td class="rbbtd"><c:out value="${order.lensdetail.lochgt}"/>&nbsp;</td>
		</tr>
	</table>
	</td>
	</tr>
	<tr>
		<td colspan="8" style="padding:0" height="30px">
			<table class="printtable" width="100%" cellspacing="0" cellpadding="0" height="100%">
			<tr>
				<td width="103px" class="lrbbtd"><spring:message code="order.remarks"/></td>
				<td width="350px" align="left" class="rbbtd"><c:out value="${order.remarks}"/>&nbsp;</td>
				<td width="56px" class="rbbtd"><spring:message code="order.tray"/></td>
			    <td align="left" class="rbbtd" style="padding:2px 20px;">
			     	<c:if test="${!empty order.tray }">				
				    <img src="<c:url value="/barcode"><c:param name="data" value="${order.tray}"/><c:param name="type" value="Code39"/><c:param name="drawText" value="true"/></c:url>"/>
				   </c:if>					
		         </td>
			</tr>
			</table>
		</td>
	</tr>
</table>
