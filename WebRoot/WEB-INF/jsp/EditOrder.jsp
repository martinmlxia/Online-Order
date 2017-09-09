
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td height="30" class=table1><img
			src="<c:url value="/resources/images/arrow.gif" />"
			width="20" height="18" hspace="4" align="absmiddle"><spring:message code="order.lensorder"/></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
		<spring:bind path="order.id">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
		</spring:bind>
		<spring:bind path="order.ordereddate">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
		</spring:bind>
		<spring:bind path="order.lensdetail.id">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
		</spring:bind>
		<spring:bind path="order.processInstance.id">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
		</spring:bind>
		<spring:bind path="order.processInstance.token">
			<input type="hidden" name="<c:out value="${status.expression}"/>"
				value="<c:out value="${status.value}"/>" />
		</spring:bind>
		<table width="93%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolor="#2268A6" bordercolordark="ffffff">
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="order.clientname"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.user.user_id">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}"/>" readonly />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind>
				</td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="order.telephone"/>&nbsp;</td>
				<td><spring:bind path="order.telephone">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.lenstype"/>&nbsp;</td>
				<td align="left" colspan="3">
				<spring:bind path="order.lensdetail.lensmodel.id">
					<select name="<c:out value="${status.expression}"/>">
						<c:forEach var="lens_model" items="${lensmodels}">
							<option
								<c:if test="${lens_model == status.value}">selected</c:if>
								value="<c:out value="${lens_model}"/>"><c:out
								value="${lens_model}" /></option>
						</c:forEach>
					</select>
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.diameter"/>&nbsp;</td>
				<td align="left" width="35%" colspan="3">
				<spring:bind path="order.lensdetail.diameter">
					<spring:transform value="${status.value}" var="diameter" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${diameter}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="frame.style"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.framestyle">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}"/>" />
				</spring:bind>
				</td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.treatment"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.rxtreatment">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}"/>" />
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.tint"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.tintlens">
					<select name="<c:out value="${status.expression}"/>">
						<c:forEach var="lens_tint" items="${tintcolors}">
							<option
								<c:if test="${lens_tint == status.value}">selected</c:if>
								value="<c:out value="${lens_tint}"/>"><c:out
								value="${lens_tint}" /></option>
						</c:forEach>
					</select>
				</spring:bind>
				</td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.quantity"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.quantity">
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.rsphere"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.rsphere">
					<spring:transform value="${status.value}" var="rsphere" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rsphere}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.lsphere"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.lsphere">
					<spring:transform value="${status.value}" var="lsphere" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lsphere}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.rcylinder"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.rcylinder">
					<spring:transform value="${status.value}" var="rcylinder" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rcylinder}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.lcylinder"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.lcylinder">
					<spring:transform value="${status.value}" var="lcylinder" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lcylinder}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.raxis"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.raxis">
					<spring:transform value="${status.value}" var="raxis" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${raxis}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.laxis"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.laxis">
					<spring:transform value="${status.value}" var="laxis" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${laxis}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.radd"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.radd">
					<spring:transform value="${status.value}" var="radd" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${radd}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.ladd"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind
					path="order.lensdetail.ladd">
					<spring:transform value="${status.value}" var="ladd" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${ladd}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.rprism"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.rhprism">
					<spring:transform value="${status.value}" var="rhprism" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rhprism}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.lprism"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.lhprism">
					<spring:transform value="${status.value}" var="lhprism" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lhprism}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" class="table8">&nbsp;<spring:message code="lens.rbasecurve"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.rbasecurve">
					<spring:transform value="${status.value}" var="rbasecurve" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rbasecurve}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
				<td align="left" class="table8">&nbsp;<spring:message code="lens.lbasecurve"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.lbasecurve">
					<spring:transform value="${status.value}" var="lbasecurve" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lbasecurve}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>						
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" class="table8">&nbsp;<spring:message code="frame.rpd"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.rpd">
					<spring:transform value="${status.value}" var="rpd" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rpd}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind>
				</td>
				<td align="left" class="table8">&nbsp;<spring:message code="frame.lpd"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.lpd">
					<spring:transform value="${status.value}" var="lpd" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lpd}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" class="table8">&nbsp;<spring:message code="frame.rmrp"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.rmrp">
					<spring:transform value="${status.value}" var="rmrp" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rmrp}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind>
				</td>
				<td align="left" class="table8">&nbsp;<spring:message code="frame.lmrp"/>&nbsp;</td>
				<td align="left" width="35%">
				<spring:bind path="order.lensdetail.lmrp">
					<spring:transform value="${status.value}" var="lmrp" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${lmrp}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind>
				</td>
			</tr>
			<tr>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.rdecentration"/>&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="order.lensdetail.rdecentration">
					<spring:transform value="${status.value}" var="rdecentration" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${rdecentration}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
				<td align="left" width="15%" class="table8">&nbsp;<spring:message code="lens.ldecentration"/>&nbsp;</td>
				<td align="left" width="35%"><spring:bind
					path="order.lensdetail.ldecentration">
					<spring:transform value="${status.value}" var="ldecentration" />
					<input type="text" name="<c:out value="${status.expression}"/>"
						value="<c:out value="${ldecentration}"/>" />
					<c:if test="${status.error}">
						<font class="red">${status.errorMessage}</font>
					</c:if>
				</spring:bind></td>
			</tr>			
			<tr>
				<td align="left" class="table8">&nbsp;<spring:message code="order.remarks"/>&nbsp;</td>
				<td align="left" colspan="3">
				<spring:bind path="order.remarks">
					<textarea cols="50" rows="3"
						name="<c:out value="${status.expression}"/>"><c:out value="${status.value}"/></textarea>
				</spring:bind>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td></td>
	</tr>
</table>
