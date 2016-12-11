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
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1"> 留言列表 </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div>
  </header>
<div class="cur-control-con" >
  <div class="weui_search_bar" id="search_bar">
    <form class="weui_search_outer">
      <input type="hidden" name="type" id="type" value="liuyan"/>
      <div class="weui_search_inner"> <i class="weui_icon_search"></i>
        <input type="search" class="weui_search_input" name="search_input" id="search_input" placeholder="搜索" value="${search_input}" required>
        <a href="javascript:" class="weui_icon_clear" id="search_clear"></a> </div>
      <label for="search_input" class="weui_search_text" id="search_text"> <i class="weui_icon_search"></i> <span>搜索</span> </label>
    </form>
    <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a> </div>
    <input type="hidden" name="pageno" id="pageno" value="0"/>
  <div class="weui_panel_con">
    <div id="liuyanlist" class="weui_panel_bd">
    <!-- 
     <a href="javascript:void(0);" class="weui_media_box weui_media_appmsg">
      <div class="weui_media_hd"> <img class="weui_media_appmsg_thumb" src="img/index_user_face.png" width="60" height="60"> </div>
      <div class="weui_media_bd">
        <h4 class="weui_media_title">张医生</h4>
        <p class="weui_media_desc">有问题找我</p>
      </div>
      <div class="weui_media_ft"> <span class="weui_media_ft_time">今天上午</span> </div>
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
<script>
$(document).ready(function(e) {
	 $("#pageno").val(0);
    $(".yuyue-slide-control div").click(function(){
		$(this).siblings().removeClass("cur-control");
		$(this).addClass("cur-control");
		$(".cur-control-con").hide();
		$(".cur-control-con").eq($(this).index()).fadeIn()
	});
	liuyanlist();
	var loading = false;  //状态标记
	$(document.body).infinite().on("infinite", function() {
	  if(loading) return;
	  loading = true;
	  setTimeout(function() {
		liuyanlist();
	    loading = false;
	  }, 1500);   //模拟延迟
	});
});
function liuyanlist(){
	var pagenum = parseInt($("#pageno").val());
	pagenum = pagenum+1;
	var title = $("#search_input").val();
//	alert(title);
	$.ajax({
		url:"<%=path%>/yuyue/replaydoctorlist.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"pageNo="+pagenum+"&title="+title,
		success:function(data){
			var liuyanlisthtml = $("#liuyanlist").html();
			var x = 0;
			$.each(data, function(i, item){  
				x = x+1;
				var title = item.TITLE;
				var answer_content = item.ANSWER_CONTENT;				
				if(title ==undefined){
					title = "";
				}else if(title.length > 10){
					title = title.substring(0,10)+"...";
				}
				if(answer_content ==undefined){
					answer_content = "";
				}else if(answer_content.length > 10){
					answer_content = answer_content.substring(0,10)+"...";
				}
				var create_time = item.CREATE_TIMESTR;
				if(create_time ==undefined){
					create_time = "";
				}
				liuyanlisthtml += '<a href="javascript:void(0);" class="weui_media_box weui_media_appmsg" onclick="question(\''+item.ID+'\')" >'
	               +' <div class="weui_media_bd more more-weak"><h4 class="weui_media_title">'+title+'</h4>'
	               +' <span class="weui_media_desc"  style="float:left;">'+answer_content+'</span><span  class="weui_media_desc"   style="float:right;">'+create_time+'</span></div>'
	               +'<div class="weui_cell_ft "></div></a> ';

			 });
			$("#liuyanlist").html(liuyanlisthtml);
			if(x<8){
				$("#loadingdiv").hide();
			}
			if(x>0){
				$("#pageno").val(pagenum);
			}
		}
	});
}
function question(id){
	window.location='<%=path %>/yuyue/historydetail.html?type=liuyan&questionid='+id;
}
</script>
</body>
</html>