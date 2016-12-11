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
#doctorlist	.weui_media_ft_time{ height: 20px; overflow: hidden; line-height: 20px;width:120px;}
</style>
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" onclick="history.go(-1)"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1"> Doctor List </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div></header>
<div class="cur-control-con" >
  <div class="weui_search_bar" id="search_bar">
    <form class="weui_search_outer">
      <div class="weui_search_inner"> <i class="weui_icon_search"></i>
        <input type="hidden" name="hospital_code" id="hospital_code" value="${hospital_code}">
        <input type="hidden" name="office_code" id="office_code" value="${office_code}">
         <input type="hidden" name="my" id="my" value="${my}">
        <input type="search" class="weui_search_input" id="search_input" name="search_input" placeholder="search"  value="${search_input}"  required>
        <a href="javascript:" class="weui_icon_clear" id="search_clear"></a> </div>
      <label for="search_input" class="weui_search_text" id="search_text"> <i class="weui_icon_search"></i> <span>search</span> </label>
    </form>
    <a href="javascript:" class="weui_search_cancel" id="search_cancel">cancel</a> </div>
    <input type="hidden" name="pageno" id="pageno" value="0"/>

    <div id="doctorlist" class="weui_panel_bd"> 
    <!-- 
    <a href="javascript:void(0);" class="weui_media_box weui_media_appmsg">
      <div class="weui_media_hd"> <img class="weui_media_appmsg_thumb" src="img/index_user_face.png" width="60" height="60"> </div>
      <div class="weui_media_bd">
        <h4 class="weui_media_title">Dr. Zhang</h4>
        <p class="weui_media_desc">Graduated from Central South University，Good medicine，Beautiful。</p>
      </div>
      <div class="weui_media_ft"> <span class="weui_media_ft_time">32year</span> <span class="weui_media_ft_btn">make an appointment</span> </div>
      </a>
       -->
       </div>
       <div class="weui-infinite-scroll" id="loadingdiv">
	  <div class="infinite-preloader"></div>
	    <!-- Being loaded... -->
	    <span  id="loading">Pull up load more</span>
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
    var loading = false;  //State mark
 
	$(document.body).infinite().on("infinite", function() {
	  if(loading) return;
	  loading = true;
	  setTimeout(function() {
		list();
	    loading = false;
	  }, 1500);   //Simulation delay
	});
});
function list(){
	var hospital_code = $("#hospital_code").val();
	var office_code = $("#office_code").val();
	var my = $("#my").val();
	var pagenum = parseInt($("#pageno").val());
	pagenum = pagenum+1;
	var search = $("#search_input").val();
	$.ajax({
		url:"doctorlist.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"pageNo="+pagenum+"&search="+search+"&hospital_code="+hospital_code+"&office_code="+office_code+"&my="+my,
		success:function(data){
			var doctorlist =  $("#doctorlist").html();
			var x = 0;
			$.each(data, function(i, item){   
				doctorlist += '<a href="javascript:void(0);" onclick="doctor(\''+item.ID+'\');"  class="weui_media_box weui_media_appmsg" >'
			    +'<div class="weui_media_hd"> <img class="weui_media_appmsg_thumb" src="'+item.PIC+'"  width="60" height="60"> </div>'
			    +'<div class="weui_media_bd">'
			    +'<h4 class="weui_media_title">'+item.NAME+'('+item.SEXSTR+')</h4>'
			    +'<p class="weui_media_desc">'+item.SPECIAL+'</p>'
			    +'</div><div class="weui_media_ft"> <span class="weui_media_ft_time">'+item.HOSPITALNAME+''+item.OFFICENAME+''+item.TITLESTR+'</span> '
			    +'<span class="weui_media_ft_btn" onclick="yuyue(\''+item.CODE+'\');">make an appointment</span> </div>'
			    +'</a>';
			    x = x+1;
			 });
			$("#doctorlist").html(doctorlist);
			if(x<8){
				$("#loadingdiv").hide();
			}
			if(x>0){
				$("#pageno").val(pagenum);
			}
		}
	});
}
function doctor(id){
	window.location='<%=path %>/yuyue/doctordetail.html?id='+id;
}
function yuyue(code){
	window.location='<%=path %>/yuyue/dialog.html?type=yuyue&doctorcode='+code;
}
</script>
</body>
</html>