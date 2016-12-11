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
<title>Residents health management service platform</title>
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="MobileOptimized" content="240">
    <meta name="Iphone-content" content="320">
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/weui.min.css">
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/jquery-weui.min.css">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/find_psw.css" rel="stylesheet">
</head>
<body  style="background:#fff;">
<header class="navbar" >
  <div class="nav-wrap-left" > 
  <a  href="javascript:history.go(-1);"  class="react back" >
  <i class="text-icon icon-back"></i>
  </a> </div>
  <span class="nav-header h1" > Leave a message </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div>
  </header>
  
    <div class="resetpwd-container" style="padding:20px; 15px">
        <div class="message-account">
<form id="liuyan_form" action="">
  <input type="hidden" name="doctor_code" id="doctor_code" value="${doctorcode}" />
    <!--Verification code received by mobile phone-->
    <div class="form-item">
        <input type="text" placeholder="Please describe your question.(200In a word)" class="ruc-input-register-auth" id="title" name="title">
    </div>
    

    <div class="form-item form-item1" style=" height:auto">
        <textarea placeholder="Please describe your specific situation." id="ruc-register-password-field" class="ruc-input-register-password yuyue-remark" type="content" id="content" name="content"></textarea>
        <i class="icon-ok"></i>
    </div>
    <div class="next-prove-reset" onclick="liuyan()"><div>Submit comments</div></div>
    </form>
</div>
    </div>
</body>
<script src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js"></script>
<script src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js"></script>
<script src="<%=path %>/js/form.js"></script>
<script type="text/javascript">
function liuyan(){
	var title = $("#title").val();
	var content = $("#content").val();
	if(title == ''){
		$.alert("Please describe your question.");
		return;
	}
	if(content == ''){
		$.alert("Please describe your specific situation.");
		return;
	}
	var formdata = $.serializeObject($("#liuyan_form"));;
	$.ajax({
		url:"saveliuyan.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:formdata,
		success:function(data){
			var doctorlist = "";
			if(data.code == 1){
				 $.toast("Message submitted", function() {
					 window.location.href='<%=path%>/index/homepage.html';
			        });
			}else{
				 $.toast("Message failed", "cancel", function() {
			          console.log('close');
			        });
			}
		}
	});
}
</script>
</html>