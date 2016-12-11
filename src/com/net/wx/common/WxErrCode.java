package com.net.wx.common;

/**
 * WeChat communications error code
 */
public class WxErrCode {

    public static final int SYS_BUSY = -1;
    public static final int OK = 0;
    public static final int ERROR_SECRET = 40001;
    public static final int INVALID_CERTIFICATE = 40002;
    public static final int INVALID_OPENID = 40003;
    public static final int INVALID_MEDIATYPE = 40004;
    public static final int INVALID_FILETYPE = 40005;
    public static final int INVALID_FILESIZE = 40006;
    public static final int INVALID_MEDIAID = 40007;
    public static final int INVALID_MESGTYPE = 40008;
    public static final int INVALID_IMAGESIZE = 40009;
    public static final int INVALID_VOICESIZE = 40010;
    public static final int INVALID_VIDEOSIZE = 40011;
    public static final int INVALID_THUMBSIZE = 40012;
    public static final int INVALID_APPID = 40013;
    public static final int INVALID_ACCESSTOKEN = 40014;
    public static final int INVALID_MENUTYPE = 40015;
    public static final int INVALID_BUTTONSIZE = 40016;
    public static final int INVALID_BUTTONSIZE2 = 40017;
    public static final int INVALID_BUTTONNAMESIZE = 40018;
    public static final int INVALID_BUTTONKEYSIZE = 40019;
    public static final int INVALID_BUTTONURLSIZE = 40020;
    public static final int INVALID_BUTTONVERSION = 40021;
    public static final int INVALID_MENULEVEL = 40022;
    public static final int INVALID_SUBBUTTONSIZE = 40023;
    public static final int INVALID_SUBMENUTYPE = 40024;
    public static final int INVALID_SUBBUTTONNAMESIZE = 40025;
    public static final int INVALID_SUBBUTTONKEYSIZE = 40026;
    public static final int INVALID_SUBBUTTONURLSIZE = 40027;
    public static final int INVALID_MENUUSER = 40028;
    public static final int INVALID_OAUTHCODE = 40029;
    public static final int INVALID_REFRESHCODE = 40030;
    public static final int INVALID_OPENIDLIST = 40031;
    public static final int INVALID_OPENIDLENGTH = 40032;
    public static final int INVALID_REQUESTSTRING = 40033;
    public static final int INVALID_PARAMETERS = 40035;
    public static final int INVALID_REQUESTSTYLE = 40038;
    public static final int INVALID_URLLENGTH = 40039;
    public static final int INVALID_GROUPID = 40050;
    public static final int INVALID_GROUPNAME = 40051;
    public static final int INVALID_MSGID = 40059;
    public static final int LOST_AESSTOKEN = 41001;
    public static final int LOST_APPID = 41002;
    public static final int LOST_REFRESHTOKEN = 41003;
    public static final int LOST_APPSECRET = 41004;
    public static final int LOST_MEDIAFILE_DATA = 41005;
    public static final int LOST_MEDIAID = 41006;
    public static final int LOST_SUBMENUS = 41007;
    public static final int LOST_OAUTHCODE = 41008;
    public static final int LOST_OPENID = 41009;
    public static final int ACCESSTOKEN_OVERTIME = 42001;
    public static final int REFRESHTOKEN_OVERTIME = 42002;
    public static final int OAUTHCODE_OVERTIME = 42003;
    public static final int GET_REUEST = 43001;
    public static final int POST_REUEST = 43002;
    public static final int HTTPS_REUEST = 43003;
    public static final int FOLLOW_REUEST = 43004;
    public static final int FRIEND_REUEST = 43005;
    public static final int NULL_MEDIAFILE = 44001;
    public static final int NULL_POSTDATA = 44002;
    public static final int NULL_NEWSDATA = 44003;
    public static final int NULL_TEXTDATA = 44004;
    public static final int MEDIASIZE_LIMIT_OVER = 45001;
    public static final int CONTENT_LIMIT_OVER = 45002;
    public static final int TITLE_LIMIT_OVER = 45003;
    public static final int DIGEST_LIMIT_OVER = 45004;
    public static final int LINKURL_LIMIT_OVER = 45005;
    public static final int PICURL_LIMIT_OVER = 45006;
    public static final int VIOCE_PLAY_LIMIT_OVER = 45007;
    public static final int NEWS_LIMIT_OVER = 45008;
    public static final int INTERFACE_LIMIT_OVER = 45009;
    public static final int MENUSIZE_LIMIT_OVER = 45010;
    public static final int REPLYTIME_LIMIT_OVER = 45015;
    public static final int SYSTEM_GROUP = 45016;
    public static final int GROUPNAME_LIMIT_OVER = 45017;
    public static final int GROUPSIZE_LIMIT_OVER = 45018;
    public static final int NOEXIST_MEDIAFILE = 46001;
    public static final int NOEXIST_MENUVERSION = 46002;
    public static final int NOEXIST_MENUDATA = 46003;
    public static final int NOEXIST_USER = 46004;
    public static final int PARSE_XML_JSON_ERR = 47001;
    public static final int UNOAUTH_MODULE_API = 48001;
    public static final int UNOATH_API = 50001;

