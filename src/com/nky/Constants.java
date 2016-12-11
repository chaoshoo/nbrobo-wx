package com.nky;

import com.net.singleton.DicSingleton;
import com.net.util.DateUtil;


/**
 * @ClassName: Constants  
 * @Description: 常量 
 * @author smiven
 * @date 2016年8月15日 上午11:40:28 
 */
public  class Constants {
	
	public static String RootPath = "";
		

	/**
	 * 用户-session-key
	 */
	public static final String CUSTOMER_SESSION_KEY = "customer_user";
	
	public static final String WX_USER_HEAD_IMG = "wx_user_head_img";
	
	/**
	 * cookie失效时间   1天
	 */
	public static final int COOKIE_FAILURE_TIME =60*60*24;
		
	/** 
	 * 初始密码
	 */
	public static final String PASSWORD = "66668888";
	
	/**
	 * 字典转义
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
	 * 得到标准时间
	 * @param time
	 * @return
	 * huilet 2013-5-3
	 * @author yuanc
	 */
	public static String getStandardTime(String time){
		return DateUtil.dateForString(DateUtil.strForDate(time, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss") ;
	}
	
	/**
	 * @Fields HOST_ADDRESS : 主机地址
	 */
	public static final String HOST_ADDRESS = "http://wx.nky.com/";
	
    /**
     * @Fields sms_code : 发送短信验证码的时间为5分钟
     */
    public static final int SMS_CODE = 5;
    
    /**
     * @Fields SMS_IP : 限制对单个IP发送短信的次数为10
     */
    public static final int SMS_IP = 10;
    
    /**
     * @Fields SMS_PHONE : 限制对同一个手机号发送短信的次数为5
     */
    public static final int SMS_PHONE = 5;
    
    /*
     * 微信
     */
         //原始ID
  	public static final String MPID = "gh_026be9e5d2a5";
  		//公众号名称
  	public static final String NICKNAME = "gh_026be9e5d2a5";
  		//appID
  	public static final String APPID = "wxfd65d5401333bc07"; 
  		//应用密钥
 	public static final String APPSECRET = "9ce209909b58457286c1c1fe8cf7f22b"; 	
 	//微信登录授权APPID
  	public static final String OAUTH_APPID = "wxfd65d5401333bc07";
  	//微信登录授权AppSecret
  	public static final String OAUTH_APPSECRET = "9ce209909b58457286c1c1fe8cf7f22b";
  	
	/**
	 * 公众号类型
	 * D:订阅号
	 * E:企业号
	 * S:服务号
	 * 
	 */
  	public static final String MPTYPE = "S";
  		//是否认证
  	public static final Boolean PASS = false;
  	//消息加密密钥
  	public static final String AESKEY = "tv4Xz6QKNoPbHS8sa5yiDfpW9qQmFl8K9kX1p1XaUh8";
  	//token
  	public static final String TOKEN = "ningky123";
   	
  	
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
  	
  	
  	
  	
  	public static final String YUEYUAN_APPKEY = "19d1819a7b9865d28470a7673bd43f95";
  	
  	public static final String YUEYUAN_RETURN_URL = HOST_ADDRESS+"yueyuan/tel/return_url.html";
  	
  	public static final String WEIXIN_MD5KEY="nky_0654321" ;//  如果要修改  对应api也要修改
  	
	
}
