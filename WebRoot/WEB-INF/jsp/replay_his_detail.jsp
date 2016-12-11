<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>居民健康管理服务平台</title>
<meta name="meituan_check">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="applicable-device" content="mobile">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<meta name="for" content="meituan.com">
<link href="<%=path%>/css/weui.css" rel="stylesheet">
<link rel="stylesheet"
	href="<%=path%>/jquery-weui/0.8.0/css/jquery-weui.min.css">
<link href="<%=path%>/css/find_psw.css" rel="stylesheet">
<link href="<%=path%>/css/common.css" rel="stylesheet">
<link href="<%=path%>/css/yuyue.css" rel="stylesheet">
<script type="application/javascript"
	src="<%=path%>/jquery-weui/0.8.0/js/jquery.min.js"></script>
<script type="application/javascript"
	src="<%=path%>/jquery-weui/0.8.0/js/jquery-weui.min.js"></script>

<style>
.replay-item {
	margin-top: 20px;
	text-align: center
}

.replay-item-title {
	display: inline-block;
	padding: 1px 10px;
	font-size: 12px;
	color: #fff;
	background: #ddd;
	border-radius: 3px;
	margin: 0 auto;
}

.replay-item-info {
	box-sizing: border-box;
	margin: 8px 15px 0px;
	text-align: left;
	background: #fff;
	border-radius: 5px;
	padding: 10px 12px;
}
</style>
</head>
<body id="account">
	<header class="navbar">
		<div class="nav-wrap-left">
			<a class="react back" onclick="history.go(-1)"><i
				class="text-icon icon-back"></i></a>
		</div>
		<span class="nav-header h1"> 留言详情 </span>
		<div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div>
	</header>

	<div id="doctorlist" class="weui_panel_bd">
		<a href="javascript:void(0);" "
			class="weui_media_box weui_media_appmsg"><div
				class="weui_media_hd">
				<img class="weui_media_appmsg_thumb"
					src="/nkywx/img/index_user_face.png" width="60" height="60">
			</div>
			<div class="weui_media_bd">
				<h4 class="weui_media_title">${doctorname}</h4>
				<p class="weui_media_desc">${titlename}</p>
			</div>
			<div class="weui_media_ft">
				<span class="weui_media_ft_time" style="width:200px;margin-top:30px;">${createtime}</span> <span
					class="weui_media_ft_btn"  onclick="doctor('${doctorid}')">预约</span>
			</div></a>	
			<div class="panel" style="padding:15px;">
			<p class="uname"  style="text-align:center; font-size:18px; margin-bottom:10px;">${title}</p>
				<p style="font-size:14px;">${content}</p>
	</div>
	</div>
				

	<div id="liuyanlist" class="cur-control-con">
		<!-- 
  <div class=" replay-item">
  <div class="replay-item-title">我&nbsp;星期天&nbsp;12：50</div>
  <div class="replay-item-info">你好，我是谢晨！前几天在您这边检查了身体，感觉还可以，什么时候可以再来！</div>
  </div>
  -->
	</div>

	<form id="liuyan_form" action="">
		<div class="form-item">
			<input type="text" placeholder="追加留言" class="ruc-input-register-auth"
				id="answer_add" name="answer_add" style=" padding-left: 40px;">
		</div>
		<div class="next-prove-reset" onclick="liuyan()">
			<div>提交留言</div>
		</div>
	</form>
	<script>
$(document).ready(function(e) {
    $(".yuyue-slide-control div").click(function(){
		$(this).siblings().removeClass("cur-control");
		$(this).addClass("cur-control");
		$(".cur-control-con").hide();
		$(".cur-control-con").eq($(this).index()).fadeIn()
	})
	liuyandetail();
});

function liuyandetail(){
	$.ajax({
		url:"<%=path%>/yuyue/replayhislist.json?questionid=${questionid}&ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"size=30",
		success:function(data){
			var liuyanlisthtml = "";
			$.each(data, function(i, item){   
				liuyanlisthtml += '<div class=" replay-item">'
					+'<div class="replay-item-title">'+item.DOCTORNAME+'&nbsp;&nbsp;'+item.CREATE_TIMESTR+'</div>'
	               +'  <div class="replay-item-info">'+item.ANSWER_CONTENT+'</div>'
	               +'</div>';

			 });
			$("#liuyanlist").html(liuyanlisthtml);
		}
	});
}
function liuyan(){
	var ask = $("#answer_add").val();
	if(ask == ''){
		$.alert("请输入追加留言内容。");
		return;
	}
	var questionid = '${questionid}';
	var fromdata = {};
	fromdata['questionid']='${questionid}';
	fromdata['answer']=ask;
	$.ajax({
		url:"zhuijliuyan.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:fromdata,
		success:function(data){
			var doctorlist = "";
			if(data.code == 1){
				 $.toast(" 留言成功", function() {
					// window.location.href='<%=path%>/index/homepage.html';
					  liuyandetail();
					  $("#answer_add").val("");
			        });
			}else{
				 $.toast(" 留言失败", "cancel", function() {
			          console.log('close');
			        });
			}
		}
	});
}

function doctor(id){
	window.location='<%=path%>/yuyue/doctordetail.html?id=' + id;
		}
	</script>
</body>
</html>