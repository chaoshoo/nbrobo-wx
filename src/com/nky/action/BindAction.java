package com.nky.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.net.util.PubMethod;
import com.net.wx.core.WxApiImpl;
import com.net.wx.vo.Follower;
import com.net.wx.vo.MPAct;
import com.nky.Constants;
import com.nky.entity.VipEntity;
import com.nky.service.VipService;

/**
 * 绑定用户身份信息
 * @author LL
 * @date 2017-8-23 下午11:08:07
 * <p>Description:</p>
 */
@RequestMapping("/index")
@Controller
public class BindAction extends WxBaseAction {
	
	@Autowired
	private VipService vipService;

	@RequestMapping("/toBind")
	public String toBind(HttpServletRequest request,Model model) throws Exception {
		Follower fo =new Follower();
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
			fo = wxApi.getFollower(openId, System.currentTimeMillis() + "");
			System.out.println("sex=="+fo.getSex());
			request.getSession().setAttribute(Constants.WX_USER_HEAD_IMG, fo.getHeadImgUrl());
			openId=fo.getOpenId();
		}
		String papersNum="";
		String heardImgUrl="";
		String nickName="";
		//没有获取到数据
		VipEntity vip=vipService.getVip(openId);
		if(vip == null){
			papersNum="";
//			return "redirect:/index/toLogin.do"; 
		}else {
			papersNum=vip.getPapers_num();
			System.out.println("getvipinfo==="+papersNum);
		}
		heardImgUrl=fo.getHeadImgUrl();
		nickName=fo.getNickName();
		System.out.println("openId==="+openId+"papersNum=="+papersNum+"heardImgUrl="+heardImgUrl+"nickName=="+nickName);
		model.addAttribute("openId", openId);
		model.addAttribute("certno", papersNum);
		model.addAttribute("heardImgUrl", heardImgUrl);
		model.addAttribute("nickName", nickName);
		return "redirect:http://wx.nbrobo.com:8098/wx/wxmain.html";  
	}
	
}
