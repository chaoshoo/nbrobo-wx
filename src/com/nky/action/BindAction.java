package com.nky.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.ServiceConstants;
import com.net.entity.bo.Data;
import com.net.jfinal.JFinalDb;
import com.net.singleton.SysId;
import com.net.util.DateUtil;
import com.net.util.MD5Util;
import com.net.util.PubMethod;
import com.net.wx.core.WxApiImpl;
import com.net.wx.exception.WxRespException;
import com.net.wx.vo.Follower;
import com.net.wx.vo.MPAct;
import com.nky.Constants;
import com.nky.entity.VipEntity;
import com.nky.service.ApiInterface;
import com.nky.service.VipService;
import com.nky.vo.Vip;

/**
 * 绑定用户身份信息
 * @author LL
 * @date 2017-8-23 下午11:08:07
 * <p>Description:</p>
 */
@RequestMapping("/index")
@Controller
public class BindAction extends WxBaseAction {

	@RequestMapping("/toBind")
	public String toBind(HttpServletRequest request,Model model) throws WxRespException {
		String openId2="";
		try {
			openId2= getVip(request).getWxopenid();
		} catch (Exception e) {
			System.out.println(e);
		}
		finally{
			
		}
		
		String code = request.getParameter("code");
		System.out.println("code----------->" + code);
		codeSess(request, code);
		String openId = openIdExists(request);
		openIdSess(request, openId);
		System.out.println("openid1----------->" + openId);
		if (PubMethod.isEmpty(openId)) {
			openId = getAccessToken(code, request);
			openIdSess(request, openId);
		}
		if (!PubMethod.isEmpty(openId)) {
			MPAct mpact = new MPAct(Constants.APPID, Constants.APPSECRET, Constants.TOKEN, Constants.AESKEY);
			WxApiImpl wxApi = new WxApiImpl(mpact);
			Follower fo = wxApi.getFollower(openId, System.currentTimeMillis() + "");
			request.getSession().setAttribute(Constants.WX_USER_HEAD_IMG, fo.getHeadImgUrl());
			System.out.println(fo);
			openId=fo.getOpenId();
			System.out.println(openId);
		}
		
		model.addAttribute("openId", openId);
		model.addAttribute("openId2", openId2);
		
		return "redirect:http://123.56.5.154:8081/sendmessage/message/send.do";  
	}
	
}
