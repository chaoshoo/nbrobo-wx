package com.nky.entity;

import java.util.Date;

import com.net.jfinal.JFinalEntity;
import com.net.jfinal.TableBind;
@TableBind(name="hospital")
public class HospitalEntity extends JFinalEntity{
	private	String	code	;//	Hospital code
	private	String	name	;//	Hospital name
	private	String	pic	;//	Hospital picture
	private	String	lever	;//	level
	private	String	area	;//	Provincial city
	private	String	address	;//	Detailed address
	private	String	mobile	;//	Telephone
	private	String	website	;//	website
	private	String	traffic	;//	traffic
	private	String	info	;//	Hospital profile
	private	String	feature	;//	Hospital characteristics
	private	Date	create_time	;//	Created time

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getLever() {
		return lever;
	}
	public void setLever(String lever) {
		this.lever = lever;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}