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
<title>居民健康管理服务平台</title>
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
#doctorlist	.weui_media_ft_time{ height: 20px; overflow: hidden; line-height: 20px;width:120px;}
</style>
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" onclick="keshi()"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1"> 医生列表 </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div></header>
<div class="cur-control-con" >
  <div class="weui_search_bar" id="search_bar">
    <form class="weui_search_outer">
       <input type="hidden" name="hosid" id="hosid" value="${hosid}">
        <input type="hidden" name="deptid" id="deptid" value="${deptid}">
        <!-- 
      <div class="weui_search_inner"> <i class="weui_icon_search"></i>
        <input type="search" class="weui_search_input" id="search_input" name="search_input" placeholder="搜索"  value="${search_input}"  required>
        <a href="javascript:" class="weui_icon_clear" id="search_clear"></a> </div>
      <label for="search_input" class="weui_search_text" id="search_text"> <i class="weui_icon_search"></i> <span>搜索</span> </label>
    -->
    </form>
    <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a> </div>
    <input type="hidden" name="pageno" id="pageno" value="0"/>

    <div id="doctorlist" class="weui_panel_bd"> 
    <!-- 
    <a href="javascript:void(0);" class="weui_media_box weui_media_appmsg">
      <div class="weui_media_hd"> <img class="weui_media_appmsg_thumb" src="img/index_user_face.png" width="60" height="60"> </div>
      <div class="weui_media_bd">
        <h4 class="weui_media_title">张医生</h4>
        <p class="weui_media_desc">中南大学毕业，医术好，长得美。</p>
      </div>
      <div class="weui_media_ft"> <span class="weui_media_ft_time">32岁</span> <span class="weui_media_ft_btn">预约</span> </div>
      </a>
       -->
       </div>
       <div class="weui-infinite-scroll" id="loadingdiv">
	  <div class="infinite-preloader"></div>
	    <!-- 正在加载... -->
	    <span  id="loading">上拉加载更多</span>
	</div>
  </div>
</div>
<script  type="text/javascript">
$(document).ready(function(e) {
	   $("#pageno").val(0);
    $(".yuyue-slide-control div").click(function(){
		$(this).siblings().removeClass("cur-control");
		$(this).addClass("cur-control");
		$(".cur-control-con").hide();
		$(".cur-control-con").eq($(this).index()).fadeIn()
	})
	list();
    var loading = false;  //状态标记

	$(document.body).infinite().on("infinite", function() {
	  if(loading) return;
	  loading = true;
	  setTimeout(function() {
		list();
	    loading = false;
	  }, 1500);   //模拟延迟
	});
});
function list(){
	var hosid = $("#hosid").val();
	var deptid = $("#deptid").val();
	var pagenum = parseInt($("#pageno").val());
	pagenum = pagenum+1;
	//var search = $("#search_input").val();
	var search = '${search_input}';
	$.ajax({
		url:"api.json?type=doctorlist&ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"rowstart="+pagenum+"&doctor="+search+"&hosid="+hosid+"&deptid="+deptid,
		success:function(data){
			var doctorlist = "";
			var x = 0;
			var success = data.success;
			if(success=='true'){
				$.each(data.list, function(i, item){   
						doctorlist += '<a href="#" onclick="yuyue(\''+item.docid+'\')" class="weui_media_box weui_media_appmsg" >'
					    +'<div class="weui_media_hd"> <img class="weui_media_appmsg_thumb" src="'+item.smallpicurl+'"  width="60" height="60"> </div>'
					    +'<div class="weui_media_bd">'
					    +'<h4 class="weui_media_title">'+item.docname+'</h4>'
					    +'<p class="weui_media_desc">'+item.deptname+'</p>'
					    +'</div><div class="weui_media_ft"> <span class="weui_media_ft_time">'+item.doctitle+'</span> '
					    +' </div>'
					    +'</a>';
					    x = x+1;
				 });
				$("#doctorlist").html(doctorlist);
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

function yuyue(code){
	var hosid=$("#hosid").val();
	var deptid=$("#deptid").val();
	window.location='<%=path %>/guahao/doctorschedule.html?docid='+code+'&hosid='+hosid+'&deptid='+deptid;
}

function keshi(){
	var hosid = $("#hosid").val();
	window.location='<%=path %>/guahao/keshi.html?hosid='+hosid;
}
</script>
</body>
</html>