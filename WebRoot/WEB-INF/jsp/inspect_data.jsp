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
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/jquery-weui.min.css">
<link href="<%=path %>/css/weui.css" rel="stylesheet">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/yuyue.css" rel="stylesheet">
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">
	Urine routine test record
  </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div></header>

<div class="cur-control-con">
   
    <input type="hidden" name="pageno" id="pageno" value="0"/>
  <div class="weui_panel_con">
    <div class="weui_cells weui_cells_access" style=" margin-top:0px;" id="datagrid"> 
       </div>
       <div id="nodata">
       <span class="weui_cell">
       <div  class="weui_cell_bd weui_cell_primary"><span>No test records</span></div>
       </span>
       </div>
       <div class="weui-infinite-scroll" id="loadingdiv">
	  <div class="infinite-preloader"></div>
	    <!-- Being loaded... -->
	    <span  id="loading">Pull up load more</span>
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
	history();
	var loading = false;  //State mark

	$(document.body).infinite().on("infinite", function() {
	  if(loading) return;
	  loading = true;
	  setTimeout(function() {
		  yiyuan();
	    loading = false;
	  }, 1500);   //Simulation delay
	});
});

function history(){
	var pagenum = parseInt($("#pageno").val());
	pagenum = pagenum+1;
	$.ajax({
		url:"historylist.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"rowstart="+pagenum,
		success:function(data){
			var data0 = $("#datagrid").html();
			var x = 0;
			var success = data.success;
			if(success=='true'){
				$.each(data.list, function(i, item){   
					x = x+1;
					data0 += '<a href="javascript:void(0);" onclick="orderinfo(\''+item.ORDERID+'\');" class="weui_cell">'
				    +'<div class="weui_cell_bd weui_cell_primary">'
				    +'<p>'+item.OUTPDATE+'</p></div>'
				    +'<div class="weui_cell_ft">'+item.STATUSSTR+'</div>';
				    +' </a>';
				 });
				$("#datagrid").html(data0);
				if(x<15){
					$("#loadingdiv").hide();
				}
				if(x>0){
					$("#pageno").val(pagenum);
				}
			}else{
				//alert('获取数据失败');
			}
			if(data0.length < 10){
				$("#nodata").show();
			}else{
				$("#nodata").hide();
			}
		}
	});
}

function orderinfo(orderid){
	window.location='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfd65d5401333bc07&redirect_uri=http://wx.nbrobo.com/guahao/orderinfo.html?orderid='+orderid+'&response_type=code&scope=snsapi_base&state=123#wechat_redirect';
}

</script>
</body>
</html>