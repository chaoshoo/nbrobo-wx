package com.net.wx.core;

import com.net.wx.exception.WxRespException;
import com.net.wx.vo.AuthInfo;
import com.net.wx.vo.MPAct;


/**
 * WeChat open platformAPIinterface design
 */
public interface WxOpenApi {

    /**
     * Set public information
     * @param mpAct Public information
     */
    void setMpAct(MPAct mpAct);

    /**
     * Set credentials
     * @param ticket    voucher
     */
    void setTicket(String ticket);

    /**
     * Token for obtaining public service component
     * @return token
     * @throws WxRespException
     */
    String getComponentToken() throws WxRespException;

    /**
     * Refresh token for public service component
     * @throws WxRespException
     */
    void refreshComponentToken() throws WxRespException;

    /**
     * Access to public pre authorization code
     * @return  Pre authorization code
     * @throws WxRespException
     */
    String getPreAuthCode() throws WxRespException;

    /**
     * Create public number pre authorization code
     * @throws WxRespException
     */
    void createPreAuthCode() throws WxRespException;

    /**
     * Use the authorization code in exchange for authorization information of the public number<pre/>
     * And in exchange forauthorizer_access_tokenandauthorizer_refresh_token
     * @param authCode  Authorization code
     * @return  Authorization information
     * @throws WxRespException
     */
    AuthInfo queryAuth(String authCode) throws WxRespException;

    /**
     * Access to a token of the public number
     *
     * @param authAppId         Authorized partyappid
     * @param authRefreshToken  Authorized party refresh token
     * @return  token
     * @throws WxRespException
     */
    String getAuthAccessToken(String authAppId, String authRefreshToken) throws WxRespException;

    /**
     * Refresh the public number of the token
     *
     * @param authAppId         Authorized partyappid
     * @param authRefreshToken  Authorized party refresh token
     * @throws WxRespException
     */
    void refreshAuthAccessToken(String authAppId, String authRefreshToken) throws WxRespException;

    /**
     * Access to the authorized party account information
     *
     * @param authAppId Authorized partyappid
     * @return  The right side of the account information
     * @throws WxRespException
     */
    AuthInfo getAuthorizerInfo(String authAppId) throws WxRespException;

    /**
     * Gets the option setting information for the Licensor
     *
     * @param authAppId     Authorized partyappid
     * @param optionName    Option name(location_report,voice_recognize,customer_service)
     * @return  Option value
     * @throws WxRespException
     */
    String getAuthorizerOption(String authAppId, String optionName) throws WxRespException;

    /**
     * Set options for the authorized party to set information
     *
     * @param authAppId     Authorized partyappid
     * @param optionName    Set the option name
     * @param optionValue   Set the option value
     *                      ocation_report(Geographical position report) 0Close 1Report to the session 2each5sReport
     *                      voice_recognize（speech recognition） 0Close 1open
     *                      customer_service（Customer service switch） 0Close 1open
     * @throws WxRespException
     */
    void setAuthorizerOption(String authAppId, String optionName, String optionValue) throws WxRespException;
}