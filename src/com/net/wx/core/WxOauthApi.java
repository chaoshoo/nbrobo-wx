package com.net.wx.core;


import com.net.wx.exception.WxRespException;

/**
 * WeChat login authorization interface design
 */
public interface WxOauthApi {
	
	/**
	 * @Title: getAccessToken
	 * @Description: Get WeChat login authorizationaccessToken
	 * @param @throws WxRespException    
	 * @return String    
	 * @throws 
	 */
	String getAccessToken() throws WxRespException;

	/**
	 * @Title: checkAccessToken
	 * @Description: inspectaccessTokenWhether effective
	 * @param @throws WxRespException    
	 * @return boolean    
	 * @throws 
	 */
	boolean checkAccessToken() throws WxRespException;
	
	/**
	 * @Title: oauthUserInfo
	 * @Description: Authorized user information
	 * @param @throws WxRespException    
	 * @return String[openid,nickname,province,city,country,headimgurl]    
	 * @throws 
	 */
	String[] oauthUserInfo() throws WxRespException;
	
}