package com.net.wx;


/**
 * @ClassName: Constants  
 * @Description: 常量 
 * @author wangshaofeng
 * @date 2015年7月20日 上午11:40:28 
 */
public  class Constants {
	
	/**
	 * @Fields HOST_ADDRESS : 主机地址
	 */
	public static final String HOST_ADDRESS = "http://wx.cunjk.com/";
  	
  	//微信登录授权APPID
  	public static final String OAUTH_APPID = "wxfd65d5401333bc07";
  	//微信登录授权AppSecret
  	public static final String OAUTH_APPSECRET = "9ce209909b58457286c1c1fe8cf7f22b";
  	
  	
  	/**
  	 * @Fields MEDIA_SAVE : 多媒体文件保存的天数 
  	 */
  	public static final int MEDIA_SAVE = 3;
  	
  	/**
  	 * @Fields MEDIA_UN_SEND : 多媒体ID未发送
  	 */
  	public static final String MEDIA_UN_SEND = "0";
  	
  	/**
  	 * @Fields MEDIA_SEND_SUCCESS : 多媒体发送成功
  	 */
  	public static final String MEDIA_SEND_SUCCESS = "1";
  	
  	/**
  	 * @Fields MEDIA_PAST : 多媒体过期
  	 */
  	public static final String MEDIA_PAST = "2";
  	
  	/**
  	 * @Fields SEND_STATUS_SUCCESS : 群发消息的状态，成功
  	 */
  	public static final String SEND_STATUS_SUCCESS = "0";
  	
  	/**
  	 * @Fields SEND_STATUS_FAILURE : 群发消息的状态，失败
  	 */
  	public static final String SEND_STATUS_FAILURE = "1";
  	
  	/**
  	 * @Fields SEND_MPNEWS_TYPE : 群发图文的类型
  	 */ 
  	public static final String SEND_MPNEWS_TYPE = "1";
  	
  	/**
  	 * @Fields SEND_TEXT_TYPE : 群发文本的类型
  	 */ 
  	public static final String SEND_TEXT_TYPE = "2";
  	
  	/**
  	 * @Fields SEND_IMAGE_TYPE : 群发图片的类型
  	 */ 
  	public static final String SEND_IMAGE_TYPE = "3";
  	
  	/**
  	 * @Fields PERPETUAL_SAVE_MEDIA : 永久保存多媒体素材
  	 */
  	public static final String PERPETUAL_SAVE_MEDIA = "1";
  	
  	/**
  	 * @Fields TEMP_SAVE_MEDIA : 临时保存多媒体素材
  	 */
  	public static final String TEMP_SAVE_MEDIA = "0";
  	
  	/**
	 * redis中存放字典列表的key值,DicSingleton
	 */
	public static final String KEY_DIC ="dic";
	/**
	 * redis中存放department的key值,DepartmentSingleton
	 */
	public static final String KEY_DPLIST ="dpList";
	/**
	 * redis中存放department的key值,DepartmentSingleton
	 */
	public static final String KEY_DPMAP ="dpMap";
	/**
	 * redis中存放department的key值,DepartmentSingleton
	 */
	public static final String KEY_SYSLIST ="sysList";
	/**
	 * redis中存放t_device_attr的key值,DeviceAttrSingleton
	 */
	public static final String KEY_ATTR ="listAttr";
	/**
	 * redis中存放考试规则的key值,AssessRuleSingleton
	 */
	public static final String KEY_RULEMAP ="rulemap";
  	
}
