package com.net.wx.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * WeChat open platform license public information
 */
public class AuthInfo {
    /**
     * Authorized party
     */
    @JSONField(name = "nick_name")
    private String nickName;
    /**
     * Authorized partyappid
     */
    @JSONField(name = "authorizer_appid")
    private String appId;
    /**
     * Authorized party token（In the authorized public numberAPIPermission time<p/>To return a value）
     */
    @JSONField(name = "authorizer_access_token")
    private String accessToken;
    /**
     * Term of validity（In the authorized public numberAPIPermission time<p/>To return a value）
     */
    @JSONField(name = "expires_in")
    private long expiresIn;
    /**
     * Authorized party head
     */
    @JSONField(name = "head_img")
    private String headImg;
    /**
     * Type of authorized public<p/>
     * 0Representative subscriber number<p/>
     * 1Representative from the history of the old account after the upgrade of the subscription number<p/>
     * 2Representative service number
     */
    @JSONField(name = "service_type_info")
    private String serTypeInfo;
    /**
     * Type of authorized party certification<p/>
     * -1Representative not certified<p/>
     * 0WeChat certified<p/>
     * 1Micro-blog certified Sina<p/>
     * 2Micro-blog certified Tencent<p/>
     * 3The representative has passed the qualification certification but has not yet passed the name authentication<p/>
     * 4Representative has passed the qualification certification、Has not passed the name authentication<p/>
     * Micro-blog Sina certified<p/>
     * 5Representative has passed the qualification certification、Has not passed the name authentication<p/>But through the Tencent micro-blog certified
     */
    @JSONField(name = "verify_type_info")
    private String verTypeInfo;
    /**
     * The original public number of the authorized partyID
     */
    @JSONField(name = "user_name")
    private String mpId;
    /**
     * The micro signal set by the authorized party public number<p/>May be empty
     */
    @JSONField(name = "alias")
    private String alias;
    /**
     * refresh token（In the authorized public numberAPIPermission time<p/>To return a value）<p/><p/>
     * Refresh token is mainly used for public service access and refresh has authorized usersaccess_token<p/><p/>
     * Will only be available at the time of authorization<p/>Please keep properly。 Once lost<p/>Only allow users to re authorize<p/><p/>
     * In order to get a new refresh token
     */
    @JSONField(name = "authorizer_refresh_token")
    private String refreshToken;

    /**
     * A list of permissions set for the developer by the public number
     */
    @JSONField(name = "func_info")
    private List<FunctionInfo> funs;

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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSerTypeInfo() {
        return serTypeInfo;
    }

    public void setSerTypeInfo(String serTypeInfo) {
        this.serTypeInfo = serTypeInfo;
    }

    public String getVerTypeInfo() {
        return verTypeInfo;
    }

    public void setVerTypeInfo(String verTypeInfo) {
        this.verTypeInfo = verTypeInfo;
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<FunctionInfo> getFuns() {
        return funs;
    }

    public void setFuns(List<FunctionInfo> funs) {
        this.funs = funs;
    }

    @Override
    public String toString() {
        return "AuthInfo{" +
                "nickName='" + nickName + '\'' +
                ", appId='" + appId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn='" + expiresIn + '\'' +
                ", headImg='" + headImg + '\'' +
                ", serTypeInfo='" + serTypeInfo + '\'' +
                ", verTypeInfo='" + verTypeInfo + '\'' +
                ", mpId='" + mpId + '\'' +
                ", alias='" + alias + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", funs=" + String.valueOf(funs) +
                '}';
    }

    /**
     * Permission set for the developer by the public number
     */
    public class FunctionInfo {

        /**
         * A list of permissions set for the developer by the public number[1-9]Respectively representative<p/>
         * 1.Message and menu permission set<p/>
         * 2.User management authority<p/>
         * 3.Account management permission set<p/>
         * 4.Web license set<p/>
         * 5.WeChat store permission set<p/>
         * 6.Multi customer service permission set<p/>
         * 7.Service notification authority set<p/>
         * 8.WeChat card set<p/>
         * 9.WeChat sweep permission set<p/>
         */
        @JSONField(name = "funcscope_category")
        private String category;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return "FunctionInfo{" +
                    "category='" + category + '\'' +
                    '}';
        }
    }
}