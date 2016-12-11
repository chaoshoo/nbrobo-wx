var member = {
	addMember : function(flag){
		var memberName = $("#memberName").val();
		var memberUID = $("#memberUID").val();
		var memSex = "";
		if($("input[name=sex]:eq(0)").attr("checked")=="checked"){
			memSex = "男";
		}else{
			memSex = "女";
		}
		var memYear = $("#memberYear").children('option:selected').val();
		var memMonth = $("#memberMonth").children('option:selected').val();
		var memDay = $("#memberDay").children('option:selected').val();
		var memBirthday = memYear + "-" + memMonth + "-" + memDay;
		var memWeight = $("#memberWeight").val();
		var memHeight = $("#memberHeight").val();
		var memImg =  $("#checkSrc").val();
		var memberId = $("#memberId").val();
		
		returnflag = "no";//标志位，当为no时，表示不return,为yes时则return;
		checkNull(memberName,"请输入合法姓名！(字母，数字或汉子)","name");
		checkNull(memberUID,"请输入合法社保号或身份证号，以逗号隔开！","memberUniqueId");
		checkNull(memWeight,"请填写合法体重！","weight");
		checkNull(memHeight,"请填写合法身高！","height");
		
		if(returnflag=="yes"){
			return;
		}
		$.ajax({
			dataType : 'text',
			type : 'post',
			url : '/member/save.do',
			data : 'memberName=' + memberName +'&memSex=' + memSex + '&memBirthday=' + memBirthday + '&memWeight=' + memWeight
			+ '&memHeight=' + memHeight + '&memImg=' + memImg + '&flag=' + flag + '&memberId=' + memberId + '&memberUID=' + memberUID,
			success : function(msg){
				if(msg=="insertSuccess"){
					alert("添加成功！");
					// location.reload();
				}else if(msg=="updateSuccess"){
					alert("修改成员信息成功！");
					 parent.location.reload();
				}else if(msg=="fail"){
					alert("操作失败！");
				}
				
			}
		});
	},
	membersave :function(){
		var accountId=$("#accountId").val();
		var name=$("#name").val();
		var password=$("#password").val();
		var mid=$("#mid").val();
		var nickname =$("#nickname").val();
		var showContent=function(url){
			var toControllerUrl ="";
			if(url.indexOf("?")==-1){
				toControllerUrl = url+"?r="+Math.random();
			}else{
				toControllerUrl = url+"&r="+Math.random();
			}
			$.get(toControllerUrl,function(data){
				$("#userContent").html(data);
			},"text");
		};
		$.ajax({
			type : "post",
			dataType : "text",
			url : '/member/membersave.do',
			data:'accountId='+accountId+'&name='+name+'&password='+ password+'&mid='+mid+'&nickname='+nickname,
			success : function(msg){
				if(msg=="success"){
					history.go("-1");
					parent.self.location(showContent('/member/index.do'));
				}else if(msg=="other"){
					$("#errormessage").html("添加成员账号或密码不正确");
				}else if(msg == "my"){
					$("#errormessage").html("主用户不能被添加");
				}
				
				else {
					$("#errormessage").html("成员已经添加");
				}
			}
		});
		
		
		
	},
	
	delMember : function(memberId){
		if(confirm("删除成员？请谨慎操作！")){
			$.ajax({
				type : 'post',
				dataType : 'text',
				url : '/member/del.do',
				data :'memberId=' + memberId,
				success : function(msg){
					if(msg=="success"){
						alert("删除成功！");
						location.reload();
					}else if(msg=="fail"){
						alert("删除失败！");
					}else if(msg=="warning"){
						alert("不能删除主成员！");
					}
				}
			})
		}else{
			return false;
		}
	},
	
	openMesssage : function(){
		var memberTel = $("#memberTel").val();
		var ifOpenFlag ;
		var memberId = $("#memberId").val();
		if(memberId==""){
			$("#registerMemberFirstTel").html("请先添加成员基本信息，再设置短信！");
		}else{
			if($("#ifMessgae").attr("class")=="an_kg"){
				ifOpenFlag = "close";
			}else{
				ifOpenFlag = "open";
			}
			var mobile = /^(13[0-9]{1}|15[^4,\D]{1}|18[0,5-9]{1})+\d{8}$/;
			if(!mobile.test(memberTel)){
				$("#registerMemberFirstTel").html("请输入正确的手机号码！");
				return;
			}else{// 手机号不为空时才去后台操作
				$.ajax({
				type : "post",
				dataType : "text",
				url : "/member/update.do",
				data : "memberTel=" + memberTel + "&ifOpenFlag=" + ifOpenFlag + "&memberId=" + memberId,
				success : function(msg){
					if(msg=="success"){
						if(ifOpenFlag=="close"){
							$("#ifMessgae").attr("class","an_kg2");
						}else if(ifOpenFlag=="open"){
							$("#ifMessgae").attr("class","an_kg");
						}
					}

				}
			})
			}
		}
	},
	
	openEmail : function(){
		var memberEmail = $("#memberEmail").val();
		var ifOpenFlag ;
		var memberId = $("#memberId").val();
		if(memberId==""){
			$("#registerMemberFirstEmail").html("请先添加成员基本信息，再设置邮箱！");// 因为是修改成员信息，必须要有成员ID
		}else{
			if($("#ifEmail").attr("class")=="an_kg"){
				ifOpenFlag = "close";
			}else{
				ifOpenFlag = "open";
			}
			var email = /^\S+@{1}\S+\.{1}(com|cn)$/;
			if(!email.test(memberEmail)){
				$("#registerMemberFirstEmail").html("请输入合法的邮箱！");
				return;
			}else{// 电话不为空时才去后台操作
				$.ajax({
					dataType : "text",
					type : "post",
					url : "/member/update.do",
					data : "memberEmail=" + memberEmail + "&ifOpenFlag=" + ifOpenFlag + "&memberId=" + memberId,
					success : function(msg){
						if(msg=="success"){
							if(ifOpenFlag=="close"){
								$("#ifEmail").attr("class","an_kg2");
							}else if(ifOpenFlag=="open"){
								$("#ifEmail").attr("class","an_kg");
							}
						}

					}
				})
			}
		}
	},
	
	cleanNotice : function(id){
		if(id == "memberTel"){
			$("#registerMemberFirstTel").html("");
		}
		if(id == "memberEmail"){
			$("#registerMemberFirstEmail").html("");
		}
		if(id == "memberName"){
			$("#registerMemberName").html("");
		}
		if(id == "memberUID"){
			$("#registerMemberUniqueId").html("");
		}
		if(id == "memberWeight"){
			$("#memberWeightDiv").html("");
		}
		if(id == "memberHeight"){
			$("#memberHeightDiv").html("");
		}
	}
	
}
var checkdata = {
		/**
		 * 将提示信息清空
		 * 
		 * @param obj
		 */
		checkDataOn:function(obj){
			var id=obj.id;
			$("#"+id+"Div").html("");
		},
		/**
		 * 判断用户输入的信息为数字
		 */
		checkDataOff : function(id){
			if(id=="memberWeight"||id=="memberHeight"){
				var value=$("#"+id).val();
				var ss= /^\d+$/;
				if((!ss.exec(value))||(value==0)){
					$("#"+id+"Div").html("请输入有效数字!");
					return false;
				}
				return true;
				
			}
		}
}
/**
 * element为一个数组
 * attention为提示语言
 * @param element
 * @param attention
 * @returns
 */
function checkNull(element,attention,id){
	if(id=="name"){
		var ss= /^\d+$/;
		var nameGrep = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
		if(!nameGrep.test(element)){
			$("#registerMemberName").html(attention);
			returnflag = "yes";
		}
	}
	if(id=="memberUniqueId"){
		 var uniqueId = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
		 if(!uniqueId.test(element)){
			 $("#registerMemberUniqueId").html(attention);
			 returnflag = "yes";
		 }
	}
	if(id=="weight"){
		var weight = /^(\d{1,3})|(\d{1,3}[.]?\d{1,2})$/;
		if((!weight.test(element))||(element==0)){
			$("#memberWeightDiv").html(attention);
			returnflag = "yes";
		}
	}
	if(id=="height"){
		var height = /^(\d{2,3})|(\d{2,3}[.]?\d{1,2})$/;
		if((!height.test(element))||(element==0)){
			$("#memberHeightDiv").html(attention);
			returnflag = "yes";
		}
	}
}
