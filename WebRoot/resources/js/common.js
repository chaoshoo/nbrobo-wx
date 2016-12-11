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

/*tabSwitching effect
$(function(){
	    var $div_li =$("div.tab_t ul li");
	    $div_li.hover(function(){
			$(this).addClass("selected")            //current<li>Element highlight
				   .siblings().removeClass("selected");  //Get rid of other peers<li>Highlight elements
            var index =  $div_li.index(this);  // Gets the current Click<li>element stay AllliIndex in an element。
			$("div.tab_c > div")   	//Select child node。If you don't select a child node，Can cause errors。If there isdiv 
					.eq(index).show()   //display <li>Element correspondence<div>element
					.siblings().hide(); //Hide other peers<div>element
		}).hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
		})
});*/

/*tabFocus map effect*/
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
 * Focus map carousel function
 * Usage method：milktea(Big picture,Small picture,Button,Next,The previous,Mouse hover area,Carousel interval（Millisecond）)
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

/*tabFocus map effect*/
$(function(){
	$(".mod3 h3").toggle(function(){
			$(this).parent().next(".con").toggle();
		},function(){
			$(this).parent().next(".con").toggle();
		});
});

/*String to space*/
String.prototype.trim=function() {  
	    return this.replace(/(^\s*)|(\s*$)/g,'');  
	};  