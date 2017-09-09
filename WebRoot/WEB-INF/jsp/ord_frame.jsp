
<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="shortcut icon" href="conant.ico"/>
<title><spring:message code="frame.caption"/></title>
</head>

<frameset rows="*,43" cols="*" framespacing="0" frameborder="no" border="0">
  <frameset rows="118,*" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="<%=request.getContextPath()%>/welcome.ord?targetURL=top" name="topFrame" scrolling="No" noresize="noresize" marginwidth="10" marginheight="0" id="topFrame" title="topFrame" />
		<frameset id="frame" rows="*" cols="275,10,*" framespacing="0" frameborder="no" border="0">
			<frame src="<%=request.getContextPath()%>/menu.ord?targetURL=menu" name="menuFrame" scrolling="No" noresize="noresize" marginwidth="10" marginheight="0" id="menuFrame" title="menuFrame" />
			<frame src="<%=request.getContextPath()%>/welcome.ord?targetURL=bar" name="barFrame" noresize scrolling="No"/>
			<frame src="<%=request.getContextPath()%>/welcome.html" name="mainFrame" marginwidth="1" marginheight="0" id="mainFrame" title="mainFrame" />
		</frameset>
  </frameset>
  <frame src="<%=request.getContextPath()%>/welcome.ord?targetURL=bottom" name="bottomFrame" scrolling="No" noresize="noresize" marginwidth="10" marginheight="0" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>
