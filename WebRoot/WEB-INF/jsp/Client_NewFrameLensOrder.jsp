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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="conant.ico"/>
<title>New Order</title>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<c:url value="/resources/js/common.js"/>"></script>
<script language="javascript" type="text/javascript">
	function showHelpTooltip(oevent, content)
	{
		var oSource = oevent.target || oevent.srcElement;
		
		var el = document.createElement('div');
		el.id = 'helpTooltip';
		el.innerHTML = content;
		el.style.top = 0;
		el.style.left = 0;
		el.style.visibility = 'hidden';
		document.body.appendChild(el);
		
		var pos = fnGetElementPos(oSource);
		el.style.top = pos[1] + 5;
		el.style.left = pos[0] + 40;
		el.style.visibility = 'visible';
	}
	function handleHelpTooltipMouseOver_customcolor(event)
	{
		var content = "<p><spring:message code="tint.tooltip.custom"/></p>";
		var oevent = event || window.event;
		showHelpTooltip(oevent, content);
	}
	function handleHelpTooltipMouseOver_lenstype_omega(event)
	{
		var content = "<img border='0' src='<c:url value="/resources/images/omega.gif"></c:url>'/>";
		var oevent = event || window.event;
		showHelpTooltip(oevent, content);
	}
	function handleHelpTooltipMouseOver_lenstype_strawhat(event)
	{
		var content = "<img border='0' src='<c:url value="/resources/images/strawhat.gif"></c:url>'/>";
		var oevent = event || window.event;
		showHelpTooltip(oevent, content);
	}
	function handleHelpTooltipMouseOut(event)
	{
		var el = document.getElementById('helpTooltip');
		el.parentNode.removeChild(el);
	}
	function enableHelpTooltips()
	{
		var el = document.getElementById('float_custom_color');
		el.onmouseover = handleHelpTooltipMouseOver_customcolor;
		el.onmouseout = handleHelpTooltipMouseOut;
		el = document.getElementById('float_lenstype_omega');
		if(el!=null){
		  el.onmouseover = handleHelpTooltipMouseOver_lenstype_omega;
		  el.onmouseout = handleHelpTooltipMouseOut;
		}
		el = document.getElementById('float_lenstype_strawhat');
		if(el!=null){
		  el.onmouseover = handleHelpTooltipMouseOver_lenstype_strawhat;
		  el.onmouseout = handleHelpTooltipMouseOut;
		}
	}
</script>

</head>

<body>

<form action="<c:url value="/addFrameLensOrder.ord"/>" method="post">

<%@ include file="EditFrameLensOrder.jsp"%>
<br/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="middle">
			<input type="reset" name="reset" class="button" value="<spring:message code="operation.reset"/>"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <input name="submitok" type="submit" class="button" onclick="this.disabled=true;this.form.submit();" value="<spring:message code="operation.add"/>"/>
			&nbsp;&nbsp;&nbsp;&nbsp;<label><input name="printAfterCommit" type="checkbox" <c:if test="${order.printAfterCommit}">checked</c:if>/><spring:message code="operation.printAfterCommit"/></label>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
 enableHelpTooltips();
</script>
<c:if test="${lang.language != 'zh'}">
<script type="text/javascript" src="<c:url value="/resources/js/getPrices.js"/>"></script>
<script language="javascript" src="<c:url value="/resources/js/data.js"/>"></script>
<script type="text/javascript">
 new Ajax.Request(
   "/Live-Order/invoice.do?action=getPrices&uid=${sessionScope.user_id}&t="+(new Date()).getTime(),{
   method : "get",
   onSuccess : function(request){
        var response = request.responseText;
	    result = response.evalJSON();
	    addEvent();
     } 
   }
 );
 initData();
</script>
</c:if>