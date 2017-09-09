
<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="conant.ico" />
<title>view order</title>
<link href="<c:url value="/resources/style/style.css"/>" type="text/css"
	rel="stylesheet" />
<script language="JavaScript" src="resources/js/common.js"></script>
<script language="javascript" type="text/javascript">
	function printOrder()
	{
		var htmlcode = document.body.innerHTML;
		var printDiv = document.getElementById("printcontent");
		var printhtml = printDiv.innerHTML;
		window.document.body.innerHTML = printhtml;
		window.print();
		//window.document.body.innerHTML = htmlcode;
		reload();
	}
	function printDoubleOrder()
	{
		var htmlcode = document.body.innerHTML;
		var printDiv = document.getElementById("printcontent");
		var printhtml = printDiv.innerHTML + "<br>" + printDiv.innerHTML;
		window.document.body.innerHTML = printhtml;
		window.print();
		//window.document.body.innerHTML = htmlcode;
		reload();
	}
	function reload()
	{
		history.go(0);
	}
	function handleHelpTooltipMouseOver1(event)
	{
		var content = "<p><spring:message code="cvo.tooltip.orderprint"/></p>";
		var oevent = event || window.event;
		handleHelpTooltipMouseOver(oevent, content);
	}
	function handleHelpTooltipMouseOver2(event)
	{
		var content = "<p><spring:message code="cvo.tooltip.dblorderprint"/></p>";
		var oevent = event || window.event;
		handleHelpTooltipMouseOver(oevent, content);
	}
	function handleHelpTooltipMouseOver(oevent, content)
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

	function handleHelpTooltipMouseOut(event)
	{
		var el = document.getElementById('helpTooltip');
		el.parentNode.removeChild(el);
	}

	function enableHelpTooltips()
	{
		var orderprints =  document.getElementsByName('orderprint');
		var dblorderprints =  document.getElementsByName('dblorderprint');
		var i, el;
		for(i = 0; i < orderprints.length; i++)
		{
			el = orderprints[i];
			el.onmouseover = handleHelpTooltipMouseOver1;
			el.onmouseout = handleHelpTooltipMouseOut;
		}
		for(i = 0; i < dblorderprints.length; i++)
		{
			el = dblorderprints[i];
			el.onmouseover = handleHelpTooltipMouseOver2;
			el.onmouseout = handleHelpTooltipMouseOut;
		}
	}
</script>
</head>
<body>

<%@ include file="Client_ViewOrder_NavBar.jsp"%>
<br />
<div id="printcontent"><%@ include file="ViewOrder.jsp"%>
</div>
<br />
<%@ include file="Client_ViewOrder_NavBar.jsp"%>
<script> 
  enableHelpTooltips();
</script> 
</body>
</html>
