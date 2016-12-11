package com.net.wx.core;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.net.wx.common.WxApiUrl;
import com.net.wx.exception.WxRespException;
import com.net.wx.util.SimpleHttpReq;
import com.net.wx.vo.OauthAct;

/**
 * WeChat Public Platform DeveloperAPIInterface implementation
 */
public class WxOauthApiImpl implements WxOauthApi {

    private static final Logger log = LoggerFactory.getLogger(WxOauthApiImpl.class);

    private OauthAct oauthAct;

    /**
     * WeChat login authorization interface construction
     *
     * @param mpAct Public information
     */
    public WxOauthApiImpl(OauthAct oauthAct) {
        this.oauthAct = oauthAct;
    }

    @Override
    public String getAccessToken() throws WxRespException {
    	String token = null;
    	String url = null;
    	synchronized (oauthAct){
    		url = String.format(WxApiUrl.OAUTH_TOKEN,
    				oauthAct.getAppId(),
    				oauthAct.getAppSecret(),
    				oauthAct.getCode());
    		refreshAccessToken(url);
    	}
    	token = oauthAct.getAccessToken();
//        String token = oauthAct.getAccessToken();
//        String url = null;
//        if(StringUtils.isEmpty(token) && StringUtils.isEmpty(oauthAct.getOpenId())){
//        }else if (oauthAct.getExpiresIn() < System.currentTimeMillis()) {
//            synchronized (oauthAct){
//            	 url = String.format(WxApiUrl.OAUTH_REFRESH_TOKEN,
//                 		oauthAct.getAppId(),
//                 		oauthAct.getRefreshToken());
//                refreshAccessToken(url);
//            }
//            token = oauthAct.getAccessToken();
//        }
        return token;
    }

    public void refreshAccessToken(String url) throws WxRespException {
 //   	log.info("url>>"+url);
        String result = "";
        try {
            result = SimpleHttpReq.get(url);
        } catch (IOException e) {
        	e.printStackTrace();
            log.error("RefreshACCESS_TOKENWhen there is abnormal!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
           throw new WxRespException(result);
        }

        oauthAct.createAccessToken(result);
    }

	@Override
	public boolean checkAccessToken() throws WxRespException {
		String url = String.format(WxApiUrl.CHECK_OAUTH_TOKEN, getAccessToken(),oauthAct.getOpenId());
		String result = null;
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			 log.error("Inspection authorization certificate(access_token)Abnormal appearance!!!");
	         log.error(e.getLocalizedMessage(), e);
	         return false;
		}
		if (result.isEmpty()
                || !result.contains("ok")) {
            throw new WxRespException(result);
        }

        return true;
	}

	@Override
	public String[] oauthUserInfo() throws WxRespException {
		String url = String.format(WxApiUrl.OAUTH_USERINFO, getAccessToken(),oauthAct.getOpenId());
		String result = null;
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			 log.error("Get the user's personal information when abnormal!!!");
	         log.error(e.getLocalizedMessage(), e);
		}
		if (result.isEmpty()
                || result.contains("errcode")) {
            throw new WxRespException(result);
        }
		
		JSONObject tmp = JSON.parseObject(result);
        String[] results = {
                tmp.getString("openid"),
                tmp.getString("nickname"),
                tmp.getString("province"),
                tmp.getString("city"),
                tmp.getString("country"),
                tmp.getString("headimgurl")
        };

		return results;
	}

	public OauthAct getOauthAct() {
		return oauthAct;
	}

}