<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>download</title>
  </head>
  <body>
    <%
      response.setHeader("Content-disposition","attachment; filename="+request.getContextPath()+"/resources/Conant Optical Order InputTemplate_cn.xls");
    %>
  </body>
</html>
