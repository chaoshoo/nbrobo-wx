package com.net.wx.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * WeChat custom menu entity
 */
public class Menu {

    public static final String CLICK = "click";
    public static final String VIEW = "view";
    public static final String SCANCODE_PUSH = "scancode_push";
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
    public static final String PIC_SYSPHOTO = "pic_sysphoto";
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    public static final String PIC_WEIXIN = "pic_weixin";
    public static final String LOCATION_SELECT = "location_select";

	/**
	 * menu title，not exceeding16Bytes，Sub menu does not exceed40Bytes
	 */
	private String name;

	/**
	 * Response action type
	 * click:Click push events
	 * view:JumpURL
	 * scancode_push：Sweep push event
	 * scancode_waitmsg：Sweep push event
	 * pic_sysphoto：Pop up system to take pictures
	 * pic_photo_or_album：Pop up a photo or a photo album
	 * pic_weixin：Pop up WeChat photo album
	 * location_select：Pop-up location selector
	 * 
	 */
	private String type;

	/**
	 * Click type menuKEYvalue，Push for message interface，not exceeding128byte 
	 */
	private String key;

	/**
	 * Web link，The user can click on the menu to open the link，not exceeding256byte
	 */
	private String url;

	/**
	 * Two level menu
	 */
    @JSONField(name = "sub_button")
	private List<Menu> subButtons;

    public Menu() {
    }

    public Menu(String name) {
        this.name = name;
    }

    /**
     * Constructor
     *
     * @param name  Menu name
     * @param type  Menu type
     * @param val   KEYvalue/URL
     */
    public Menu(String name, String type, String val) {
        this.name = name;
        this.type = type;
        if (VIEW.equals(type)) {
            this.url = val;
        } else {
            this.key = val;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getSubButtons() {
        return subButtons;
    }

    public void setSubButtons(List<Menu> subButtons) {
        this.subButtons = subButtons;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", subButtons=" + subButtons +
                '}';
    }
}