    /**
     * Get the wrong information
     *
     * @param errCode   error code
     * @return  Error description
     */
    public static String getErrDesc(int errCode){
        switch (errCode) {
            case OK:
                return "Request successfully";
            case SYS_BUSY:
                return "System busy";
            case ERROR_SECRET:
                return "Obtainaccess_tokentimeAppSecreterror，perhapsaccess_tokeninvalid";
            case INVALID_CERTIFICATE:
                return "The type of document that is not valid";
            case INVALID_OPENID:
                return "IllegalOpenID";
            case INVALID_MEDIATYPE:
                return "Illegal media file type";
            case INVALID_FILETYPE:
                return "Illegal file type";
            case INVALID_FILESIZE:
                return "Illegal file size";
            case INVALID_MEDIAID:
                return "Illegal media fileid";
            case INVALID_MESGTYPE:
                return "Illegal message type";
            case INVALID_IMAGESIZE:
                return " Image size is not valid";
            case INVALID_VOICESIZE:
                return "Illegal voice file size";
            case INVALID_VIDEOSIZE:
                return "Illegal video file size";
            case INVALID_THUMBSIZE:
                return "Thumbnail file size is not valid";
            case INVALID_APPID:
                return "IllegalAPPID";
            case INVALID_ACCESSTOKEN:
                return "Illegalaccess_token";
            case INVALID_MENUTYPE:
                return "Illegal menu type";
            case INVALID_BUTTONSIZE:
            case INVALID_BUTTONSIZE2:
                return "Number of illegal buttons";
            case INVALID_BUTTONNAMESIZE:
                return "Illegal button name length";
            case INVALID_BUTTONKEYSIZE:
                return "Illegal buttonKEYlength";
            case INVALID_BUTTONURLSIZE:
                return "Illegal buttonURLlength";
            case INVALID_BUTTONVERSION:
                return "Illegal menu version number";
            case INVALID_MENULEVEL:
                return "Illegal sub menu series";
            case INVALID_SUBBUTTONSIZE:
                return "Number of not valid sub menu button";
            case INVALID_SUBMENUTYPE:
                return "Illegal sub menu button type";
            case INVALID_SUBBUTTONNAMESIZE:
                return "Name of sub menu button is not valid";
            case INVALID_SUBBUTTONKEYSIZE:
                return "Illegal sub menu buttonKEYlength";
            case INVALID_SUBBUTTONURLSIZE:
                return "Illegal sub menu buttonKEYlength";
            case INVALID_MENUUSER:
                return "Illegal custom menu user";
            case INVALID_OAUTHCODE:
                return "Illegaloauth_code";
            case INVALID_REFRESHCODE:
                return "Illegalrefresh_token";
            case INVALID_OPENIDLIST:
                return "Illegalopenidlist";
            case INVALID_OPENIDLENGTH:
                return "IllegalopenidList length";
            case INVALID_REQUESTSTRING:
                return "Illegal request character，Cannot contain\\uxxxxCharacter format";
            case INVALID_PARAMETERS:
                return "Illegal parameter";
            case INVALID_REQUESTSTYLE:
                return "Illegal request form";
            case INVALID_URLLENGTH:
                return "IllegalURLlength";
            case INVALID_GROUPID:
                return "Illegal groupid";
            case INVALID_GROUPNAME:
                return "Group name is not valid";
            case INVALID_MSGID:
                return "Illegal newsID";
            case LOST_AESSTOKEN:
                return "Lackaccess_tokenparameter";
            case LOST_APPID:
                return "Lackappidparameter";
            case LOST_REFRESHTOKEN:
                return "Lackrefresh_tokenparameter";
            case LOST_APPSECRET:
                return "Lacksecretparameter";
            case LOST_MEDIAFILE_DATA:
                return "Lack of multimedia file data";
            case LOST_MEDIAID:
                return "Lackmedia_idparameter";
            case LOST_SUBMENUS:
                return "Missing child menu data";
            case LOST_OAUTHCODE:
                return "Lackoauth code";
            case LOST_OPENID:
                return "Lackopenid";
            case ACCESSTOKEN_OVERTIME:
                return "access_tokenovertime";
            case REFRESHTOKEN_OVERTIME:
                return "refresh_tokenovertime";
            case OAUTHCODE_OVERTIME:
                return "oauth codeovertime";
            case GET_REUEST:
                return "demandGETrequest";
            case POST_REUEST:
                return "demandPOSTrequest";
            case HTTPS_REUEST:
                return "demandHTTPSrequest";
            case FOLLOW_REUEST:
                return "Requires recipient attention";
            case FRIEND_REUEST:
                return "Need good friends";
            case NULL_MEDIAFILE:
                return "Multimedia file is empty";
            case NULL_POSTDATA:
                return "POSTThe packet is empty";
            case NULL_NEWSDATA:
                return "Message content is empty";
            case NULL_TEXTDATA:
                return "Text message is empty";
            case MEDIASIZE_LIMIT_OVER:
                return "Multimedia file size exceeds limit";
            case CONTENT_LIMIT_OVER:
                return "Message content over limit";
            case TITLE_LIMIT_OVER:
                return "Title field exceeds limit";
            case DIGEST_LIMIT_OVER:
                return "Description fields exceed limits";
            case LINKURL_LIMIT_OVER:
                return "Link fields exceed limits";
            case PICURL_LIMIT_OVER:
                return "Picture link fields oversize";
            case VIOCE_PLAY_LIMIT_OVER:
                return "Voice play time over limit";
            case NEWS_LIMIT_OVER:
                return "Message oversize";
            case INTERFACE_LIMIT_OVER:
                return "Interface calls over limit";
            case MENUSIZE_LIMIT_OVER:
                return "Create menu number more than limit";
            case REPLYTIME_LIMIT_OVER:
                return "Reply time exceeds limit";
            case SYSTEM_GROUP:
                return "System grouping，Not allowed to modify";
            case GROUPNAME_LIMIT_OVER:
                return "Group name is too long";
            case GROUPSIZE_LIMIT_OVER:
                return "Packet number exceeds the upper limit";
            case NOEXIST_MEDIAFILE:
                return "There is no media data";
            case NOEXIST_MENUVERSION:
                return "A menu version does not exist";
            case NOEXIST_MENUDATA:
                return "Nonexistent menu data";
            case NOEXIST_USER:
                return "Non existing user";
            case PARSE_XML_JSON_ERR:
                return "analysisJSON/XMLContent error";
            case UNOAUTH_MODULE_API:
                return "apiFunction not authorized";
            case UNOATH_API:
                return "The user does not authorize theapi";
            default:
                return "Unknown anomaly";
        }
    }
}