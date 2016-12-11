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
<link href="<%=path %>/css/weui.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/jquery-weui.min.css">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/index.css" rel="stylesheet">
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js" ></script>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js" ></script>

</head>
<body id="account" style="background:#fff;"> 
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href='javascript:history.go(-1);' ><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">Doctor details</span>
   <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div>
</header>
<div><a class="my-account" href="#">
        <img class="avater" src="${doctor.pic}">
        <div class="user-info">
            <p class="uname">${doctor.name}<i class="level-icon level2"></i><span class="user-number">Job number ${doctor.code}</span></p>
            <p> ${officename}${titlename}</p>
            <p style=" margin-top:10px;">${hospitalname}</p>
            <div id="guanzhuno" class="guanzhu-btn" onclick="guanzhu('${doctor.code}')" >follow</div>
            <div id="guanzhuyes" class="guanzhu-btn" style="display:none" >Fowllowed</div>
        </div>
</a></div>

<div class="info">
<p>${doctor.info}</p>
</div>
<div class="xiangqing-footer1">
<a class="xiangqing-footer-left" href="#"  onclick="vdialog('liuyan','${doctor.code}')" >Leave a message</a>
<a class="xiangqing-footer-right" href="#" onclick="vdialog('yeyue','${doctor.code}')" >make an appointment</a>
</div>
</body>
<script type="text/javascript"  src="<%=path %>/js/common.js"></script>
<script  type="text/javascript" >
$(document).ready(function(e) {
	var guanzhu = '${guanzhu}';
	if(guanzhu == 'yes'){
		$("#guanzhuno").hide();
		$("#guanzhuyes").show();
	}else{
		$("#guanzhuno").show();
		$("#guanzhuyes").hide();
	}
});
function vdialog(type,id){
	window.location='<%=path %>/yuyue/dialog.html?type='+type+'&doctorcode='+id;
}
function guanzhu(doctorcode){
	$.ajax({
		url:"<%=path %>/yuyue/guanzhu.json?doctorcode="+doctorcode+"&ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		success:function(data){
			if(data.code == 1){
				 $.toast("Followed", function() {
					 console.log('close');
					    $("#guanzhuno").hide();
						$("#guanzhuyes").show();
			        });
			}else{
				 $.toast("Follow failed", "cancel", function() {
			          console.log('close');
			        });
			}
		}
	});
}
</script>
</html>