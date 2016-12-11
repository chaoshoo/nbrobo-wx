var member = {
	addMember : function(flag){
		var memberName = $("#memberName").val();
		var memberUID = $("#memberUID").val();
		var memSex = "";
		if($("input[name=sex]:eq(0)").attr("checked")=="checked"){
			memSex = "male";
		}else{
			memSex = "female";
		}
		var memYear = $("#memberYear").children('option:selected').val();
		var memMonth = $("#memberMonth").children('option:selected').val();
		var memDay = $("#memberDay").children('option:selected').val();
		var memBirthday = memYear + "-" + memMonth + "-" + memDay;
		var memWeight = $("#memberWeight").val();
		var memHeight = $("#memberHeight").val();
		var memImg =  $("#checkSrc").val();
		var memberId = $("#memberId").val();
		
		returnflag = "no";//Flag bit，should benotime，No representationreturn,byyesWhenreturn;
		checkNull(memberName,"Please enter a valid name！(Letter，Number or Chinese)","name");
		checkNull(memberUID,"Please enter a legal social security number or ID number，Separated by a comma！","memberUniqueId");
		checkNull(memWeight,"Please input valid weight！","weight");
		checkNull(memHeight,"Please input valid height！","height");
		
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
					alert("Added！");
					// location.reload();
				}else if(msg=="updateSuccess"){
					alert("Member information Changed！");
					 parent.location.reload();
				}else if(msg=="fail"){
					alert("operation failed！");
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
					$("#errormessage").html("Add account or password is incorrect");
				}else if(msg == "my"){
					$("#errormessage").html("Primary user cannot be added");
				}
				
				else {
					$("#errormessage").html("Member added");
				}
			}
		});
		
		
		
	},
	
	delMember : function(memberId){
		if(confirm("Member deleted？Please proceed with caution！")){
			$.ajax({
				type : 'post',
				dataType : 'text',
				url : '/member/del.do',
				data :'memberId=' + memberId,
				success : function(msg){
					if(msg=="success"){
						alert("Deleted！");
						location.reload();
					}else if(msg=="fail"){
						alert("Delete failed！");
					}else if(msg=="warning"){
						alert("Master member cannot be deleted！");
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
			$("#registerMemberFirstTel").html("Please add the basic information，Set SMS again！");
		}else{
			if($("#ifMessgae").attr("class")=="an_kg"){
				ifOpenFlag = "close";
			}else{
				ifOpenFlag = "open";
			}
			var mobile = /^(13[0-9]{1}|15[^4,\D]{1}|18[0,5-9]{1})+\d{8}$/;
			if(!mobile.test(memberTel)){
				$("#registerMemberFirstTel").html("Please enter the correct cell phone number.！");
				return;
			}else{// The phone number is not empty before going to the background operation
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
			$("#registerMemberFirstEmail").html("Please add the basic information，Set email again！");// Because it is a member of the modified information，There must be a memberID
		}else{
			if($("#ifEmail").attr("class")=="an_kg"){
				ifOpenFlag = "close";
			}else{
				ifOpenFlag = "open";
			}
			var email = /^\S+@{1}\S+\.{1}(com|cn)$/;
			if(!email.test(memberEmail)){
				$("#registerMemberFirstEmail").html("Please enter a valid email address.！");
				return;
			}else{// The phone does not go to the background when it is empty.
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
		 * Empty the message
		 * 
		 * @param obj
		 */
		checkDataOn:function(obj){
			var id=obj.id;
			$("#"+id+"Div").html("");
		},
		/**
		 * Determine the user input information for the digital
		 */
		checkDataOff : function(id){
			if(id=="memberWeight"||id=="memberHeight"){
				var value=$("#"+id).val();
				var ss= /^\d+$/;
				if((!ss.exec(value))||(value==0)){
					$("#"+id+"Div").html("Please enter a valid number!");
					return false;
				}
				return true;
				
			}
		}
}
/**
 * elementFor an array
 * attentionPrompt language
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