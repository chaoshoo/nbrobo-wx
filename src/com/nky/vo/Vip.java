package com.nky.vo;

import org.apache.commons.beanutils.BeanUtils;

import com.nky.entity.VipEntity;

public class Vip extends VipEntity{
	//性别
	private String sexstr;
	//区域名称
	private String areaname;

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getSexstr() {
		return sexstr;
	}

	public void setSexstr(String sexstr) {
		this.sexstr = sexstr;
	}
	
	
}
