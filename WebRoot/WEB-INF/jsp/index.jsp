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
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/index.css" rel="stylesheet">
</head>
<body id="account">
<div class="index-top">
 <a class="react headSearch" href="#" onclick="message();" style="position:absolute; width:60px;height:60px; top:0px; right:0px; padding-right:10px;line-height:40px;font-size:24px;color:#fff;"> 
  <img alt="" src="../img/bullhorn.png" style="width:1.2em;"/>
  </a> 
<input type="hidden" id="vip_code" name="vip_code" value="${vip.vip_code}">
<input type="hidden" id="card_code" name="card_code" value="${vip.card_code}">
 <a href="#" onclick="userhome();" ><img src="${heard_img_url}" width="100"><i class="text-icon order-card order-icon" style="position:absolute;width:80px;top:85px;padding:2px;font-size:12px !important;margin-left:-15px;">进入个人中心</i></a>
  <div class="username white">${vip.nick_name}</div>
 
  <div class="userinfo white"> <i class="text-icon order-card order-icon" style="background:#F5B345">卡</i>${vip.card_code}</div>

  <dl class="list" style="background:none; border-top:none;border-bottom:none;">
    <dd>
      <ul class="orderindex" id="inspectnearul">
      </ul>
    </dd>
  </dl>
  <div class="index-top-bottom">
    <ul class="guahaoindex">
      <li>
        <input type="hidden" id="orderid" value="${orderid}"/>
        <a class="text-icon green left" style="font-size: .4rem;" id="last_guahao" href="#" onclick="showguahao()">${outpdate}</a>
        <div class="gray left" id="guahao_no" >无挂号记录</div>
        <div class="gray left" id="guahao_more"  style="display:none" ><a href="#" onclick="showmore()">更多挂号记录</a></div>
      </li>
      <li class="right"><a href="#" onclick="guahao();" class="guahao-btn react">马上挂号</a> </li>
    </ul>
    <div class="clearfix"></div>
  </div>
</div>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" >
    <div class="more more-weak" onclick="yuyue();"> <i class="text-icon order-jiudian order-icon">约</i>立即预约<!-- <span class="more-after"></span> --> 
    </div>
    </a> <span class="yuyue"></span> </dd>
</dl>
<dl class="list"  style=" border-top:none;border-bottom:none;">
  <dd><a class="react" href="#" onclick="member();">
    <div class="more more-weak"> <i class="text-icon order-lottery order-icon">家</i>家庭成员 </div>
    </a> <span class="jiating"></span> </dd>
</dl>
<div class="weui_grids" id="inspectlisthtml"> 
<!-- 
<a href="" class="weui_grid">
  <div class="weui_grid_icon icon-niaosuan"> <img src="<%=path %>/img/icon_niaosuan.png" width="50" height="50"> </div>
  <p class="weui_grid_label"> 尿酸 </p>
  </a>
  -->
 </div>
</body>
<script type="text/javascript"  src="<%=path %>/js/jquery.1.7.2.min.js"></script>
<script type="text/javascript"  src="<%=path %>/js/common.js"></script>
<script type="text/javascript">
var guahao_size = '${guahao_size}';
$(document).ready(function(e) {
	if(guahao_size != '0'){
		$("#guahao_no").hide();
		$("#guahao_more").show();
	}
	inspectnear();
	inspectlist();
});
	function inspectnear(){
			$.ajax({
				url:"<%=path%>/vip/inspectnear.json?ttt="+new Date().getTime(),
				type:"get",
				dataType:"json",
				data:"size=3",
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
	function yuyue(){
		window.location='<%=path %>/yuyue/keshi.html?';
	}
	function member(){
		window.location='<%=path %>/member/member.html?';
	}
	function userhome(){
		window.location='<%=path %>/vip/userhome.html?';
	}
	function message(){
		window.location='<%=path %>/vip/message.html?';
	}
	function guahao(){
		window.location='<%=path %>/guahao/yiyuan.html?';
	}
	function showguahao(){
		var orderid=$("#orderid").val();
		window.location='<%=path %>/guahao/orderinfo.html?orderid='+orderid;
	}
	function showmore(){
		window.location='<%=path %>/guahao/history.html?';
	}
</script>
</html>