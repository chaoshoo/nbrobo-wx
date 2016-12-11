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
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js" ></script>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js" ></script>
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">
  预约记录
  </span>
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div> </header>

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
    <div id="yuyuelist" class="weui_cells weui_cells_access" style=" margin-top:0px;"> 
    <!--  
    <a class="weui_cell" href="javascript:;">
      <div class="weui_cell_hd"><img src="img/index_user_face.png" alt="" style="width:40px;margin-right:5px;display:block"></div>
      <div class="weui_cell_bd weui_cell_primary">
        <p>医师甲</p>
      </div>
      <span class="weui_cell_ft">最近预约时间</span> </a> 
      -->
       </div>
        <div class="weui-infinite-scroll" id="loadingdiv">
	  <div class="infinite-preloader"></div>
	    <!-- 正在加载... -->
	    <span  id="loading">上拉加载更多</span>
	</div>
  </div>
</div>
<script>
$(document).ready(function(e){
	 $("#pageno").val(0);
	yuyuelist();
	var loading = false;  //状态标记
	$(document.body).infinite().on("infinite", function() {
	  if(loading) return;
	  loading = true;
	  setTimeout(function() {
		  yuyuelist();
	    loading = false;
	  }, 1500);   //模拟延迟
	});
});
function yuyuelist(){
	var pagenum = parseInt($("#pageno").val());
	pagenum = pagenum+1;
	var search = $("#search_input").val();
	$.ajax({
		url:"<%=path%>/yuyue/yuyuedoctorlist.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"pageNo="+pagenum+"&search="+search,
		success:function(data){
			var yuyuelisthtml = $("#yuyuelist").html();
			var x = 0;
			$.each(data, function(i, item){
				yuyuelisthtml += '<a class="weui_cell" href="javascript:;" onclick="doctor(\''+item.DOCTOR_CODE+'\')"'
								+'<div class="weui_cell_hd"><img src="'+item.PIC+'" style="width:40px;margin-right:5px;display:block"></div>'
				               +' <div class="weui_cell_bd weui_cell_primary"><p>'+item.DOCTORNAME+'</p></div>'
				               +'<span class="weui_cell_ft">'+item.ORDER_TIMESTR+'</span> </a> ';
				x = x+1;         
			 });
			$("#yuyuelist").html(yuyuelisthtml);
			
			if(x<8){
				$("#loadingdiv").hide();
			}
			if(x>0){
				$("#pageno").val(pagenum);
			}
		}
	});
}

function doctor(doctor_code){
	window.location='<%=path %>/yuyue/historydetail.html?type=yuyue&doctorcode='+doctor_code;
}
</script>
</body>
</html>