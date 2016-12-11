package com.net.wx.util;

import com.net.wx.common.WxMsgType;
import com.net.wx.vo.Article;
import com.net.wx.vo.Article2;
import com.net.wx.vo.OutPutMsg;
import com.net.wx.vo.Template;



/**
 * JsonTool class
 */
public class JsonMsgBuilder {

    private final StringBuffer msgBuf = new StringBuffer("{");

    /**
     * Establish
     */
    public static JsonMsgBuilder create() {
        return new JsonMsgBuilder();
    }

    /**
     * Create message body prefix
     *
     * @param msg   Customer service message entity
     */
    void msgPrefix(OutPutMsg msg) {
        msgBuf.append("\"touser\":\"")
                .append(msg.getToUserName())
                .append("\",");
        msgBuf.append("\"msgtype\":\"")
                .append(msg.getMsgType())
                .append("\",");
    }

    void msgSuffix(OutPutMsg msg) {
        msgBuf.append("\"msgtype\":\"").append(msg.getMsgType()).append("\"");
    }

    /**
     * Text customer service message
     *
     * @param msg   Customer service message entity
     */
    public JsonMsgBuilder text(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("\"text\": {");
        msgBuf.append(" \"content\":\"")
                .append(msg.getContent())
                .append("\"");
        msgBuf.append("}");
        return this;
    }

    /**
     * Image customer service message
     *
     * @param msg   Customer service message entity
     */
    public JsonMsgBuilder image(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("\"image\": {");
        msgBuf.append(" \"media_id\":\"")
                .append(msg.getMediaId())
                .append("\"");
        msgBuf.append("}");
        return this;
    }

    /**
     * Voice customer service
     *
     * @param msg   Customer service message entity
     */
    public JsonMsgBuilder voice(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("\"voice\": {");
        msgBuf.append(" \"media_id\":\"")
                .append(msg.getMediaId())
                .append("\"");
        msgBuf.append("}");
        return this;
    }

    /**
     * Video customer service
     *
     * @param msg   Customer service message entity
     */
    public JsonMsgBuilder video(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("\"video\": {");
        msgBuf.append(" \"media_id\":\"")
                .append(msg.getMediaId())
                .append("\",");
        msgBuf.append(" \"thumb_media_id\":\"")
                .append(msg.getThumbMediaId())
                .append("\",");
        msgBuf.append(" \"title\":\"")
                .append(msg.getTitle())
                .append("\",");
        msgBuf.append(" \"description\":\"")
                .append(msg.getDescription())
                .append("\"");
        msgBuf.append("}");
        return this;
    }

    /**
     * Music customer service
     *
     * @param msg   Customer service message entity
     */
    public JsonMsgBuilder music(OutPutMsg msg) {
        msgPrefix(msg);
        msgBuf.append("\"music\": {");
        msgBuf.append(" \"title\":\"")
                .append(msg.getTitle())
                .append("\",");
        msgBuf.append(" \"description\":\"")
                .append(msg.getDescription())
                .append("\",");
        msgBuf.append(" \"musicurl\":\"")
                .append(msg.getMusicUrl())
                .append("\",");
        msgBuf.append(" \"hqmusicurl\":\"")
                .append(msg.gethQMusicUrl())
                .append("\",");
        msgBuf.append(" \"thumb_media_id\":\"")
                .append(msg.getThumbMediaId())
                .append("\"");
        msgBuf.append("}");
        return this;
    }

    /**
     * Multi graphic customer service news
     *
     * @param msg   Customer service message entity
     */
    public JsonMsgBuilder news(OutPutMsg msg) {
        msgPrefix(msg);
        StringBuffer arts_buf = new StringBuffer("\"articles\": [");
        StringBuffer art_buf = new StringBuffer();
        for (Article art : msg.getArticles()) {
            art_buf.setLength(0);
            art_buf.append("{");
            art_buf.append(" \"title\":\"")
                    .append(art.getTitle())
                    .append("\",");
            art_buf.append(" \"description\":\"")
                    .append(art.getDescription())
                    .append("\",");
            art_buf.append(" \"picurl\":\"")
                    .append(art.getPicUrl())
                    .append("\",");
            art_buf.append(" \"url\":\"")
                    .append(art.getUrl());
            art_buf.append("\"},");
        }
        arts_buf.append(art_buf.substring(0, art_buf.lastIndexOf(",")));
        arts_buf.append("]");
        msgBuf.append("\"news\": {");
        msgBuf.append(arts_buf);
        msgBuf.append("}");
        return this;
    }

    /**
     * Template message
     *
     * @param openId    Recipient
     * @param templateId    TemplateID
     * @param topColor  Top color
     * @param url   Link address
     * @param templates Template data
     */
    public JsonMsgBuilder template(String openId, String templateId,
                                   String topColor, String url, Template... templates) {
        msgBuf.append("\"touser\":\"").append(openId).append("\",");
        msgBuf.append("\"template_id\":\"").append(templateId).append("\",");
        msgBuf.append("\"url\":\"").append(url).append("\",");
        msgBuf.append("\"topcolor\":\"").append(topColor).append("\",");
        msgBuf.append("\"data\":{");
        StringBuffer data = new StringBuffer("");
        for (Template t : templates) {
           data.append(t.templateData()).append(",");
        }
        msgBuf.append(data.substring(0, data.lastIndexOf(",")));
        msgBuf.append("}");
        return this;
    }

