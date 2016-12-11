package com.net.wx.core;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.net.wx.common.WxApiUrl;
import com.net.wx.exception.WxRespException;
import com.net.wx.util.SimpleHttpReq;
import com.net.wx.vo.AuthInfo;
import com.net.wx.vo.MPAct;

/**
 * WeChat open platformAPIinterface design
 */
public class WxOpenApiImpl implements WxOpenApi {

    private static final Logger log = LoggerFactory.getLogger(WxOpenApiImpl.class);

    private MPAct mpAct;

    private String ticket;

    public WxOpenApiImpl() {
    }

    /**
     * WeChat open platform interface
     *
     * @param mpAct     Service component public information
     * @param ticket    Permission document
     */
    public WxOpenApiImpl(MPAct mpAct, String ticket) {
        this.mpAct = mpAct;
        this.ticket = ticket;
    }

    @Override
    public void setMpAct(MPAct mpAct) {
        this.mpAct = mpAct;
    }

    @Override
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String getComponentToken() throws WxRespException {
        String token = mpAct.getAccessToken();
        if (null == token
                || token.isEmpty()
                || mpAct.getExpiresIn() < System.currentTimeMillis()) {
            synchronized (this){
                refreshComponentToken();
            }
            token = mpAct.getAccessToken();
        }
        return token;
    }

    @Override
    public void refreshComponentToken() throws WxRespException {
        String result = "";

        String data = "{" +
                "\"component_appid\":\"" + mpAct.getAppId() + "\"," +
                "\"component_appsecret\":\"" + mpAct.getAppSecret() + "\"," +
                "\"component_verify_ticket\":\"" + ticket + "\"" +
                "}";

        try {
            result = SimpleHttpReq.post(WxApiUrl.COMPONENT_TOKEN_API,
                    SimpleHttpReq.APPLICATION_JSON,
                    data);
        } catch (IOException e) {
            log.error("Refresh service componentACCESS_TOKENWhen there is abnormal!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
            throw new WxRespException(result);
        }

        mpAct.createAccessToken(result);
    }

    @Override
    public void createPreAuthCode() throws WxRespException {
        String url = String.format(WxApiUrl.COMPONENT_API,
                "api_create_preauthcode", getComponentToken());
        String result = "";
        String data = "{" +
                "\"component_appid\":\"" + mpAct.getAppId() + "\"" +
                "}";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
        } catch (IOException e) {
            log.error("Exception occurred when creating public power pre authorization code!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
            throw new WxRespException(result);
        }

        mpAct.createPreAuthCode(result);
    }

    @Override
    public String getPreAuthCode() throws WxRespException {
        String auth_code = mpAct.getPreAuthCode();
        if (null == auth_code
                || mpAct.getPreAuthExpiresIn() < System.currentTimeMillis()){
            synchronized (this.mpAct){
                createPreAuthCode();
            }
        }
        return auth_code;
    }

    @Override
    public AuthInfo queryAuth(String authCode) throws WxRespException {
        String url = String.format(WxApiUrl.COMPONENT_API, "api_query_auth", getComponentToken());
        String result = "";
        String data = "{" +
                "\"component_appid\":\"" + mpAct.getAppId() + "\" ," +
                "\" authorization_code\": \"" + authCode + "\"" +
                "}";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
        } catch (IOException e) {
            log.error("In exchange for the public information on the number of abnormal!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
            throw new WxRespException(result);
        }

        AuthInfo tmp = JSON.parseObject(result, AuthInfo.class);
        return tmp;
    }

    @Override
    public String getAuthAccessToken(String authAppId, String authRefreshToken) throws WxRespException {
        return null;
    }

    @Override
    public void refreshAuthAccessToken(String authAppId, String authRefreshToken) throws WxRespException {

    }

    @Override
    public AuthInfo getAuthorizerInfo(String authAppId) throws WxRespException {
        return null;
    }

    @Override
    public String getAuthorizerOption(String authAppId, String optionName) throws WxRespException {
        String url = String.format(WxApiUrl.COMPONENT_API, "api_get_authorizer_option", getComponentToken());
        String result = "";
        String data = "{" +
                "\"component_appid\":\""+mpAct.getAppId()+"\"," +
                "\"authorizer_appid\": \""+authAppId+"\"," +
                "\"option_name\": \""+optionName+"\"" +
                "}";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
        } catch (IOException e) {
            log.error("Acquire to authorize public account[{}]An exception occurs when the value of the option is!!!",authAppId);
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || result.contains("errcode")) {
            throw new WxRespException(result);
        }

        JSONObject tmp = JSON.parseObject(result);
        String option_value = tmp.getString("option_value");
        if (log.isInfoEnabled()) {
            String info = "Acquire to authorize public account["+authAppId+"]";
            if(StringUtils.equals(optionName, "location_report")){
            	info += "Geographic location reporting options,Current status as: ";
            	if (option_value.equals("0")) {
                    info += "No report";
                } else if (option_value.equals("1")) {
                    info += "Report to the session";
                } else {
                    info += "each5sReport";
                }
            }else if(StringUtils.equals(optionName, "voice_recognize")){
            	info += "Speech recognition options,Current status as: ";
                info += (option_value.equals("0")?"Close":"open");
            }else if(StringUtils.equals(optionName, "customer_service")){
            	info += "Multiple customer service options,Current status as: ";
                info += (option_value.equals("0")?"Close":"open");
            }
            /*switch (optionName) {
                case "location_report":
                    info += "Geographic location reporting options,Current status as: ";
                    if (option_value.equals("0")) {
                        info += "No report";
                    } else if (option_value.equals("1")) {
                        info += "Report to the session";
                    } else {
                        info += "each5sReport";
                    }
                    break;
                case "voice_recognize":
                    info += "Speech recognition options,Current status as: ";
                    info += (option_value.equals("0")?"Close":"open");
                    break;
                case "customer_service":
                    info += "Multiple customer service options,Current status as: ";
                    info += (option_value.equals("0")?"Close":"open");
                    break;
                default:
                    break;
            }*/
            log.info("{}",info);
        }

        return result;
    }

