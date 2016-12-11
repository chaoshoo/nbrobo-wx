package com.net.wx.common;

/**
 * WeChat allAPIaddress
 */
public interface WxApiUrl {

    /**
     * WeChatAPIEntrance
     */
    public static final String WX_API = "https://api.weixin.qq.com/cgi-bin";
    /**
     * WeChat open platform portal
     */
    public static final String WX_OPEN_API = "https://open.weixin.qq.com";
    /**
     * WeChat multimedia fileAPIEntrance
     */
    public static final String MEDIA_API = "http://file.api.weixin.qq.com/cgi-bin/media";
    /**
     * tokenAPIEntrance
     */
    public static final String ACCESS_TOKEN_API = WX_API + "/token?grant_type=client_credential&appid=%1$s&secret=%2$s";
    /**
     * Get WeChat serverIP
     */
    public static final String IP_LIST_API = WX_API + "/getcallbackip?access_token=%1$s";
    /**
     * Custom menuAPIEntrance[create, get, delete]
     */
    public static final String CUSTOM_MENU_API = WX_API + "/menu/%1$s?access_token=%2$s";
    /**
     * Grouping managementAPIEntrance[create, get, getid, update]
     */
    public static final String GROUPS_API = WX_API + "/groups/%1$s?access_token=%2$s";
    /**
     * User groupingAPIEntrance
     */
    public static final String GROUPS_USER_API = WX_API + "/groups/members/update?access_token=%1$s";
    /**
     * WeChat user informationAPIEntrance
     */
    public static final String USER_INFO_API = WX_API + "/user/info?access_token=%1$s&openid=%2$s&lang=%3$s";
    /**
     * Public concernAPIEntrance
     */
    public static final String FOLLOWS_USER_API = WX_API + "/user/get?access_token=%1$s&next_openid=%2$s";
    /**
     * Download multimedia filesAPIEntrance
     */
    public static final String MEDIA_DL_API = MEDIA_API + "/get?access_token=%1$s&media_id=%2$s";
    /**
     * Upload multimedia filesAPIEntrance
     */
    public static final String MEDIA_UP_API = MEDIA_API + "/upload?type=%1$s&access_token=%2$s";
    /**
     * Send customer service information portal
     */
    public static final String CUSTOM_MESSAGE_API = WX_API + "/message/custom/send?access_token=%1$s";
    /**
     * Web authorization request address
     */
    public static final String WEB_OAUTH2 = WX_OPEN_API + "/connect/oauth2/authorize?appid=%1$s&redirect_uri=%2$s&response_type=code&scope=%3$s&state=%4$s#wechat_redirect";
    /**
     * Inspection authorization certificate（access_token）Whether effective
     */
    public static final String CHECK_OAUTH_TOKEN = "https://api.weixin.qq.com/sns/auth?access_token=%1$s&openid=%2$s";
    /**
     * Web access authorizationTOKEN
     */
    public static final String OAUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%1$s&secret=%2$s&code=%3$s&grant_type=authorization_code";
    /**
     * Web access to refreshTOKEN
     */
    public static final String OAUTH_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid==%1$s&grant_type=refresh_token&refresh_token==%2$s";
    
    /**
     * Web license to obtain user information
     */
    public static final String OAUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%1$s&openid=%2$s";
    /**
     * Send template message address
     */
    public static final String TEMPLATE_MESSAGE_API = WX_API + "/message/template/send?access_token=%1$s";
    /**
     * Upload graphic material address
     */
    public static final String NEWS_UPLOAD_API = WX_API + "/media/uploadnews?access_token=%1$s";
    /**
     * Upload permanent graphic material address
     */
    public static final String NEWS_UPLOAD_PERPETUAL_API = WX_API + "/material/add_news?access_token=%1$s";
    /**
     * Update permanent graphic material address
     */
    public static final String NEWS_UPDATE_PERPETUAL_API = WX_API + "/material/update_news?access_token=%1$s";
    /**
     * Delete permanent material address
     */
    public static final String DEL_MEDIA_PERPETUAL_API = WX_API + "/material/del_material?access_token=%1$s";
    /**
     * Multimedia permanent material address
     */
    public static final String MEDIA_UPLOAD_PERPETUAL_API = WX_API + "/material/add_material?type=%1$s&access_token=%2$s";
    /**
     * Packet group message[sendall,send,delete]Entrance
     */
    public static final String GROUP_NEWS_MESSAGE_API = WX_API + "/message/mass/%1$s?access_token=%2$s";
    /**
     * Mass message upload video address
     */
    public static final String MEDIA_UPVIDEO_API = MEDIA_API + "/uploadvideo?access_token=%1$s";
    /**
     * Service componentAPIEntrance
     */
    public static final String COMPONENT_API = WX_API + "/component/%1$s?component_access_token=%2$s";
    /**
     * Access service componentstokenaddress
     */
    public static final String COMPONENT_TOKEN_API = WX_API + "/component/api_component_token";
    /**
     * ObtainJSTICKETaddress
     */
    public static final String JSAPI_TICKET_URL = WX_API + "/ticket/getticket?type=jsapi&access_token=";
}