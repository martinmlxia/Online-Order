<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="conant.ico"/>
<link href="<c:url value="/resources/style/style.css" />" type="text/css" rel="stylesheet">
<title>Batch Import Orders</title>
<script language="JavaScript">
	function checkSubmit(form)
	{
		if(form.file.value == "")
		{
			alert('<spring:message code="import.selectRequired"/>');
			form.file.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>

<form action="<c:url value="/batchImportOrders.ord"/>" 
	method="post" enctype="multipart/form-data" onSubmit="return checkSubmit(this)">
<br/>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="10">
	<tr>
		<td align="center"><spring:message code="import.select"/></td>
	</tr>
	<tr>
		<td align="center">
			<input name="file" type="file"/>
		</td>
	</tr>
	<tr>
		<td align="center">
			<input name="submit" type="submit" class="button" align="middle" value="<spring:message code="import.upload"/>"/>
		</td>
	</tr>
	<tr>
	   <td align="center"><a target="_blank" href="${pageContext.request.contextPath }/resources/<spring:message code="import.filename"/>"><spring:message code="import.download"/></a></td>
	</tr>
</table>
</form>

</body>
</html>
