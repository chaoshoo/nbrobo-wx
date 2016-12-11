<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html style=""
	class=" -moz- js flexbox canvas canvastext webgl touch geolocation postmessage no-websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients no-cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>居民健康管理服务平台</title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<meta name="MobileOptimized" content="240">
<meta name="Iphone-content" content="320">
<link href="<%=path%>/css/weui.css" rel="stylesheet">
<link rel="stylesheet"
	href="<%=path%>/jquery-weui/0.8.0/css/jquery-weui.min.css">
<script type="application/javascript"
	src="<%=path%>/jquery-weui/0.8.0/js/jquery.min.js"></script>
<script type="application/javascript"
	src="<%=path%>/jquery-weui/0.8.0/js/jquery-weui.min.js"></script>

<link href="<%=path%>/css/common.css" rel="stylesheet">
<!--<link rel="stylesheet" href=".css">-->
<style>
html, body, body div, span, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, abbr, address, cite, code, del, dfn, em, img, ins, kbd,
	q, samp, small, strong, sub, sup, var, b, i, dl, dt, dd, ol, ul, li,
	fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr,
	th, td, article, aside, figure, footer, header, menu, nav, section,
	time, mark, audio, video, details, summary {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font-weight: 400;
	vertical-align: baseline;
	background: 0
}

article, aside, figure, footer, header, nav, section, details, summary {
	display: block
}

html {
	box-sizing: border-box
}

*, *:before, *:after {
	box-sizing: inherit
}

img, object, embed {
	max-width: 100%
}

html {
	overflow-y: scroll
}

ul {
	list-style: none
}

li {
	list-style: none;
	vertical-align: top
}

blockquote, q {
	quotes: none
}

blockquote:before, blockquote:after, q:before, q:after {
	content: '';
	content: none
}

a {
	margin: 0;
	padding: 0;
	font-size: 100%;
	vertical-align: baseline;
	background: 0
}

del {
	text-decoration: line-through
}

abbr[title], dfn[title] {
	border-bottom: 1px dotted #000;
	cursor: help
}

table {
	border-collapse: collapse;
	border-spacing: 0
}

th {
	font-weight: 700;
	vertical-align: bottom
}

td {
	font-weight: 400;
	vertical-align: top
}

hr {
	display: block;
	height: 1px;
	border: 0;
	border-top: 1px solid #ccc;
	margin: 1em 0;
	padding: 0
}

input, select {
	vertical-align: middle
}

pre {
	white-space: pre;
	white-space: pre-wrap;
	white-space: pre-line;
	word-wrap: break-word
}

input[type="radio"] {
	vertical-align: text-bottom
}

input[type="checkbox"] {
	vertical-align: bottom
}

.ie7 input[type="checkbox"] {
	vertical-align: baseline
}

.ie6 input {
	vertical-align: text-bottom
}

select, input, textarea {
	font: 99% sans-serif
}

table {
	font-size: inherit;
	font: 100%
}

small {
	font-size: 85%
}

strong {
	font-weight: 700
}

td, td img {
	vertical-align: top
}

sub, sup {
	font-size: 75%;
	line-height: 0;
	position: relative
}

sup {
	top: -.5em
}

sub {
	bottom: -.25em
}

pre, code, kbd, samp {
	font-family: monospace, sans-serif
}

.clickable, label, input[type=button], input[type=submit], input[type=file],
	button {
	cursor: pointer
}

button, input, select, textarea {
	margin: 0
}

button, input[type=button] {
	width: auto;
	overflow: visible
}

.ie7 img {
	-ms-interpolation-mode: bicubic
}

.clearfix:before, .clearfix:after {
	content: "\0020";
	display: block;
	height: 0;
	overflow: hidden
}

.clearfix:after {
	clear: both
}

.clearfix {
	zoom: 1
}

body {
	font: 13px "STHeiti", "Microsoft YaHei", "SimSun", "STSong",
		"\5b8b\4f53", "Arial", "Helvetica", "sans-serif"
}

body, select, input, textarea {
	color: #333
}

a {
	color: #03f;
	text-decoration: none
}

a:hover {
	color: #69f;
	text-decoration: none
}

a:link {
	text-decoration: none
}

a:visited {
	text-decoration: none
}

a:active {
	text-decoration: none
}

::-moz-selection {
	background: #fcd700;
	color: #fff;
	text-shadow: none
}

::-moz-selection {
	background: #fcd700;
	color: #fff;
	text-shadow: none
}

a:link {
	-webkit-tap-highlight-color: rgba(255, 255, 255, 0)
}

ins {
	background-color: #fcd700;
	color: #000;
	text-decoration: none
}

mark {
	background-color: #fcd700;
	color: #000;
	font-style: italic;
	font-weight: 700
}

input:-moz-placeholder {
	color: #a9a9a9
}

textarea:-moz-placeholder {
	color: #a9a9a9
}
</style>

<!--<link rel="stylesheet" href="http://s3.pdim.gs/static/35e57cbcd2e162dd.css" />-->
<style>
html, body {
	width: 100%;
	height: 100%;
	font-size: 14px
}

