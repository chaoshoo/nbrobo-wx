package com.net.wx.core;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.net.wx.Constants;
import com.net.wx.common.WxApiUrl;
import com.net.wx.common.WxMsgType;
import com.net.wx.exception.WxRespException;
import com.net.wx.util.AesException;
import com.net.wx.util.JsonMsgBuilder;
import com.net.wx.util.SHA1;
import com.net.wx.util.SimpleHttpReq;
import com.net.wx.vo.Article2;
import com.net.wx.vo.FollowList;
import com.net.wx.vo.Follower;
import com.net.wx.vo.Group;
import com.net.wx.vo.MPAct;
import com.net.wx.vo.Menu;
import com.net.wx.vo.OutPutMsg;
import com.net.wx.vo.Template;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * WeChat Public Platform DeveloperAPIInterface implementation
 */
public class WxApiImpl implements WxApi {

    private static final Logger log = LoggerFactory.getLogger(WxApiImpl.class);

    private MPAct mpAct;

    /**
     * WeChat public platform interface
     *
     * @param mpAct Public information
     */
    public WxApiImpl(MPAct mpAct) {
        this.mpAct = mpAct;
    }

    @Override
    public String getAccessToken() throws WxRespException {
        String token = mpAct.getAccessToken();
        if (null == token
                || token.isEmpty()
                || mpAct.getExpiresIn() < System.currentTimeMillis()) {
            synchronized (mpAct){
                refreshAccessToken();
            }
            token = mpAct.getAccessToken();
        }
        return token;
    }

    @Override
    public void refreshAccessToken() throws WxRespException {
        String url = String.format(WxApiUrl.ACCESS_TOKEN_API,
                mpAct.getAppId(),
                mpAct.getAppSecret());
        String result = "";
        try {
            result = SimpleHttpReq.get(url);
        } catch (IOException e) {
        	e.printStackTrace();
            log.error("RefreshACCESS_TOKENWhen there is abnormal!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
           throw new WxRespException(result);
        }

        mpAct.createAccessToken(result);
    }

    @Override
    public List<String> getServerIp() throws WxRespException {
        String url = String.format(WxApiUrl.IP_LIST_API, getAccessToken());
        String result = "";
        try {
            result = SimpleHttpReq.get(url);
        } catch (IOException e) {
            log.error("Get WeChat serverIPWhen there is abnormal!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
            throw new WxRespException(result);
        }

        JSONObject tmp = JSON.parseObject(result);
        List<String> ips = JSONObject.parseArray(tmp.getString("ip_list"), String.class);

        return ips;
    }

    @Override
    public List<Menu> getMenu() throws WxRespException {
        String url = String.format(WxApiUrl.CUSTOM_MENU_API, "get", getAccessToken());
        String result = "";
        try {
            result = SimpleHttpReq.get(url);
        } catch (IOException e) {
            log.error("Gets exception when you get the current custom menu!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
            throw new WxRespException(result);
        }
        JSONObject tmp = JSON.parseObject(result).getJSONObject("menu");
        List<Menu> menus = JSON.parseArray(tmp.getString("button"), Menu.class);
        return menus;
    }

    @Override
    public boolean createMenu(Menu... menus) throws WxRespException {
        // 创建JSON格式化
        PropertyFilter null_filter = new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object value) {
                if(name.equals("key")
                        && (null==value || "".equals(value))) {
                    return false;
                }
                if (name.equals("url")
                        && (null==value || "".equals(value))) {
                    return false;
                }
                return true;
            }
        };
        // 准备菜单数据
        Map<String, Object> buttons = new HashMap<String, Object>();
        buttons.put("button", Arrays.asList(menus));
        String btn_json = JSONObject.toJSONString(buttons, null_filter);
        if (log.isInfoEnabled()) {
            log.info("Created by WeChat custom menu: {}", btn_json);
        }
        // 提交并创建菜单
        String url = String.format(WxApiUrl.CUSTOM_MENU_API, "create", getAccessToken());
        String result = "";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, btn_json);
        } catch (IOException e) {
        	e.printStackTrace();
            log.error("Exception when creating a custom menu!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || !result.contains("ok")) {
            throw new WxRespException(result);
        }

        return true;
    }

