package com.net.wx.vo;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nky.Constants;

/**
 * WeChat public information
 */
public class MPAct {

	/**
	 * Public number originalID
	 */
	private String mpId;

	/**
	 * Public number
	 */
	private String nickName;

	/**
	 * applicationId
	 */
	private String appId;

	/**
	 * Application key
	 */
	private String appSecret;

    /**
     * token
     */
    private String token;

	/**
	 * AESSecure encryption key
	 */
	private String AESKey;

	/**
	 * Public number type
	 * D:Subscription number
	 * E:Enterprise number
	 * S:Service number
	 * 
	 */
	private String mpType;

	/**
	 * Whether authentication
	 */
	private boolean pass;

	/**
	 * Application certificate
	 */
	private String accessToken;

	/**
	 * Certificate valid time(second)
	 */
	private long expiresIn;

    /**
     * Pre authorization code
     */
    private String preAuthCode;

    /**
     * Pre authorization code effective time(second)
     */
    private long preAuthExpiresIn;

    /**
     * JSAPIvoucher
     */
    private String jsTicket;

    /**
     * JSAPICertificate valid time(second)
     */
    private long jsExpiresIn;
    
    private String dpid;

	public MPAct(String mpId,String nickName,String appId,String appSecret,String mpType,boolean pass,String token,String AESKey, String dpid) {
		this.mpId = mpId;
		this.nickName = nickName;
		this.appId = appId;
		this.appSecret = appSecret;
		this.mpType = mpType;
		this.pass = pass;
		this.token = token;
		this.AESKey = AESKey;
		this.dpid=dpid;
	}

	public MPAct() {
		// TODO Auto-generated constructor stub
	}
	public MPAct(String appid,String appseret,String token,String aeskey){
		this.setAppId(appid);
        this.setAppSecret(appseret);
        this.setToken(token);
        this.setAESKey(aeskey);
	}

	
	public String getDpid() {
		return dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}

	public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAESKey() {
        return AESKey;
    }

    public void setAESKey(String AESKey) {
        this.AESKey = AESKey;
    }

    public String getMpType() {
        return mpType;
    }

    public void setMpType(String mpType) {
        this.mpType = mpType;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
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
        long lose_time = (tmp.getLong("expires_in")-60) * 1000
                + System.currentTimeMillis();
        setExpiresIn(lose_time);
    }

    public String getPreAuthCode() {
        return preAuthCode;
    }

    public void setPreAuthCode(String preAuthCode) {
        this.preAuthCode = preAuthCode;
    }

    public long getPreAuthExpiresIn() {
        return preAuthExpiresIn;
    }

    public void setPreAuthExpiresIn(long preAuthExpiresIn) {
        this.preAuthExpiresIn = preAuthExpiresIn;
    }

    /**
     * Analysis of WeChat server to return the message generated pre authorization code
     *
     * @param result    Return message
     */
    public void createPreAuthCode(String result) {
        JSONObject tmp = JSONObject.parseObject(result);
        setPreAuthCode(tmp.getString("pre_auth_code"));
        long lose_time = (tmp.getLong("expires_in")-60) * 1000
                + System.currentTimeMillis();
        setPreAuthExpiresIn(lose_time);
    }

    public String getJsTicket() {
        return jsTicket;
    }

    public void setJsTicket(String jsTicket) {
        this.jsTicket = jsTicket;
    }

    public long getJsExpiresIn() {
        return jsExpiresIn;
    }

    public void setJsExpiresIn(long jsExpiresIn) {
        this.jsExpiresIn = jsExpiresIn;
    }

    /**
     * Analysis of WeChat server to return message generationJSTICKET
     *
     * @param result    Return message
     */
    public void createJsTicket(String result) {
        JSONObject tmp = JSONObject.parseObject(result);
        setJsTicket(tmp.getString("ticket"));
        long lose_time = (tmp.getLong("expires_in")-60) * 1000
                + System.currentTimeMillis();
        setJsExpiresIn(lose_time);
    }

    @Override
    public String toString() {
        return "MPAct{" +
                "mpId='" + mpId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", token='" + token + '\'' +
                ", AESKey='" + AESKey + '\'' +
                ", mpType='" + mpType + '\'' +
                ", pass=" + pass +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", preAuthCode='" + preAuthCode + '\'' +
                ", preAuthExpiresIn=" + preAuthExpiresIn +
                ", jsTicket='" + jsTicket + '\'' +
                ", jsExpiresIn=" + jsExpiresIn +
                '}';
    }
}