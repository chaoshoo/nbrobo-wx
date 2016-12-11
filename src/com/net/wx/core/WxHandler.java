package com.net.wx.core;

import com.net.wx.util.AesException;
import com.net.wx.vo.OutPutMsg;
import com.net.wx.vo.ReceiveMsg;


/**
 * WeChat message processing interface
 */
public interface WxHandler {

    /**
     * WeChat access timeURLVerification
     *
     * @param token     secret key
     * @param signature autograph
     * @param timestamp time stamp
     * @param nonce     Random character
     * @return  trueorfalse
     * @throws AesException Signature anomaly
     */
    boolean check(String token,
                 String signature,
                 String timestamp,
                 String nonce) throws AesException;

    /**
     * News to deal with the new features of WeChat
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg def(ReceiveMsg rm);

    /**
     * Processing text messages
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg text(ReceiveMsg rm,String dpid);

    /**
     * Image message processing
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg image(ReceiveMsg rm);

    /**
     * Processing audio messages
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg voice(ReceiveMsg rm);

    /**
     * Processing video messages
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg video(ReceiveMsg rm);

    /**
     * Handle active upload location information
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg location(ReceiveMsg rm);

    /**
     * Handle link messages
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg link(ReceiveMsg rm);

    /**
     * Handle menu click event messages
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg eClick(ReceiveMsg rm, String dpid);

    /**
     * Handle menu view event messages
     *
     * @param rm    Received message
     */
    void eView(ReceiveMsg rm);

    /**
     * Handle subscription event messages
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg eSub(ReceiveMsg rm);

    /**
     * To unsubscribe from the event message processing
     *
     * @param rm    Received message
     */
    void eUnSub(ReceiveMsg rm);

    /**
     * Handle scan event messages
     *
     * @param rm    Received message
     */
    OutPutMsg eScan(ReceiveMsg rm);

    /**
     * Handle automatically upload geographic event messages
     *
     * @param rm    Received message
     */
    OutPutMsg eLocation(ReceiveMsg rm);

    /**
     * Handling two-dimensional code scanning event messages
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg eScanCodePush(ReceiveMsg rm);

    /**
     * Scan push events and pop“Message reception”prompt box
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg eScanCodeWait(ReceiveMsg rm);

    /**
     * Handling the event push pop up system to take pictures
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg ePicSysPhoto(ReceiveMsg rm);

    /**
     * Handling events pop up pictures or photo albums sent to push the event
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg ePicPhotoOrAlbum(ReceiveMsg rm);

    /**
     * Handle events pop up WeChat photo albums
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg ePicWeixin(ReceiveMsg rm);

    /**
     * Event push message handling pop up location selector
     *
     * @param rm    Received message
     * @return  Reply message
     */
    OutPutMsg eLocationSelect(ReceiveMsg rm);

    /**
     * Processing template to send event messages
     *
     * @param rm    Received message
     */
    void eTemplateFinish(ReceiveMsg rm);

    /**
     * Dealing with mass news event messages
     *
     * @param rm    Received message
     */
    void eSendJobFinish(ReceiveMsg rm);

    /**
     * Handle WeChat open platform pushTicketEvent message<pre/>
     * (Just reply"success"that will do)
     * @param rm    Received message
     */
    void eComponentVerifyTicket(ReceiveMsg rm);

    /**
     * Deal with WeChat open platform push the cancellation of the number of public events to cancel the event<pre/>
     * (Just reply"success"that will do)
     * @param rm    Received message
     */
    void eUnAuthorizerMP(ReceiveMsg rm);
}