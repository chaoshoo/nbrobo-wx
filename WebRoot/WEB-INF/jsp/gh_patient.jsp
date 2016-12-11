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
  Patient information
  </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div></header>
<form action="" id="patientform">
<input type="hidden" name="hosid" id="hosid" value="${hosid}">
        <input type="hidden" name="deptid" id="deptid" value="${deptid}">
         <input type="hidden" name="docid" id="docid" value="${docid}">
<input type="hidden" name="scheduleid" id="scheduleid" value="${scheduleid}">
<input type="hidden" name="outpdate" id="outpdate" value="${outpdate}">
<input type="hidden" name="timeinterval" id="timeinterval" value="${timeinterval}">
<input type="hidden" name="orderfee" id="orderfee" value="${orderfee}">
<input type="hidden" name="partscheduleid" id="partscheduleid" value="${partscheduleid}">
<input type="hidden" name="parttime" id="parttime" value="${parttime}">

<div class="weui_cells weui_cells_form">
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">Patient name</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="patientname" type="text" placeholder="" value="${patientname}">
    </div>
  </div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">Patient ID No.</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="idcard" type="text" placeholder="" value="${idcard}">
    </div>
  </div>

<div class="weui_cell weui_cell_select">
<div class="weui_cell_hd"><label class="weui_label">Gender</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <select class="weui_select" id="patientsex" name="patientsex" >
        <option value="">Choose</option>
        <option value="1">male</option>
        <option value="2">female</option>
      </select>
    </div>
  </div>
    <div class="weui_cell">
    <div class="weui_cell_hd"><label for="" class="weui_label">Birthday</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" type="date" name="patientbirthday" value="${patientbirthday}">
    </div>
  </div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">Contact number</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="contactphone" type="text" placeholder="" value="${contactphone}">
    </div>
  </div>

  
 <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">Detailed address</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="familyaddress" type="text" placeholder="" value="${familyaddress}">
    </div>
  </div>
  <div class="weui_cell" id="loadingdiv" style="display: none">
    <div class="weui-infinite-scroll" id="loadingdiv">
	  <div class="infinite-preloader"></div>
	    <!-- Being loaded... -->
	    <span  id="loading">Please be patient.</span>
	</div>
  </div>

</div>
</form>

<div class="xiangqing-footer" id="guahaodiv">
<a class="xiangqing-footer-center" href="#"  onclick="guahao()"  style=" font-size:18px; border-radius:4px;">Save registration</a>
</div>
<script>
$(document).ready(function(e) {
	var sex = '${patientsex}';
	if(sex != '' && sex != undefined){
		$("#patientsex").val(sex);
	}
});
var orderfee = "0.1";
function guahao(){
	var formdata = $.serializeObject($("#patientform"))
	$("#guahaodiv").hide();
	$("#loadingdiv").show();
	 $.ajax({
			url:"<%=path%>/guahao/ghlock.json?ttt="+new Date().getTime(),
			type:"get",
			dataType:"json",
			data:formdata,
			success:function(data){
				$("#loadingdiv").hide();
				if(data.ret==0){
					 $.confirm("Date,："+$("#outpdate").val()+",Registration fee"+data.orderfee, "Confirmation of registration", function() {
						 orderfee =  data.orderfee;
						 guahaoconfirm(data.orderid);
				        }, function() {
				          //取消操作
				        });
				}else{
					if(data.msg != ''){
						//$.toast(data.msg,"cancel");
						$.alert(data.msg, "fail！");
					}else{
						$.toast("Locked number failed","cancel");
						$("#guahaodiv").show();
					}
					
				}
			}
		});
}
function guahaoconfirm(orderid){
	$.ajax({
		url:"<%=path%>/guahao/api.json?type=confirmorder&ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:"orderid="+orderid,
		success:function(data){
			if(data.ret==0){
				$("#guahaodiv").hide();
				$.alert(data.orderconfirmsms, function() {
					  orderpay(orderid);
					});
			}else{
				$.toast("Registr failed","cancel");
			}
		}
	});
}
function orderpay(orderid){
	var url="<%=path%>/guahao/pay.json?tt="+new Date().getTime();
	 $.confirm("Registration fee"+orderfee+"element", "Confirm payment", function() {
		 $.ajax({
				url:url,
				type:"get",
				dataType:"json",
				data:"orderid="+orderid,
				success:function(data){
					if(data.code=='0'){
						wxpay(data.appid,data.timeStamp,data.nonceStr,data.package,data.paySign,orderid)
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