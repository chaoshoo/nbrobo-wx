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
<link href="<%=path %>/css/yuyue.css" rel="stylesheet">
<link href="<%=path %>/css/index.css" rel="stylesheet">
<link href="<%=path %>/css/common.css" rel="stylesheet">
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html" ><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">
  家庭成员列表
  </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div>
  </header>

<div class="cur-control-con">
<!-- 
  <div class="weui_search_bar" id="search_bar">
    <form class="weui_search_outer">
      <div class="weui_search_inner"> <i class="weui_icon_search"></i>
        <input type="search" class="weui_search_input" id="search_input" placeholder="搜索" required>
        <a href="javascript:" class="weui_icon_clear" id="search_clear"></a> </div>
      <label for="search_input" class="weui_search_text" id="search_text"> <i class="weui_icon_search"></i> <span>搜索</span> </label>
    </form>
    <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a> </div>
    -->
  <div  class="weui_panel_con">
    <div id="memberlisthtml" class="weui_cells weui_cells_access" style=" margin-top:0px;"> 
    <!--  
    <a class="weui_cell" href="javascript:;">
      <div class="weui_cell_hd"><img src="img/index_user_face.png" alt="" style="width:40px;margin-right:5px;display:block"></div>
      <div class="weui_cell_bd weui_cell_primary">
        <p>成员名</p>
      </div>
      <span class="weui_cell_ft">最后一次检测情况</span> </a> 
      -->
     </div>
  </div>
   <div class="xiangqing-footer">
<a  class="xiangqing-footer-center" href="javascript:void(0)"  onclick="addmember();" style=" font-size:18px; border-radius:4px;">添加成员</a>
</div>
</div>
<script type="text/javascript"  src="<%=path %>/js/common.js"></script>
<script src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js"></script>
<script src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
	memberlist();
});
function addmember(){
	window.location='<%=path %>/member/memberadd.html?';
}
function memberlist(){
	$.ajax({
		url:"<%=path%>/member/memberlist.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"size=30",
		success:function(data){
			var memberlisthtml = "";
			$.each(data, function(i, item){   
				memberlisthtml += '<div class="weui_cell"  >'
								+'<div class="weui_cell_hd"  onclick="looktail(\''+item.vip_code+'\',\''+item.status+'\')"><img src="'+item.heard_img_url+'" style="width:40px;margin-right:5px;display:block"> </div>'
				               +' <div class="weui_cell_bd weui_cell_primary" onclick="looktail(\''+item.vip_code+'\',\''+item.status+'\')"><p>'+item.nick_name+'</p></div>';
				              if(item.status=='ok'){
				            	  //memberlisthtml +=  '<span class="weui_cell_ft">'+item.inspect_name+':'+item.inspect_value </span>;				     
				            	  //if(item.inspect_time != ''){
				            		 // memberlisthtml +=  '&nbsp;&nbsp;'+item.inspect_time;
				            	  //}
				            	  memberlisthtml +=  '<img src="<%=path%>/img/cancel.png" onclick="memberdel(\''+item.vip_code+'\',\''+item.card_code+'\');" style="width:20px;margin-right:5px;display:block"> </div> '
				              }else{
				            	  memberlisthtml +=  '<span>绑定失效</span>&nbsp;&nbsp; <img src="<%=path%>/img/cancel.png" onclick="memberdel(\''+item.vip_code+'\',\''+item.card_code+'\');" style="width:20px;margin-right:5px;display:block"> </div> '
				              }
			 });
			$("#memberlisthtml").html(memberlisthtml);
		}
	});
}

function looktail(vip_code,status){
	if(status=='ok'){
		window.location='<%=path %>/member/memberinfo.html?vip_code='+vip_code;
	}
}
function memberdel(vip_code,card_code){
	$.confirm("确定要解除绑定吗？", function() {
		  //点击确认后的回调函数
			  $.ajax({
				url:"<%=path%>/member/memberdel.json?vip_code="+vip_code+"&card_code="+card_code+"&ttt="+new Date().getTime(),
				type:"get",
				dataType:"json",
				data:"size=30",
				success:function(data){
					if(data.code==1){
						$.toast("操作成功");
						memberlist();
					}else{
						$.toast("操作失败","cancel");
					}
				}
			});
		  }, function() {
		  //点击取消后的回调函数
		  });
}
</script>
</body>
</html>