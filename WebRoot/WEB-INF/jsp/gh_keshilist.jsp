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
<link rel="stylesheet" href="<%=path %>/jquery-weui/0.8.0/css/jquery-weui.min.css">
<link href="<%=path %>/css/weui.css" rel="stylesheet">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/yuyue.css" rel="stylesheet">
</head>
<body id="account">
<header class="navbar">
  <div class="nav-wrap-left"> <a class="react back" href="<%=path %>/index/homepage.html"><i class="text-icon icon-back"></i></a> </div>
  <span class="nav-header h1">
  <div class="yuyue-slide-control">
    	Departments list
  </div>
  </span> 
  <div class="nav-wrap-right"> <a class="react headSearch" href="javascript:void(0)"> 
  <font style="font-weight:bold;font-style:italic;"></font>
  </a> </div></header>
<div class="cur-control-con">
  <div class="weui_search_bar" id="search_bar">
    <form class="weui_search_outer">
      <div class="weui_search_inner"> <i class="weui_icon_search"></i>     
        <input type="search" class="weui_search_input" id="search_input" name="search_input" placeholder="Enter a doctor`s name" value="${search_input}" required>
        <a href="javascript:" class="weui_icon_clear" id="search_clear"></a> </div>
      <label for="search_input" class="weui_search_text" id="search_text"> <i class="weui_icon_search"></i> <span>Search doctor</span> </label>
     <input type="hidden" name="hosid" id="hosid" value="${hosid}">
    </form>
    <a href="javascript:" class="weui_search_cancel" id="search_cancel">cancel</a> </div>
  <div class="weui_cells weui_cells_access" id="keshigrid"></div>
 
</div>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery.min.js" ></script>
<script type="application/javascript" src="<%=path %>/jquery-weui/0.8.0/js/jquery-weui.min.js" ></script>
<script type="text/javascript"  src="<%=path %>/js/common.js"></script>
<script  type="text/javascript" >
var inityiyuan = false;
$(document).ready(function(e) {
    	keshi();
});

function keshi(){
	var name = $("#search_input").val();
	if(name !=''){
		window.location='<%=path %>/guahao/doctor.html?deptid=0&hosid='+$("#hosid").val()+'&search_input='+name;
	}else{
		//alert(name)
		$.ajax({
			url:"api.json?type=deptalllist&ttt="+new Date().getTime(),
			type:"get",
			dataType:"json",
			data:"hosid="+$("#hosid").val()+"&keshi="+name,
			success:function(data){
				var keshi = "";
				var x = 0;
				var success = data.success;
				if(success=='true'){
					$.each(data.list, function(i, item){   
						x = x+1;
						keshi += '<a href="javascript:void(0);" onclick="doctor(\''+item.deptid+'\');" class="weui_cell">'
					    +'<div class="weui_cell_bd weui_cell_primary">'
					    +'<p >'+item.deptname+'</p>'
					    +'</div><span class="weui_cell_ft"></span> </a>';
					 });
					$("#keshigrid").html(keshi);
				}else{
					$.alert('Failed to get registeration data');
				}
			}
		});
	}   
}



function doctor(code){
	window.location='<%=path %>/guahao/doctor.html?deptid='+code+'&hosid='+$("#hosid").val();
}

</script>
</body>
</html>