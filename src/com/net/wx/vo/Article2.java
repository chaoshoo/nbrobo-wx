package com.net.wx.vo;

/**
 * Advanced mass news text
 */
public class Article2 {

    /**
     * Thumbnailmedia_id
     */
    private String mediaId;
    /**
     * Graphic message author
     */
    private String author;
    /**
     * Title
     */
    private String title;
    /**
     * Graphic message page click“Read the original text”After the page
     */
    private String sourceUrl;
    /**
     * Content message page，SupportHTMLLabel
     */
    private String content;
    /**
     * Description of picture and text message
     */
    private String digest;
    /**
     * Display cover，1In order to show，0In order not to show
     */
    private int showCover;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getShowCover() {
        return showCover;
    }

    public void setShowCover(int showCover) {
        this.showCover = showCover;
    }

	@Override
    public String toString() {
        return "Article2{" +
                "mediaId='" + mediaId + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", content='" + content + '\'' +
                ", digest='" + digest + '\'' +
                ", showCover=" + showCover +
                '}';
    }
}