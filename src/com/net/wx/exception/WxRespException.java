package com.net.wx.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.net.wx.common.WxErrCode;

/**
 * WeChat response error exception
 */
public class WxRespException extends Exception {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
     * error code
     */
    private final int errCode;
    /**
     * Error description in Chinese
     */
    private final String errMesg;
    // 临时JSON对象
    protected static JSONObject error;

    public WxRespException(String message) {
        super(convertMesg(message));
        this.errCode = error.getInteger("errcode");
        this.errMesg = error.getString("errmsg");
    }

    /**
     * Turn message intoJSONobject
     * @param message   Message
     * @return  errorobject
     */
    protected static String convertMesg(String message) {
        if (null == message || message.isEmpty()) {
            throw new RuntimeException("Network communication exception,Please check!!!");
        }
        String err_desc = "";
        error = JSON.parseObject(message);
        err_desc = WxErrCode.getErrDesc(error.getInteger("errcode"));
        return err_desc;
    }

    /**
     * Get error code
     *
     * @return errCode
     */
    public int getErrCode() {
        return errCode;
    }

    /**
     * Get an error description
     *
     * @return  errmsg
     */
    public String getErrMesg() {
        return errMesg;
    }
}