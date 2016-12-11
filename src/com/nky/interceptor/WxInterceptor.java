package com.nky.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.net.util.PubMethod;
import com.nky.Constants;


/**
 * 拦截器
 * @author smiven
 *
 */
public class WxInterceptor implements HandlerInterceptor{

	private static final String URL_PLOGIN = "/index/toLogin.html";
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String uri = request.getRequestURI();
		if(!PubMethod.isEmpty(uri)  &&  uri.startsWith("/nkywx/customer") 
				&& !uri.endsWith("/index/toLogin.html")//登录界面
				&& !uri.endsWith("/index/login.json")//登录验证密码
				&& !uri.endsWith("/index/updatePWD.html")//修改密码
				&& !uri.endsWith("/index/sendYZM.html")//获取验证码
				&& !uri.endsWith("/index/findPSW.html")){//找回密码
			if(isLogin(request)){
				return true;
			}else{
				PrintWriter pw = response.getWriter();
				pw.write("<script>window.parent.location.href='" + request.getContextPath() + URL_PLOGIN + "'</script>");
				pw.flush();
				return false;
			}
		}
		return true;
	}

	
	private boolean isLogin(HttpServletRequest request) {
		Object pharmacistEntity = request.getSession().getAttribute(Constants.CUSTOMER_SESSION_KEY);
		if (pharmacistEntity != null) {//用户信息已存储至session中
			return true;
		}
		
		return false;
	}
}
