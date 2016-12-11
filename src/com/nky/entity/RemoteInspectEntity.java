package com.nky.entity;

import java.util.Date;

import com.net.jfinal.JFinalEntity;
import com.net.jfinal.TableBind;
@TableBind(name="remote_inspect")
public class RemoteInspectEntity  extends JFinalEntity implements java.io.Serializable{
	private	String	code	;//	远程诊断编码
	private	String	vip_code	;//	会员编码
	private	String	doctor_code	;//	医生编码
	private	String	hospital_code	;//	医院编码
	private	Date	order_time	;//	预约时间
	private	Date	affirm_time	;//	医生确认时间
	private	Integer	isZd	;//	是否应诊 1,处理，0 不处理
	private	Integer	isDeal	;//	是否处理 1,处理，0 不处理
	private	Date	zd_begin_time	;//	诊断开始时间
	private	Date	zd_end_Time	;//	诊断结束时间
	private	Date	create_time	;//	创建时间
	private String remark;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
	public String getHospital_code() {
		return hospital_code;
	}
	public void setHospital_code(String hospital_code) {
		this.hospital_code = hospital_code;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Date getAffirm_time() {
		return affirm_time;
	}
	public void setAffirm_time(Date affirm_time) {
		this.affirm_time = affirm_time;
	}
	public Integer getIsZd() {
		return isZd;
	}
	public void setIsZd(Integer isZd) {
		this.isZd = isZd;
	}
	public Integer getIsDeal() {
		return isDeal;
	}
	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}
	public Date getZd_begin_time() {
		return zd_begin_time;
	}
	public void setZd_begin_time(Date zd_begin_time) {
		this.zd_begin_time = zd_begin_time;
	}
	public Date getZd_end_Time() {
		return zd_end_Time;
	}
	public void setZd_end_Time(Date zd_end_Time) {
		this.zd_end_Time = zd_end_Time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}