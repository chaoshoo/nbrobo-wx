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
 * 微信公众平台开发者API接口设计
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
	 * 创建ACCESS_TOKEN
     *
     * @return 高级API需要的access_token
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	String getAccessToken() throws WxRespException;

    /**
     * 刷新过期的ACCESS_TOKE
     *
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
	void refreshAccessToken() throws WxRespException;

	/**
	 * 获取微信服务器IP列表
     *
	 * @return IP地址集合
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	List<String> getServerIp() throws WxRespException;

    /**
     * 上传多媒体文件
     * @param mediaType 媒体文件类型[image, voice, video]
     * @param file      文件
     * @param saveType  多媒体保存类型。1为永久微信服务器只保存5000个，0为临时微信服务器只保存3天
     * @return  储存媒体ID
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
	JSONObject upMedia(String mediaType, File file,String saveType) throws WxRespException;
    
    /**
     * 下载多媒体文件
     *
     * @param mediaId   多媒体ID
     * @param file      本地存储位置[默认使用媒体ID作文件名]
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
    void dlMedia(String mediaId, File file) throws WxRespException;

    /**
     * 获取微信菜单
     *
     * @return  微信菜单集合
     */
	List<Menu> getMenu() throws WxRespException;

    /**
     * 创建微信菜单
     *
     * @param menus 微信菜单
     * @return  true或false
     */
	boolean createMenu(Menu... menus) throws WxRespException;

	/**
	 * 删除自定义菜单
     *
	 * @return true或false
	 */
	boolean deleteMenu() throws WxRespException;

	/**
	 * 创建分组
     *
	 * @param name 分组名称
	 * @return 分组ID
	 */
	int creatGroup(String name) throws WxRespException;

	/**
	 * 获取所有分组
     *
	 * @return  Groups集合
	 */
	List<Group> getGroups() throws WxRespException;

	/**
	 * 重命名分组
     *
	 * @param id	分组ID
	 * @param name	新的分组名称
	 * @return true或false
	 */
	boolean renGroup(int id, String name) throws WxRespException;

	/**
	 * 获取用户分组ID
     *
	 * @param openId 	用户ID
	 * @return 分组ID
	 */
	int getGroupId(String openId) throws WxRespException;

	/**
	 * 移动用户分组
     *
	 * @param openId	用户ID
	 * @param groupId	新分组ID
	 * @return true或false
	 */
	boolean move2Group(String openId, int groupId) throws WxRespException;

	/**
	 * 获取关注用户列表
     *
	 * @param	nextOpenId	 	拉取列表的后一个用户的OPENID
	 * @return 关注用户ID列表
	 */
	FollowList getFollowerList(String nextOpenId) throws WxRespException;

	/**
	 * 获取关注者的信息
     *
	 * @param	openId	用户ID
	 * @param 	lang	使用语言
	 * @return 关注者的基本信息
	 */
	Follower getFollower(String openId, String lang) throws WxRespException;

    /**
     * 发送客服消息
     *
     * @param msg       消息
     * @return  false或true
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
	boolean sendCustomerMsg(OutPutMsg msg) throws WxRespException;

    /**
     * 发送模板消息
     *
     * @param openId        接收者ID
     * @param templateId    模板ID
     * @param topColor      顶部颜色
     * @param url           跳转地址
     * @param templates     模样数据
     * @return false或true
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
	boolean sendTemplateMsg(String openId, String templateId, String topColor, String url, Template... templates) throws WxRespException;

	/**
	 * 上传图文消息素材
	 *
	 * @param articles2s 图文消息实体数组
	 * @param saveType  图片的保存类型.1为永久,0为临时
	 * @return [0 消息类型, 1 多媒体ID, 2 创建时间],直接返回多媒体ID
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	String upNews(String saveType,Article2... articles2s) throws WxRespException;
	
	/**
	 * 更新永久图文素材
	 *
	 * @param articles2s 图文消息实体数组
	 * @param updateMediaId 需要修改的图文素材ID
	 * @param index 修改多图文素材中的索引.单个图文素材为0
	 * @return [errcode, errmsg].正确时errcode的值应为0
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	int updateNews(String updateMediaId,int index,Article2 articles2s) throws WxRespException;
	
	/**
	 * 删除永久素材
	 *
	 * @param mediaId 删除的素材Id
	 * @return [errcode, errmsg].正确时errcode的值应为0
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	int delMedia(String mediaId) throws WxRespException;

	/**
	 * 上传群发消息中的视频文件
	 *
	 * @param mediaId	多媒体ID[需通过基础支持中的上传下载多媒体文件来得到]
	 * @param title		标题
	 * @param description	描述
	 * @return [0 消息类型, 1 多媒体ID, 2 创建时间]
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	String[] upVideo(String mediaId, String title, String description) throws WxRespException;

	/**
	 * 群发消息[分组或指定用户]
     *
	 * @param msg	消息输出实体[groupId或toUsers, content, msgType, mediaId]
	 * @return	消息ID
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	String sendAll(OutPutMsg msg) throws WxRespException;

	/**
	 * 删除群发消息<pre/>
	 *
	 * 只有已经发送成功的消息才能删除删除消息只是将消息的图文详情页失效，<pre/>
	 * 已经收到的用户，还是能在其本地看到消息卡片。 另外，删除群发消息只能<pre/>
	 * 删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
	 * @param msgId	发送消息的ID
	 * @return	false或true
	 * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
	 */
	boolean delSendAll(String msgId) throws WxRespException;

    /**
     * 获取JSAPI签名
     *
     * @param url 使用jsapi页面地址
     * @return  集合[url, ticket, nonce, timestamp, sign]
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
    Map<String, Object> getJsAPISign(String url) throws WxRespException;

    /**
     * 刷新过期的JSAPI TICKET
     *
     * @throws org.elkan1788.osc.weixin.mp.exception.WxRespException
     */
    void refreshJsAPITicket() throws WxRespException;
}
