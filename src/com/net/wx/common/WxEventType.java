package com.net.wx.common;

/**
 * WeChat event type
 */
public enum WxEventType {
    /**
     * User subscription event
     */
    subscribe,
    /**
     * Unsubscribe events
     */
    unsubscribe,
    /**
     * Scan event
     */
    SCAN,
    /**
     * Auto upload location
     */
    LOCATION,
    /**
     * Click event
     */
    CLICK,
    /**
     * Jump event
     */
    VIEW,
    /**
     * Template message push event
     */
    TEMPLATESENDJOBFINISH,
    /**
     * Mass message push event
     */
    MASSSENDJOBFINISH,

    // 以下事件微信iPhone5.4.1+, Android5.4+仅支持
    /**
     * Sweep push event
     */
    scancode_push,
    /**
     * Scan push events and pop“Message reception”prompt box
     */
    scancode_waitmsg,
    /**
     * Pop up system to take pictures
     */
    pic_sysphoto,
    /**
     * Pop up pictures or photo albums
     */
    pic_photo_or_album,
    /**
     * Pop up WeChat photo album
     */
    pic_weixin,
    /**
     * Pop-up location selector
     */
    location_select
}