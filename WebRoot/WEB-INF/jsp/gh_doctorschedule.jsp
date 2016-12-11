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
<link href="<%=path %>/css/yuyue.css" rel="stylesheet">
<script type="application/javascript"
	src="<%=path%>/jquery-weui/0.8.0/js/jquery.min.js"></script>
<script type="application/javascript"
	src="<%=path%>/jquery-weui/0.8.0/js/jquery-weui.min.js"></script>
<style>
#doctorlist .weui_media_ft{ width: auto;}
#doctorlist	.weui_media_ft_time{
	float: none;
		display: inline-block;
		margin-top: 28px;
}
#doctorlist .weui_media_ft_disbtn{
	float: none;
	display: inline-block;
	margin-top: 21px;
}
</style>
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" onclick="history.go(-1)"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1"> The doctor schedule list </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div></header>
<div class="cur-control-con" >
 <input type="hidden" name="hosid" id="hosid" value="${hosid}">
        <input type="hidden" name="deptid" id="deptid" value="${deptid}">
         <input type="hidden" name="docid" id="docid" value="${docid}">

    <div id="doctorlist" class="weui_panel_bd"> 
       </div>

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
	var deptid = $("#deptid").val();
	var docid = $("#docid").val();
	$.ajax({
		url:"api.json?type=doctorschedule&ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"&docid="+docid+"&hosid="+hosid+"&deptid="+deptid,
		success:function(data){
			var doctorlist = "";
			var x = 0;
			var success = data.success;
			if(success=='true'){
				$.each(data.list, function(i, item){   
						doctorlist += '<span  class="weui_media_box weui_media_appmsg" >'
					    +'<div class="weui_media_bd">'
					    +'<h4 class="weui_media_title">'+item.outpdate+'('+item.timeinterval+')</h4>'
					    +'<p class="weui_media_desc">'+item.validflagstr+'</p>'
					    +'</div><div class="weui_media_ft"> <span class="weui_media_ft_time">￥'+item.orderfee+'</span> ';
					    if(item.validflag=='1' || item.validflag=='7'  ){
					    	doctorlist += '<span class="weui_media_ft_btn" onclick="yuyue('+item.validflag+',\''+item.scheduleid+'\',\''+item.outpdate+'\',\''+item.timeinterval+'\',\''+item.orderfee+'\');">Register</span> </div>';
					    }else{
					    	doctorlist += '<span class="weui_media_ft_disbtn" >Register</span> </div>';
					    }
					    doctorlist += '</span>';
					    x = x+1;
				 });
				$("#doctorlist").html(doctorlist);
			}else{
				//alert('未查询到该医生排班记录');
				doctorlist += '<span class="weui_media_box weui_media_appmsg" >'
				    +'<div class="weui_media_bd">'
				    +'<h4 class="weui_media_title">The doctor did not inquire into the scheduling records</h4>'
				    +'</div></span>';
				$("#doctorlist").html(doctorlist);
			}
		}
	});
}

function yuyue(validflag,code,outpdate,timeinterval,orderfee){
	var hosid=$("#hosid").val();
	var deptid=$("#deptid").val();
	var docid=$("#docid").val();
	if(validflag == '7'){
		window.location='<%=path %>/guahao/parttime.html?docid='+docid+'&hosid='+hosid+'&deptid='+deptid+'&scheduleid='+code+'&validflag='+validflag+'&outpdate='+outpdate+'&timeinterval='+timeinterval+'&orderfee='+orderfee;
	}else{
		window.location='<%=path %>/guahao/patient.html?docid='+docid+'&hosid='+hosid+'&deptid='+deptid+'&scheduleid='+code+'&partscheduleid='+code+'&outpdate='+outpdate+'&timeinterval='+timeinterval+'&orderfee='+orderfee;
	}
}
</script>
</body>
</html>