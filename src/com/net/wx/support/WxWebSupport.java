package com.net.wx.support;

import com.net.wx.core.WxBase;
import com.net.wx.core.WxHandler;
import com.net.wx.vo.MPAct;

/**
 * WeChatWEBEnvironment interface design
 * @author zhaidong
 *
 */
public interface WxWebSupport {

    /**
     * Set WeChat public information
     *
     * @param mpAct Public information
     */
    void setMpAct(MPAct mpAct);

    /**
     * WeChat message processor
     *
     * @param wxHandler WeChat message processor
     */
    void setWxHandler(WxHandler wxHandler);

    /**
     * Get WeChat basic functions
     *
     * @return Basic function
     */
    WxBase getWxBase();
}