    @Override
    public void setAuthorizerOption(String authAppId, String optionName, String optionValue) throws WxRespException {
        String url = String.format(WxApiUrl.COMPONENT_API, "api_set_authorizer_option", getComponentToken());
        String result = "";
        String data = "{" +
                "\"component_appid\":\""+mpAct.getAppId()+"\"," +
                "\"authorizer_appid\": \""+authAppId+"\"," +
                "\"option_name\": \""+optionName+"\"," +
                "\"option_value\":\""+optionValue+"\"" +
                "}";
        try {
            result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
        } catch (IOException e) {
            log.error("Set authorized public account[{}]An exception occurs when the value of the option is!!!",authAppId);
            log.error(e.getLocalizedMessage(), e);
        }

        if (result.isEmpty()
                || !result.contains("ok")) {
            throw new WxRespException(result);
        }

        if (log.isInfoEnabled()) {
            String info = "Set authorized public account["+authAppId+"]";
            if(StringUtils.equals(optionName, "location_report")){
            	info += "Geographic location reporting options,Current status as: ";
                if (optionValue.equals("0")) {
                    info += "No report";
                } else if (optionValue.equals("1")) {
                    info += "Report to the session";
                } else {
                    info += "each5sReport";
                }
            }else if(StringUtils.equals(optionName, "voice_recognize")){
            	info += "Speech recognition options,Current status as: ";
                info += (optionValue.equals("0")?"Close":"open");
            }else if(StringUtils.equals(optionName, "customer_service")){
            	info += "Multiple customer service options,Current status as: ";
                info += (optionValue.equals("0")?"Close":"open");
            }
            /*switch (optionName) {
                case "location_report":
                    info += "Geographic location reporting options,Current status as: ";
                    if (optionValue.equals("0")) {
                        info += "No report";
                    } else if (optionValue.equals("1")) {
                        info += "Report to the session";
                    } else {
                        info += "each5sReport";
                    }
                    break;
                case "voice_recognize":
                    info += "Speech recognition options,Current status as: ";
                    info += (optionValue.equals("0")?"Close":"open");
                    break;
                case "customer_service":
                    info += "Multiple customer service options,Current status as: ";
                    info += (optionValue.equals("0")?"Close":"open");
                    break;
                default:
                    break;
            }*/
            log.info("{}",info);
        }
    }
}