html {
	overflow-x: hidden;
	overflow-y: auto;
	-moz-user-select: none
}

body {
	text-align: left
}

a {
	touch-callout: none !important;
	-webkit-touch-callout: none !important;
	tap-highlight-color: rgba(0, 0, 0, 0) !important;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0) !important
}

.wrapper {
	width: 100%;
	padding: 1rem .5rem 0 .5rem
}

.ruc-register-container {
	box-sizing: border-box;
	width: 100%;
	margin: 0 auto;
	padding-top: 1px
}

.ruc-register-container .form-item {
	position: relative;
	display: flex;
	width: 100%;
	height: 42px;
	overflow: hidden;
	margin: 1.2rem 0px;
}

.ruc-register-container .form-item input {
	width: 100%;
	height: 100%;
	padding-left: 40px;
	border: 1px solid #d8d8d8;
	border-radius: 4px;
	line-height: normal;
	vertical-align: middle;
	flex: 1;
	-moz-box-flex: 1;
	font-size: 16px;
	background-repeat: no-repeat;
	background-position: 12px 10px
}

.ruc-register-container .form-item input.ruc-input-register-name {
	width: 60%
}

.ruc-register-container .form-item input.ruc-input-register-captcha {
	width: 53%;
	background-position: 10px -33px
}

.ruc-register-container .form-item input.ruc-input-register-auth {
	background-position: 10px -33px
}

.ruc-register-container .form-item input.ruc-input-register-nickname {
	background-position: 10px -74px
}

.ruc-register-container .form-item input.ruc-input-register-password {
	background-position: 10px -119px
}

.ruc-register-container .form-item #ruc-send-auth-code-btn {
	display: inline-block;
	width: 120px;
	height: 42px;
	line-height: 42px;
	font-size: 16px;
	border-radius: 4px;
	vertical-align: middle;
	text-align: center;
	color: #fff;
	background-color: #1993e8;
	margin-left: 10px
}

.ruc-register-container .form-item #ruc-send-auth-code-btn:hover {
	background-color: #0280cf
}

.ruc-register-container .form-item #ruc-send-auth-code-btn.button-disable,
	.ruc-register-container .form-item #ruc-send-auth-code-btn.button-disable:hover
	{
	background-color: #d7dbdc
}

.ruc-register-container .form-item .ruc-image-register-captcha {
	display: inline-block;
	width: 90px;
	height: 42px;
	line-height: 42px;
	font-size: 14px;
	border-radius: 0;
	vertical-align: middle;
	text-align: center;
	border: 0;
	margin-left: 5px
}

.ruc-register-container .form-item .ruc-a-register-change {
	display: inline-block;
	width: 50px;
	height: 42px;
	line-height: 42px;
	font-size: 14px;
	border-radius: 0;
	vertical-align: middle;
	text-align: center;
	color: #c9c9c9;
	margin-left: 5px
}

.ruc-register-container .form-item a.ruc-register-agreement {
	text-align: center;
	font-size: 1rem;
	color: #1993e8
}

.ruc-register-container .button-container {
	margin-top: 3rem
}

.ruc-register-container .button-container .input-item-btn {
	display: inline-block;
	width: 100%;
	height: 42px;
	line-height: 42px;
	font-size: 22px;
	border-radius: 4px;
	vertical-align: middle;
	text-align: center;
	flex: 1;
	-moz-box-flex: 1;
	background-color: #1993e8;
	color: #fff;
	cursor: pointer
}

.ruc-register-container .input-item-error-text {
	position: fixed;
	top: 120px;
	left: 50%;
	display: inline-block;
	width: 260px;
	height: 42px;
	line-height: 42px;
	font-size: 14px;
	border-radius: 2px;
	vertical-align: middle;
	text-align: center;
	display: block;
	display: none;
	margin-left: -130px;
	border: 1px solid #333;
	background-color: #333;
	color: #fff
}

.ruc-register-container .ruc-regist-sending-mask {
	position: absolute;
	top: 0;
	left: 0;
	display: none;
	width: 100%;
	height: 100%;
	opacity: .4;
	background-color: #3c3c3c
}

#pop {
	position: fixed;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	text-align: center;
	overflow: auto
}

.hide {
	display: none
}

.show {
	display: block
}

.gt_holder {
	margin-top: 100px;
	display: inline-block
}

#pop .gt_help_button {
	display: none
}
</style>
<style>
html {
	font-size: 30px !important;
}

html, body {
	background-color: #f5f5f5;
	font-family: 'microsoft yahei' !important;
}

input {
	font-family: 'microsoft yahei' !important;
}

a {
	-webkit-tap-highlight-color: rgba(255, 0, 0, 0);
}

#ruc_regist_btn {
	-webkit-tap-highlight-color: rgba(255, 0, 0, 0);
}

.ruc-register-container .form-item input {
	border: none;
	outline: none;
	border-radius: 5px;
	background-image: url('<%=path%>/img/register_icon.png');
}

#ruc-send-auth-code-btn, #ruc_regist_btn {
	border-radius: 21px !important;
	background-color: #1cd39b !important;
}

