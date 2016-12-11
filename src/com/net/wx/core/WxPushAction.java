package com.net.wx.core;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.singleton.WxRegisterSingleton;
import com.net.util.MD5Util;
import com.net.wx.vo.MPAct;
import com.net.wx.vo.OutPutMsg;
import com.nky.Constants;

@Controller
@RequestMapping("/wxPush")
public class WxPushAction {

	/**
	 * External interface  
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/send")
	@ResponseBody
	public String send(HttpServletRequest req) {
		try {
//			String dpid = req.getParameter("dpid")+"";
//			dpid = dpid.substring(0, 3);
			String dpid="100";
			Record r = WxRegisterSingleton.getInstance().getInfoByDpid(dpid);
			if(r==null){
				return "false:WeChat public account configuration error。";
			}
			String openid = req.getParameter("wxid");
			String message = req.getParameter("message");
			String token =  req.getParameter("token");
			System.out.println("openid::"+openid);
			System.out.println("message::"+message);
			String str = Constants.WEIXIN_MD5KEY+openid+message;
			if(!MD5Util.MD5(str, "utf-8").equals(token)){
				System.out.println("token 认证失败");
				return "false:Authentication failure。";
			}
			MPAct mpAct = new MPAct();
			mpAct.setAppId(r.get("APPID") + "");
			mpAct.setAppSecret(r.get("APPSECRET") + "");
			mpAct.setToken(r.get("TOKEN") + "");
			mpAct.setAESKey(r.get("AESKEY") + "");
			mpAct.setAccessToken(r.get("ACCESSTOKEN") + "");
			mpAct.setDpid(dpid);

			WxApi wxApi = new WxApiImpl(mpAct);
			String[] openids = openid.split(",");
			int x = 0;
			for (int i = 0; i < openids.length; i++) {
				OutPutMsg msg = new OutPutMsg(openids[i],mpAct.getAppId(), "text");
				msg.setContent(message);
				boolean result = wxApi.sendCustomerMsg(msg);
				if(result){
					x++;
				}
			}
			if(x == openids.length){
				return "true";
			}else{
				return "false:Partial push failure。";
			}
//			String sql = "insert  into `t_weixin_log`(`wxid`,`content`,`result`,`createtime`) values ('"+openid+"','"+message+"','"+result+"',now())";

//			Db.update(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "false:Abnormal system。";
		}

	}

}