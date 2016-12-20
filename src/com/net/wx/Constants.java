package com.net.wx;


/**
 * @ClassName: Constants  
 * @Description: constant 
 * @author wangshaofeng
 * @date 2015year7month20day morning11:40:28 
 */
public  class Constants {
	
	/**
	 * @Fields HOST_ADDRESS : Host address
	 */
	public static final String HOST_ADDRESS = "http://wx.cunjk.com/";
  	
  	//微信登录授权APPID
  	public static final String OAUTH_APPID = "wx595d88175949ba23";
  	//微信登录授权AppSecret
  	public static final String OAUTH_APPSECRET = "ef925e66aa2888bafedd644c200cd531";
  	
  	
  	/**
  	 * @Fields MEDIA_SAVE : Number of days for multimedia file storage 
  	 */
  	public static final int MEDIA_SAVE = 3;
  	
  	/**
  	 * @Fields MEDIA_UN_SEND : Multi-MediaIDNot sent
  	 */
  	public static final String MEDIA_UN_SEND = "0";
  	
  	/**
  	 * @Fields MEDIA_SEND_SUCCESS : Multimedia delivery success
  	 */
  	public static final String MEDIA_SEND_SUCCESS = "1";
  	
  	/**
  	 * @Fields MEDIA_PAST : Multimedia expired
  	 */
  	public static final String MEDIA_PAST = "2";
  	
  	/**
  	 * @Fields SEND_STATUS_SUCCESS : State of mass message，Success
  	 */
  	public static final String SEND_STATUS_SUCCESS = "0";
  	
  	/**
  	 * @Fields SEND_STATUS_FAILURE : State of mass message，fail
  	 */
  	public static final String SEND_STATUS_FAILURE = "1";
  	
  	/**
  	 * @Fields SEND_MPNEWS_TYPE : Type of mass text
  	 */ 
  	public static final String SEND_MPNEWS_TYPE = "1";
  	
  	/**
  	 * @Fields SEND_TEXT_TYPE : Type of bulk text
  	 */ 
  	public static final String SEND_TEXT_TYPE = "2";
  	
  	/**
  	 * @Fields SEND_IMAGE_TYPE : Mass image type
  	 */ 
  	public static final String SEND_IMAGE_TYPE = "3";
  	
  	/**
  	 * @Fields PERPETUAL_SAVE_MEDIA : Permanent preservation of multimedia material
  	 */
  	public static final String PERPETUAL_SAVE_MEDIA = "1";
  	
  	/**
  	 * @Fields TEMP_SAVE_MEDIA : Temporary storage of multimedia material
  	 */
  	public static final String TEMP_SAVE_MEDIA = "0";
  	
  	/**
	 * redisList of dictionarieskeyvalue,DicSingleton
	 */
	public static final String KEY_DIC ="dic";
	/**
	 * redisMedium storagedepartmentThekeyvalue,DepartmentSingleton
	 */
	public static final String KEY_DPLIST ="dpList";
	/**
	 * redisMedium storagedepartmentThekeyvalue,DepartmentSingleton
	 */
	public static final String KEY_DPMAP ="dpMap";
	/**
	 * redisMedium storagedepartmentThekeyvalue,DepartmentSingleton
	 */
	public static final String KEY_SYSLIST ="sysList";
	/**
	 * redisMedium storaget_device_attrThekeyvalue,DeviceAttrSingleton
	 */
	public static final String KEY_ATTR ="listAttr";
	/**
	 * redisIn the examination ruleskeyvalue,AssessRuleSingleton
	 */
	public static final String KEY_RULEMAP ="rulemap";
  	
}