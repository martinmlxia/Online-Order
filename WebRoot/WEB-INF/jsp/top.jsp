
<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>top</title>
<style type="text/css">
<!--
.STYLE9 {font-family: "ËÎÌå", verdana, arial, sans-serif; font-size: 12px; color: #444444; font-weight: bold; }
.STYLE10 {font-size: 12px; color: #444444; font-family: "ËÎÌå", verdana, arial, sans-serif;}
h1,h2,h3,h4,h5,h6 {
	font-family: TOP;
}
-->
</style>
<script>
	function confirmExit()
	{
		window.parent.close();
	}
</script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <th style="text-aligh:center;"width="680" height="84" align="center" valign="absmiddle" background="<%=request.getContextPath()%>/resources/images/top.jpg" scope="col"><img src="<%=request.getContextPath()%>/resources/images/logo.png"/></th>
    <th style="text-aligh:center;"width="680" height="84" align="center" valign="absmiddle" background="<%=request.getContextPath()%>/resources/images/top.jpg" scope="col"><img src="<%=request.getContextPath()%>/resources/images/name.png" width="598" height="47" id="Image1" /></th>
    <th width="39%" align="right" background="<%=request.getContextPath()%>/resources/images/top.jpg" scope="col"><p><img src="<%=request.getContextPath()%>/resources/images/LOGO1.png" width="182" height="18" id="Image2" /></p>
    <p>&nbsp;</p></th>
  </tr>
</table>
<table width="100%" height="6" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th height="6" background="<%=request.getContextPath()%>/resources/images/holine.gif" scope="col"></th>
  </tr>
</table>
<table width="100%" height="26" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th height="26" background="<%=request.getContextPath()%>/resources/images/ho1.jpg" scope="col">
    <table width="40%" height="16" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
         <th width="35%" valign="middle" align="right" class="STYLE10" scope="col">
          <c:if test="${user_name=='admin'}">
          	 <a target="mainFrame" href="/Live-Order/jsp/common/delete_order.jsp">É¾³ý¶©µ¥</a>
           </c:if>
        </th>
        <th width="20%" valign="middle" align="right" style="padding-right:10px;" scope="col">
        	<span class="STYLE9"><spring:message code="nav.welcome"/>&nbsp;<%=(String)request.getSession().getAttribute("user_name")%></span>
        </th>
        <th width="10%" valign="middle" scope="col">
        	<img src="<%=request.getContextPath()%>/resources/images/cart.gif"  height="20"/>
        </th>
         <th width="15%" align="left" valign="middle" class="STYLE10" scope="col">
        	<a target="mainFrame" href="/Live-Order/stock.do?action=viewcart"><spring:message code="nav.cart"/></a>
        </th>
        <th width="10%" valign="middle" scope="col">
        	<img src="<%=request.getContextPath()%>/resources/images/Exit_C.gif" width="16" height="20" />
        </th>
        <th width="10%" align="left" valign="middle" class="STYLE10" scope="col">
        	<a href="javascript:confirmExit();"><spring:message code="nav.exit"/></a>
        </th>
      </tr>
    </table>
    </th>
  </tr>
</table>
</body>
</html>
