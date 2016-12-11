package com.net.wx.util;

import com.net.wx.vo.Article;
import com.net.wx.vo.OutPutMsg;


/**
 * Create WeChat passive message reply
 */
public class XmlMsgBuilder {

    private final StringBuffer msgBuf = new StringBuffer("<xml>\n");;

    /**
     * Establish
     */
    public static XmlMsgBuilder create() {
        return new XmlMsgBuilder();
    }

    /**
     * Create message body prefix
     *
     * @param msg   Output message entity
     */
    void msgPrefix(OutPutMsg msg) {
        msgBuf.append("<ToUserName><![CDATA[")
                .append(msg.getToUserName())
                .append("]]></ToUserName>\n");
        msgBuf.append("<FromUserName><![CDATA[")
                .append(msg.getFromUserName())
                .append("]]></FromUserName>\n");
        msgBuf.append("<CreateTime>")
                .append(msg.getCreateTime())
                .append("</CreateTime>\n");
        msgBuf.append("<MsgType><![CDATA[")
                .append(msg.getMsgType())
                .append("]]></MsgType>\n");
    }

    /**
     * Passive text message
     * @param msg   Output message entity
     */
    public XmlMsgBuilder text(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Content><![CDATA[")
                .append(msg.getContent()).append("]]></Content>\n");
        return this;
    }

    /**
     * Passive image message
     *
     * @param msg   Output message entity
     */
    public XmlMsgBuilder image(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Image>");
        msgBuf.append("<MediaId><![CDATA[")
                .append(msg.getMediaId())
                .append("]]></MediaId>\n");
        msgBuf.append("</Image>");
        return this;
    }

    /**
     * Passive voice message
     *
     * @param msg   Output message entity
     */
    public XmlMsgBuilder vioce(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Voice>");
        msgBuf.append("<MediaId><![CDATA[")
                .append(msg.getMediaId())
                .append("]]></MediaId>\n");
        msgBuf.append("</Voice>\n");
        return this;
    }

    /**
     * Passive video message
     *
     * @param msg   Output message entity
     */
    public XmlMsgBuilder video(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Video>");
        msgBuf.append("<MediaId><![CDATA[")
                .append(msg.getMediaId())
                .append("]]></MediaId>\n");
        msgBuf.append("<Title><![CDATA[")
                .append(msg.getTitle())
                .append("]]></Title>\n");
        msgBuf.append("<Description><![CDATA[")
                .append(msg.getDescription())
                .append("]]></Description>\n");
        msgBuf.append("</Video>\n");
        return this;
    }

    /**
     * Passive Music News
     *
     * @param msg   Output message entity
     */
    public XmlMsgBuilder music(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("<Music>");
        msgBuf.append("<Title><![CDATA[")
                .append(msg.getTitle())
                .append("]]></Title>\n");
        msgBuf.append("<Description><![CDATA[")
                .append(msg.getDescription())
                .append("]]></Description>\n");
        msgBuf.append("<MusicUrl><![CDATA[")
                .append(msg.getMusicUrl())
                .append("]]></MusicUrl>\n");
        msgBuf.append("<HQMusicUrl><![CDATA[")
                .append(msg.gethQMusicUrl())
                .append("]]></HQMusicUrl>\n");
        msgBuf.append("<ThumbMediaId><![CDATA[")
                .append(msg.getThumbMediaId())
                .append("]]></ThumbMediaId>\n");
        msgBuf.append("</Music>\n");
        return this;
    }

    /**
     * Passive multi graphic message
     *
     * @param msg   Output message entity
     */
    public XmlMsgBuilder news(OutPutMsg msg) {
        msgPrefix(msg);
        StringBuffer arts_buf = new StringBuffer("<Articles>\n");
        StringBuffer item_buf = new StringBuffer();
            for (Article art : msg.getArticles()) {
                item_buf.setLength(0);
                item_buf.append("<item>\n");
                item_buf.append("<Title><![CDATA[")
                        .append(art.getTitle())
                        .append("]]></Title>\n");
                item_buf.append("<Description><![CDATA[")
                        .append(art.getDescription())
                        .append("]]></Description>\n");
                item_buf.append("<PicUrl><![CDATA[")
                        .append(art.getPicUrl())
                        .append("]]></PicUrl>\n");
                item_buf.append("<Url><![CDATA[")
                        .append(art.getUrl())
                        .append("]]></Url>\n");
                item_buf.append("</item>\n");
                arts_buf.append(item_buf);
            }
        arts_buf.append("</Articles>\n");
        msgPrefix(msg);
        msgBuf.append("<ArticleCount>")
                .append(msg.getArticles().size())
                .append("</ArticleCount>\n");
        msgBuf.append(arts_buf);
        return this;
    }

    /**
     * AESEncryption information
     *
     * @param xml           Message text
     * @param msgSignature  Message signature
     * @param timeStamp     time stamp
     * @param nonce         Random character
     */
    public String encrypt(String xml, String msgSignature,
                                 String timeStamp, String nonce) {

        msgBuf.setLength(0);
        msgBuf.append("<xml>\n");
        msgBuf.append("<Encrypt><![CDATA[")
                .append(xml)
                .append("]]></Encrypt>\n");
        msgBuf.append("<MsgSignature><![CDATA[")
                .append(msgSignature)
                .append("]]></MsgSignature>\n");
        msgBuf.append("<TimeStamp>")
                .append(timeStamp)
                .append("</TimeStamp>\n");
        msgBuf.append("<Nonce><![CDATA[")
                .append(nonce)
                .append("]]></Nonce>\n");
        msgBuf.append("</xml>");
        return  msgBuf.toString();
    }

    /**
     * Create reply message
     *
     * @return  Reply message
     */
    public String build() {
        msgBuf.append("</xml>");
        System.out.println("=======auto reply xml:"+msgBuf.toString());
        return msgBuf.toString();
    }
}