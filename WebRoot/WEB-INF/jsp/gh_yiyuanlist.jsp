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
	医院列表
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
	yiyuan();
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

function yiyuan(){
	var pagenum = parseInt($("#pageno").val());
	pagenum = pagenum+1;
	var search = $("#search_input").val();
	$.ajax({
		url:"api.json?type=hospitalAllList&ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"hosname="+search+"&rowstart="+pagenum,
		success:function(data){
			var yiyuan = $("#yiyuangrid").html();
			var x = 0;
			var success = data.success;
			if(success=='true'){
				$.each(data.list, function(i, item){   
					x = x+1;
					yiyuan += '<a href="javascript:void(0);" onclick="keshi(\''+item.hosid+'\');" class="weui_cell">'
				    +'<div class="weui_cell_bd weui_cell_primary">'
				    +'<p >'+item.hosname+'</p>'
				    +'</div><span class="weui_cell_ft"></span> </a>';
				 });
				$("#yiyuangrid").html(yiyuan);
				if(x<15){
					$("#loadingdiv").hide();
				}
				if(x>0){
					$("#pageno").val(pagenum);
				}
			}else{
				$.alert('获取挂号网数据失败');
			}
		}
	});
}

function keshi(hosid){
	window.location='<%=path %>/guahao/keshi.html?hosid='+hosid;
}

</script>
</body>
</html>