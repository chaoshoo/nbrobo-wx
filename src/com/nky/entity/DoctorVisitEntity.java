package com.nky.entity;

import java.util.Date;

import com.net.jfinal.JFinalEntity;
import com.net.jfinal.TableBind;
@TableBind(name="doctor_visit")
public class DoctorVisitEntity extends JFinalEntity {
	private	String	doctor_code	;//	医生编码
	private	Long	visit_user	;//	被访问者id
	private	Date	begin_time	;//	访问开始时间
	private	Date	end_time	;//	访问结束时间
	private	Date	create_time	;//	创建时间
	private	String	content	;//	访问内容
	public String getDoctor_code() {
		return doctor_code;
	}
	public void setDoctor_code(String doctor_code) {
		this.doctor_code = doctor_code;
	}
	public Long getVisit_user() {
		return visit_user;
	}
	public void setVisit_user(Long visit_user) {
		this.visit_user = visit_user;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
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
