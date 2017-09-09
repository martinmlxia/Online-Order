<c:if test="${!empty order.lensdetail.radd}">
   <table class="print_class">
      <tr>
        <td>${order.patient}&nbsp;</td>
        <td>${order.id}&nbsp;</td>
        <td colspan="2">R</td>
      </tr>
      <tr>
         <td width="25%">
              <c:if test="${!empty order.lensdetail.material}">
					<spring:message code="lensmaterial.${order.lensdetail.material.id}"/>
				</c:if>&nbsp;
				<spring:message code="lensmaterial.${order.lensdetail.material2.id}"/>&nbsp;
		</td>
         <td width="25%"><spring:message code="lenstype.${order.lensdetail.lenstype.id}"/></td>
         <td width="25%">
                <c:out value="${order.lensdetail.diameter}"/>
				<spring:message code="diameterunit.${order.lensdetail.diameterUnit}"/>&nbsp;
		</td>
         <td width="25%"><c:if test="${!empty order.lensdetail.rbasecurve}">${order.lensdetail.rbasecurve}</c:if></td>
      </tr>
      <tr>
          <td colspan="3" rowspan="3">
             <c:if test="${order.lensdetail.rsphere > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.rsphere}" pattern="0.00"/> - <c:if test="${order.lensdetail.rcylinder > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.rcylinder}" pattern="0.00"/> - <c:out value="${order.lensdetail.raxis}"/><br />
          </td>
          <td>
            <c:if test="${order.lensdetail.radd > 0.0}">+</c:if><c:out value="${order.lensdetail.radd}"/> &nbsp; 
        </td>
      </tr>
      <tr>
          <td>&nbsp;<spring:message code="treatment.treat.${order.lensdetail.treat}"/>			
			    <c:if test="${order.lensdetail.uv}">&nbsp;<spring:message code="treatment.uv"/></c:if></td>
      </tr>
      <tr>
          <td><fmt:formatDate value="${order.ordereddate}" pattern="yyyy/MM/dd HH:mm"/>&nbsp;</td>
      </tr>
      <tr>
          <td colspan="4"><c:out value="${order.remarks}"/>&nbsp;</td>
      </tr>
   </table>
 </c:if>
 
 <c:if test="${!empty order.lensdetail.ladd}">
 <br />
   <table class="print_class">
      <tr>
        <td>${order.patient}&nbsp;</td>
        <td>${order.id}&nbsp;</td>
        <td colspan="2">L</td>
      </tr>
      <tr>
         <td width="25%">
              <c:if test="${!empty order.lensdetail.material}">
					<spring:message code="lensmaterial.${order.lensdetail.material.id}"/>
				</c:if>&nbsp;
				<c:if test="${!empty order.lensdetail.material2}">
					<spring:message code="lensmaterial.${order.lensdetail.material2.id}"/>
				</c:if>&nbsp;
		</td>
         <td width="25%"><spring:message code="lenstype.${order.lensdetail.lenstype.id}"/></td>
         <td width="25%">
                <c:out value="${order.lensdetail.diameter}"/>
				<spring:message code="diameterunit.${order.lensdetail.diameterUnit}"/>&nbsp;
		</td>
         <td width="25%"><c:if test="${!empty order.lensdetail.lbasecurve}">${order.lensdetail.lbasecurve}</c:if></td>
      </tr>
      <tr>
          <td colspan="3" rowspan="3">
             <c:if test="${order.lensdetail.lsphere > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.lsphere}" pattern="0.00"/> - <c:if test="${order.lensdetail.lcylinder > 0.0}">+</c:if><fmt:formatNumber value="${order.lensdetail.lcylinder}" pattern="0.00"/> - <c:out value="${order.lensdetail.laxis}"/><br />
          </td>
          <td>
            <c:if test="${order.lensdetail.ladd > 0.0}">+</c:if><c:out value="${order.lensdetail.ladd}"/> &nbsp; 
        </td>
      </tr>
      <tr>
          <td>&nbsp;<spring:message code="treatment.treat.${order.lensdetail.treat}"/>			
			    <c:if test="${order.lensdetail.uv}">&nbsp;<spring:message code="treatment.uv"/></c:if></td>
      </tr>
      <tr>
          <td><fmt:formatDate value="${order.ordereddate}" pattern="yyyy/MM/dd HH:mm"/>&nbsp;</td>
      </tr>
      <tr>
          <td colspan="4"><c:out value="${order.remarks}"/>&nbsp;</td>
      </tr>
   </table>
 </c:if>