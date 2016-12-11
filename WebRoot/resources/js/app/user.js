var USER = {
	/**
	 * User login
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
				alert("User name or password can not be empty！");
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
				    $("#errormessage").html("User name or password error，Please input again!");
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
	 * Account management statement
	 * 
	 * @returns {Boolean}
	 */
	update : function() {
		var accountName = $("#accountName").val();
		var oldPassword = $("#oldPassword").val();
		if (oldPassword == "") {
			$("#oldPasswordError").html("Please enter the original password");
			return ;
		}
		if (oldPassword != ($("#hiddenPassword").val()) && oldPassword != "") {
			$("#oldPasswordError").html("Inconsistent with the original password，Please input again");
			return ;
		}

		var newPassword = $("#newPassword").val();
		if (newPassword == "") {
			$("#newPasswordError").html("Please enter a new password");
			return ;
		}
		var reNewPassword = $("#reNewPassword").val();
		if (reNewPassword== undefined   ||reNewPassword == "" || reNewPassword == null ) {
			$("#reNewPasswordError").html("Please enter a new password again");
			return ;
		}
		if (newPassword != reNewPassword) {
			$("#reNewPasswordError").html("Two password inputs are not consistent，Please input again");
			return ;
		}
		var email = $("#email").val();
		if (email == "") {
			$("#emailError").html("Please input email");
			return ;
		}else{
			 var myreg = /^(\S)+[@]{1}(\S)+[.]{1}(com|cn)$/;
			 if(!myreg.test(email)){
				 $("#emailError").html("Please enter a valid email address.");
				 return ;
			 }
		}
		var tel = $("#tel").val();
		if (tel == "") {
			$("#telError").html("Please input phone number.");
			return ;
		}else{
			 var mobile=/^((13[0-9]{1})|15[^4,\D]{1}|18[0,5-9]{1})+\d{8}$/;
			 if(!mobile.test(tel)){
				 $("#telError").html("Please enter a valid phone number"); 
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
					alert("Update failed！");
				} else if (msg == "success") {
					$("#hiddenPassword").attr("value", newPassword);
					alert("Update account information successfully！");
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
	 * Empty the message
	 * 
	 * @param obj
	 */
	checkDataOn : function(obj) {
		var id = obj.id;
		$("#" + id + "Div").html("");
	},
	/**
	 * Account registration check
	 * 
	 * @param id
	 * @returns {Boolean}
	 */
	checkDataOff : function(id) {
		if (id == "username") {// Judgment account logic
			var value = $("#" + id).val();
			var arr = new Array();
			arr = value.split(" ");
			if (value == "") {
				$("#" + id + "Div").html("User name cannot be empty!");
				return false;
			}
			if (arr.length != 1) {
				$("#" + id + "Div").html("Please enter a valid character!");
				return false;
			}
			if (value.length > 25 || value.length < 2) {
				$("#" + id + "Div").html("Please input2-25A valid character!");
				return false;
			} else {
				var flag = RECH.recheckData(value);
				if (flag != null && flag.length > 0) {
					$("#" + id + "Div").html("User name has been registered!");
					return false
				}
				return true;
			}
		} else if (id == "mail") {// Mailbox logic
			var value = $("#" + id).val();
			var arr = new Array();
			arr = value.split(" ");
			if (value == "") {
				$("#" + id + "Div").html("Mailbox cannot be empty!");
				return false;
			}
			if (arr.length != 1) {
				$("#" + id + "Div").html("Please enter a valid character!");
				return false;
			}
			if (value.length > 25 || value.length < 2) {
				$("#" + id + "Div").html("Please input2-25A valid character!");
				return false;
			} else {
				var flag = RECH.recheckData(value);
				if (flag != "" && flag.length > 0) {
					$("#" + id + "Div").html("Mailbox has been registered!");
					return false
				}
				return true;
			}
		} else if (id == "password") {// Judge password logic
			var value = $("#" + id).val();
			var arr = new Array();
			arr = value.split(" ");
			if (value == "") {
				$("#" + id + "Div").html("Password can not be empty！");
				return false;
			}
			if (arr.length != 1) {
				$("#" + id + "Div").html("Please enter a valid character!");
				return false;
			}
			if (value.length > 25 || value.length < 2) {
				$("#" + id + "Div").html("Please input2-25A valid character!");
				return false;
			}
			return true;
		} else if (id == "passwordsure") {// Judge confirm password logic
			var value = $("#" + id).val();
			if (value == "") {
				$("#" + id + "Div").html("Confirm password cannot be empty！");
				return false;
			} else {
				if (value != $("#password").val()) {
					$("#" + id + "Div").html("Two password inputs are not consistent！");
					return false;
				}
				return true;
			}
		} else if (id == "yzmTxt") {// To determine whether the verification code is empty
			var value = $("#" + id).val();
			if (value == "") {
				$("#" + id + "Div").html("Verification code cannot be empty！");
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
	 * User registration
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
					$("#yzmTxtDiv").html("Verification code is not correct!");
				} else {
					alert("Registeration failed");
				}
			}
		});
	},
	/**
	 * Heavy load verification code
	 */
	reloadVerifyCode : function() {
		var timenow = new Date().getTime();
		$("#yzmImge").attr("src",
				"/user/image.do?d=" + timenow);
	},
	/**
	 * To determine whether the user is reading a health agreement
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
	 * Check tourist account
	 */	
	checkTestUser:function(){
		var guestId = $("#mainAccountId").val();
		if(guestId=="1"){
			alert("Experience account without this permission！");
			return false;
		}
		return true;
	}
}