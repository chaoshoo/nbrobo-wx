package com.nky.entity;

import java.util.Date;

import com.net.jfinal.JFinalEntity;
import com.net.jfinal.TableBind;

@TableBind(name="t_vip_log")
public class VipLogEntity  extends JFinalEntity implements java.io.Serializable{
	  private String vip_code;// 'Member code',
	  private Date create_time;// 'Created time',
	  private String oper;// 'Operation content',
	public String getVip_code() {
		return vip_code;
	}
	public void setVip_code(String vip_code) {
		this.vip_code = vip_code;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	  
}