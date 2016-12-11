package com.net.wx.vo;

/**
 * WeChat template message
 */
public class Template {

    /**
     * Default color(blue)
     */
    @SuppressWarnings("unused")
	private String DEFAULT_COLOR = "#119EF3";

    /**
     * Template field name
     */
    private String name;

    /**
     * Display color
     */
    private String color;

    /**
     * Display data
     */
    private String value;

    public Template() {
    }

    public Template(String name, String color, String value) {
        this.name = name;
        this.color = color;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Assembly template data
     */
    public String templateData() {
        StringBuffer data = new StringBuffer("\"" + name + "\":{");
        data.append("\"value\":\"").append(value).append("\",");
        data.append("\"color\":\"").append(color).append("\"}");
        return data.toString();
    }

}