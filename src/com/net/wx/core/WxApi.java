package com.net.wx.core;


import java.io.File;
import java.util.List;
import java.util.Map;

import com.net.wx.exception.WxRespException;
import com.net.wx.vo.Article2;
import com.net.wx.vo.FollowList;
import com.net.wx.vo.Follower;
import com.net.wx.vo.Group;
import com.net.wx.vo.Menu;
import com.net.wx.vo.OutPutMsg;
import com.net.wx.vo.Template;

import com.alibaba.fastjson.JSONObject;

/**
 * WeChat Public Platform DeveloperAPIinterface design
 */
public interface WxApi {

    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String MUSIC = "music";
    public static final String NEWS = "news";
    public static final String MPNEWS = "mpnews";
    public static final String MPVIDEO = "mpvideo";

	/**
	 * EstablishACCESS_TOKEN
     *
     * @return seniorAPINeededaccess_token
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	String getAccessToken() throws WxRespException;

    /**
     * Refresh expiredACCESS_TOKE
     *
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
	void refreshAccessToken() throws WxRespException;

	/**
	 * Get WeChat serverIPlist
     *
	 * @return IPAddress set
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	List<String> getServerIp() throws WxRespException;

    /**
     * Upload multimedia files
     * @param mediaType Media file type[image, voice, video]
     * @param file      file
     * @param saveType  Multimedia storage type。1Save only for permanent WeChat server5000individual，0Save only for temporary WeChat servers3day
     * @return  Storage mediaID
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
	JSONObject upMedia(String mediaType, File file,String saveType) throws WxRespException;
    
    /**
     * Download multimedia files
     *
     * @param mediaId   Multi-MediaID
     * @param file      Local storage location[Default use mediaIDComposition name]
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
    void dlMedia(String mediaId, File file) throws WxRespException;

    /**
     * Get WeChat menu
     *
     * @return  WeChat menu set
     */
	List<Menu> getMenu() throws WxRespException;

    /**
     * Create WeChat menu
     *
     * @param menus WeChat menu
     * @return  trueorfalse
     */
	boolean createMenu(Menu... menus) throws WxRespException;

	/**
	 * Delete custom menu
     *
	 * @return trueorfalse
	 */
	boolean deleteMenu() throws WxRespException;

	/**
	 * Create group
     *
	 * @param name Group name
	 * @return GroupingID
	 */
	int creatGroup(String name) throws WxRespException;

	/**
	 * Get all groups
     *
	 * @return  Groupsaggregate
	 */
	List<Group> getGroups() throws WxRespException;

	/**
	 * Rename group
     *
	 * @param id	GroupingID
	 * @param name	New group name
	 * @return trueorfalse
	 */
	boolean renGroup(int id, String name) throws WxRespException;

	/**
	 * Get user groupsID
     *
	 * @param openId 	userID
	 * @return GroupingID
	 */
	int getGroupId(String openId) throws WxRespException;

	/**
	 * Mobile user group
     *
	 * @param openId	userID
	 * @param groupId	New groupingID
	 * @return trueorfalse
	 */
	boolean move2Group(String openId, int groupId) throws WxRespException;

	/**
	 * Get attention to the user list
     *
	 * @param	nextOpenId	 	After pulling the list of a user'sOPENID
	 * @return Focus on userIDlist
	 */
	FollowList getFollowerList(String nextOpenId) throws WxRespException;

	/**
	 * Get the attention of the information
     *
	 * @param	openId	userID
	 * @param 	lang	Use language
	 * @return The basic information of the concerned
	 */
	Follower getFollower(String openId, String lang) throws WxRespException;

    /**
     * Send customer service messages
     *
     * @param msg       Message
     * @return  falseortrue
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
	boolean sendCustomerMsg(OutPutMsg msg) throws WxRespException;

    /**
     * Send template messages
     *
     * @param openId        RecipientID
     * @param templateId    TemplateID
     * @param topColor      Top color
     * @param url           Jump address
     * @param templates     Pattern data
     * @return falseortrue
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
	boolean sendTemplateMsg(String openId, String templateId, String topColor, String url, Template... templates) throws WxRespException;

	/**
	 * Upload graphics and text message material
	 *
	 * @param articles2s Graphic message entity array
	 * @param saveType  Save type of image.1As permanent,0As temporary
	 * @return [0 Message type, 1 Multi-MediaID, 2 Created time],Direct return to multimediaID
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	String upNews(String saveType,Article2... articles2s) throws WxRespException;
	
	/**
	 * Update permanent graphic material
	 *
	 * @param articles2s Graphic message entity array
	 * @param updateMediaId Need to modify the graphic materialID
	 * @param index Modify the index of multi graphic material.Single graphic material for0
	 * @return [errcode, errmsg].Correct timeerrcodeThe value should be0
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	int updateNews(String updateMediaId,int index,Article2 articles2s) throws WxRespException;
	
	/**
	 * Delete permanent material
	 *
	 * @param mediaId Deleted materialId
	 * @return [errcode, errmsg].Correct timeerrcodeThe value should be0
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	int delMedia(String mediaId) throws WxRespException;

	/**
	 * Upload video files in bulk messages
	 *
	 * @param mediaId	Multi-MediaID[Need to upload and download multimedia files through the basic support to get]
	 * @param title		Title
	 * @param description	description
	 * @return [0 Message type, 1 Multi-MediaID, 2 Created time]
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	String[] upVideo(String mediaId, String title, String description) throws WxRespException;

	/**
	 * Mass message[Group or user specified]
     *
	 * @param msg	Message output entity[groupIdortoUsers, content, msgType, mediaId]
	 * @return	MessageID
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	String sendAll(OutPutMsg msg) throws WxRespException;

	/**
	 * Delete mass message<pre/>
	 *
	 * Only the message has been sent to delete the message to delete the message is only the message details page failure，<pre/>
	 * Already received user，Or be able to see message cards in their local。 in addition，Delete group messages only<pre/>
	 * Delete text message and video message，Other types of messages are sent，Cannot delete。
	 * @param msgId	Send messageID
	 * @return	falseortrue
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	boolean delSendAll(String msgId) throws WxRespException;

    /**
     * ObtainJSAPIautograph
     *
     * @param url UsejsapiPage address
     * @return  aggregate[url, ticket, nonce, timestamp, sign]
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
    Map<String, Object> getJsAPISign(String url) throws WxRespException;

    /**
     * Refresh expiredJSAPI TICKET
     *
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
    void refreshJsAPITicket() throws WxRespException;
}