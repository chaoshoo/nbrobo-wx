package com.net.wx.vo;

import java.util.List;

/**
 * Receive message entity
 */
public class ReceiveMsg extends BaseMsg {

	/**
	 * pictures linking
	 */
	private String picUrl;

	/**
	 * Audio format(arm,wav,mp3)
	 */
	private String format;

	/**
	 * Speech recognition results,UTF-8Code
	 */
	private String recognition;

	/**
	 * Map zoom size
	 */
	private int scale;

    /**
     * Location information
     */
	private String label;

	/**
	 * Link address
	 */
	private String url;

	/**
	 * EventKEYvalue
	 */
	private String eventKey;

	/**
	 * Two-dimensional codeticketOr the service side of an open platform.ComponentVerifyTicket
	 */
	private String ticket;

	/**
	 * Geographical position latitude
	 */
	private double latitude;

	/**
	 * Location longitude 
	 */
	private double longitude;

	/**
	 * Location accuracy
	 */
	private double precision;

    /**
     * Scan type,Generallyqcode
     */
    private String scanType;

    /**
     * Scan results，Two-dimensional code corresponding to the string information
     */
    private String scanResult;

    /**
     * Send the number of pictures
     */
    private int count;

    /**
     * Send a list of pictures
     */
    private List<PicInfo> picList;

    /**
     * MomentsPOIName，May be empty
     */
    private String poiName;

	/**
	 * WeChat sends message status（Template message,Mass message）<pre/>
     * Mass structure，by“send success”or“send fail”or“err(num)”。butsend successtime，<pre/>
     * It is possible for users to reject the public information、System error and other reasons caused a small amount of users to receive a failure。err(num)Is the specific reason for the failure of the audit，<pre/>
     * Possible circumstances are as follows：<pre/>
     * err(10001), //Suspected of advertising <pre/>
     * err(20001), //Alleged political <pre/>
     * err(20004), //Suspected of social <pre/>
     * err(20002), //Suspected of pornography <pre/>
     * err(20006), //Suspected of illegal crime <pre/>
     * err(20008), //Suspected fraud <pre/>
     * err(20013), //Alleged copyright <pre/>
     * err(22000), //Suspected of mutual push(Mutual promotion) <pre/>
     * err(21000), //Suspected of other
	 */
	private String status;

    /**
     * group_idNumber of fans；perhapsopenid_listNumber of fans in
     */
    private int totalCnt;

    /**
     * filter（Filtering is a specific area、Sex filtration、User set reject filter，
     * User received over4Filtration of strip）Back，Number of fans ready to send，In principle，
     * FilterCount = SentCount + ErrorCount
     */
    private int filterCnt;

    /**
     * Send a number of successful fans
     */
    private int sentCnt;

    /**
     * The number of fans who failed to send
     */
    private int errorCnt;

    /**
     * serviceappid
     */
    private String appId;

    /**
     * none,On behalf of the message push to the service
     * unauthorized,On behalf of the public to cancel the authorization
     */
    private String infoType;

    /**
     * To cancel the authorization of the public number
     */
    private String unAuthAppid;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public String getScanResult() {
        return scanResult;
    }

    public void setScanResult(String scanResult) {
        this.scanResult = scanResult;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PicInfo> getPicList() {
        return picList;
    }

    public void setPicList(List<PicInfo> picList) {
        this.picList = picList;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getFilterCnt() {
        return filterCnt;
    }

    public void setFilterCnt(int filterCnt) {
        this.filterCnt = filterCnt;
    }

    public int getSentCnt() {
        return sentCnt;
    }

    public void setSentCnt(int sentCnt) {
        this.sentCnt = sentCnt;
    }

    public int getErrorCnt() {
        return errorCnt;
    }

    public void setErrorCnt(int errorCnt) {
        this.errorCnt = errorCnt;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getUnAuthAppid() {
        return unAuthAppid;
    }

    public void setUnAuthAppid(String unAuthAppid) {
        this.unAuthAppid = unAuthAppid;
    }

    @Override
    public String toString() {
        return "ReceiveMsg{" +
                "msgId='" + msgId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", msgType='" + msgType + '\'' +
                ", event='" + event + '\'' +
                ", content='" + content + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", thumbMediaId='" + thumbMediaId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", format='" + format + '\'' +
                ", recognition='" + recognition + '\'' +
                ", scale=" + scale +
                ", label='" + label + '\'' +
                ", url='" + url + '\'' +
                ", eventKey='" + eventKey + '\'' +
                ", ticket='" + ticket + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", precision=" + precision +
                ", status='" + status + '\'' +
                ", totalCnt='" + totalCnt + '\'' +
                ", filterCnt='" + filterCnt + '\'' +
                ", sentCnt='" + sentCnt + '\'' +
                ", errorCnt='" + errorCnt + '\'' +
                ", appId='" + appId + '\'' +
                ", infoType='" + infoType + '\'' +
                ", unAuthAppid='" + unAuthAppid + '\'' +
                "} ";
    }
}