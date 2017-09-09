
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<title>hand over order</title>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css"
	rel="stylesheet" />
</head>
<body>

<form action="<c:url value="/orderCheck.ord"/>" method="post">
	<%@ include file="Handover.jsp" %>
</form>

</body>
</html>
