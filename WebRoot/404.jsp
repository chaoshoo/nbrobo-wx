<%@ page language="java" import="java.util.*" isErrorPage="true" pageEncoding="utf-8"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
		<title>404</title>
		<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	 	<link rel="stylesheet" href="css/error.css" />
  </head>
  
  <body>
	<div class="content">
    	<img class="error" src="img/404.png"/>
 	 </div>

  </body>
</html>