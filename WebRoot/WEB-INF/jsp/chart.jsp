<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Residents health management service platform</title>
<meta name="meituan_check">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="applicable-device" content="mobile">
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<meta name="for" content="meituan.com">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/index.css" rel="stylesheet">
</head>
<body id="account">
<iframe id="myiframe" src="${url}" height="100%" width="100%"  frameborder="0">
</iframe>
</body>
<script type="text/javascript"  src="<%=path %>/js/jquery.1.7.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
	var ifm= document.getElementById("myiframe");
	ifm.height=document.documentElement.clientHeight;
});
</script>

</html>