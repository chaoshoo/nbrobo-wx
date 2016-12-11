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
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/index.css" rel="stylesheet">
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js" ></script>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js" ></script>

<style>
.yuyue {
    position: absolute;
	width:50px
    height: 100%;
    top: 0px;
    right: 0.6rem;
    display: block;
	line-height:47px;
	background:none !important;
    color:#666;
}
</style>
</head>
<body id="account"> 
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" onclick="history.go(-1)"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">预约详情</span>
 <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div>
</header>
<div><a class="my-account" href="#" onclick="doctor(${doctor.id})">
        <img class="avater" src="${doctor.pic}">
        <div class="user-info more more-weak">    
            <p class="uname">${doctor.name}<i class="level-icon level2"></i><span class="user-number">工号 ${doctor.code}</span></p>
            <p> ${officename}&nbsp;${titlename}</p>
            <p style=" margin-top:10px;">${hospitalname}</p>
        </div>
</a></div>

<dl id="yuyuelist" class="list">
<!-- 
  <dd><a class="react" href="#">
    <div> <i class="text-icon order-jiudian order-icon">约</i>预约号 <span class="green">20151544</span>
    </div> 
    </a> <span class="yuyue">未视频</span> </dd>
 -->
</dl>
<!-- 
<dl class="list">
  <dd><a class="react" href="#">
    <div> <i class="text-icon order-lottery order-icon">信</i>其他信息 </div>
    </a> <span class="yuyue">注释</span> </dd>
</dl>
 -->
<div class="xiangqing-footer">
<a class="xiangqing-footer-left" href="#"  onclick="vdialog('liuyan','${doctor.code}')">留言</a>
<a class="xiangqing-footer-right" href="#" onclick="vdialog('yeyue','${doctor.code}')">预约</a>
</div>
<script  type="text/javascript">
$(document).ready(function(e){
	yuyuelist();
});
function yuyuelist(){
	$.ajax({
		url:"<%=path%>/yuyue/yuyuehislist.json?doctorcode=${doctorcode}&ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"size=30",
		success:function(data){
			var yuyuelisthtml = "";
			$.each(data, function(i, item){
				yuyuelisthtml += '<dd><a class="react" href="#">'
								+'<div> <i class="text-icon order-jiudian order-icon">约</i>预约时间 <span class="green">'+item.ORDER_TIMESTR+'</span>';
								if(item.ISZD=='2' || item.ISDEAL){
									yuyuelisthtml += ' </div> </a> <span class="yuyue">医生拒绝</span> </dd>';
								}else if(item.CREATE_TIMESTR==undefined){
									yuyuelisthtml += ' </div> </a> <span class="yuyue">未视频</span> </dd>';
								}else{
									yuyuelisthtml += ' </div> </a> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;视频时间&nbsp;&nbsp;'+item.CREATE_TIMESTR+'</span> </dd>';
								}
				            
			 });
			$("#yuyuelist").html(yuyuelisthtml);
		}
	});
}

function vdialog(type,id){
	window.location='<%=path %>/yuyue/dialog.html?type='+type+'&doctorcode='+id;
}
function doctor(id){
	window.location='<%=path %>/yuyue/doctordetail.html?id='+id;
}
</script>

</body>
</html>