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
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/jquery-weui.min.css">
<link href="<%=path %>/css/weui.css" rel="stylesheet">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/yuyue.css" rel="stylesheet">
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">
  <div class="yuyue-slide-control">
    <div id="keshicontrol" class="left-control cur-control">科室</div>
    <div  id="yiyuancontrol" class="left-control">医院</div>
  </div>
  </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div></header>
<div class="cur-control-con">
  <div class="weui_search_bar" id="search_bar">
    <form class="weui_search_outer">
      <div class="weui_search_inner"> <i class="weui_icon_search"></i>     
        <input type="search" class="weui_search_input" id="search_input" name="search_input" placeholder="搜索" value="${search_input}" required>
        <a href="javascript:" class="weui_icon_clear" id="search_clear"></a> </div>
      <label for="search_input" class="weui_search_text" id="search_text"> <i class="weui_icon_search"></i> <span>搜索</span> </label>
    </form>
    <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a> </div>
  <div class="weui_grids" id="keshigrid"></div>
</div>
<div class="cur-control-con" style="display:none;">
  <div class="weui_search_bar" id="search_bar">
    <form class="weui_search_outer">
      <div class="weui_search_inner"> <i class="weui_icon_search"></i>
        <input type="hidden" name="type" id="tabtype" value="${type}" />
        <input type="search" class="weui_search_input" id="search_input2" name="search_input2" placeholder="搜索" value="${search_input2}" required>
        <a href="javascript:" class="weui_icon_clear" id="search_clear"></a> </div>
      <label for="search_input" class="weui_search_text" id="search_text"> <i class="weui_icon_search"></i> <span>搜索</span> </label>
    </form>
    <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a> </div>
    <input type="hidden" name="pageno" id="pageno" value="0"/>
  <div class="weui_panel_con">
    <div class="weui_cells weui_cells_access" style=" margin-top:0px;" id="yiyuangrid"> 
       </div>
       <div class="weui-infinite-scroll" id="loadingdiv">
	  <div class="infinite-preloader"></div>
	    <!-- 正在加载... -->
	    <span  id="loading">上拉加载更多</span>
	</div>
  </div>
</div>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js" ></script>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js" ></script>
<script type="text/javascript"  src="<%=path %>/js/common.js"></script>
<script  type="text/javascript" >
var inityiyuan = false;
$(document).ready(function(e) {
	$("#pageno").val(0);
    $(".yuyue-slide-control div").click(function(){
		$(this).siblings().removeClass("cur-control");
		$(this).addClass("cur-control");
		$(".cur-control-con").hide();
		$(".cur-control-con").eq($(this).index()).fadeIn()
		if($(this).index() == 1){
			if(!inityiyuan){
				yiyuan();
			}
			$("#tabtype").val("yiyuan");
		}else if($(this).index() == 0){
			keshi();
			$("#tabtype").val("keshi");
		}
	});
    if($("#tabtype").val()=='yiyuan'){
    	 $("#yiyuancontrol").click();
    }else{
    	keshi();
    }
	
	var loading = false;  //状态标记
	$(document.body).infinite().on("infinite", function() {
	  if(loading) return;
	  loading = true;
	  setTimeout(function() {
		  yiyuan();
	    loading = false;
	  }, 1500);   //模拟延迟
	});
});

function keshi(){
	var name = $("#search_input").val();
    //alert(name)
	var password = $("#password").val();
	$.ajax({
		url:"keshilist.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"name="+name,
		success:function(data){
			var keshi = "";
			$.each(data, function(i, item){   
				keshi += '<a href="javascript:void(0);" onclick="doctor(\'office_code\',\''+item.CODE+'\');" class="weui_grid">'
			    +'<div class="weui_grid_icon icon-niaosuan"> <img src="'+item.PIC+'" width="50" height="50"> </div>'
			    +'<p class="weui_grid_label">'+item.NAME+'</p> </a>';
			 });
			$("#keshigrid").html(keshi);
		}
	});
}


function yiyuan(){
	inityiyuan = true;
	var password = $("#password").val();
	var pagenum = parseInt($("#pageno").val());
	pagenum = pagenum+1;
	var search = $("#search_input2").val();
	$.ajax({
		url:"yiyuanlist.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"search="+search+"&pageNo="+pagenum,
		success:function(data){
			var yiyuan = $("#yiyuangrid").html();
			var x = 0;
			$.each(data, function(i, item){   
				x = x+1;
				yiyuan += '<a href="javascript:void(0);" onclick="doctor(\'hospital_code\',\''+item.CODE+'\');" class="weui_cell">'
			    +'<div class="weui_cell_hd"> <img src="'+item.PIC+'" alt="" style="width:40px;margin-right:5px;display:block"> </div>'
			    +'<div class="weui_cell_bd weui_cell_primary">'
			    +'<p >'+item.NAME+'('+item.LEVERSTR+')</p>'
			    +'</div><span class="weui_cell_ft"></span> </a>';
			 });
			$("#yiyuangrid").html(yiyuan);
			if(x<8){
				$("#loadingdiv").hide();
			}
			if(x>0){
				$("#pageno").val(pagenum);
			}
		}
	});
}

function doctor(type,code){
	window.location='<%=path %>/yuyue/doctor.html?'+type+'='+code;
}

</script>
</body>
</html>