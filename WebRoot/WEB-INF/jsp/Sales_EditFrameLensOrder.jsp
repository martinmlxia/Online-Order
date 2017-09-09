<%-- 
    Document   : AddLensOrder
    Created on : 2008-11-16, 23:19:30
    Author     : Administrator
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<title>Modify Order</title>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css" rel="stylesheet">
<script language="JavaScript" src="resources/js/common.js"></script>
<script language="javascript" type="text/javascript">
	function handleHelpTooltipMouseOver(event)
	{
		var content = "<p><spring:message code="tint.tooltip.custom"/></p>";
		
		var oevent = event || window.event;
		var oSource = oevent.target || oevent.srcElement;
		
		var el = document.createElement('div');
		el.id = 'helpTooltip';
		el.innerHTML = content;
		el.style.top = 0;
		el.style.left = 0;
		el.style.visibility = 'hidden';
		document.body.appendChild(el);
		
		var pos = fnGetElementPos(oSource);
		el.style.top = pos[1];
		el.style.left = pos[0] + 40;
		el.style.visibility = 'visible';
	}
	function handleHelpTooltipMouseOut(event)
	{
		var el = document.getElementById('helpTooltip');
		el.parentNode.removeChild(el);
	}
	function enableHelpTooltips()
	{
		var el = document.getElementById('custom_color');
		el.onmouseover = handleHelpTooltipMouseOver;
		el.onmouseout = handleHelpTooltipMouseOut;
	}
</script>

</head>

<body>

<form action="<c:url value="${commitUrl}"/>" method="post">

<%@ include file="EditFrameLensOrder.jsp"%>
<br/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
			<input name="submitok" type="submit" class="button" value="<spring:message code="operation.save"/>"/>
			&nbsp;&nbsp;<input type="button" class="button" value="<spring:message code="operation.back"/>" onclick="javascript:window.location.href='<c:url value="${returnUrl}"/>'"/>
		</td>
	</tr>
</table>

</form>

<script> 
  enableHelpTooltips();
</script> 

</body>

</html>
<c:if test="${lang.language != 'zh'}">
<script type="text/javascript" src="<c:url value="/resources/js/getPrices.js"/>"></script>
<script language="javascript" src="<c:url value="/resources/js/data.js"/>"></script>
<script type="text/javascript">
<spring:bind path="order.userid">
 new Ajax.Request(
   "/Live-Order/invoice.do?action=getPrices&uid=${status.value}",{
   method : "get",
   onSuccess : function(request){
        var response = request.responseText;
	    result = response.evalJSON();
	    addEvent();
     } 
   }
 );
  initData();
 </spring:bind>
</script>
</c:if>