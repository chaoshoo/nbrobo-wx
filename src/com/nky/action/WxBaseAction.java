package com.nky.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.net.wx.core.WxOauthApi;
import com.net.wx.core.WxOauthApiImpl;
import com.net.wx.exception.WxRespException;
import com.net.wx.vo.OauthAct;
import com.nky.Constants;
import com.nky.entity.VipEntity;

public class WxBaseAction {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	public String openIdExists(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("openId");
		if (null == obj) {
			return null;
		} else {
			return (String) obj;
		}

	}

	public String getAccessToken(String code, HttpServletRequest request) {
		WxOauthApi wxOauthApi = getOauthApi(request);
		OauthAct oa = ((WxOauthApiImpl) wxOauthApi).getOauthAct();
		// 获取toKen
		try {
			oa.setCode(code);
			wxOauthApi.getAccessToken();
			oa = ((WxOauthApiImpl) wxOauthApi).getOauthAct();
		} catch (WxRespException e) {
			e.printStackTrace();
			return null;
		}
		return oa.getOpenId();
	}

	public void openIdSess(HttpServletRequest request, String openId) {
		HttpSession session = request.getSession();
		session.setAttribute("openId", openId);
	}
	public void codeSess(HttpServletRequest request, String code) {
		HttpSession session = request.getSession();
		session.setAttribute("wxcode", code);
	}

	// 从session中获取授权接口对象
	public WxOauthApi getOauthApi(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("oauth_api");
		if (null == obj) {
			initOauthApi(request);
			obj = request.getSession().getAttribute("oauth_api");
		}
		WxOauthApi wxOauthApi = (WxOauthApi) obj;
		return wxOauthApi;
	}

	// 初始化授权接口保存到session中
	public void initOauthApi(HttpServletRequest request) {
		WxOauthApi wxOauthApi = new WxOauthApiImpl(new OauthAct());
		request.getSession().setAttribute("oauth_api", wxOauthApi);
	}

	/**
	 * 返回Entity登录实体
	 * @param request
	 * @return
	 */
	public VipEntity getVip(HttpServletRequest request) {
		VipEntity pe = (VipEntity) request.getSession().
				getAttribute(Constants.CUSTOMER_SESSION_KEY);
		return pe;
	}
	
	public int getPageNo(HttpServletRequest request){
		String page = request.getParameter("pageNo");
		int pageNo = 1;
		if(!StringUtils.isEmpty(page)){
			pageNo = Integer.parseInt(page);
		}
		return pageNo;
	}
}
