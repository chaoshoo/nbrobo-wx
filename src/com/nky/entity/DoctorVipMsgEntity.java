package com.nky.entity;

import java.util.Date;

import com.net.jfinal.JFinalEntity;
import com.net.jfinal.TableBind;
@TableBind(name="doctor_vip_msg")
public class DoctorVipMsgEntity extends JFinalEntity {
	private	String	doctor_code	;//	医生编码
	private	Long	vip_code	;//	会员编码
	private	Date	create_time	;//	创建时间
	private	String	content	;//	内容
	public String getDoctor_code() {
		return doctor_code;
	}
	public void setDoctor_code(String doctor_code) {
		this.doctor_code = doctor_code;
	}
	public Long getVip_code() {
		return vip_code;
	}
	public void setVip_code(Long vip_code) {
		this.vip_code = vip_code;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
