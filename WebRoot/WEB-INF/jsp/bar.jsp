
<%@ page contentType="text/html; charset=utf-8" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>bar</title>
<style type="text/css">
<!--
#Layer1 {
	position:absolute;
	left:-2px;
	top:299px;
	width:7px;
	height:93px;
	z-index:1;
}
body {
	background-image: url(Images/b.png);
}
-->
</style>
</head>
<script language="JavaScript">
function preloadImg(src)
   {
    var img=new Image();
    img.src=src;    
   }
   preloadImg("<%=request.getContextPath()%>/resources/images/Sidebar_O.gif");
   
    var displayBar=true;
    
    function switchBar(obj)
    {   
        if(displayBar)
        {
            parent.frame.cols="0,10,*";
            displayBar=false;
            obj.src="<%=request.getContextPath()%>/resources/images/Sidebar_O.gif";
            obj.title="open left menu";
         }
          else
          {
            parent.frame.cols="275,10,*";
            displayBar=true;
            obj.src="<%=request.getContextPath()%>/resources/images/Sidebar_C.gif";
            obj.title="close left menu";
          }
   }
</script>
<body>

<div id="Layer1"><img src="<%=request.getContextPath()%>/resources/images/Sidebar_C.gif" width="8" height="47" onclick="switchBar(this)" /> </div>
</body>
</html>
