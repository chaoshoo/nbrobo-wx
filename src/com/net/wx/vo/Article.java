package com.net.wx.vo;

/**
 * Multi graphic message entity
 */
public class Article {

	/**
	 * Graphic message header
	 */
	private String title;

	/**
	 * Graphic message description
	 */
	private String description;

	/**
	 * pictures linking，SupportJPG、PNGformat，Better effect for the larger picture360*200，A small map200*200 
	 */
	private String picUrl;

	/**
	 * Click on the link
	 */
	private String url;

    public Article() {
    }

    public Article(String title, String description, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}