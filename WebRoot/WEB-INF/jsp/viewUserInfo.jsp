
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<title>view user</title>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css"
	rel="stylesheet" />
</head>
<body>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td align="center">
			<input type="button" class="button" onclick="javascript:window.location.href='<c:url value="/queryUser.ord"/>'" value="<spring:message code="operation.back"/>"/>&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
</table>

<br/>

<table width="95%" align="center" border="1" cellpadding="3"
	cellspacing="1" bordercolor="#2268A6" bordercolordark="ffffff">

	<tr>
		<td align="center" colspan="12">
			<b><spring:message code="userInfo"/>#<c:out value="${success.user_tag}"/></b>
		</td>
	</tr>
<!--	
	<tr>
		<td align="center" colspan="12">
			<b><spring:message code="order.clientdetails"/></b>
		</td>
	</tr>
-->
	<tr>
		<td colspan="2"><spring:message code="userInfo.user_id"/>:</td>
		<td colspan="4">${fn:substring(success.user_id, 9, -1)}&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.user_code"/>:</td>
		<td colspan="4"><c:out value="${success.user_code}"/>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.user_name"/>:</td>
		<td colspan="4"><c:out value="${success.user_tag}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.lastName"/>:</td>
		<td colspan="4"><c:out value="${success.lastName}"/>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.email"/>:</td>
		<td colspan="4"><c:out value="${success.email}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.address"/>:</td>
		<td colspan="4"><c:out value="${success.address}"/>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.home_tel"/>:</td>
		<td colspan="4"><c:out value="${success.home_tel}"/>&nbsp;</td>	
		<td colspan="2"><spring:message code="userInfo.mobile"/>:</td>
		<td colspan="4"><c:out value="${success.mobile}"/>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.login_ip"/>:</td>
		<td colspan="4"><c:out value="${success.login_ip}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.login_time"/>:</td>
		<td colspan="4"><c:out value="${success.login_time}"/>&nbsp;</td>		
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.add_date"/>:</td>
		<td colspan="4"><c:out value="${success.add_date}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.add_userid"/>:</td>
		<td colspan="4"><c:out value="${success.add_userid}"/>&nbsp;</td>		
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.upd_date"/>:</td>
		<td colspan="4"><c:out value="${success.upd_date}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.upd_userid"/>:</td>
		<td colspan="4"><c:out value="${success.upd_userid}"/>&nbsp;</td>		
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.lock_flag"/>:</td>
		<td colspan="4"><c:out value="${success.lock_flag}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.limit_date"/>:</td>
		<td colspan="4"><c:out value="${success.limit_date}"/>&nbsp;</td>		
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.limit_time"/>:</td>
		<td colspan="4"><c:out value="${success.limit_time}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.limit_ip"/>:</td>
		<td colspan="4"><c:out value="${success.limit_ip}"/>&nbsp;</td>		
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.is_admin"/>:</td>
		<td colspan="4"><c:out value="${success.is_admin}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.is_login"/>:</td>
		<td colspan="4"><c:out value="${success.is_login}"/>&nbsp;</td>		
	</tr>
	<tr>
		<td colspan="2"><spring:message code="userInfo.del_flag"/>:</td>
		<td colspan="4"><c:out value="${success.del_flag}"/>&nbsp;</td>
		<td colspan="2"><spring:message code="userInfo.is_audit"/>:</td>
		<td colspan="4"><c:out value="${success.is_audit}"/>&nbsp;</td>		
	</tr>					

		<tr>
			<td align="center" colspan="12">
				<b><spring:message code="userInfo.businessInfo"/></b>
			</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="userInfo.businessName"/>:</td>
			<td colspan="10"><c:out value="${success.businessName}"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="userInfo.businessStreetNumber"/>:</td>
			<td colspan="4"><c:out value="${success.businessStreetNumber}"/>&nbsp;</td>
			<td colspan="2"><spring:message code="userInfo.businessStreetName"/>:</td>
			<td colspan="4"><c:out value="${success.businessStreetName}"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="userInfo.businessSuit"/>:</td>
			<td colspan="4"><c:out value="${success.businessSuit}"/>&nbsp;</td>
			<td colspan="2"><spring:message code="userInfo.businessCity"/>:</td>
			<td colspan="4"><c:out value="${success.businessCity}"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="userInfo.businessState"/>:</td>
			<td colspan="4"><c:out value="${success.businessState}"/>&nbsp;</td>
			<td colspan="2"><spring:message code="userInfo.businessCountry"/>:</td>
			<td colspan="4"><c:out value="${success.businessCountry}"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="userInfo.businessState"/>:</td>
			<td colspan="4"><c:out value="${success.businessState}"/>&nbsp;</td>
			<td colspan="2"><spring:message code="userInfo.businessCountry"/>:</td>
			<td colspan="4"><c:out value="${success.businessCountry}"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="userInfo.businessZip"/>:</td>
			<td colspan="4"><c:out value="${success.businessZip}"/>&nbsp;</td>
			<td colspan="2"><spring:message code="userInfo.businessPhone"/>:</td>
			<td colspan="4"><c:out value="${success.businessPhone}"/>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><spring:message code="userInfo.businessExt"/>:</td>
			<td colspan="4"><c:out value="${success.businessExt}"/>&nbsp;</td>
			<td colspan="2"><spring:message code="userInfo.businessFax"/>:</td>
			<td colspan="4"><c:out value="${success.businessFax}"/>&nbsp;</td>
		</tr>
</table>
<br/>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td align="center">
			<input type="button" class="button" 
				onclick="javascript:window.location.href='<c:url value="/queryUser.ord"/>'" 
				value="<spring:message code="operation.back"/>"/>&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
</table>

</body>
</html>
