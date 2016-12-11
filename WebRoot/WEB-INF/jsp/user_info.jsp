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
  个人信息
  </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;">&nbsp;&nbsp;</font>
  </a> </div></header>

    <a href="javascript:void(0);" class="weui_media_box weui_media_appmsg">
      <div class="weui_media_hd">
        <img class="weui_media_appmsg_thumb" src="${heard_img_url}"  alt="">
      </div>
      <div class="weui_media_bd">
        <h4 class="weui_media_title">注册时间&nbsp;${create_timestr}</h4>
        <h4 class="weui_media_title">登录手机号&nbsp;${vip.mobile}</h4>
      </div>
    </a>
<form action="" id="userinfoform">
<div class="weui_cells weui_cells_form">
<input type="hidden" name="id" value=${vip.id}>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">昵称</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="nick_name" type="text" placeholder="" value="${vip.nick_name}">
    </div>
  </div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">真实姓名</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="real_name" type="text" placeholder="" value="${vip.real_name}">
    </div>
  </div>

<div class="weui_cell weui_cell_select">
<div class="weui_cell_hd"><label class="weui_label">&nbsp;&nbsp;性别</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <select class="weui_select" id="sex" name="sex" >
        <option value="">选择</option>
        <option value="0">男</option>
        <option value="1">女</option>
      </select>
    </div>
  </div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">联系电话</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="phone" type="text" placeholder="" value="${vip.phone}">
    </div>
  </div>
<div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">卡号</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="card_code" type="text" placeholder="" value="${vip.card_code}">
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">身份证号</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="papers_num" type="text" placeholder="" value="${vip.papers_num}">
    </div>
  </div>
  <div class="weui_cell">
    <div class="weui_cell_hd"><label for="" class="weui_label">生日</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" type="date" name="birthday" value="${vip.birthday}">
    </div>
  </div>
  <!-- 
  <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">区域</label></div>
    <div class="weui_cell_bd weui_cell_primary">
    </div>
  </div>
  
 <div class="weui_cell">
    <div class="weui_cell_hd"><label class="weui_label">详细地址</label></div>
    <div class="weui_cell_bd weui_cell_primary">
      <input class="weui_input" name="address" type="text" placeholder="" value="${vip.address}">
    </div>
  </div>
 -->
</div>
</form>

<div class="xiangqing-footer">
<a class="xiangqing-footer-center" href="#"  onclick="edit()"  style=" font-size:18px; border-radius:4px;">保存修改</a>
</div>
<script>
$(document).ready(function(e) {
	var sex = '${vip.sex}';
	if(sex != '' && sex != undefined){
		$("#sex").val(sex);
	}
});

function edit(){
	var formdata = $.serializeObject($("#userinfoform"))
	 $.ajax({
			url:"<%=path%>/vip/saveUserinfo.json?ttt="+new Date().getTime(),
			type:"get",
			dataType:"json",
			data:formdata,
			success:function(data){
				if(data.code==1){
					$.toast("操作成功");
					memberlist();
				}else{
					$.toast("操作失败","cancel");
				}
			}
		});
}
</script>

</body>
</html>