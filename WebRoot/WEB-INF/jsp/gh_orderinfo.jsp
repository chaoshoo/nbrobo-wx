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
<link href="<%=path %>/css/index.css" rel="stylesheet">
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js" ></script>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js" ></script>
<script src="<%=path %>/js/form.js"></script>
<script src="<%=path %>/js/pay.js?v=20161014"></script>
<style>
.status_ready{ display:inline-block; width:8px; height:8px; background:red; border-radius:50%; margin-left:20px; position:relative; top:-2px;}
.status_end{ display:inline-block; width:8px; height:8px; background:#fff; border-radius:50%; margin-left:20px; position:relative; top:-2px;}
.index-top{ padding-bottom:20px; padding-top:15px;}
</style>

</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html" ><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">
  病人信息
  </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div></header>
<form action="" id="patientform">
<input type="hidden" name="hosid" id="hosid" value="${vipreg.hosid}">
        <input type="hidden" name="deptid" id="deptid" value="${vipreg.deptid}">
         <input type="hidden" name="docid" id="docid" value="${vipreg.docid}">
<input type="hidden" name="scheduleid" id="scheduleid" value="${vipreg.scheduleid}">
<input type="hidden" name="outpdate" id="outpdate" value="${vipreg.outpdate}">
<input type="hidden" name="timeinterval" id="timeinterval" value="${timeinterval}">
<input type="hidden" name="orderfee" id="orderfee" value="${vipreg.orderfee}">
<input type="hidden" name="partscheduleid" id="partscheduleid" value="${vipreg.partscheduleid}">
<input type="hidden" name="parttime" id="parttime" value="${parttime}">

<div class="weui_cells weui_cells_form">
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">挂号医院</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${hosname}</span>
    </div>
</div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">科室</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${deptname}</span>
    </div>
</div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">医生</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${docname}</span>
    </div>
</div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">病人姓名</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${vipreg.patientname}</span>
    </div>
  </div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">病人身份证号</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${vipreg.idcard}</span>
    </div>
  </div>

<div class="weui_cell weui_cell_select">
<div class="weui_cell_hd"><label class="weui_label">&nbsp;&nbsp;性别</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <select class="weui_select" id="patientsex" name="patientsex" >
        <option value="">选择</option>
        <option value="1">男</option>
        <option value="2">女</option>
      </select>
    </div>
  </div>
    <div class="weui_cell">
    <div class="weui_cell_hd"><label for="" class="weui_label">生日</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${vipreg.patientbirthday}</span>
    </div>
  </div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">联系电话</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${vipreg.contactphone}</span>
    </div>
  </div>

 <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">详细地址</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${vipreg.familyaddress}</span>
    </div>
  </div>
 <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">挂号日期</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${vipreg.outpdate}</span>
    </div>
  </div>
  <div class="weui_cell" >
    <div class="weui_cell_hd"><label class="weui_label">就诊号</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${vipreg.serial}</span>
    </div>
  </div>
  <div class="weui_cell" >
    <div class="weui_cell_hd"><label class="weui_label">挂号费</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${vipreg.orderfee}</span>
    </div>
  </div>
   <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">状态</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <span>${statusstr}</span>
    </div>
  </div>
</div>
</form>

<div class="xiangqing-footer" id="guahaodiv">
<a id="guahaobtn" class="xiangqing-footer-center" href="#"  onclick="guahao('${vipreg.orderid}')"  style=" font-size:18px; border-radius:4px;"><span id="buttontxt">保存挂号</span></a>
</div>
<script>
var status = '${vipreg.status}';
$(document).ready(function(e) {
	var sex = '${vipreg.patientsex}';
	if(sex != '' && sex != undefined){
		$("#patientsex").val(sex);
	}
	if(status == '1'){
		$("#buttontxt").html("确认挂号");
	}else if(status == '2' || status == '4' ){
		$("#buttontxt").html("支付");
	}else if(status == '3' ){
		$("#buttontxt").html("取消挂号");
	}else{
		$("#guahaobtn").hide();
	}
});

function guahao(orderid){
	$("#guahaobtn").hide();
	var url = "<%=path%>/guahao/api.json?type=confirmorder&tt="+new Date().getTime();
	if(status=='2'){
		orderpay(orderid);
		return;
	}else if(status=='3'){
		url="<%=path%>/guahao/api.json?type=cancelorder&tt="+new Date().getTime();
	}	
	 $.ajax({
			url:url,
			type:"get",
			dataType:"json",
			data:"orderid="+orderid,
			success:function(data){
				 if(status=='1'){
					if(data.ret==0){
						status = 2;
						$.alert(data.orderconfirmsms, function() {
							  orderpay(orderid);
							});
					}else{
						$.toast("挂号失败","cancel");
					}
				}else if(status=='3'){
					if(data.ret==0){
						$.toast("取消挂号成功");
						status = 5;
						window.location='<%=path %>/guahao/history.html?';
					}else{
						$.toast("取消挂号失败","cancel");	
					}
				}
			}
		});
}

function orderpay(orderid){
	var url="<%=path%>/guahao/pay.json?tt="+new Date().getTime();
	var orderfee = "${vipreg.orderfee}";
	 $.confirm("挂号费用"+orderfee+"元", "确认支付", function() {
		 $.ajax({
				url:url,
				type:"get",
				dataType:"json",
				data:"orderid="+orderid,
				success:function(data){
					if(data.code=='0'){
						wxpay(data.appid,data.timeStamp,data.nonceStr,data.package,data.paySign,orderid);
					}else{
						$.alert(data.message);
					}
				}
			});
       }, function() {
         //取消操作
       });
	
}

</script>

</body>
</html>