package com.net.entity.bo;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Data {

	/**
	 * Operation code 1:Success ,0:fail
	 */
	private Integer code=1;
	
	private String msg;
	
	public Data() {}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}