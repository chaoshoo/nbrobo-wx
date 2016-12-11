//topbar
/**
$(function(){
	$(".topbar .t_andl").click(function(){
		$(".t_dlq").hide();
		$(".t_dlh").show();
	});
	$(".topbar .t_exit").click(function(){
		$(".t_dlq").show();
		$(".t_dlh").hide();
	});	
});
**/
/*search*/
$(function(){
	var SearchBox = {};	
		SearchBox.inputInit=function(obj){
			$(obj).bind({
				focus:function() {
					var $val = $(this).val();
					if($val == this.defaultValue){
						$(this).val("");	
					}		
				},
				blur:function(){
					var $val = $(this).val();
					if($val == ""){
						$(this).val(this.defaultValue);	
					}
				}
			});
		}	
	SearchBox.inputInit(".ss_input");	
});

/*tab切换效果
$(function(){
	    var $div_li =$("div.tab_t ul li");
	    $div_li.hover(function(){
			$(this).addClass("selected")            //当前<li>元素高亮
				   .siblings().removeClass("selected");  //去掉其他同辈<li>元素的高亮
            var index =  $div_li.index(this);  // 获取当前点击的<li>元素 在 全部li元素中的索引。
			$("div.tab_c > div")   	//选取子节点。不选取子节点的话，会引起错误。如果里面还有div 
					.eq(index).show()   //显示 <li>元素对应的<div>元素
					.siblings().hide(); //隐藏其他几个同辈的<div>元素
		}).hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
		})
});*/

/*tab焦点图效果开始*/
$(function(){
// 基本配置
milktea(
		".jdtc1 > .jdt_a > ul > li",  
		".jdtc1 > .jdt_b > ol > li",
		".jdtc1 > .jdt_c a",
		".jdtc1 > .jdt_d > .pgdown",
		".jdtc1 > .jdt_d > .pgup",
		".jdtc1",
		1000
);

milktea(
		"#jdtc2 > .jdt_a > ul > li",
		"#jdtc2 > .jdt_b > ol > li",
		"#jdtc2 > .jdt_c a",
		"#jdtc2 > .jdt_d > .pgdown",
		"#jdtc2 > .jdt_d > .pgup",
		"#jdtc2",
		1000
);

milktea(
		"#jdtc3 > .jdt_a > ul > li",
		"#jdtc3 > .jdt_b > ol > li",
		"#jdtc3 > .jdt_c a",
		"#jdtc3 > .jdt_d > .pgdown",
		"#jdtc3 > .jdt_d > .pgup",
		"#jdtc3",
		1000
);

milktea(
		"#jdtc4 > .jdt_a > ul > li",
		"#jdtc4 > .jdt_b > ol > li",
		"#jdtc4 > .jdt_c a",
		"#jdtc4 > .jdt_d > .pgdown",
		"#jdtc4 > .jdt_d > .pgup",
		"#jdtc4",
		1000
);
	
});

/*
 * 焦点图轮播函数
 * 使用方法：milktea(大图片,小图片,按钮,下一个,上一个,鼠标悬停区域,轮播间隔时间（毫秒）)
 */
function milktea(largeImages,smallImages,buttons,nextButton,previousButton,hover,interval) {
	var L = $(largeImages),S = $(smallImages),B = $(buttons),H = $(hover);
	var Svc = S.filter(":visible").length;
	var index = 0,tid;
	
	function roll(n) {
		if (n < 0) index = n = S.length-1;
		$(L[n]).show().siblings().hide();
		if ($(S[n]).is(":hidden")) {
			if (n > 0) {
				$(S.first().hide().siblings()[n-1]).show()
			} else {
				S.filter(":lt("+Svc+")").show().siblings().filter(":gt("+(Svc-1)+")").hide();
			}
		}
		$(S[n]).addClass("on").siblings().removeClass("on");
		$(B[n]).addClass("on").siblings().removeClass("on");
	}
	
	function slide() { roll(++index%L.length); }
	$(nextButton).click(function(){roll(++index%L.length)});
	$(previousButton).click(function(){roll(--index%L.length)});
	var tid = setInterval(slide,interval);
	
	H.mouseover(function(){
		clearInterval(tid);
	}).mouseout(function(){
		clearInterval(tid);
		tid = setInterval(slide,interval);
	});
	
	$(B.selector+","+S.selector).mouseover(function(){
		clearInterval(tid);
		roll(index=$(this).index());
	});
};

/*tab焦点图效果结束*/
$(function(){
	$(".mod3 h3").toggle(function(){
			$(this).parent().next(".con").toggle();
		},function(){
			$(this).parent().next(".con").toggle();
		});
});

/*字符串去空格*/
String.prototype.trim=function() {  
	    return this.replace(/(^\s*)|(\s*$)/g,'');  
	};  