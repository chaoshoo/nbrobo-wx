package com.net.wx.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Output message entity
 */
public class OutPutMsg extends BaseMsg {

	/**
	 * Music links
	 */
	private String musicUrl;

	/**
	 * High quality music links
	 */
	private String hQMusicUrl;

    /**
     * Multi graphic message
     */
	private List<Article> articles = new ArrayList<Article>();

    /**
     * Grouping in a group messageID
     */
    private String groupId;

    /**
     * User for mass messageID,Most support1000individual
     */
    private List<String> toUsers = new ArrayList<String>();

    /**
     * Custom reply content<p/>
     * WeChat too much,In an open platform,LOCATIONEvents actually can reply message<p/>
     * Therefore, to consider the subsequent expansion,Add this field
     */
    private String customReply;

    /**
     * Constructor with basic parameters
     * @param fromUserName WeChat useropenId
     * @param toUserName   WeChat public number originalID
     * @param msgType   Message type
     */
    public OutPutMsg(String fromUserName, String toUserName, String msgType) {
        this.fromUserName = toUserName;
        this.toUserName = fromUserName;
        this.msgType =  msgType;
        this.createTime = System.currentTimeMillis() / 1000;
    }

    public OutPutMsg(ReceiveMsg rm) {
        this.fromUserName = rm.getToUserName();
        this.toUserName = rm.getFromUserName();
        this.createTime = System.currentTimeMillis() / 1000;
    }

    public OutPutMsg() {
        this.createTime = System.currentTimeMillis() / 1000;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public List<Article> getArticles() {

        return articles;
    }

    public void setArticles(List<Article> articles) {
        if (null != articles
                && articles.size() > 10) {
            this.articles = articles.subList(0, 10);
        } else {
            this.articles = articles;
        }
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getToUsers() {
        return toUsers;
    }

    public void setToUsers(List<String> toUsers) {
        this.toUsers = toUsers;
    }

    public String getCustomReply() {
        return customReply;
    }

    public void setCustomReply(String customReply) {
        this.customReply = customReply;
    }

    @Override
    public String toString() {
        return "OutPutMsg{" +
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
                ", musicUrl='" + musicUrl + '\'' +
                ", hQMusicUrl='" + hQMusicUrl + '\'' +
                ", groupId='" + groupId + '\'' +
                ", articles=" + ((null==articles) ? 0 : articles.size()) +
                ", toUsers=" + ((null==toUsers) ? 0 : toUsers.size()) +
                ", customReply=" + customReply +
                "} " + super.toString();
    }
}