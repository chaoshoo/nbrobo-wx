package com.net.wx.support;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.net.wx.core.WxBase;
import com.net.wx.core.WxHandler;
import com.net.wx.util.AesException;
import com.net.wx.vo.MPAct;

/**
 * SpringMVCEnvironmental access
 * @author zhaidong
 */
public abstract class WxSpringSupport implements WxWebSupport {

    private static final Logger log = LoggerFactory.getLogger(WxSpringSupport.class);
    // 微信基础功能
    private WxBase wxBase = new WxBase();

    protected void init(HttpServletRequest req) {
        // 重写此方法
        // 1.设置微信公众号的信息
        // 2.setMpAct();
        // 3.setWxHandler();
    }

    /**
     * WeChat information interaction<pre/>
     * Only write aSpringMVCThis method is called by the portal.,Entrance useResponseBodyoutput
     *
     * @param req   response
     * @return  Reply message
     * @throws IOException
     */
    protected String wxInteract(HttpServletRequest req) throws IOException {
        // 准备
        this.init(req);
        this.wxBase.init(req);
        String reply = "";
        // 区分POST与GET来源
        String method = req.getMethod();
        // 微信接入验证
        if ("GET".equals(method)) {
            try {
                reply = this.wxBase.check();
                if (reply.isEmpty()) {
                    reply = "error";
                    log.error("WeChat access authenticationURLWhen failure!!!");
                    log.error("WeChat serverechoStrvalue: {}", this.wxBase.getEchostr());
                    log.error("SHA1autographechoStrvalue: {}", reply);
                }
            } catch (AesException e) {
                log.error("WeChat access authenticationURLWhen there is abnormal!!!");
                log.error(e.getLocalizedMessage(), e);
            }
            return reply;
        }
        System.out.println("----->"+1);
        // 信息互动
        try {
            reply = this.wxBase.handler();
        } catch (Exception e) {
            log.error("Abnormal analysis of WeChat news!!!");
            log.error(e.getLocalizedMessage(), e);
        }

        return reply;
    }

    @Override
    public void setMpAct(MPAct mpAct) {
        this.wxBase.setMpAct(mpAct);
    }

    @Override
    public void setWxHandler(WxHandler wxHandler) {
        this.wxBase.setWxHandler(wxHandler);
    }

    @Override
    public WxBase getWxBase() {
        return this.wxBase;
    }
}