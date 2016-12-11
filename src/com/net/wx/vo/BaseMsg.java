package com.net.wx.vo;

/**
 * WeChat message entity base class
 */
public abstract class BaseMsg {

	/**
	 * Message onlyID(64short)
	 */
	protected long msgId;

	/**
	 * Message creation time （integer） 
	 */
	protected long createTime;

	/**
	 * Message type(text, image, video, voice, location, link,event)
	 */
	protected String msgType;

	/**
	 * Message event:
	 * subscribe:Subscribe
	 * unsubscribe:unsubscribe
	 * SCAN:Post scene scan
	 * LOCATION:Active upload location
	 * VIEW,CLICK:Menu click event
	 * TEMPLATESENDJOBFINISH:Template message push
	 */
	protected String event;

	/**
	 * Receive message userID
	 */
	protected String toUserName;

	/**
	 * Message from userID
	 */
	protected String fromUserName;

	/**
	 * Text message content
	 */
	protected String content;

	/**
	 * Multimedia messagesID(WeChat server effective time for3day)
	 */
	protected String mediaId;

	/**
	 * link,Article message title
	 */
	protected String title;

	/**
	 * Detailed description
	 */
	protected String description;

	/**
	 * Video message thumbnail mediaid
	 */
	protected String thumbMediaId;
    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}