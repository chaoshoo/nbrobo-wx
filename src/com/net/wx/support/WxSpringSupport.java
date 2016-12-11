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
 * SpringMVC环境接入
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
     * 与微信信息交互<pre/>
     * 实现时只需写个SpringMVC入口调用此方法即可,入口用ResponseBody输出
     *
     * @param req   响应
     * @return  回复消息
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
                    log.error("微信接入验证URL时失败!!!");
                    log.error("微信服务器echoStr值: {}", this.wxBase.getEchostr());
                    log.error("SHA1签名echoStr值: {}", reply);
                }
            } catch (AesException e) {
                log.error("微信接入验证URL时出现异常!!!");
                log.error(e.getLocalizedMessage(), e);
            }
            return reply;
        }
        System.out.println("----->"+1);
        // 信息互动
        try {
            reply = this.wxBase.handler();
        } catch (Exception e) {
            log.error("解析微信消息时出现异常!!!");
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