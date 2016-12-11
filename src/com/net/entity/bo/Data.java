package com.net.entity.bo;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Data {

	/**
	 * 操作代码 1:成功 ,0:失败
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