    /**
     * Upload multiple pictures and text messages
     *
     * @param articles2s    Graphic message
     */
    public JsonMsgBuilder uploadNews(Article2... articles2s) {
        msgBuf.append("\"articles\":[");
        StringBuffer art2_buf = new StringBuffer();
        for (Article2 art2 : articles2s) {
            art2_buf.append("{");
            art2_buf.append("\"thumb_media_id\":\"").append(art2.getMediaId()).append("\",");
            art2_buf.append("\"author\":\"").append(art2.getAuthor()).append("\",");
            art2_buf.append("\"title\":\"").append(art2.getTitle()).append("\",");
            art2_buf.append("\"content_source_url\":\"").append(art2.getSourceUrl()).append("\",");
            art2_buf.append("\"content\":\"").append(art2.getContent()).append("\",");
            art2_buf.append("\"digest\":\"").append(art2.getDigest()).append("\",");
            art2_buf.append("\"show_cover_pic\":\"").append(art2.getShowCover()).append("\"");
            art2_buf.append("},");
        }
        msgBuf.append(art2_buf.substring(0, art2_buf.lastIndexOf(",")));
        msgBuf.append("]");
        return this;
    }
    
    /**
     * Update permanent graphic material
     *
     * @param articles2s    Graphic message
     */
    public JsonMsgBuilder updateNews(String updateMediaId,int index,Article2 art2) {
    	msgBuf.append("\"media_id\":\"").append(updateMediaId).append("\",");
    	msgBuf.append("\"index\":").append(index).append(",");
    	msgBuf.append("\"articles\":{");
    	msgBuf.append("\"thumb_media_id\":\"").append(art2.getMediaId()).append("\",");
    	msgBuf.append("\"author\":\"").append(art2.getAuthor()).append("\",");
    	msgBuf.append("\"title\":\"").append(art2.getTitle()).append("\",");
    	msgBuf.append("\"content_source_url\":\"").append(art2.getSourceUrl()).append("\",");
    	msgBuf.append("\"content\":\"").append(art2.getContent()).append("\",");
    	msgBuf.append("\"digest\":\"").append(art2.getDigest()).append("\",");
		msgBuf.append("\"show_cover_pic\":\"").append(art2.getShowCover()).append("\"");
		msgBuf.append("}");
    	return this;
    }

	public JsonMsgBuilder delMedia(String mediaId) {
		msgBuf.append("\"media_id\":\"").append(mediaId).append("\"");
		return this;
	}

    /**
     * Upload video
     *
     * @param mediaId   Multi-MediaID
     * @param title Video title
     * @param description   Video description
     */
    public JsonMsgBuilder uploadVideo(String mediaId, String title, String description) {
        msgBuf.append("\"media_id\":\"").append(mediaId).append("\",");
        msgBuf.append("\"title\":\"").append(title).append("\",");
        msgBuf.append("\"description\":\"").append(description).append("\"");
        return this;
    }

    /**
     * Mass message
     *
     * @param msg Output message entity
     */
    public JsonMsgBuilder sendAll(OutPutMsg msg) {
        if (msg.getToUsers().isEmpty()) {
          msgBuf.append("\"filter\":{")
                  .append("\"group_id\":\"")
                  .append(msg.getGroupId())
                  .append("\"},");
        } else {
            msgBuf.append("\"touser\":[");
            StringBuffer users_buf = new StringBuffer();
            for (String touser : msg.getToUsers()) {
                users_buf.append("\"").append(touser).append("\",");
            }
            msgBuf.append(users_buf.substring(0, users_buf.lastIndexOf(",")));
            msgBuf.append("],");
        }

        WxMsgType type = WxMsgType.valueOf(msg.getMsgType());
        switch (type) {
            case text:
                msgBuf.append("\"text\":{\"content\":\"").append(msg.getContent()).append("\"}");
                break;
            case image:
                msgBuf.append("\"image\":{\"media_id\":\"").append(msg.getMediaId()).append("\"}");
                break;
            case voice:
                msgBuf.append("\"voice\":{\"media_id\":\"").append(msg.getMediaId()).append("\"}");
                break;
            case mpvideo:
                msgBuf.append("\"mpvideo\":{\"media_id\":\"").append(msg.getMediaId()).append("\"}");
                break;
            case video:
                msgBuf.append("\"video\":{\"media_id\":\"")
                        .append(msg.getMediaId())
                        .append("\",\"title\":\"")
                        .append(msg.getTitle())
                        .append("\",\"description\":\"")
                        .append(msg.getDescription()).append("\"}");
                break;
            case mpnews:
                msgBuf.append("\"mpnews\":{\"media_id\":\"").append(msg.getMediaId()).append("\"}");
                break;
            default:
                break;
        }

        msgBuf.append(",\"msgtype\":\"").append(msg.getMsgType()).append("\"");

        return this;
    }

    public String build() {
        msgBuf.append("}");
        return msgBuf.toString();
    }

}