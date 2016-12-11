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
    <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <meta name="MobileOptimized" content="240">
    <meta name="Iphone-content" content="320">
    <link href="<%=path %>/css/weui.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/jquery-weui.min.css">

<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/find_psw.css" rel="stylesheet">
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js" ></script>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js" ></script>
</head>
<style>
body{background:#fff;}
.resetpwd-container{ padding:15px;}
#getmessage{ background:#2fd3a3;color:#fff; line-height:30px; padding:0px 5px; font-size:16px;}
</style>
<body >

<header class="navbar">
	<div class="nav-wrap-left">
			<a class="react back" href="<%=path %>/index/toLogin.html"><i
				class="text-icon icon-back"></i></a>
		</div>
  <span class="nav-header h1" >Retrieve password</span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div>
  </header>

  <div  class="resetpwd-container">     
        <div class="message-account">
    <div class="form-item">
        <input type="text" placeholder="Phone number"  class="ruc-input-register-name" id="tel" name="tel" value="${tel}" >
    </div>
    <!--Verification code received by mobile phone-->
    <div class="form-item">
        <input type="text" placeholder="Cell phone verification code" class="ruc-input-register-auth" id="auth" name="auth">
        <a id="getmessage" href="javascript:;" onclick="message();" id="ruc-send-auth-code-btn" style="vertical-align: middle;">Get verification code</a>
    </div>
    
    <!--Input password-->
    <div class="form-item">
        <input placeholder="New password" id="ruc-register-password-field" class="ruc-input-register-password" type="password" name="password">
        <i class="icon-ok"></i>
    </div>
    <div class="form-item">
        <input placeholder="Confirm password" id="ruc-register-password-field-confirm" class="ruc-input-register-password" type="password" name="password">
        <i class="icon-ok"></i>
    </div>
    <div class="next-prove-reset" onclick="changepwd();"><div>Confirm modification</div></div>
</div>
      
    </div>
</body>
<script type="text/javascript"  src="<%=path %>/js/common.js"></script>
<script type="text/javascript">
var mytel = '${tel}';
$(document).ready(function(e) {
	if(mytel != '' ){
		$("#tel").attr("readonly","readonly");
	}
});
function message(){
	var tel = $("#tel").val();
	if(isEmpty(tel)){
		$.alert('Phone number is empty');
	} else if(!istel(tel)){
		$.alert('Please enter a valid phone number');
	}else{
		$.ajax({
			url:"getMessage.json?type=changepwd&ttt="+new Date().getTime(),
			type:"get",
			dataType:"json",
			data:"tel="+tel,
			success:function(data){
				if(data.code == 1){
					$("#getmessage").hide();
					$.toast("Please check SMS", function() {
						console.log('close');
			        });
				}else{
					$.alert(data.msg);
				}
			}
		});
	}
}

	function changepwd(){
		var tel = $("#tel").val();
		var auth = $("#auth").val();
		var password1 = $("#ruc-register-password-field").val(); 
		var password2 = $("#ruc-register-password-field-confirm").val(); 
		if(!istel(tel)){
			$.alert('Please enter a valid phone number!');	
		}else if( isEmpty(auth)){
			$.alert("Please enter verification code！");
		}else if(password1.length < 6  ){
			$.alert('Password length is at least6position!');
		}else if(password1 != password2){
			$.alert('Two password inputs are not consistent!');
		}else {
			$.ajax({
				url:"updatePWD.json?ttt="+new Date().getTime(),
				type:"get",
				dataType:"json",
				data:"tel="+tel+"&password="+password1+"&rand="+auth,
				success:function(data){
					if(data.code == 1){
						$.alert("Password modification done", function() {
							  location.href = "toLogin.html";
							});
						
					}else{
						$.alert(data.msg);
					}
				}
			});
		}
	}
	
</script>

</html>