package com.net.wx.common;

/**
 * WeChat message type
 */
public enum WxMsgType {
    /**
     * Text message
     */
    text,
    /**
     * Image message
     */
    image,
    /**
     * Voice message
     */
    voice,
    /**
     * Video message
     */
    video,
    /**
     * Location information
     */
    location,
    /**
     * Link message
     */
    link,
    /**
     * information about the music
     */
    music,
    /**
     * Multi graphic message
     */
    news,
    /**
     * Message in the mass message
     */
    mpnews,
    /**
     * Mass message in the video message
     */
    mpvideo,
    
    /**
     * User subscription event
     */
    subscribe,
    /**
     * Unsubscribe events
     */
    unsubscribe,
    /**
     * Click event
     */
    CLICK;
}