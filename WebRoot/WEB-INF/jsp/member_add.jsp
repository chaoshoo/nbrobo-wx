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
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/find_psw.css" rel="stylesheet">
</head>
<body>
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" onclick="history.go(-1)"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1" style="font-size: 18px;" > 绑定成员 </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div></header>

  
    <div class="resetpwd-container">
    <form id="yuyue_form" action="">
        <div class="message-account">
        
    <div class="form-item">
        <input type="text"  placeholder="请输入对方电话号码或会员卡号" maxlength="18" id="mobile" name="mobile">
    </div>
    
	<div class="form-item">
        <input id="passwordid" placeholder="请输入对方登录密码" type="password" name="password">
    </div>
    <div class="next-prove-reset" onclick="memberadd()" ><div>提交绑定</div></div>
</div>
      </form>
    </div>
</body>
<script src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js"></script>
<script src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js"></script>
<script src="<%=path %>/js/form.js"></script>
<script type="text/javascript">
var mymobile = '${mobile}';
$(document).ready(function(e) {
	
});
function memberadd(){
	var formdata = $.serializeObject($("#yuyue_form"));;
	var mobile = $("#mobile").val();
	if(mobile == mymobile){
		$.alert("亲，不能绑定自己手机号哦！");
		return;
	}
	$.ajax({
		url:"membersave.json?ttt="+new Date().getTime(),
		type:"get",
		dataType:"json",
		data:formdata,
		success:function(data){
			var doctorlist = "";
			if(data.code == 1){
				 $.toast("绑定成功", function() {
					   window.location.href='<%=path%>/index/homepage.html';
			        });
			}else{
//				 $.toast(data.msg, "cancel", function() {
//			          console.log('close');
//			        });
				$.alert(data.msg);
			}
		}
	});
}
</script>

</html>