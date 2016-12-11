<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
<title>居民健康管理服务平台</title>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="MobileOptimized" content="240">
    <meta name="Iphone-content" content="320">
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/weui.min.css">
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/jquery-weui.min.css">
<link href="<%=path %>/css/find_psw.css" rel="stylesheet">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<style>
html{ font-size:16px;}
</style>
</head>
<body>

	<header class="navbar" >
  <div class="nav-wrap-left" > 
  <a  href="javascript:history.go(-1);"  class="react back" >
  <i class="text-icon icon-back"></i>
  </a> </div>
  <span class="nav-header h1" > 预约诊断 </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div>
  </header>
  
    <div class="resetpwd-container" style="background:#fff;">
    <form id="yuyue_form" action="" style="padding:0px 15px;">
        <div class="message-account">
          <input type="hidden" name="doctor_code" id="doctor_code" value="${doctorcode}">
        
    <div class="form-item">
        <input type="text"  placeholder="请选择预约时间" class="ruc-input-register-name yuyue-date" id="datetime-picker" name="order_time_str">
    </div>

    <div class="form-item form-item1" style=" height:auto">
        <textarea id="remark" placeholder="请填写您的备注" id="ruc-register-password-field" class="ruc-input-register-password yuyue-remark"  name="remark"></textarea>
        <i class="icon-ok"></i>
    </div>
    <div class="next-prove-reset" onclick="yuetime()"  style=" font-size:18px; border-radius:4px;"><div>提交预约</div></div>
</div>
      </form>
    </div>
</body>
<script src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js"></script>
<script src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js"></script>
<script src="<%=path %>/js/form.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
	var nTime = new Date();
	var format = nTime.getFullYear() + "-" + (nTime.getMonth()+1) + "-" + nTime.getDate() + " " + (nTime.getHours()) + ":" + nTime.getMinutes();
	var param = {'min':format};
	$("#datetime-picker").datetimePicker(param);
//	$("#datetime-picker").datetimePicker();
});
function yuetime(){
	var datetime_picker = $("#datetime-picker").val();
	var remark = $("#remark").val();
	if(datetime_picker == ''){
		$.alert("预约时间不能为空");
		return;
	}
	if(remark == ''){
		$.alert("请填写您的备注");
		return;
	}
	var formdata = $.serializeObject($("#yuyue_form"));;
	$.ajax({
		url:"saveyuetime.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:formdata,
		success:function(data){
			var doctorlist = "";
			if(data.code == 1){
				// $.toast("预约成功", function() {
				//	   window.location.href='<%=path%>/index/homepage.html';
			    //    });
				 $.alert("预约成功", function() {
					 window.location.href='<%=path%>/index/homepage.html';
					});
			}else{
				// $.toast("预约失败", "cancel", function() {
			    //      console.log('close');
			   //     });
				 $.alert("预约失败");
			}
		}
	});
}
</script>

</html>