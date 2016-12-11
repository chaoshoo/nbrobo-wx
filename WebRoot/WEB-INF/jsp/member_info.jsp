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
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/index.css" rel="stylesheet">
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">
	Family member information
  </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div></header>
<div class="index-top">
<input type="hidden" id="vip_code" name="vip_code" value="${vip.vip_code}">
<input type="hidden" id="card_code" name="card_code" value="${vip.card_code}">
  <div class="userinfo white"> <i class="text-icon order-card order-icon" style="background:#F5B345">card</i>${vip.card_code}${vip.nick_name}</div>

  <dl class="list" style="background:none; border-top:none;border-bottom:none;">
    <dd>
      <ul class="orderindex" id="inspectnearul">
      </ul>
    </dd>
  </dl>
</div>
<div class="weui_grids" id="inspectlisthtml"> 
<!-- 
<a href="" class="weui_grid">
  <div class="weui_grid_icon icon-niaosuan"> <img src="<%=path %>/img/icon_niaosuan.png" width="50" height="50"> </div>
  <p class="weui_grid_label"> uric acid </p>
  </a>
  -->
 </div>
</body>
<script type="text/javascript"  src="<%=path %>/js/jquery.1.7.2.min.js"></script>
<script type="text/javascript"  src="<%=path %>/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
	inspectnear();
	inspectlist();
});
	function inspectnear(){
			$.ajax({
				url:"<%=path%>/vip/inspectnear.json?ttt="+new Date().getTime(),
				type:"get",
				dataType:"json",
				data:"size=3&card_code=${vip.card_code}",
				success:function(data){
					var inspectnearhtml = "";
					$.each(data, function(i, item){   
						inspectnearhtml += '<li><a href="#" class="react"> <span class="text-icon white">'+item.value
						               +'</span> <span class="white">'+item.name+'</span> </a> </li>';
					 });
					$("#inspectnearul").html(inspectnearhtml);
				}
			});
	}
	function inspectlist(){
		$.ajax({
			url:"<%=path%>/vip/inspectlist.json?ttt="+new Date().getTime(),
			type:"get",
			dataType:"json",
			data:"size=30",
			success:function(data){
				var inspectlisthtml = "";
				$.each(data, function(i, item){   
					inspectlisthtml += '<a  href="javascript:void(0);" onclick="looktail(\''+item.dic_name+'\')" class="weui_grid">'
									+'<div class="weui_grid_icon icon-niaosuan"> <img src="'+item.dic_remark+'" width="50" height="50"> </div>'
					               +' <p class="weui_grid_label">'+item.dic_value+'</p></a>';
				 });
				$("#inspectlisthtml").html(inspectlisthtml);
			}
		});
}
	function looktail(code){
	    var card_code = $("#card_code").val();
		//window.location=chart_http+'/'+card_code+'/'+code+'/ALL/0-0/0.html';
	    window.location='<%=path %>/vip/chart.html?card_code='+card_code+'&code='+code;
}
</script>
</html>