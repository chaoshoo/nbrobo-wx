

	function wxpay(appId,timestamp,nonceStr,content,paySign,orderid){
//		$.confirm({
//			  title: '支付',
//			  text: '支付金额是'+orderfee+'元。',
//			  onOK: function () {
//				alert(appId+","+timestamp+','+nonceStr+','+paySign);
				  WeixinJSBridge.invoke("getBrandWCPayRequest",{
			            "appId" : appId, //Name of public number，Incoming by merchant
			            "timeStamp" : timestamp, //time stamp 
			            "nonceStr" : nonceStr, //Random string
			            "package" :content,//Extended field，Incoming by merchant
			            "signType" : "MD5", //WeChat signature:sha1
			            "paySign" : paySign //WeChat signature
			            },
			            function(res){
			            	//WeixinJSBridge.log(res.err_msg);
//			            	alert(JSON.stringify(res)); 
			                if(res.err_msg == "get_brand_wcpay_request:ok"){  
				                $.toast("Payed",  function() {
							          console.log('close');
							          $.ajax({
											url:'/nkywx/guahao/paysuccess.html?',
											type:"get",
											dataType:"json",
											data:"orderid="+orderid,
											success:function(data){
												if(data=='1'){
													window.location='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx595d88175949ba23&redirect_uri=http://wx.nbrobo.com/index/toLogin.html&response_type=code&scope=snsapi_base&state=123#wechat_redirect';
												}else{
													$.alert("Update registration order status failed，Please contact customer service。");
												}
											}
										}); 
							        });
				            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
				                $.toast("User cancel payment!", "cancel", function() {
							          console.log('close');
							        });
				            }else{  
				            	$.toast("Payment failed", "cancel", function() {
							          console.log('close');
							        });
				            }
			            });
//			  },
//			  onCancel: function () {
//			  }
//			});
	}
