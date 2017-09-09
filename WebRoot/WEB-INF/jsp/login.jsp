<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="login.caption"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="conant.ico"/>
<link href="<c:url value="/resources/style/login_style.css"/>" type="text/css" rel="stylesheet"/>
<script src="<c:url value="/resources/js/common.js"/>" type="text/javascript"></script>
<script language="JavaScript">
	function checkSafe(form)
	{
		if(form.userName.value == "")
		{
			alert('<spring:message code="login.accountRequired"/>');
			form.userName.focus();
			return false;
		}
		if(form.password.value == "")
		{
			alert('<spring:message code="login.passwordRequired"/>');
			form.password.focus();
			return false;
		}
		return true;
	}
	
	function initForm()
	{	
		document.getElementById("userName").focus();
		
		var array = fnGetPageSize();
		var loginImg = document.getElementById("loginImg");
		var loginCtrl = document.getElementById("loginCtrl");
		var loginBtn = document.getElementById("loginBtn");
		
		var iw = 672, ih = 484;
		loginImg.style.position = "absolute";
		loginImg.style.zIndex = 1;
        loginImg.style.left = ((array[2] - iw) / 2) + "px";
        loginImg.style.top = ((array[3] - ih) / 2) + "px";
        loginImg.style.width = iw + "px";
        loginImg.style.height = ih + "px";
        
        var cw = 240, ch = 90, cl = 260, ct = 186;
        loginCtrl.style.position = "absolute";
        loginCtrl.style.zIndex = 2;
        loginCtrl.style.left = ((array[2] - iw) / 2 + cl) + "px";
        loginCtrl.style.top = ((array[3] - ih) / 2 + ct) + "px";
        loginCtrl.style.width = cw + "px";
        loginCtrl.style.height = ch + "px";

        var bw = 104, bh = 36, bl = 541, bt = 336;
        loginBtn.style.position = "absolute";
        loginBtn.style.zIndex = 3;
        loginBtn.style.left = ((array[2] - iw) / 2 + bl) + "px";
        loginBtn.style.top = ((array[3] - ih) / 2 + bt) + "px";
        loginBtn.style.width = bw + "px";
        loginBtn.style.height = bh + "px";
	}
	
</script>
</head>
<body onload="initForm()">
	<form name="loginForm" action="<c:url value="/login.ord"/>"
		method="post" onSubmit="return checkSafe(this)">
		<div id="loginImg">
			<img border="0" src="<%=request.getContextPath()%>/resources/images/<spring:message code="login.png"/>" usemap="#commandMap">
			<map name="commandMap">
				<area shape="rect" coords="257,335,361,371" tabindex="3" href="<c:url value="/welcome.ord?targetURL=register"/>" alt="Register">
				<area shape="rect" coords="398,335,502,371" tabindex="4" href="#" alt="Cancel">
			</map>
		</div>
		<div id="loginCtrl">
			<table id="login_font" cellpadding="0" cellspacing="0" width="100%" height="100%">
				<tr>
					<td align='center'><spring:message code="login.account"/></td>
					<td><input id="userName" name="userName" type="text" tabindex="1" style="width:130px" onfocus="this.select()"/></td>
				</tr>
				<tr>
					<td align='center'><spring:message code="login.password"/></td>
					<td><input type="password" id="password" name="password" tabindex="2" style="width:130px" onfocus="this.select()"/></td>
				</tr>
			</table>			
		</div>
		<div id="loginBtn">
			<input type="image" src="<c:url value="/resources/images/dologin.gif"/>" name="submit" value="Enter"/>
		</div>
	</form>
</body>
</html>
