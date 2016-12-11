

	function wxpay(appId,timestamp,nonceStr,content,paySign,orderid){
//		$.confirm({
//			  title: '支付',
//			  text: '支付金额是'+orderfee+'元。',
//			  onOK: function () {
//				alert(appId+","+timestamp+','+nonceStr+','+paySign);
				  WeixinJSBridge.invoke("getBrandWCPayRequest",{
			            "appId" : appId, //公众号名称，由商户传入
			            "timeStamp" : timestamp, //时间戳 
			            "nonceStr" : nonceStr, //随机串
			            "package" :content,//扩展字段，由商户传入
			            "signType" : "MD5", //微信签名方式:sha1
			            "paySign" : paySign //微信签名
			            },
			            function(res){
			            	//WeixinJSBridge.log(res.err_msg);
//			            	alert(JSON.stringify(res)); 
			                if(res.err_msg == "get_brand_wcpay_request:ok"){  
				                $.toast("&nbsp;&nbsp;支付成功",  function() {
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
													$.alert("更新挂号订单状态失败，请联系客服人员。");
												}
											}
										}); 
							        });
				            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
				                $.toast("&nbsp;&nbsp;用户取消支付!", "cancel", function() {
							          console.log('close');
							        });
				            }else{  
				            	$.toast("&nbsp;&nbsp;支付失败", "cancel", function() {
							          console.log('close');
							        });
				            }
			            });
//			  },
//			  onCancel: function () {
//			  }
//			});
	}

