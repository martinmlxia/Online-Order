
<spring:bind path="order.*">
	<c:forEach items="${status.errorMessages}" var="error" varStatus="itemStatus">
		<font color="ff0000" size="2"><b><c:out value="${error}"/></b></font><c:if test="${!itemStatus.last}"><br></c:if>
	</c:forEach>
</spring:bind>

<spring:bind path="order.id"><input name="<c:out value="${status.expression}"/>" type="hidden" value="<c:out value="${status.value}"/>"></spring:bind>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="700">
	<tr>
		<td colspan="8" valign="middle"><font size="4"><b><spring:message code="order.patientinfo"/></b></font></td>
	</tr>
	<tr>
		<td colspan="8" height="0"><hr></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3" align="center" valign="middle"><spring:message code="order.patient"/>
			<spring:bind path="order.patient"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="1" size="20"></spring:bind>
		</td>
		<td colspan="3" align="left" valign="middle"><spring:message code="order.tray"/>
			<spring:bind path="order.tray"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="1" size="10"></spring:bind>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="8" height="0">
		</td>
	</tr>
	<tr>
		<td colspan="8" valign="middle"><font size="4"><b><spring:message code="frame"/></b></font></td>
	</tr>
	<tr>
		<td colspan="8" height="0">
		<hr>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	    <td align="center"><font size=2><b><spring:message code="frame.a"/></b></font></td>
	    <td align="center"><font size=2><b><spring:message code="frame.b"/></b></font></td>
	    <td align="center"><font size=2><b><spring:message code="frame.ed"/></b></font></td>
	    <td align="center"><font size=2><b><spring:message code="frame.dbl"/></b></font></td>
	    <td>&nbsp;</td>
	    <td align="left" colspan="2"><font size=2><b><spring:message code="lens.diameter.full"/></b></font></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><spring:bind path="order.rboxasize"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="2" size="10"></spring:bind></td>
		<td align="center"><spring:bind path="order.rboxbsize"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="2" size="10"></spring:bind></td>
		<td align="center"><spring:bind path="order.ed"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="2" size="10"></spring:bind></td>
		<td align="center"><spring:bind path="order.dbl"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="2" size="10"></spring:bind></td>
	    <td align="center"><font size=2><b><spring:message code="select.or"/></b></font></td>
	    <td align="left" colspan="2">
	    	<spring:bind path="order.diameter">
	    		<input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="2" size="10">
	    	</spring:bind>
	    	<c:if test="${lang.language == 'zh'}">
	    	<spring:bind path="order.diameterUnit">
				<select class="input" name='<c:out value="${status.expression}"/>'>
					<c:forEach var="diameter_unit" items="${listDiameterUnit}">
						<option
							<c:if test="${diameter_unit == status.value}">selected</c:if>
							value='<c:out value="${diameter_unit}"/>'><c:out value="${diameter_unit}"/></option>
					</c:forEach>
				</select>
	    	</spring:bind>
	    	</c:if>
	    </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="2" align="left">&nbsp;<font size=2><b><spring:message code="frame.style"/></b></font></td>
		<td colspan="5">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="4" align="left">
			&nbsp;<spring:bind path="order.framestyle">
				<input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="2">
			</spring:bind>
		    	<spring:bind path="order.frametype">
					<select class="input" name='<c:out value="${status.expression}"/>'>
						<c:forEach var="frame_type" items="${listFrameType}">
							<option
								<c:if test="${frame_type == status.value}">selected</c:if>
								value='<c:out value="${frame_type}"/>'><c:out value="${frame_type}"/></option>
						</c:forEach>
					</select>
		    	</spring:bind>
		    	<spring:bind path="order.framepattern">
					<select class="input" name='<c:out value="${status.expression}"/>'>
						<c:forEach var="frame_pattern" items="${listFramePattern}">
							<option
								<c:if test="${frame_pattern == status.value}">selected</c:if>
								value='<c:out value="${frame_pattern}"/>'><c:out value="${frame_pattern}"/></option>
						</c:forEach>
					</select>
		    	</spring:bind>
		</td>
		<td colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="8" height="0"></td>
	</tr>
    <tr>
    	<td colspan="8" valign="middle"><font size="4"><b><spring:message code="lens.rx"/></b></font></td>
    </tr>
	<tr>
		<td colspan="8" height="0">
		<hr>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	    <td align="center" valign="bottom"><font size=2><b><spring:message code="lens.sphere.short"/></b></font></td>
	    <td align="center" valign="bottom"><font size=2><b><spring:message code="lens.cylinder.short"/></b></font></td>
	    <td align="center" valign="bottom"><font size=2><b><spring:message code="lens.axis.short"/></b></font></td>
	    <td align="center" valign="bottom"><font size=2><b><spring:message code="lens.prism.short"/></b></font></td>
	    <td align="center" valign="bottom"><font size=2><b><spring:message code="lens.prism.direction"/></b></font></td>
	    <td align="center" valign="bottom"><font size=2><b><spring:message code="lens.basecurve.short"/></b></font></td>
	    <td align="center" valign="bottom"><font size=2><b><spring:message code="lens.ochgt.short"/></b></font></td>
	</tr>
	<tr>
    	<td align="right"><font size="2"><spring:message code="lens.right"/>&nbsp;</font></td>
        <td align="center"><spring:bind path="order.rsphere"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.rcylinder"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.raxis"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.rhprism"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center">
			<spring:bind path="order.rhpd">
				<select name="<c:out value="${status.expression}"/>">
					<option value="" <c:if test="${empty status.value}">selected</c:if>><spring:message code="list.select"/></option>
					<option value="UP" <c:if test="${'UP' == status.value}">selected</c:if>>UP</option>
					<option value="DOWN" <c:if test="${'DOWN' == status.value}">selected</c:if>>DOWN</option>
					<option value="IN" <c:if test="${'IN' == status.value}">selected</c:if>>IN</option>
					<option value="OUT" <c:if test="${'OUT' == status.value}">selected</c:if>>OUT</option>
				</select>
			</spring:bind>
        </td>
        <td align="center"><spring:bind path="order.rbasecurve"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.rochgt"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
	</tr>
	<tr>
    	<td align="right"><font size="2"><spring:message code="lens.left"/>&nbsp;</font></td>
        <td align="center"><spring:bind path="order.lsphere"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.lcylinder"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.laxis"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.lhprism"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center">
			<spring:bind path="order.lhpd">
				<select name="<c:out value="${status.expression}"/>">
					<option value="" <c:if test="${empty status.value}">selected</c:if>><spring:message code="list.select"/></option>
					<option value="UP" <c:if test="${'UP' == status.value}">selected</c:if>>UP</option>
					<option value="DOWN" <c:if test="${'DOWN' == status.value}">selected</c:if>>DOWN</option>
					<option value="IN" <c:if test="${'IN' == status.value}">selected</c:if>>IN</option>
					<option value="OUT" <c:if test="${'OUT' == status.value}">selected</c:if>>OUT</option>
				</select>
			</spring:bind>
        </td>
        <td align="center"><spring:bind path="order.lbasecurve"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.lochgt"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="3" size="10"></spring:bind></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center"><font size="2"><b><spring:message code="lens.add.short"/></b></font></td>
        <td align="center"><font size="2"><b><spring:message code="lens.seght.short"/></b></font></td>
        <td align="center"><font size="2"><b><spring:message code="lens.farpd.short"/></b></font></td>
        <td align="center"><font size="2"><b><spring:message code="lens.nearpd.short"/></b></font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><font size="2"><spring:message code="lens.right"/>&nbsp;</font></td>
        <td align="center"><spring:bind path="order.radd"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="4" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.rsegheight"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="4" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.rfarpd"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="4" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.rnearpd"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="4" size="10"></spring:bind></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><font size="2"><spring:message code="lens.left"/>&nbsp;</font></td>
        <td align="center"><spring:bind path="order.ladd"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="4" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.lsegheight"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="4" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.lfarpd"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="4" size="10"></spring:bind></td>
        <td align="center"><spring:bind path="order.lnearpd"><input name="<c:out value="${status.expression}"/>" type="text" value="<c:out value="${status.value}"/>" tabindex="4" size="10"></spring:bind></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="8" height="0"></td>
	</tr>
    <tr>
    	<td colspan="8" valign="middle"><font size="4"><b><spring:message code="lens.es"/></b></font></td>
    </tr>
	<tr>
		<td colspan="8" height="0">
		<hr>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="left" valign="top">
        	<table border="0">
        		<tr>
        			<td colspan="3" align="left">
	        			<b><spring:message code="lens.material"/><br><font color="#ff0000">(<spring:message code="select1or2"/>)</font><br></b>
        			</td>
        		</tr>
        		<tr>
	        		<td valign="top">
	        			<spring:bind path="order.material">
	        			<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="None" <c:if test="${empty status.value}">checked</c:if>><spring:message code="none"/></label><br>
			            <label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="CR39" <c:if test="${'CR39' == status.value}">checked</c:if>><spring:message code="material.cr39"/></label><br>
						<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="1.56" <c:if test="${'1.56' == status.value}">checked</c:if>><spring:message code="material.156"/></label><br>
			            <label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="1.60" <c:if test="${'1.60' == status.value}">checked</c:if>><spring:message code="material.160"/></label><br>
						<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="1.67" <c:if test="${'1.67' == status.value}">checked</c:if>><spring:message code="material.167"/></label><br>
						<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="1.74" <c:if test="${'1.74' == status.value}">checked</c:if>><spring:message code="material.174"/></label><br>	
						<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="Polycarbonate" <c:if test="${'Polycarbonate' == status.value}">checked</c:if>><spring:message code="material.pc"/></label><br>
						<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="HI-VEX" <c:if test="${'HI-VEX' == status.value}">checked</c:if>><spring:message code="material.hivex"/></label><br>
						</spring:bind>
					</td>
					<td width="20">&nbsp;</td>
					<td valign="top">
						<spring:bind path="order.material2">
						<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="None" <c:if test="${empty status.value}">checked</c:if>><spring:message code="none"/></label><br>
			          	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="Polarized(Grey)" <c:if test="${'Polarized(Grey)' == status.value}">checked</c:if>><spring:message code="material.plrz.gry"/></label><br>
			          	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="Polarized(Brown)" <c:if test="${'Polarized(Brown)' == status.value}">checked</c:if>><spring:message code="material.plrz.brw"/></label><br>
			          	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="Polarized(G-15)" <c:if test="${'Polarized(G-15)' == status.value}">checked</c:if>><spring:message code="material.plrz.g15"/></label><br>
			          	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="Photochromic(Grey)" <c:if test="${'Photochromic(Grey)' == status.value}">checked</c:if>><spring:message code="material.phchm.gry"/></label><br>
			          	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="Photochromic(Brown)" <c:if test="${'Photochromic(Brown)' == status.value}">checked</c:if>><spring:message code="material.phchm.brw"/></label><br>
			          	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="Transition (Grey)" <c:if test="${'Transition (Grey)' == status.value}">checked</c:if>><spring:message code="material.trans.gry"/></label><br>
			          	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="5" value="Transition (Brown)" <c:if test="${'Transition (Brown)' == status.value}">checked</c:if>><spring:message code="material.trans.brw"/></label><br>
			          	</spring:bind>
			        </td>
			    </tr>
			</table>
		</td>
		<td colspan="3" align="left" valign="top">
			<spring:bind path="order.lenstype">
			<table>
				<tr>
					<td colspan="2" align="left">
						<strong><spring:message code="lens.type"/><br><font color="#FF0000">(<spring:message code="selectone"/>)</font><br></strong>
					</td>
				</tr>
				<tr>
					<td valign="top">
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="SV" <c:if test="${empty status.value}">checked</c:if><c:if test="${'SV' == status.value}">checked</c:if>><spring:message code="lenstype.sv"/></label><br>
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="FT 28" <c:if test="${'FT 28' == status.value}">checked</c:if>><spring:message code="lenstype.ft28"/></label><br>
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="FT 35" <c:if test="${'FT 35' == status.value}">checked</c:if>><spring:message code="lenstype.ft35"/></label><br>
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Round 24" <c:if test="${'Round 24' == status.value}">checked</c:if>><spring:message code="lenstype.round24"/></label><br>
		              	<c:if test="${lang.language == 'zh'}">
		              		<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Round 28" <c:if test="${'Round 28' == status.value}">checked</c:if>><spring:message code="lenstype.round28"/></label><br>
		              		<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Arc" <c:if test="${'Arc' == status.value}">checked</c:if>><spring:message code="lenstype.arc"/></label><br>
		              	</c:if>
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Blended" <c:if test="${'Blended' == status.value}">checked</c:if>><spring:message code="lenstype.blended"/></label><br>
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Trifocal 8X35" <c:if test="${'Trifocal 8X35' == status.value}">checked</c:if>><spring:message code="lenstype.trifocal835"/></label><br>
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Trifocal 7X28" <c:if test="${'Trifocal 7X28' == status.value}">checked</c:if>><spring:message code="lenstype.trifocal728"/></label><br>
	              	</td>
	              	<td valign="top">
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Prog/Short" <c:if test="${'Prog/Short' == status.value}">checked</c:if>><spring:message code="lenstype.progshort"/></label><br>
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Prog/Regular" <c:if test="${'Prog/Regular' == status.value}">checked</c:if>><spring:message code="lenstype.progregular"/></label><br>
		              	<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="FreeForm" <c:if test="${'FreeForm' == status.value}">checked</c:if>><spring:message code="lenstype.freeform"/></label><br>
		              	<c:if test="${lang.language == 'zh'}">
		              		<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="DoctorProg" <c:if test="${'DoctorProg' == status.value}">checked</c:if>><spring:message code="lenstype.doctorprog"/></label><br>
		              		<label id="float_lenstype_omega"><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Omega" <c:if test="${'Omega' == status.value}">checked</c:if>><spring:message code="lenstype.omega"/></label><br>
		              		<label id="float_lenstype_strawhat"><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Strawhat" <c:if test="${'Strawhat' == status.value}">checked</c:if>><spring:message code="lenstype.strawhat"/></label><br>
		              		<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Anti-Fatigue" <c:if test="${'Anti-Fatigue' == status.value}">checked</c:if>><spring:message code="lenstype.antifatigue"/></label><br>
		              		<label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="6" value="Non-Spherical" <c:if test="${'Non-Spherical' == status.value}">checked</c:if>><spring:message code="lenstype.nonspherical"/></label><br>
		              	</c:if>
		            </td>
		        </tr>
		    </table>
            </spring:bind>
         </td>
         <td colspan="2" align="left" valign="top">
         	<table align="left" cellpadding="0" cellspacing="0">
         		<tr>
         			<td>
						<table align="left" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="1"><strong><spring:message code="treatment"/>&nbsp;</strong></td>
								<td colspan="2">&nbsp;</td>
							</tr>
							<spring:bind path="order.treat">
							<tr>
								<td><spring:message code="treatment.treat.unc"/></td>
								<c:set var="tvalue" scope="page"><spring:message code="treatment.treat.unc"/></c:set>
						        <td><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="<spring:message code="treatment.treat.unc"/>" <c:if test="${tvalue == status.value||status.value==''}">checked</c:if>/></td>
						        <td>&nbsp;</td>
					        </tr>
					        <tr>
								<td><spring:message code="treatment.treat.hc"/></td>
								<c:set var="tvalue" scope="page"><spring:message code="treatment.treat.hc"/></c:set>
						        <td><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="<spring:message code="treatment.treat.hc"/>" <c:if test="${tvalue == status.value}">checked</c:if>/></td>
						        <td>&nbsp;</td>
					        </tr>
					        <tr>
								<td><spring:message code="treatment.treat.thc"/></td>
								<c:set var="tvalue" scope="page"><spring:message code="treatment.treat.thc"/></c:set>
						        <td><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="<spring:message code="treatment.treat.thc"/>" <c:if test="${tvalue == status.value}">checked</c:if>/></td>
						        <td>&nbsp;</td>
					        </tr>
					        <tr>
								<td><spring:message code="treatment.treat.hmc"/></td>
								<c:set var="tvalue" scope="page"><spring:message code="treatment.treat.hmc"/></c:set>
						        <td><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="<spring:message code="treatment.treat.hmc"/>" <c:if test="${tvalue == status.value}">checked</c:if>/></td>
						        <td>&nbsp;</td>
					        </tr>
					         <tr>
								<td><spring:message code="treatment.treat.shmc"/></td>
								<c:set var="tvalue" scope="page"><spring:message code="treatment.treat.shmc"/></c:set>
						        <td><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="<spring:message code="treatment.treat.shmc"/>" <c:if test="${tvalue == status.value}">checked</c:if>/></td>
						        <td>&nbsp;</td>
					        </tr>
					       </spring:bind>
					         <tr>
								<td colspan="1">&nbsp;</td>
								<td colspan="2"><strong><spring:message code="yesno"/>&nbsp;</strong></td>
							</tr>
							<tr>
					           	<td><spring:message code="treatment.uv"/></td>
					           	<spring:bind path="order.uv">
					       			<td><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="uv" <c:if test="${'uv' == status.value}">checked</c:if>/></td>
					       			<td><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="" <c:if test="${empty status.value}">checked</c:if>/></td>
					       		</spring:bind>
					       	</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="0">&nbsp;</td>
				</tr>
				<tr>
					<td> 
					    <table align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td align="left" colspan="2"><strong><spring:message code="tint"/></strong></td>
							</tr>
							<spring:bind path="order.tinttype">
						       	<tr>
						       		<td colspan="2"><label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="None" <c:if test="${empty status.value}">checked</c:if><c:if test="${'None' == status.value}">checked</c:if>/><spring:message code="tint.none"/></label></td>
						        </tr>
						        <tr>
						           	<td colspan="2"><label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="Solid" <c:if test="${'Solid' == status.value}">checked</c:if>/><spring:message code="tint.solid"/></label></td>
						        </tr>
						        <tr>
						           	<td colspan="2"><label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="Gradient" <c:if test="${'Gradient' == status.value}">checked</c:if>/><spring:message code="tint.gradient"/></label></td>
								</tr>
								<tr>
									<td><spring:message code="lens.color"/></td>
									<td>
										<spring:bind path="order.tintcolor1">
											<select name="<c:out value="${status.expression}"/>">
												<option value="" <c:if test="${empty status.value}">selected</c:if>><spring:message code="list.pleaseselect"/></option>
												<option value="Grey - 10%" <c:if test="${'Grey - 10%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey10"/></option>
												<option value="Grey - 50%" <c:if test="${'Grey - 50%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey50"/></option>
												<option value="Grey - 75%" <c:if test="${'Grey - 75%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey75"/></option>
												<option value="Grey - 80%" <c:if test="${'Grey - 80%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey80"/></option>
												<option value="Grey - 85%" <c:if test="${'Grey - 85%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey85"/></option>
												<option value="Brown - 10%" <c:if test="${'Brown - 10%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown10"/></option>
												<option value="Brown - 50%" <c:if test="${'Brown - 50%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown50"/></option>
												<option value="Brown - 75%" <c:if test="${'Brown - 75%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown75"/></option>
												<option value="Brown - 80%" <c:if test="${'Brown - 80%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown80"/></option>
												<option value="Brown - 85%" <c:if test="${'Brown - 85%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown85"/></option>
												<option value="G-15 - 10%" <c:if test="${'G-15 - 10%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1510"/></option>
												<option value="G-15 - 50%" <c:if test="${'G-15 - 50%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1550"/></option>
												<option value="G-15 - 80%" <c:if test="${'G-15 - 80%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1580"/></option>
												<option value="Blue - 10%" <c:if test="${'Blue - 10%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue10"/></option>
												<option value="Blue - 50%" <c:if test="${'Blue - 50%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue50"/></option>
												<option value="Blue - 80%" <c:if test="${'Blue - 80%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue80"/></option>
												<option value="Purple - 10%" <c:if test="${'Purple - 10%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple10"/></option>
												<option value="Purple - 50%" <c:if test="${'Purple - 50%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple50"/></option>
												<option value="Purple - 80%" <c:if test="${'Purple - 80%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple80"/></option>
												<option value="Pink - 10%" <c:if test="${'Pink - 10%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink10"/></option>
												<option value="Pink - 50%" <c:if test="${'Pink - 50%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink50"/></option>
												<option value="Pink - 80%" <c:if test="${'Pink - 80%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink80"/></option>
												<option value="Yellow - 10%" <c:if test="${'Yellow - 10%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow10"/></option>
												<option value="Yellow - 50%" <c:if test="${'Yellow - 50%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow50"/></option>
												<option value="Yellow - 80%" <c:if test="${'Yellow - 80%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow80"/></option>
											    <option value="Grey - 20%" <c:if test="${'Grey - 20%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey20"/></option>
												<option value="Grey - 30%" <c:if test="${'Grey - 30%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey30"/></option>
												<option value="Grey - 40%" <c:if test="${'Grey - 40%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey40"/></option>
												<option value="Grey - 60%" <c:if test="${'Grey - 60%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey60"/></option>
												<option value="Grey - 70%" <c:if test="${'Grey - 70%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey70"/></option>
												<option value="Grey - 90%" <c:if test="${'Grey - 90%' == status.value}">selected</c:if>><spring:message code="tintcolor.grey90"/></option>
												<option value="Brown - 20%" <c:if test="${'Brown - 20%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown20"/></option>
												<option value="Brown - 30%" <c:if test="${'Brown - 30%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown30"/></option>
												<option value="Brown - 40%" <c:if test="${'Brown - 40%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown40"/></option>
												<option value="Brown - 60%" <c:if test="${'Brown - 60%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown60"/></option>
												<option value="Brown - 70%" <c:if test="${'Brown - 70%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown70"/></option>
												<option value="Brown - 90%" <c:if test="${'Brown - 90%' == status.value}">selected</c:if>><spring:message code="tintcolor.brown90"/></option>
												<option value="G-15 - 20%" <c:if test="${'G-15 - 20%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1520"/></option>
												<option value="G-15 - 30%" <c:if test="${'G-15 - 30%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1530"/></option>
												<option value="G-15 - 40%" <c:if test="${'G-15 - 40%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1540"/></option>
												<option value="G-15 - 60%" <c:if test="${'G-15 - 60%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1560"/></option>
												<option value="G-15 - 70%" <c:if test="${'G-15 - 70%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1570"/></option>
												<option value="G-15 - 75%" <c:if test="${'G-15 - 75%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1575"/></option>
												<option value="G-15 - 90%" <c:if test="${'G-15 - 90%' == status.value}">selected</c:if>><spring:message code="tintcolor.g1590"/></option>
												<option value="Blue - 20%" <c:if test="${'Blue - 20%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue20"/></option>
												<option value="Blue - 30%" <c:if test="${'Blue - 30%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue30"/></option>
												<option value="Blue - 40%" <c:if test="${'Blue - 40%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue40"/></option>
												<option value="Blue - 60%" <c:if test="${'Blue - 60%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue60"/></option>
												<option value="Blue - 70%" <c:if test="${'Blue - 70%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue70"/></option>
												<option value="Blue - 75%" <c:if test="${'Blue - 75%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue75"/></option>
												<option value="Blue - 85%" <c:if test="${'Blue - 85%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue85"/></option>
												<option value="Blue - 90%" <c:if test="${'Blue - 90%' == status.value}">selected</c:if>><spring:message code="tintcolor.blue90"/></option>
												<option value="Purple - 20%" <c:if test="${'Purple - 20%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple20"/></option>
												<option value="Purple - 30%" <c:if test="${'Purple - 30%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple30"/></option>
												<option value="Purple - 40%" <c:if test="${'Purple - 40%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple40"/></option>
												<option value="Purple - 60%" <c:if test="${'Purple - 60%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple60"/></option>
												<option value="Purple - 70%" <c:if test="${'Purple - 70%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple70"/></option>
												<option value="Purple - 75%" <c:if test="${'Purple - 75%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple75"/></option>
												<option value="Purple - 85%" <c:if test="${'Purple - 85%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple85"/></option>
												<option value="Purple - 90%" <c:if test="${'Purple - 90%' == status.value}">selected</c:if>><spring:message code="tintcolor.purple90"/></option>
												<option value="Pink - 20%" <c:if test="${'Pink - 20%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink20"/></option>
												<option value="Pink - 30%" <c:if test="${'Pink - 30%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink30"/></option>
												<option value="Pink - 40%" <c:if test="${'Pink - 40%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink40"/></option>
												<option value="Pink - 60%" <c:if test="${'Pink - 60%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink60"/></option>
												<option value="Pink - 70%" <c:if test="${'Pink - 70%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink70"/></option>
												<option value="Pink - 75%" <c:if test="${'Pink - 75%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink75"/></option>
												<option value="Pink - 85%" <c:if test="${'Pink - 85%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink85"/></option>
												<option value="Pink - 90%" <c:if test="${'Pink - 90%' == status.value}">selected</c:if>><spring:message code="tintcolor.pink90"/></option>
												<option value="Yellow - 20%" <c:if test="${'Yellow - 20%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow20"/></option>
												<option value="Yellow - 30%" <c:if test="${'Yellow - 30%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow30"/></option>
												<option value="Yellow - 40%" <c:if test="${'Yellow - 40%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow40"/></option>
												<option value="Yellow - 60%" <c:if test="${'Yellow - 60%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow60"/></option>
												<option value="Yellow - 70%" <c:if test="${'Yellow - 70%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow70"/></option>
												<option value="Yellow - 75%" <c:if test="${'Yellow - 75%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow75"/></option>
												<option value="Yellow - 85%" <c:if test="${'Yellow - 85%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow85"/></option>
												<option value="Yellow - 90%" <c:if test="${'Yellow - 90%' == status.value}">selected</c:if>><spring:message code="tintcolor.yellow90"/></option>											
											</select>
										</spring:bind>
									</td>
								</tr>
						        <tr>
						           	<td colspan="2" id="float_custom_color"><label><input name="<c:out value="${status.expression}"/>" type="radio" tabindex="7" value="Customers Color" <c:if test="${'Customers Color' == status.value}">checked</c:if>/><spring:message code="tint.custom"/></label></td>
								</tr>
							</spring:bind>							
						</table>
					</td>
				</tr>
			</table>
         </td>
         <td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="8" height="0"></td>
	</tr>	
    <tr>
    	<td colspan="8" valign="middle"><font size="4"><b><spring:message code="others"/></b></font></td>
    </tr>	
	<tr>
		<td colspan="8" height="0">
		<hr>
		</td>
	</tr>
	<tr>
		<td align="left" colspan="8">
			&nbsp;<spring:message code="order.emergent"/>&nbsp;
			<spring:bind path="order.emergent">
				<input name="<c:out value="${status.expression}"/>" type="checkbox" <c:if test="${status.value}">checked</c:if>/>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td colspan="8" height="0">&nbsp;</td>
	</tr>	
    <tr>
		<td align="left" valign="middle"><strong><spring:message code="order.remarks"/></strong>:&nbsp;</td>
		<spring:bind path="order.remarks">
      		<td align="left" valign="top" colspan="6"><textarea name="<c:out value="${status.expression}"/>" cols="80" rows="5" tabindex="8"><c:out value="${status.value}"/></textarea></td>
      	</spring:bind>
      	<td>&nbsp;</td>
    </tr>
</table>
