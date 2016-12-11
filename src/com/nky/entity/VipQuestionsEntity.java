package com.nky.entity;

import java.util.Date;

import com.net.jfinal.JFinalEntity;
import com.net.jfinal.TableBind;
@TableBind(name="vip_questions")
public class VipQuestionsEntity  extends JFinalEntity implements java.io.Serializable{
	private	String	vip_code	;//	会员编码
	private	String	doctor_code	;//	医生编码
	private	String	title	;//	问题标题
	private	String	content	;//	问题描述
	private	String	attachement	;//	问题附件
	private	Date	create_time	;//	创建时间
	public String getVip_code() {
		return vip_code;
	}
	public void setVip_code(String vip_code) {
		this.vip_code = vip_code;
	}
	public String getDoctor_code() {
		return doctor_code;
	}
	public void setDoctor_code(String doctor_code) {
		this.doctor_code = doctor_code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttachement() {
		return attachement;
	}
	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}