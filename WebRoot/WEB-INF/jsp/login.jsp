<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
	

<!DOCTYPE html>
<html style="font-size: 76.8px;" data-dpr="1"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
<title>环球健康管理平台</title>
    <meta name="keywords" contnet="登录">
    <meta name="description" contnet="登录">
    <link href="<%=path %>/css/common.css" rel="stylesheet">
    <style type="text/css">
    html{ font-size:32px !important;}
    *{margin: 0px; padding: 0px; font-family: "微软雅黑";}
    ::-webkit-input-placeholder {color: #c9c9c9;}
    html,body{ background-color: #f5f5f5;}
    .loginbtn,.registbtn{-webkit-tap-highlight-color: rgba(255,0,0,0);}
    input:-webkit-autofill, textarea:-webkit-autofill, select:-webkit-autofill {
     background-color: rgb(250, 255, 189); /* #FAFFBD; */
     background-image: none;
     color: rgb(0, 0, 0);
    }
    body{-moz-user-select:none;-webkit-user-select:none;-ms-user-select:none;-khtml-user-select:none;user-select:none;}
    .loginform{ width: 100%; margin-top: 0.9375rem;}
    .inputgroup{ width: 93.75%; margin-left: 3.125%; }
    .inputdiv{width: 96.32%; margin-left: 1.84%; height: 1.25rem; background: #ffffff; position: relative;background: #ffffff; border-radius:20px;overflow: hidden;}
    .inputdiv.hover{
        box-shadow: 0px 10px 15px #d3e3fb; 
    }
    .inputdiv.username{margin-bottom:0.25rem;}
    .inputdiv img{ position: absolute; width: 0.5625rem; left: 18px; top: 0.328125rem;}
    .inputdiv span{ position: absolute; width: 40px; left: 18%; top: 0;color:#8e8e8e;font-size: 16px;line-height: 1.25rem;height: 1.25rem;}
    .inputdiv input{border: none;height: 100%;width: 80%;padding-left: 34%;line-height: normal;font-size:16px;color:#8e8e8e;outline:none;}
    .noborder{ border: none;}
    .loginbtn{ width: 90.625%; margin-left: 4.6875%; background:#1cd39b;color: #ffffff; height: 45px; margin-top: 1.3rem;text-align: center;line-height: 45px;font-size: 20px; border-radius:20px; box-shadow:1px 10px 20px #dad9d9; }
    .registbtn{ width: 90.625%; margin-left: 4.6875%; background:#1cd39b;color: #fff; height: 45px; margin-top: 0.390625rem;text-align: center;line-height: 45px;font-size: 20px; border-radius:20px;box-shadow:1px 10px 20px #dad9d9;}
    .forgetpwd{ float: right; margin-right: 4.6875%; margin-top: 0.46875rem;color: #1cd39b; font-size: 14px;}
    .input-item-error-text {position: fixed;top: 2rem;left: 5%;display: inline-block;width: 90%;height: 1rem;line-height: 1rem;font-size: 0.45rem;border-radius: 2px;vertical-align: middle;text-align: center;display: block;display: none;border: 1px solid #333;background-color: #333;color: #fff;z-index:9999999;}
    .codepopdiv{position: fixed;left:0;top:0;height:100%;width:100%;background: rgba(0,0,0,0.3);z-index: 99999; display:none;}
    .popcontent{ width: 78.125%; height: 4.5625rem; margin-left: 10.9375%; margin-top: 1.953125rem; background: #ffffff;border-radius: 0.09375rem; padding-top: 0.625rem}
    .popcontent .div{ height:1.09375rem;}
    .popcontent p{ color: #8e8e8e; width:91.2%;margin-bottom: 0.4375rem; margin-left: 4.4%;font-size: 0.4225rem;}
    .codeinput{float: left; margin-left: 4.4%; width: 40.8%;height:0.49375rem; border: 1px solid #ededed; background: #f7f7f7;border-radius: 0.05375rem; padding: 4%; line-height: normal;font-size: 0.4225rem; color: #8e8e8e;outline:none;}
    .codebtn{float: left; margin-left: 2%;height:1.09375rem;background: #1cd39b; width: 40.4%;border-radius: 0.05375rem; color: #ffffff; text-align: center; line-height: 1.09375rem; font-size: 0.3625rem;}
    .codelogin{ color: #ffffff; width:91.2%;margin-top: 0.3125rem; margin-left: 4.4%;font-size: 0.4225rem; background: #1cd39b; height: 1.09375rem; line-height: 1.09375rem; text-align: center;border-radius: 0.05375rem;}
    .codebtnactivi{ background: #f3f3f3; color: #8f8f8f}
    .getcodeactivi{ background: #f2f3f3; color: #8e8e8e; border: 1px solid #c5c5c5;}
    .otherlogin{ position: absolute; bottom: 0px; left: 0px; width: 100%; height: 4.0625rem;}
    .otherlogin .title{ width: 100%; text-align: center;color: #535353; font-size: 0.375rem;}
    .logintype{ width: 68.75%; margin-left: 15.625%; margin-top: 0.703125rem;}
    .loginitem{ width: 50%; float: left;text-align: center;}
    .loginitem a{ text-decoration: none; color: #535455; font-size: 0.28125rem;}
    .loginitem img{width: 1.25rem; height:1.25rem;}
    .loginitem p{margin-top: 0.3125rem;}
    .errormsg{ display: none;position: absolute;top: 0.25rem;width: 100%;text-align: center;color:#8e8e8e;font-size: 0.35rem;}
    </style>
<meta content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" name="viewport">
</head>
<body style="font-size: 12px;">
    <div class="errormsg">您的帐号存在登录异常情况</div>
      
  <header class="navbar">
   <div class="nav-wrap-left"> 
  <a  href="<%=path %>/index/toLogin.html"  class="react back"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">登录</span>
  <div class="nav-wrap-right"> <a class="react headSearch" href="#" onclick="message();"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div>
</header>
    <div class="loginform">
        <div class="inputgroup">
            <div class="username inputdiv">
                <img src="<%=path %>/img/login1.png">
                <span>账号</span>
                <input id="phone" placeholder="(输入手机号码)" maxlength="11" type="text">
            </div>
            <div class="password inputdiv noborder">
                <img src="<%=path %>/img/login2.png">
                <span>密码</span>
                <input id="password" placeholder="(输入登录密码)" type="password">
            </div>
        </div>
        <div class="loginbtn" onclick="login();">登录</div>
        <div class="registbtn" onclick="regist();">注册</div>
        <div class="forgetpwd" onclick="findPWD();">忘记密码？</div>
    </div>
    
    <div class="input-item-error-text"></div>
    <div class="codepopdiv">
        <div class="popcontent">
            <p>您的账号存在被盗风险。</p>
            <div class="div">
                <input class="codeinput" id="codeinput" maxlength="6" placeholder="请输入验证码">
                <div class="codebtn" id="codebtn">获取短信验证码</div>
            </div>
            <div class="codelogin" id="codelogin">立即验证</div>
        </div>
    </div>
</body>
<script type="text/javascript"  src="<%=path %>/js/jquery.1.7.2.min.js"></script>
<script type="text/javascript"  src="<%=path %>/js/common.js"></script>
<script type="text/javascript">
	function login(){
		var tel = $("#phone").val();
		var password = $("#password").val();
		if( isEmpty(password)){
			alert("手机号码或密码未填写！");
		}else if(!istel(tel)){
			alert('请输入有效的手机号码');
		}else{
			$.ajax({
				url:"login.json?ttt="+new Date().getTime(),
				type:"get",
				dataType:"json",
				data:"tel="+tel+"&password="+password,
				success:function(data){
					if(data.code == 1){
						location.href = "homepage.html";
					}else if(data.code == -1){
						alert("该账号禁用，请联系客服人员。");
					}else if(data.code == 2){
						alert("SESSION失效，请退出重新进入公众号。");
					}else{
						alert("电话号码或密码不正确！");
					}
				}
			});
		}
	}
	
	function findPWD(){
		//var tel = $("#phone").val();
		window.location='findPSW.html?tel=';
	}
	
	function regist(){
		window.location='toRegister.html?';
	}
	
	
</script>
</html>