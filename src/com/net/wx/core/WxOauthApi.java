package com.net.wx.core;


import com.net.wx.exception.WxRespException;

/**
 * 微信登录授权接口设计
 */
public interface WxOauthApi {
	
	/**
	 * @Title: getAccessToken
	 * @Description: 获取微信登录授权的accessToken
	 * @param @throws WxRespException    
	 * @return String    
	 * @throws 
	 */
	String getAccessToken() throws WxRespException;

	/**
	 * @Title: checkAccessToken
	 * @Description: 检查accessToken是否有效
	 * @param @throws WxRespException    
	 * @return boolean    
	 * @throws 
	 */
	boolean checkAccessToken() throws WxRespException;
	
	/**
	 * @Title: oauthUserInfo
	 * @Description: 授权后的用户信息
	 * @param @throws WxRespException    
	 * @return String[openid,nickname,province,city,country,headimgurl]    
	 * @throws 
	 */
	String[] oauthUserInfo() throws WxRespException;
	
}
