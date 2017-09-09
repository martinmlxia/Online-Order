
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<title>Query Users</title>
<link href="<c:url value="/resources/style/style.css" />"
	type="text/css" rel="stylesheet" />
<script language="javascript"
	src="<c:url value="/resources/calendar/WdatePicker.js"/>"
	type="text/javascript">
</script>	
<script language="javascript" type="text/javascript">

	function checkAll(form) 
	{
		for(var i = 0; i < form.elements.length; i++)
		{
			var elmt = form.elements[i];
			if(elmt.type == "checkbox" && elmt.name != "selectAll")
			{
				elmt.checked = form.selectAll.checked;
			}
		}
	}


	function checkSelected()
	{
	  var allcheck=document.getElementsByName("userIds");
	  for(i=0;i<allcheck.length;i++){
		if(allcheck[i].checked){
		  return true;
		}	  
	  }
	  alert("<spring:message code="operation.selected"/>");
	  return false;
	}	
</script>
</head>

<body>

<table width="99%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td class="table1">
			<img src="<c:url value="/resources/images/arrow.gif"/>"
				width="20" height="18" hspace="4" align="absmiddle">
			<spring:message code="userInfo.list"/>
		</td>
	</tr>
	
     <form name="UserInfoForm" id="UserInfoForm" action="<c:url value="/deleteUserInfo.ord"/>" method="post" onsubmit="return checkSelected(UserInfoForm)">
	<tr>
		<td>
		
		<table width="99%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolor="#2268A6" bordercolordark="ffffff">
			<tr class="table7">
				<td width="3%">&nbsp;</td>
				<td width="10%"><spring:message code="userInfo.user_id"/></td>
				<td width="10%"><spring:message code="userInfo.user_code"/></td>
				<td width="10%"><spring:message code="userInfo.user_tag"/></td>
				<td width="10%"><spring:message code="userInfo.user_name"/></td>
				<td width="15%"><spring:message code="userInfo.email"/></td>
				<td width="15%"><spring:message code="userInfo.businessName"/></td>
				<td width="20%"><spring:message code="order.operation"/></td>
			</tr>
			<c:forEach var="userInfo" items="${success.listUser}" varStatus="itemStatus">
				<c:choose>
					<c:when test="${(itemStatus.index % 2 == 0)}">
						<tr class="table5">
					</c:when>
					<c:when test="${(itemStatus.index % 2 != 0)}">
						<tr class="table3">
					</c:when>
				</c:choose>
				<td>&nbsp;<input name="userIds" type="checkbox" value="<c:out value="${userInfo.user_id}"/>"/></td>
				<td>&nbsp;<c:out value="${userInfo.user_id}"/></td>
				<td>&nbsp;<c:out value="${userInfo.user_code}"/></td>
				<td>&nbsp;<c:out value="${userInfo.user_tag}"/></td>
				<td>&nbsp;<c:out value="${userInfo.user_name}"/></td>
				<td>&nbsp;<c:out value="${userInfo.email}"/></td>
				<td>&nbsp;<c:out value="${userInfo.businessName}"/></td>
				<td>
					<a href="<c:url value="/viewUserInfo.ord"><c:param name="user_id" value="${userInfo.user_id}"/></c:url>">
					<b><spring:message code="operation.view"/></b>
					</a>
					<a href="<c:url value="/updateUserInfo.ord"><c:param name="user_id" value="${userInfo.user_id}"/></c:url>">
					<b><spring:message code="operation.audit"/></b>
					</a>
				</td>
				</tr>
			</c:forEach>
		</table>
		<table width="99%" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr class="fy">
				<td width="60%" align="left">
					&nbsp;<input name="selectAll" type="checkbox" value="on" onclick="checkAll(this.form)"/>
					<spring:message code="operation.selectAll"/>
					&nbsp;<input name="method" type="submit" class="button" align="middle" value="<spring:message code="operation.delete"/>"/>
				</td>
				<td width="20%" align="left" valign="middle">
					<spring:message code="nav.records"/>:&nbsp;<c:out value="${success.recordCount}"/>
					&nbsp;<spring:message code="nav.current"/>&nbsp;<c:out value="${success.pageNo + 1}"/>/<c:out value="${success.pageCount}"/>&nbsp;<spring:message code="nav.page"/>
				</td>
				<td width="20%" align="center">
					<a href="<c:url value="/queryUser.ord"><c:param name="pageNo" value="0"/></c:url>">
						<b><spring:message code="nav.first"/></b>
					</a>
					<a href="<c:url value="/queryUser.ord"><c:param name="pageNo" value="${((success.pageNo - 1) > 0) ? (success.pageNo - 1) : 0}"/></c:url>">
						<b><spring:message code="nav.prev"/></b>
					</a>
					<a href="<c:url value="/queryUser.ord"><c:param name="pageNo" value="${((success.pageNo + 1) < (success.pageCount - 1)) ? (success.pageNo + 1) : (success.pageCount - 1)}"/></c:url>">
						<b><spring:message code="nav.next"/></b>
					</a>
					<a href="<c:url value="/queryUser.ord"><c:param name="pageNo" value="${success.pageCount - 1}"/></c:url>">
						<b><spring:message code="nav.last"/></b>
					</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	</form>
</table>
</body>

</html>
