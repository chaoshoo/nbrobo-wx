package com.nky;

import com.net.singleton.DicSingleton;
import com.net.util.DateUtil;


/**
 * @ClassName: Constants  
 * @Description: constant 
 * @author smiven
 * @date 2016year8month15day morning11:40:28 
 */
public  class Constants {
	
	public static String RootPath = "";
		

	/**
	 * user-session-key
	 */
	public static final String CUSTOMER_SESSION_KEY = "customer_user";
	
	public static final String WX_USER_HEAD_IMG = "wx_user_head_img";
	
	/**
	 * cookieFailure time   1day
	 */
	public static final int COOKIE_FAILURE_TIME =60*60*24;
		
	/** 
	 * Initial password
	 */
	public static final String PASSWORD = "66668888";
	
	/**
	 * Dictionary escape
	 * @param dicType
	 * @param keyValue
	 * @return
	 * huilet 2013-5-3
	 * @author yuanc
	 */
	public static String getValueBykeyDic(String dicType,String keyValue){
		return DicSingleton.getInstance().getValueBykeyDic(dicType,keyValue);
	}
	/**
	 * Get standard time
	 * @param time
	 * @return
	 * huilet 2013-5-3
	 * @author yuanc
	 */
	public static String getStandardTime(String time){
		return DateUtil.dateForString(DateUtil.strForDate(time, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss") ;
	}
	
	/**
	 * @Fields HOST_ADDRESS : Host address
	 */
	public static final String HOST_ADDRESS = "http://wx.nky.com/";
	
    /**
     * @Fields sms_code : Send SMS verification code time5Minute
     */
    public static final int SMS_CODE = 5;
    
    /**
     * @Fields SMS_IP : Limit to a singleIPThe number of times to send text messages10
     */
    public static final int SMS_IP = 10;
    
    /**
     * @Fields SMS_PHONE : Restrictions on the number of messages sent to the same phone number as5
     */
    public static final int SMS_PHONE = 5;
    
    /*
     * WeChat
     */
         //原始ID
  	public static final String MPID = "gh_026be9e5d2a5";
  		//公众号名称
  	public static final String NICKNAME = "gh_026be9e5d2a5";
  		//appID
  	public static final String APPID = "wx595d88175949ba23"; 
  		//应用密钥
 	public static final String APPSECRET = "ef925e66aa2888bafedd644c200cd531"; 	
 	//微信登录授权APPID
  	public static final String OAUTH_APPID = "wx595d88175949ba23";
  	//微信登录授权AppSecret
  	public static final String OAUTH_APPSECRET = "ef925e66aa2888bafedd644c200cd531";
  	
	/**
	 * Public number type
	 * D:Subscription number
	 * E:Enterprise number
	 * S:Service number
	 * 
	 */
  	public static final String MPTYPE = "S";
  		//是否认证
  	public static final Boolean PASS = false;
  	//消息加密密钥
  	public static final String AESKEY = "IScLdey4qoiyhOPz9x6mlK3RVCrYwRyZj49rMjuc2ko";
  	//token
  	public static final String TOKEN = "globaldoctor123";
   	
  	
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
  	
  	
  	
  	
  	public static final String YUEYUAN_APPKEY = "19d1819a7b9865d28470a7673bd43f95";
  	
  	public static final String YUEYUAN_RETURN_URL = HOST_ADDRESS+"yueyuan/tel/return_url.html";
  	
  	public static final String WEIXIN_MD5KEY="nky_0654321" ;//  If you want to modify  CorrespondingapiAlso want to modify
  	
	
}