    @Override
    public boolean deleteMenu() throws WxRespException {
        String url = String.format(WxApiUrl.CUSTOM_MENU_API, "delete", getAccessToken());
        String result = "";
        try {
            result = SimpleHttpReq.get(url);
        } catch (IOException e) {
            log.error("Exception occurred while deleting WeChat custom menu!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || !result.contains("ok")) {
            throw new WxRespException(result);
        }

        return true;
    }

    @Override
    public int creatGroup(String name) throws WxRespException {
        String url = String.format(WxApiUrl.GROUPS_API, "create", getAccessToken());
        String data = "{\"group\":{\"name\":\""+name+"\"}}";
        String result = "";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
        } catch (IOException e) {
            log.error("Exception when creating the WeChat group!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || !result.contains("group")) {
            throw new WxRespException(result);
        }

        JSONObject tmp = JSON.parseObject(result).getJSONObject("group");

        return tmp.getInteger("id");
    }

    @Override
    public List<Group> getGroups() throws WxRespException {
        String url = String.format(WxApiUrl.GROUPS_API, "get", getAccessToken());
        String result = "";
        try {
            result = SimpleHttpReq.get(url);
        } catch (IOException e) {
            log.error("Failed to get all packets!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.indexOf("errcode") > -1) {
            throw new WxRespException(result);
        }

        String tmp = JSON.parseObject(result).getString("groups");
        List<Group> groups = JSON.parseArray(tmp, Group.class);

        return groups;
    }

    @Override
    public boolean renGroup(int id, String name) throws WxRespException {
        String url = String.format(WxApiUrl.GROUPS_API, "update", getAccessToken());
        name = name.length() > 30 ? name.substring(0,30) : name;
        String data = "{\"group\":{\"id\":"+id+",\"name\":\""+name+"\"}}";
        String result = "";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
        } catch (IOException e) {
            log.error("Exception when modifying a group name!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
            throw new WxRespException(result);
        }

        return true;
    }

    @Override
    public int getGroupId(String openId) throws WxRespException {
        String url = String.format(WxApiUrl.GROUPS_API, "getid", getAccessToken());
        String data = "{\"openid\":\""+openId+"\"}";
        String result = "";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
        } catch (IOException e) {
            log.error("Get exception when the user is in group!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.indexOf("errcode") > -1) {
            throw new WxRespException(result);
        }

        int group_id = JSON.parseObject(result).getInteger("groupid");

        return group_id;
    }

    @Override
    public boolean move2Group(String openId, int groupId) throws WxRespException {
        String url = String.format(WxApiUrl.GROUPS_USER_API, getAccessToken());
        String data = "{\"openid\":\""+openId+"\",\"to_groupid\":"+groupId+"}";
        String result = "";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
        } catch (IOException e) {
            log.error("Abnormal when the mobile user group!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || !result.contains("ok")) {
            throw new WxRespException(result);
        }

        return true;
    }

    @Override
    public FollowList getFollowerList(String nextOpenId) throws WxRespException {
        String url = String.format(WxApiUrl.FOLLOWS_USER_API, getAccessToken(), nextOpenId);
        String result = "";
        try {
            result = SimpleHttpReq.get(url);
        } catch (IOException e) {
            log.error("Get attention to the user list when the exception occurs!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.indexOf("errcode") > -1) {
            throw new WxRespException(result);
        }

        FollowList followers = JSON.parseObject(result, FollowList.class);
        String openid = JSON.parseObject(result).getJSONObject("data").getString("openid");
        List<String> openids = JSON.parseArray(openid, String.class);
        followers.setOpenIds(openids);
        return followers;
    }

    @Override
    public Follower getFollower(String openId, String lang) throws WxRespException {
        String url = String.format(WxApiUrl.USER_INFO_API, getAccessToken(), openId, lang);
        String result = "";
        try {
            result = SimpleHttpReq.get(url);
        } catch (IOException e) {
            log.error("When the user information to get abnormal!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.indexOf("errcode") > -1) {
            throw new WxRespException(result);
        }

        Follower follower = JSON.parseObject(result, Follower.class);

        return follower;
    }

    @Override
    public boolean sendCustomerMsg(OutPutMsg msg) throws WxRespException {
        String url = String.format(WxApiUrl.CUSTOM_MESSAGE_API, getAccessToken());
        String custom_msg = "";
        WxMsgType type = WxMsgType.valueOf(msg.getMsgType());
        switch (type) {
            case text:
                custom_msg = JsonMsgBuilder.create().text(msg).build();
                break;
            case image:
                custom_msg = JsonMsgBuilder.create().image(msg).build();
                break;
            case voice:
                custom_msg = JsonMsgBuilder.create().voice(msg).build();
                break;
            case video:
                custom_msg = JsonMsgBuilder.create().video(msg).build();
                break;
            case music:
                custom_msg = JsonMsgBuilder.create().music(msg).build();
                break;
            case news:
                custom_msg = JsonMsgBuilder.create().news(msg).build();
                break;
            default:
                break;
        }
        String result = "";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, custom_msg);
        } catch (IOException e) {
            log.error("Exception occurs when the customer service message is sent!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty() 
                || !result.contains("ok")) {
            throw new WxRespException(result);
        }

        return true;
    }

    @Override
    public boolean sendTemplateMsg(String openId, String templateId,
                                   String topColor, String url, Template... templates) throws WxRespException {
        String api_url = String.format(WxApiUrl.TEMPLATE_MESSAGE_API, getAccessToken());
        String template_msg = JsonMsgBuilder.create()
                .template(openId, templateId, topColor, url, templates)
                .build();
        String result = "";
        try {
            result = SimpleHttpReq.post(api_url, SimpleHttpReq.APPLICATION_JSON, template_msg);
        } catch (IOException e) {
            log.error("Exception when sending a template message!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty() ||
                result.contains("errcode")) {
            throw new WxRespException(result);
        }

        return true;
    }

    @Override
    public JSONObject upMedia(String mediaType, File file,String saveType) throws WxRespException {
    	String url = null;
    	if(Constants.TEMP_SAVE_MEDIA.equals(saveType)){
    		url = String.format(WxApiUrl.MEDIA_UP_API, mediaType, getAccessToken());
    	}else{
    		url = String.format(WxApiUrl.MEDIA_UPLOAD_PERPETUAL_API, mediaType, getAccessToken());
    	}
        String result = "";
        try {
            result = SimpleHttpReq.upload(url, file);
        } catch (IOException e) {
            log.error("Exception when uploading a multimedia file!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                ||result.contains("errcode")) {
            throw new WxRespException(result);
        }

        return JSON.parseObject(result);
    }
    
    @Override
    public void dlMedia(String mediaId, File file) throws WxRespException {

        String url = String.format(WxApiUrl.MEDIA_DL_API, getAccessToken(), mediaId);
        // 检查文件夹是否存在
        if(!file.exists()) {
            String path = file.getAbsolutePath();
            String separ = System.getProperties().getProperty("file.separator");
            File dir = new File(path.substring(0, path.lastIndexOf(separ)));
            dir.mkdirs();
        }

        try {
            SimpleHttpReq.download(url, file);
        } catch (IOException e) {
            log.error("Exception when downloading multimedia files!!!");
            log.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public String upNews(String saveType,Article2... articles2s) throws WxRespException {
    	String url = null;
    	if(Constants.TEMP_SAVE_MEDIA.equals(saveType)){
    		 url = String.format(WxApiUrl.NEWS_UPLOAD_API, getAccessToken());
    	}else{
    		url = String.format(WxApiUrl.NEWS_UPLOAD_PERPETUAL_API, getAccessToken());
    	}
        String upnews_msg = JsonMsgBuilder.create().uploadNews(articles2s).build();
        String result = "";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, upnews_msg);
        } catch (IOException e) {
            log.error("Upload mass text messages appear abnormal!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                ||result.contains("errcode")) {
            throw new WxRespException(result);
        }
        JSONObject tmp = JSON.parseObject(result);
        String mediaId = tmp.getString("media_id");
        return mediaId;
    }
    
    @Override
    public int updateNews(String updateMediaId,int index,Article2 articles2s) throws WxRespException {
    	String url = String.format(WxApiUrl.NEWS_UPDATE_PERPETUAL_API, getAccessToken());;
    	String updatenews_msg = JsonMsgBuilder.create().updateNews(updateMediaId,index,articles2s).build();
    	String result = "";
    	try {
    		result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, updatenews_msg);
    	} catch (IOException e) {
    		log.error("Upload mass text messages appear abnormal!!!");
    		log.error(e.getLocalizedMessage(), e);
    	}
    	
    	if(StringUtils.isEmpty(result)){
    		throw new WxRespException(result);
    	}
    	
    	JSONObject tmp = JSON.parseObject(result);
    	int errcode = tmp.getInteger("errcode");
    	return errcode;
    }
    
    @Override
    public int delMedia(String mediaId) throws WxRespException {
    	String url = String.format(WxApiUrl.DEL_MEDIA_PERPETUAL_API, getAccessToken());;
    	String updatenews_msg = JsonMsgBuilder.create().delMedia(mediaId).build();
    	String result = "";
    	try {
    		result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, updatenews_msg);
    	} catch (IOException e) {
    		log.error("Upload mass text messages appear abnormal!!!");
    		log.error(e.getLocalizedMessage(), e);
    	}
    	
    	if(StringUtils.isEmpty(result)){
    		throw new WxRespException(result);
    	}
    	
    	JSONObject tmp = JSON.parseObject(result);
    	int errcode = tmp.getInteger("errcode");
    	return errcode;
    }

    @Override
    public String[] upVideo(String mediaId,
                                String title, String description) throws WxRespException {
        String url = String.format(WxApiUrl.MEDIA_UPVIDEO_API, getAccessToken());
        String result = "";
        String upvideo_msg = JsonMsgBuilder.create().uploadVideo(mediaId,title,description).build();
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, upvideo_msg);
        } catch (IOException e) {
            log.error("An exception occurs when the video is uploaded to the mass.!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                ||result.contains("errcode")) {
            throw new WxRespException(result);
        }
        JSONObject tmp = JSON.parseObject(result);
        String[] results = {
                tmp.getString("type"),
                tmp.getString("media_id"),
                tmp.getString("created_at")
        };
        return results;
    }

    @Override
    public String sendAll(OutPutMsg msg) throws WxRespException {
        String group_id = msg.getGroupId();
        List<String> to_users = msg.getToUsers();
        if (null != group_id
                && !group_id.isEmpty()
                && !to_users.isEmpty()) {
            throw new RuntimeException("Group message can only choose a model");
        }

        String url = "";
        if (to_users.isEmpty()) {
           url =  String.format(WxApiUrl.GROUP_NEWS_MESSAGE_API, "sendall", getAccessToken());
        } else {
            url = String.format(WxApiUrl.GROUP_NEWS_MESSAGE_API, "send", getAccessToken());
        }

        String result = "";
        String send_msg = JsonMsgBuilder.create().sendAll(msg).build();
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, send_msg);
        } catch (IOException e) {
            log.error("Exception when sending a group message!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                ||!result.contains("msg_id")) {
            throw new WxRespException(result);
        }

        JSONObject tmp = JSON.parseObject(result);

        return tmp.getString("msg_id");
    }

    @Override
    public boolean delSendAll(String msgId) throws WxRespException {
        String url = String.format(WxApiUrl.GROUP_NEWS_MESSAGE_API, "delete", getAccessToken());
        String result = "";
        String del_msg = "{\"msg_id\":"+ msgId +"}";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, del_msg);
        } catch (IOException e) {
            log.error("Exception occurs when deleting a group message!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                ||!result.contains("ok")) {
            throw new WxRespException(result);
        }

        return true;
    }

    @Override
    public Map<String, Object> getJsAPISign(String url) throws WxRespException {
        // 1.创建JSTICKET
        String ticket = mpAct.getJsTicket();
        if (null == ticket
                || ticket.isEmpty()
                || mpAct.getJsExpiresIn() < System.currentTimeMillis()) {
            synchronized (mpAct){
                refreshJsAPITicket();
            }
            ticket = mpAct.getJsTicket();
        }

        // 2.生成签名
        String sign_param = "jsapi_ticket=%1$s&noncestr=%2$s&timestamp=%3$s&url=%4$s";
        String nonce = UUID.randomUUID().toString().toLowerCase();
        long time = System.currentTimeMillis() / 1000;
        String sign = null;
        try {
            sign = SHA1.calculate(String.format(sign_param, ticket, nonce, time, url));
        } catch (AesException e) {
            log.error("generateJSTICKETAn exception occurs when the signature is signed!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        // 3.返回参数与值
        Map<String, Object> sign_map = new HashMap<String, Object>();
        sign_map.put("url", url);
        sign_map.put("ticket", ticket);
        sign_map.put("nonce", nonce);
        sign_map.put("timestamp", time);
        sign_map.put("sign", sign);
        return sign_map;
    }

    @Override
    public void refreshJsAPITicket() throws WxRespException {

        String token_url = WxApiUrl.JSAPI_TICKET_URL+getAccessToken();

        String result = "";
        try {
            result = SimpleHttpReq.get(token_url);
        } catch (IOException e) {
            log.error("RefreshJSTICKETWhen there is abnormal!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || !result.contains("ok")) {
            throw new WxRespException(result);
        }

        mpAct.createJsTicket(result);
    }
}