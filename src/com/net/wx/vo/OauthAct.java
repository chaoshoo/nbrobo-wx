package com.net.wx.vo;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nky.Constants;

/**
 * WeChat login authorization information
 */
public class OauthAct {

	/**
	 * License applicationId
	 */
	private String oauthAppId;

	/**
	 * License application key
	 */
	private String oauthAppSecret;
	/**
	 * Public number applicationId
	 */
	private String appId;
	
	/**
	 * Public key
	 */
	private String appSecret;
	
	/**
	 * ObtainaccessTokenBill
	 */
	private String code;

	/**
	 * Application certificate
	 */
	private String accessToken;
	
	/**
	 * WhenaccessTokenSuper from time to time,userefreshTokenTo refreshaccessToken
	 */
	private String refreshToken;
	
	/**
	 * Authorized userID
	 */
	private String openId;

	/**
	 * Certificate valid time(second)
	 */
	private long expiresIn;
	
	/**
	 * User authorization scope
	 */
	private String scope;
	
	private String state = "123";

    public OauthAct() {
		this.oauthAppId = Constants.OAUTH_APPID;
		this.oauthAppSecret = Constants.OAUTH_APPSECRET;
		this.appId = Constants.APPID;
		this.appSecret = Constants.APPSECRET;
	}

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
    

    public String getOauthAppId() {
		return oauthAppId;
	}

	public void setOauthAppId(String oauthAppId) {
		this.oauthAppId = oauthAppId;
	}

	public String getOauthAppSecret() {
		return oauthAppSecret;
	}

	public void setOauthAppSecret(String oauthAppSecret) {
		this.oauthAppSecret = oauthAppSecret;
	}

	/**
     * WeChat server to parse the return message generation advancedAPICertificate of service or service
     *
     * @param result    Return message
     */
    public void createAccessToken(String result) {

        JSONObject tmp = JSON.parseObject(result);
        if (tmp.containsKey("access_token")) {
            setAccessToken(tmp.getString("access_token"));
        } else {
            setAccessToken(tmp.getString("component_access_token"));
        }
        setRefreshToken(tmp.getString("refresh_token"));
        setOpenId(tmp.getString("openid"));
        setScope(tmp.getString("scope"));
        long lose_time = (tmp.getLong("expires_in")-60) * 1000
                + System.currentTimeMillis();
        setExpiresIn(lose_time);
    }
    
    

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
    public String toString() {
        return "OauthAct{" +
                "appId='" + appId + '\'' +
                ",appSecret='" + appSecret + '\'' +
                ",oauthAppId='" + oauthAppId + '\'' +
                ",oauthAppSecret='" + oauthAppSecret + '\'' +
                "code='" + code + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                ", state='" + state + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}