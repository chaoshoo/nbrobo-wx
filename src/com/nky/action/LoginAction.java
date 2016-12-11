package com.nky.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.net.wx.vo.Follower;
import com.net.wx.vo.MPAct;
import com.nky.Constants;
import com.nky.entity.VipEntity;
import com.nky.service.ApiInterface;
import com.nky.service.VipService;
import com.nky.vo.Vip;

@RequestMapping("/index")
@Controller
public class LoginAction extends WxBaseAction {
	// 用于调试;
	private String testopenId = "ojZ-ZxDQfJcmwJ-8pC94VeqnfCmE-";
	@Autowired
	private VipService vipService;

	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request) {
		String tel = request.getParameter("tel");// Mobile phone number used to retrieve the password，Have on display ，Nothing is empty
		if (tel == null) {
			tel = "";
		}
		String code = request.getParameter("code");
		System.out.println("code----------->" + code);
		codeSess(request, code);
		String openId = openIdExists(request);
		if (ServiceConstants.dev_debug) {
			openId = testopenId;
		}
		openIdSess(request, openId);
		System.out.println("openid1----------->" + openId);
		// 执行openid查询 有记录 返回 首页 无记录登录
		VipEntity vip = null;
		try {
			if (PubMethod.isEmpty(openId)) {
				openId = getAccessToken(code, request);
				openIdSess(request, openId);
			}
			if (!PubMethod.isEmpty(openId)) {
				vip = vipService.isLogin(null, null, openId);
				MPAct mpact = new MPAct(Constants.APPID, Constants.APPSECRET, Constants.TOKEN, Constants.AESKEY);
				WxApiImpl wxApi = new WxApiImpl(mpact);
				if(!ServiceConstants.dev_debug){
					Follower fo = wxApi.getFollower(openId, System.currentTimeMillis() + "");
					request.getSession().setAttribute(Constants.WX_USER_HEAD_IMG, fo.getHeadImgUrl());
				}
			}
			// 写入session
			if (vip != null && vip.getIsvalid() == 1) {
				vipService.updatelasttime(vip.getVip_code());
				request.getSession().setAttribute(Constants.CUSTOMER_SESSION_KEY, vip);
				//更新微信code
				Db.update("update t_vip set wx_code=? where vip_code=?",code,vip.getVip_code());
				return homePage(request);
			}
			request.setAttribute("tel", tel);
			System.out.println("openid2----------->" + openId);
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}
		return "login";
	}

	@RequestMapping("/login")
	@ResponseBody
	public Data login(HttpServletRequest request, String tel, String password) {
		Data data = new Data();
		Object code = request.getSession().getAttribute("wxcode");
		log.info("code>>"+code);
		String wxcode = "";
		if(code != null){
			wxcode = code.toString();
		}
		try {
			VipEntity vip = vipService.isLogin(tel, password, null);
			if (PubMethod.isEmpty(vip)) {
				data.setCode(0);
			}else if(vip.getIsvalid() != 1){
				data.setCode(-1);
			} else {
				vipService.updatelasttime(vip.getVip_code());
				String openId = openIdExists(request);
				if (PubMethod.isEmpty(openId)) {
						data.setCode(2);
						return data;
				}
				vip.setWxopenid(openId);
				request.getSession().setAttribute(Constants.CUSTOMER_SESSION_KEY, vip);
				vipService.setOpenId(vip.getId(), openId,wxcode);
				data.setCode(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			data.setCode(0);
		}
		return data;
	}
	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest request) {
		try {
			VipEntity vip = getVip(request);
			if (!PubMethod.isEmpty(vip)) {
				//wxopenid 加一个-结尾  表示微信退退出登录
				String wxopenid = vip.getWxopenid()+"-";
				vipService.setOpenId(vip.getId(), wxopenid,"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  "login";
	}

	@RequestMapping("/homepage")
	public String homePage(HttpServletRequest request) {
		try {
			String openId = openIdExists(request);
			log.info("homepage openid:"+openId);
			if (PubMethod.isEmpty(openId)) {
				return toLogin(request);
			}
			Vip vip = vipService.getVipInfo(openId);
			request.setAttribute("vip", vip);
			String url = (String)request.getSession().getAttribute(Constants.WX_USER_HEAD_IMG);
			if(StringUtils.isEmpty(url)){
				url = ServiceConstants.default_heard_img_url;
			}
			request.setAttribute("heard_img_url", url);
			//取最近一次挂号记录的id
			log.info("vip_code="+vip.getVip_code());
//			String nowtime = DateUtil.nowDateForStrYMD();
//			String sql = "select * from vip_reg where vip_code=? and outpdate>=? order by outpdate limit 1" ;
//			Record record = Db.findFirst(sql,vip.getVip_code(),nowtime);
//			if(record == null){
//				//没有大于当前日期的挂号  取最后一次挂号记录
				String sql = "select * from vip_reg where vip_code=? order by outpdate desc limit 1";
				Record record = Db.findFirst(sql,vip.getVip_code());
//			}
			if(record == null){
				request.setAttribute("orderid", "");
				request.setAttribute("guahao_size", "0");
				request.setAttribute("outpdate", "");
			}else{
				request.setAttribute("orderid", record.get("orderid"));
				request.setAttribute("guahao_size", "1");
				request.setAttribute("outpdate", record.get("outpdate"));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "index";
	}

	@RequestMapping("/getMessage")
	@ResponseBody
	public Data getMessage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Data data = new Data();
		String tel = request.getParameter("tel");
		String type = request.getParameter("type");
		String result = "Abnormal system";
		if (type != null && type.equals("register")) {
			// 验证手机号是否已经注册
			if (vipService.isExistTel(tel)) {
				data.setCode(0);
				data.setMsg("Mobile phone number has been registered,Cannot duplicate registration");
				return data;
			}
			result = ApiInterface.registMessage(tel);
		} else if (type != null && type.equals("changepwd")) {
			result = ApiInterface.changepwdmessage(tel);
		}
		if (ApiInterface.isSuccess(result)) {
			data.setCode(1);
		} else {
			data.setCode(0);
			data.setMsg(result);
		}
		return data;
	}

	/**
	 * Jump to retrieve password
	 */
	@RequestMapping(value = "/findPSW")
	public String findPSW(HttpServletRequest request) {
		VipEntity vip = getVip(request);
		if(vip == null){
			request.setAttribute("tel", "");
		}else{
			request.setAttribute("tel", vip.getMobile());
		}
		return "find_psw";
	}

	/**
	 * Change password
	 */
	@RequestMapping(value = "/updatePWD")
	@ResponseBody
	public Data updatePWD(HttpServletRequest request) {
		Data data = new Data();
		try {
			String tel = request.getParameter("tel");
			String pwd = request.getParameter("password");
			String pwdmd5 = MD5Util.MD5(pwd, "utf-8");
			String rand = request.getParameter("rand");
			// 验证 验证码
			String result = ApiInterface.checkmessage(tel, rand);
			if (!ApiInterface.isSuccess(result)) {
				data.setCode(0);
				data.setMsg("Verification code error");
				return data;
			}
			Db.update("update t_vip set login_password=? where mobile=?", pwdmd5, tel);
			request.setAttribute("tel", tel);
			// 清理session
			request.getSession().setAttribute(Constants.CUSTOMER_SESSION_KEY, new VipEntity());
			data.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			data.setCode(0);
			data.setMsg("Abnormal system");
		}

		return data;
	}

	/**
	 * register
	 */
	@RequestMapping(value = "/toRegister")
	public String toRegister(HttpServletRequest request) {
		String openId = openIdExists(request);
		return "register";
	}

	@RequestMapping(value = "/register")
	@ResponseBody
	public Data register(HttpServletRequest request) {
		Data data = new Data();
		String rand = request.getParameter("rand");
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		String idcard = request.getParameter("idcard");
		// 验证 验证码
		String result = ApiInterface.checkmessage(tel, rand);
		if (!ApiInterface.isSuccess(result)) {
			data.setCode(0);
			data.setMsg("Verification code error");
			return data;
		}
		// 验证手机号是否已经注册
		if (vipService.isExistTel(tel)) {
			data.setCode(0);
			data.setMsg("Mobile phone number has been registered,Cannot duplicate registration");
			return data;
		}
		try {
			MPAct mpact = new MPAct(Constants.APPID, Constants.APPSECRET, Constants.TOKEN, Constants.AESKEY);
			WxApiImpl wxApi = new WxApiImpl(mpact);
			String openId = openIdExists(request);
			System.out.println("register openid::" + openId);
			Follower fo = wxApi.getFollower(openId, System.currentTimeMillis() + "");

			VipEntity entity = new VipEntity();
			entity.setMobile(tel);
			entity.setLogin_account(tel);
			entity.setPhone(tel);
			entity.setVip_code(SysId.getNextVipCode());
			entity.setPapers_num(idcard);
			entity.setCard_code(idcard);
			entity.setHeard_img_url(fo.getHeadImgUrl());
			entity.setNick_name(fo.getNickName());
			entity.setSex(fo.getSex()+"");
			entity.setLogin_password(MD5Util.MD5(password, "utf-8").toLowerCase());
			entity.setIsvalid(1);
			// String openId = getAccessToken(code, request);
			entity.setCreate_time(new Date());
			boolean flag = JFinalDb.save(entity);
			if (flag) {
				data.setCode(1);
			} else {
				data.setCode(0);
				data.setMsg("Registeration failed，Please contact administrator");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			data.setCode(0);
			data.setMsg("Registration abnormal，Please contact administrator");
		}
		return data;
	}
	
}