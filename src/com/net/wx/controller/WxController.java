package com.net.wx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.net.wx.core.WxDefaultHandler;
import com.net.wx.support.WxSpringSupport;
import com.net.wx.vo.MPAct;
import com.nky.Constants;

@Controller
@RequestMapping("/weixin")
public class WxController extends WxSpringSupport{
	
	private static final Logger log = LoggerFactory.getLogger(WxSpringSupport.class);
	
	/**
	 * Sign in
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String toLoginJsp(HttpServletRequest request,
			HttpServletResponse response) {
		String weixinId = request.getParameter("weixinId");
		String dpid = request.getParameter("dpid");
		request.setAttribute("weixinId", weixinId);
		request.setAttribute("dpid", dpid);
		return "weixin/login";
	}



    protected void init(HttpServletRequest req) {
    	try {
            MPAct mpAct = new MPAct();
            mpAct.setAppId(Constants.APPID);
            mpAct.setAppSecret(Constants.APPSECRET);
            mpAct.setToken(Constants.TOKEN);
            mpAct.setAESKey(Constants.AESKEY);
 //           mpAct.setAccessToken(Constants.ACCESSTOKEN);
            this.setMpAct(mpAct);
            // 可实现自己的WxHandler
            this.setWxHandler(new WxDefaultHandler());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/webChat")
    @ResponseBody
    public String wxCore(HttpServletRequest req,HttpServletResponse rep) {
        String reply = "";
        try {
            reply = wxInteract(req);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return reply;
    }
}