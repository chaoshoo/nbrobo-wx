package com.net.wx.support;

import com.net.wx.core.WxBase;
import com.net.wx.core.WxHandler;
import com.net.wx.vo.MPAct;

/**
 * 微信WEB环境接口设计
 * @author zhaidong
 *
 */
public interface WxWebSupport {

    /**
     * 设置微信公众号信息
     *
     * @param mpAct 公众号信息
     */
    void setMpAct(MPAct mpAct);

    /**
     * 设置微信消息处理器
     *
     * @param wxHandler 微信消息处理器
     */
    void setWxHandler(WxHandler wxHandler);

    /**
     * 获取微信基础功能
     *
     * @return 基础功能
     */
    WxBase getWxBase();
}
