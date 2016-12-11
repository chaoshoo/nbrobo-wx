package com.net.wx.vo;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nky.Constants;

/**
 * 微信登录授权信息
 */
public class OauthAct {

	/**
	 * 授权应用Id
	 */
	private String oauthAppId;

	/**
	 * 授权应用密钥
	 */
	private String oauthAppSecret;
	/**
	 * 公众号应用Id
	 */
	private String appId;
	
	/**
	 * 公众号应用密钥
	 */
	private String appSecret;
	
	/**
	 * 获取accessToken的票据
	 */
	private String code;

	/**
	 * 应用凭证
	 */
	private String accessToken;
	
	/**
	 * 当accessToken超时时,用refreshToken来刷新accessToken
	 */
	private String refreshToken;
	
	/**
	 * 授权的用户ID
	 */
	private String openId;

	/**
	 * 凭证有效时间(秒)
	 */
	private long expiresIn;
	
	/**
	 * 用户授权的作用域
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
     * 解析微信服务器返回消息生成高级API或服务的凭证
     *
     * @param result    返回消息
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
