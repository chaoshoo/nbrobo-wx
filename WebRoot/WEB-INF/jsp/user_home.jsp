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
<title>环球健康管理平台</title>
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

<style>
.status_ready{ display:inline-block; width:8px; height:8px; background:red; border-radius:50%; margin-left:20px; position:relative; top:-2px;}
.status_end{ display:inline-block; width:8px; height:8px; background:#fff; border-radius:50%; margin-left:20px; position:relative; top:-2px;}
.index-top{ padding-bottom:20px; padding-top:15px;}
</style>

</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html" ><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">我的信息</span>
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div>
</header>
<a href="#"  style="display:block">
<div class="index-top"> <img src="${heard_img_url}" width="100">
  <div class="username white">${vip.nick_name}</div>
  <div class="userinfo"><span class="white">${vip.sexstr}</span>&nbsp;&nbsp;<span class="white">${vip.age}岁</span>&nbsp;&nbsp;<span class="white">${vip.areaname}</span></div>
</div>
</a>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="history('yuyue')">
    <div class="more more-weak" > <i class="text-icon order-jiudian order-icon">约</i>我的预约<!--<span class="status_ready"></span> <span class="more-after"></span> --> 
    </div>
    </a></dd>
</dl>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="history('liuyan')">
    <div class="more more-weak"> <i class="text-icon order-jiudian order-icon" style="background:#8ce88e;">留</i>我的留言<!--<span class="status_ready"></span>  <span class="more-after"></span> --> 
    </div>
    </a></dd>
</dl>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="doctor()">
    <div class="more more-weak"> <i class="text-icon order-jiudian order-icon" style="background:#3cd9b3;">医</i>我的医生<!--<span class="status_ready"></span>  <span class="more-after"></span> --> 
    </div>
    </a></dd>
</dl>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="member();">
    <div class="more more-weak"> <i class="text-icon order-lottery order-icon">家</i>我的家庭成员 </div>
    </a></dd>
</dl>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="guahao();">
    <div class="more more-weak"> <i class="text-icon order-lottery order-icon" style="background:#6e65f5;">挂</i>我的挂号 </div>
    </a></dd>
</dl>
<!-- 
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="bingli();">
    <div class="more more-weak"> <i class="text-icon order-lottery order-icon" style="background:#9e95f5;">病</i>我的病历 </div>
    </a></dd>
</dl> -->
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="updateuser()">
    <div class="more more-weak"> <i class="text-icon order-lottery order-icon" style="background:#5e95f5;">改</i>个人资料</div>
    </a></dd>
</dl>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="findPWD()">
    <div class="more more-weak"> <i class="text-icon order-lottery order-icon" style="background:#9d55b8;">改</i>修改密码</div>
    </a></dd>
</dl>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="exit()">
    <div class="more more-weak"> <i class="text-icon order-lottery order-icon" style="background:#c0b5c8;">退</i>退出登录</div>
    </a></dd>
</dl>
<script>
$(document).ready(function(e) {
//    $(".list").click(function(){
//		$(this).find(".status_ready").addClass("status_end").removeClass("status_ready");
//		})
});
function findPWD(){
	window.location='<%=path %>/index/findPSW.html?tel=';
}
function member(){
	window.location='<%=path %>/member/member.html?';
}
function history(type){
	window.location='<%=path %>/yuyue/history.html?type='+type;
}
function updateuser(){
	window.location='<%=path %>/vip/userinfo.html?';
}
function doctor(){
	window.location='<%=path %>/yuyue/doctor.html?my=yes';
}
function exit(){
	window.location='<%=path %>/index/loginout.html?';
}
function guahao(){
	window.location='<%=path %>/guahao/history.html?';
}
function bingli(){
	window.location='<%=path %>/vip/bingli.html?';
}
</script>

</body>
</html>