
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>menu</title>
<link href="<c:url value="/resources/style/csstab.css"/>" type="text/css" rel="stylesheet">
</head>

<body>
<table width="216" height="100%" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<th width="216" height="400" valign="top" background="<c:url value="/resources/images/14.png"/>" scope="col">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th height="30" align="center" bgcolor="#CEE3F8" scope="col">
					<spring:message code="menu.title"/>
				</th>
			</tr>
			<c:forEach var="menuItem" items="${success}" varStatus="itemStatus">
				<c:if test="${menuItem.func_Pid != -1 && menuItem.func_Type == 0}">
					<tr>
						<td height="31" align="left" valign="bottom">
						<div align="left" class="STYLE3" onMouseOver="this.className='test1'" onMouseOut="this.className='test'">
							<b>&nbsp;&nbsp;<c:set var="function" value="${menuItem.func_Name}"/><spring:message	code="${fn:replace(function,' ','')}"/></b>
						</div>
						</td>
					</tr>
				</c:if>
				<c:if test="${menuItem.func_Pid != -1 && menuItem.func_Type == 1}">
					<tr>
						<td height="30" align="left">
						<div align="left" class="STYLE3" onMouseOver="this.className='test1'" onMouseOut="this.className='test'">
						&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="<c:url value="/${menuItem.func_URL}"/>" target="mainFrame">
								<c:set var="function" value="${menuItem.func_Name}"/><spring:message code="${fn:replace(function,' ','')}"/>
							</a>
						</div>
						</td>
					</tr>		
					<tr>
						<td align="center"><span class="STYLE2">..............................................................</span></td>
					</tr>					
				</c:if>
			</c:forEach>
		</table>
		</th>
	</tr>
	<tr>
		<th height="400" valign="top" background="<c:url value="/resources/images/14.png"/>" scope="col">&nbsp;</th>
	</tr>
</table>
</body>
</html>
