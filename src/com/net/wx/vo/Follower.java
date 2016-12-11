package com.net.wx.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * WeChat subscriber information
 */
public class Follower {

    /**
     * Whether to subscribe(0 Unsubscribe, 1 Subscribe)
     */
	private int subscribe;

    /**
     * Concerned about the number of public signs only
     */
	private String openId;

    /**
     * WeChat Nick
     */
	private String nickName;

    /**
     * Gender(1 male, 2 female, 0 Unknown)
     */
	private int sex;

    /**
     * User City
     */
	private String city;

    /**
     * User country
     */
	private String country;

    /**
     * User Province
     */
	private String province;

    /**
     * User language，Simplified Chinese forzh_CN
     */
	private String language;

    /**
     * User profile，The last number represents the size of a square head.<br/>
     * （Yes0、46、64、96、132Numerical optional，0representative640*640Square<br/>
     * Shape head），The user has no head when the item is empty
     */
	private String headImgUrl;

    /**
     * User attention time，Time stamp。If the user has a number of concerns，Take the last time
     */
    @JSONField(name = "subscribe_time")
	private long subscribeTime;

    /**
     * Only after the user is bound to the public number of WeChat open platform account，This field will appear
     */
	private String unionid;

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "subscribe=" + subscribe +
                ", openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", language='" + language + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", subscribeTime=" + subscribeTime +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}