#ruc_regist_btn {
	background-color: !important;
	color: #fff;
}

.ruc-register-container .form-item #ruc-send-auth-code-btn {
	font-size: 12px;
	height: 27px;
	line-height: 100%;
	padding: 7px 0;
	width: 84px;
	position: absolute;
	right: 5px;
	top: 8px;
}

#ruc_regist_btn:hover, #ruc_regist_btn:active, #ruc_regist_btn.active {
	background-color: #568cde;
}

.ruc-register-container .form-item {
	margin: 0.5rem 0px;
	width: auto;
}

.ruc-register-container .form-item:first-child {
	background-color: #fff;
	border-radius: 5px;
}

.ruc-register-container .form-item.button-container {
	margin-top: 50px;
}

.ruc-register-container .form-item a.ruc-register-agreement {
	color: #1cd39b;
}
</style>
</head>
<body>

	<header class="navbar">
		<div class="nav-wrap-left">
			<a href="<%=path%>/index/toLogin.html" class="react back"><i
				class="text-icon icon-back"></i></a>
		</div>
		<span class="nav-header h1">注册</span>
		<div class="nav-wrap-right">
			<a class="react headSearch" href="#" onclick="message();"> <font
				style="font-weight: bold; font-style: italic;">&nbsp;&nbsp;</font>
			</a>
		</div>
	</header>

	<div class="wrapper">
		<div class="ruc-register-container">
			<!--注册手机号-->
			<div class="form-item">
				<input placeholder="请输入手机号码" class="ruc-input-register-name"
					id="tel" name="tel" type="tel"><a id="getmessage"
					href="javascript:;" onclick="message()" id="ruc-send-auth-code-btn"
					style="background: #1cd39b !important; border-radius: 15px; line-height: 30px; padding: 0px 15px; color: #fff; margin: 5px 5px 5px 0px;">获取验证码</a>
			</div>
			<!--手机收到的验证码-->
			<div class="form-item">
				<input placeholder="请输入验证码" class="ruc-input-register-auth"
					id="rand" name="rand" type="text">
			</div>
			<!--设置身份证号-->
			<div class="form-item">
				<input placeholder="请输入身份证号" class="ruc-input-register-nickname"
					id="idcard" name="idcard" type="text"> <i class="icon-ok"></i>
			</div>
			<!--输入密码-->
			<div class="form-item">
				<input placeholder="密码(至少6位)" class="ruc-input-register-password"
					id="password" name="password" type="password"> <i
					class="icon-ok"></i>
			</div>
			<div class="form-item">
				<input placeholder="重复输入密码" class="ruc-input-register-password"
					id="password2" name="password2" type="password"> <i
					class="icon-ok"></i>
			</div>
			<!--注册按钮-->
			<div class="form-item button-container">
				<div id="ruc_regist_btn" class="input-item-btn" onclick="regist();">注册</div>
			</div>
			<div class="input-item-error-text"></div>
			<div class="ruc-regist-sending-mask"></div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>

<script type="text/javascript">
	function message() {
		var tel = $("#tel").val();
		if (isEmpty(tel)) {
			$.alert('电话号码为空');
		} else if (!istel(tel)) {
			$.alert('请输入有效的手机号码');
		} else {
			$.ajax({
				url : "getMessage.json?type=register&ttt="
						+ new Date().getTime(),
				type : "get",
				dataType : "json",
				data : "tel=" + tel,
				success : function(data) {
					if (data.code == 1) {
						$("#getmessage").hide();
						$.toast("&nbsp;&nbsp;请查收短信", function() {
							console.log('close');
						});
					} else {
						$.alert(data.msg);
					}
				}
			});
		}
	}

	function regist() {
		var tel = $("#tel").val();
		var password = $("#password").val();
		var password2 = $("#password2").val();
		var idcard = $("#idcard").val();
		var rand = $("#rand").val();
		if (!istel(tel)) {
			$.alert('请输入有效的手机号码！');
		} else if (isEmpty(rand)) {
			$.alert("验证码未填写！");
		} else if (isEmpty(idcard)) {
			$.alert("身份证号码未填写！");
		} else if (!(idcard.length == 15 || idcard.length == 18)) {
			$.alert("请输入正确的身份证号码！");
		} else if (isEmpty(password)) {
			$.alert("密码未填写！");
		} else if (password.length < 6) {
			$.alert("密码长度需要大与6位！");
		} else if (password != password2) {
			$.alert("两次输入密码不一致！");
		} else {
			$.ajax({
				url : "register.json?ttt=" + new Date().getTime(),
				type : "get",
				dataType : "json",
				data : "tel=" + tel + "&password=" + password + "&rand=" + rand
						+ "&idcard=" + idcard,
				success : function(data) {
					if (data.code == 1) {
						$.toast("&nbsp;&nbsp;注册成功", function() {
							location.href = "toLogin.html";
						});
					} else {
						$.toast("&nbsp;&nbsp;注册失败", "cancel", function() {
							console.log('close');
						});
					}
				}
			});
		}
	}
</script>

</html>