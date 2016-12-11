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
<link href="<%=path %>/css/yuyue.css" rel="stylesheet">
<script type="application/javascript"
	src="<%=path%>/jquery-weui/0.8.0/js/jquery.min.js"></script>
<script type="application/javascript"
	src="<%=path%>/jquery-weui/0.8.0/js/jquery-weui.min.js"></script>
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" onclick="history.go(-1)"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1"> 医生分时排班列表 </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div></header>
<div class="cur-control-con" >
 <input type="hidden" name="hosid" id="hosid" value="${hosid}">
        <input type="hidden" name="deptid" id="deptid" value="${deptid}">
         <input type="hidden" name="docid" id="docid" value="${docid}">
<input type="hidden" name="scheduleid" id="scheduleid" value="${scheduleid}">
<input type="hidden" name="outpdate" id="outpdate" value="${outpdate}">
<input type="hidden" name="timeinterval" id="timeinterval" value="${timeinterval}">
<input type="hidden" name="orderfee" id="orderfee" value="${orderfee}">
    <div id="doctorlist" class="weui_panel_bd"> 
       </div>

  </div>
<script  type="text/javascript">
$(document).ready(function(e) {
    $(".yuyue-slide-control div").click(function(){
		$(this).siblings().removeClass("cur-control");
		$(this).addClass("cur-control");
		$(".cur-control-con").hide();
		$(".cur-control-con").eq($(this).index()).fadeIn()
	})
	list();
});
function list(){
	var hosid = $("#hosid").val();
	var scheduleid = $("#scheduleid").val();
	$.ajax({
		url:"api.json?type=parttime&ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"&scheduleid="+scheduleid+"&hosid="+hosid,
		success:function(data){
			var doctorlist = "";
			var x = 0;
			var success = data.success;
			if(success=='true'){
				$.each(data.list, function(i, item){   
						doctorlist += '<span href="#"  class="weui_media_box weui_media_appmsg" >'
					    +'<div class="weui_media_bd">'
					    +'<h4 class="weui_media_title">'+item.bstp+'-'+item.estp+'</h4>'
					    +'</div><div class="weui_media_ft">';
					    if(item.regflag=='1' ){
					    	doctorlist += '<span class="weui_media_ft_btn" onclick="yuyue(\''+item.partscheduleid+'\',\''+item.bstp+'\',\''+item.estp+'\');">挂号</span> </div>';
					    }else{
					    	doctorlist += '<span class="weui_media_ft_disbtn" >挂号</span> </div>';
					    }
					    doctorlist += '</span>';
					    x = x+1;
				 });
				$("#doctorlist").html(doctorlist);
			}else{
				//alert('获取挂号网数据失败');
				doctorlist += '<span class="weui_media_box weui_media_appmsg" >'
				    +'<div class="weui_media_bd">'
				    +'<h4 class="weui_media_title">未查询到该医生分时排班记录</h4>'
				    +'</div></span>';
				$("#doctorlist").html(doctorlist);
			}
		}
	});
}

function yuyue(code,bstp,estp){
	var hosid=$("#hosid").val();
	var deptid=$("#deptid").val();
	var docid=$("#docid").val();
	var scheduleid=$("#scheduleid").val();
	var outpdate=$("#outpdate").val();
	var timeinterval=$("#timeinterval").val();
	var orderfee=$("#orderfee").val();
	window.location='<%=path %>/guahao/patient.html?docid='+docid+'&hosid='+hosid+'&deptid='+deptid+'&scheduleid='+scheduleid+'&partscheduleid='+code+'&outpdate='+outpdate+'&timeinterval='+timeinterval+'&orderfee='+orderfee+'&parttime='+bstp+'-'+estp;
}
</script>
</body>
</html>