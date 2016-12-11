<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ attribute name="id" type="java.lang.String" required="true" rtexprvalue="true"%>
<script type="text/javascript">
$(function(){
	$(".a").removeClass("selectfooter");
	$("#${id}").addClass("selectfooter");
	
	$("#vip").attr("href","wxvip/homepage.html");
	$("#medicine").attr("href","medicine/medicines.html");
	$("#homepage").attr("href","pharmacist/homepage.html");
	$("#train").attr("href","train/homepage.html");
	$("#my").attr("href","my/my.html");
	
	$("#${id}").removeAttr("href");
});
</script>
<div class="footer">
   <ul>
       <li class="menuicon hy">
           <a id="vip" class="footer_icon" href="wxvip/homepage.html">
               <i class="iconm m1"></i>
               <span>会员</span>
           </a>
       </li>
       <li class="menuicon yp">
           <a id="medicine" class="footer_icon" href="medicine/medicines.html">
               <i class="iconm m2"></i>
               <span>药品</span>
           </a>
       </li>
       <li class="menuicon sy">
           <a id="homepage" class="footer_icon" href="pharmacist/homepage.html">
               <i class="iconm m3"></i>
               <span>首页</span>
           </a>
       </li>
       <li class="menuicon px">
           <a id="train" class="footer_icon" href="train/homepage.html">
               <i class="iconm m4"></i>
               <span>培训</span>
           </a>
       </li>
       <li class="menuicon wd">
           <a id="my" class="footer_icon" href="my/my.html">
               <i class="iconm m5"></i>
               <span>我的</span>
           </a>
       </li>
   </ul>
</div>