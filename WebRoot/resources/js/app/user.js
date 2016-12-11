var USER = {
	/**
	 * 用户登录
	 * 
	 * @returns {Boolean}
	 */
	login : function(type) {
		var userName = $("#username").val();
		var password = $("#password").val();
		var checkbox = "false";
		if ($("#checkbox").attr("checked")) {
			checkbox = "true";
		}
		if(type!="guest"){
			if (userName == "" || password == "") {
				alert("用户名或密码不能为空！");
				return false;
			}
		}
		

		$.ajax({
			type : 'post',
			dataType : 'text',
			url : '/user/checkAccout.do',
			data : 'userName=' + userName + '&password=' + password
					+ '&checkbox=' + checkbox+'&type='+type,
			success : function(msg) {
				if (msg == "success") {
					if(type=="person" || type=="guest"){
						self.location = '/user/myHealthCenter.do?flag=show';
					}else if(type=="doctor"){
						self.location = '/doctor/doctorManager.do';
				    } 
				}else {
				    $("#errormessage").html("用户名或者密码错误，请重新输入!");
			    }
			}
		});
	},
	exit : function() {
		//获取当前时间 
		var date=new Date(); 
		//将date设置为过去的时间 
		date.setTime(date.getTime()-20000*10); 
		//将userId这个cookie删除 
		document.cookie="id=null; expires="+date.toGMTString()+"; path=/";; 
		document.cookie="type=null; expires="+date.toGMTString()+"; path=/";; 
		document.cookie="mianid=null; expires="+date.toGMTString()+"; path=/";; 
		document.cookie="name=null; expires="+date.toGMTString()+"; path=/";; 
		document.cookie="clubcard=null; expires="+date.toGMTString()+"; path=/";; 
		document.cookie="sfz=null; expires="+date.toGMTString()+"; path=/";; 
		document.cookie="JSESSIONID=null; expires="+date.toGMTString()+"; path=/";;
		self.location = '/user/login.do';
//		$.ajax({
//			type : 'post',
//			dataType : 'text',
//			url : '/user/exit.do',
//			success : function(msg) {
//				if (msg == "success") {
//					self.location = '/user/login.do';
//					// 不能回退页
//
//				}
//			}
//		});
	},
	/**
	 * 账户管理表中更新账户
	 * 
	 * @returns {Boolean}
	 */
	update : function() {
		var accountName = $("#accountName").val();
		var oldPassword = $("#oldPassword").val();
		if (oldPassword == "") {
			$("#oldPasswordError").html("请输入原密码");
			return ;
		}
		if (oldPassword != ($("#hiddenPassword").val()) && oldPassword != "") {
			$("#oldPasswordError").html("与原密码不一致，请重新输入");
			return ;
		}

		var newPassword = $("#newPassword").val();
		if (newPassword == "") {
			$("#newPasswordError").html("请输入新密码");
			return ;
		}
		var reNewPassword = $("#reNewPassword").val();
		if (reNewPassword== undefined   ||reNewPassword == "" || reNewPassword == null ) {
			$("#reNewPasswordError").html("请再次输入新密码");
			return ;
		}
		if (newPassword != reNewPassword) {
			$("#reNewPasswordError").html("两次密码输入不一致，请重新输入");
			return ;
		}
		var email = $("#email").val();
		if (email == "") {
			$("#emailError").html("请填写邮箱");
			return ;
		}else{
			 var myreg = /^(\S)+[@]{1}(\S)+[.]{1}(com|cn)$/;
			 if(!myreg.test(email)){
				 $("#emailError").html("请输入有效的邮箱");
				 return ;
			 }
		}
		var tel = $("#tel").val();
		if (tel == "") {
			$("#telError").html("请填手机号码");
			return ;
		}else{
			 var mobile=/^((13[0-9]{1})|15[^4,\D]{1}|18[0,5-9]{1})+\d{8}$/;
			 if(!mobile.test(tel)){
				 $("#telError").html("请输入有效手机号码"); 
				 return ;
			 }
		}
	
		$.ajax({
			type : 'post',
			dataType : 'text',
			url : '/user/saveUpdateAccount.do',
			data : 'accountName=' + accountName + '&newPassword='
					+ newPassword + '&email=' + email + '&tel=' + tel,
			success : function(msg) {
				if (msg == "fail") {
					alert("更新失败！");
				} else if (msg == "success") {
					$("#hiddenPassword").attr("value", newPassword);
					alert("更新账号信息成功！");
				}
			}
		});
		
	},

	cleanNotice : function(id) {
		if (id == "oldPassword") {
			$("#oldPasswordError").html("");
		}
		if (id == "newPassword") {
			$("#newPasswordError").html("");
		}
		if (id == "reNewPassword") {
			$("#reNewPasswordError").html("");
		}
		if (id == "email") {
			$("#emailError").html("");
		}
		if (id == "tel") {
			$("#telError").html("");
		}
	},
	toMainJsp : function(){
		self.location = '/index.do';
	},
	
	
	userSelect : function(){
		$.ajax({
			type : 'post',
			dataType : 'text',
			url : '/user/userDoctorIndex.do',
			success : function(url) {
			   self.location = url;
			}
		});
		
	}
}
var CHEC = {
	/**
	 * 将提示信息清空
	 * 
	 * @param obj
	 */
	checkDataOn : function(obj) {
		var id = obj.id;
		$("#" + id + "Div").html("");
	},
	/**
	 * 账户注册检查
	 * 
	 * @param id
	 * @returns {Boolean}
	 */
	checkDataOff : function(id) {
		if (id == "username") {// 判断账户逻辑
			var value = $("#" + id).val();
			var arr = new Array();
			arr = value.split(" ");
			if (value == "") {
				$("#" + id + "Div").html("用户名不能为空!");
				return false;
			}
			if (arr.length != 1) {
				$("#" + id + "Div").html("请输入有效字符!");
				return false;
			}
			if (value.length > 25 || value.length < 2) {
				$("#" + id + "Div").html("请输入2-25个有效字符!");
				return false;
			} else {
				var flag = RECH.recheckData(value);
				if (flag != null && flag.length > 0) {
					$("#" + id + "Div").html("用户名已经被注册!");
					return false
				}
				return true;
			}
		} else if (id == "mail") {// 判断邮箱逻辑
			var value = $("#" + id).val();
			var arr = new Array();
			arr = value.split(" ");
			if (value == "") {
				$("#" + id + "Div").html("邮箱不能为空!");
				return false;
			}
			if (arr.length != 1) {
				$("#" + id + "Div").html("请输入有效字符!");
				return false;
			}
			if (value.length > 25 || value.length < 2) {
				$("#" + id + "Div").html("请输入2-25个有效字符!");
				return false;
			} else {
				var flag = RECH.recheckData(value);
				if (flag != "" && flag.length > 0) {
					$("#" + id + "Div").html("邮箱已经被注册!");
					return false
				}
				return true;
			}
		} else if (id == "password") {// 判断密码逻辑
			var value = $("#" + id).val();
			var arr = new Array();
			arr = value.split(" ");
			if (value == "") {
				$("#" + id + "Div").html("密码不能为空！");
				return false;
			}
			if (arr.length != 1) {
				$("#" + id + "Div").html("请输入有效字符!");
				return false;
			}
			if (value.length > 25 || value.length < 2) {
				$("#" + id + "Div").html("请输入2-25个有效字符!");
				return false;
			}
			return true;
		} else if (id == "passwordsure") {// 判断确认密码逻辑
			var value = $("#" + id).val();
			if (value == "") {
				$("#" + id + "Div").html("确认密码不能为空！");
				return false;
			} else {
				if (value != $("#password").val()) {
					$("#" + id + "Div").html("两次密码输入不一致！");
					return false;
				}
				return true;
			}
		} else if (id == "yzmTxt") {// 判断验证码是否为空
			var value = $("#" + id).val();
			if (value == "") {
				$("#" + id + "Div").html("验证码不能为空！");
				return false;
			}
		}
		return true;
	}
}
var RECH = {
	recheckData : function(val) {
		var flag = "";
		$.ajax({
			type : 'post',
			async : false,
			dataType : 'text',
			url : '/user/ifDataExit.do',
			data : 'value=' + val,
			success : function(msg) {
				flag = msg;
			}
		});
		return flag;
	}
}
var REGI = {
	/*
	 * 用户注册
	 */
	register : function() {
		var username = $("#username").val();
		var password = $("#password").val();
		var passwordsure = $("#passwordsure").val();
		var mail = $("#mail").val();
		var validation = $("#yzmTxt").val();
		// 校验数据输入格式
		if (!(CHEC.checkDataOff("username")
				&& CHEC.checkDataOff("validation")
				&& CHEC.checkDataOff("password")
				&& CHEC.checkDataOff("mail") && CHEC
				.checkDataOff("passwordsure"))) {
			return false;
		}
		$.ajax({
			type : 'post',
			dataType : 'text',
			url : '/user/registerUser.do',
			data : 'username=' + username + '&password=' + password + '&mail='
					+ mail + '&validation=' + validation,
			success : function(msg) {
				if (msg == "insertSuccess") {
					self.location = "/user/myHealthCenter.do";

				} else if (msg == "validationFail") {
					$("#yzmTxtDiv").html("验证码输入不正确!");
				} else {
					alert("注册失败");
				}
			}
		});
	},
	/**
	 * 重载验证码
	 */
	reloadVerifyCode : function() {
		var timenow = new Date().getTime();
		$("#yzmImge").attr("src",
				"/user/image.do?d=" + timenow);
	},
	/**
	 * 判断用户是否阅读存健康协议书
	 */
	ifRegBntDisabled : function() {
		if ($("#checkbox").attr("checked")) {
			$("#regBnt").css("filter", "");
			$("#regBnt").attr("disabled", false);
		} else {
			$("#regBnt").css("filter", "gray"); // filter:gray
			$("#regBnt").attr("disabled", true);
		}
	}
}

var news = {
	open:function(_this){
		
	}

}

var comm = {
	/**
	 * 校验游客帐号
	 */	
	checkTestUser:function(){
		var guestId = $("#mainAccountId").val();
		if(guestId=="1"){
			alert("体验账号无此权限！");
			return false;
		}
		return true;
	}
}
