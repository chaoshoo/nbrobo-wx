function slideDown(obj){
	$(obj).parent().find(".sildeDiv").toggle(500)
}

$(function(){
	$(".search-box-input").keyup(function(){
		if($(this).val()!=""){
			$(".search-box-input-delete").fadeIn(500);
		}else{$(".search-box-input-delete").fadeOut(500);}

    });
	
	 $(".search-box-input1").keyup(function(){
	    if($(this).val()!=""){
           $(this).parent().find(".search-box-input-delete").fadeIn(500);
	    }else{
	         $(".search-box-input-delete").fadeOut(500);}
	    });
	
	 $(".box-search").find("input").focus(function(){
	    $(this).parent().addClass("op-1"); 
	   	 })
	  $(".box-search").find("input").blur(function(){
	    $(this).parent().removeClass("op-1");
   })
	 
	$(".search-box-input-delete").click(function(){
		$(this).hide();
		$(this).siblings().val("");
		//$(".search-box-input").val("");
		//$(".search-box-input1").val("");
	});
	
	$(".cancel-span").click(function(){
		$(".search-input").val("")
	});	
	
	$(".search-input").keyup(function(){
		if($(this).val()!=""){
			$(".search-box-input-delete").fadeIn(500);
		}else{$(".search-box-input-delete").fadeOut(500);}

    });

});


/*****function showDelet(){
	$(".search-box-input").keyup(function(){
		alert(1);
		if($(this).val()!=""){
			$(".search-box-input-delete").fadeIn(500);
		}else{$(".search-box-input-delete").fadeOut(500);}

})}
	*****/	
