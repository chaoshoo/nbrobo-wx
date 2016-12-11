package com.net.wx.common;

/**
 * 微信消息的类型
 */
public enum WxMsgType {
    /**
     * 文本消息
     */
    text,
    /**
     * 图像消息
     */
    image,
    /**
     * 语音消息
     */
    voice,
    /**
     * 视频消息
     */
    video,
    /**
     * 地理位置消息
     */
    location,
    /**
     * 链接消息
     */
    link,
    /**
     * 音乐消息
     */
    music,
    /**
     * 多图文消息
     */
    news,
    /**
     * 群发消息中的图文消息
     */
    mpnews,
    /**
     * 群发消息中的视频消息
     */
    mpvideo,
    
    /**
     * 用户订阅事件
     */
    subscribe,
    /**
     * 退订事件
     */
    unsubscribe,
    /**
     * 点击事件
     */
    CLICK;
}
