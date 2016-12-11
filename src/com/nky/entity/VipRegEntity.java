package com.nky.entity;

import java.util.Date;

import com.net.jfinal.JFinalEntity;
import com.net.jfinal.TableBind;
@TableBind(name="vip_reg")
public class VipRegEntity  extends JFinalEntity implements java.io.Serializable{
	private	String	orderid	;//	订单号
	private	String	status	;//	支付状态
	private	String	orderfee	;//	
	private	Date	payrtime	;//	
	private	String	ordertime	;//	
	private	String	vip_code	;//	
	private	String	certtypeno	;//	
	private	String	idcard	;//	
	private	String	patientname	;//	
	private	String	patientsex	;//	
	private	String	patientbirthday	;//	
	private	String	contactphone	;//	
	private	String	familyaddress	;//	
	private	String	docid	;//	
	private	String	deptid	;//	
	private	String	hosid	;//	
	private	String	ghdesc	;//	
	private	String	scheduleid	;//	
	private	String	partscheduleid	;//	
	private	String	serial	;//	
	private	String	outpdate	;//	
	private	String	orderconfirmsms	;//	
	private	Date	canceltime	;//	
	private	String	cancelreason	;//	
	private	String	ret	;//	
	private	String	msg	;//	
	private	String	parttime	;//	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderfee() {
		return orderfee;
	}
	public void setOrderfee(String orderfee) {
		this.orderfee = orderfee;
	}
	public Date getPayrtime() {
		return payrtime;
	}
	public void setPayrtime(Date payrtime) {
		this.payrtime = payrtime;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getVip_code() {
		return vip_code;
	}
	public void setVip_code(String vip_code) {
		this.vip_code = vip_code;
	}
	public String getCerttypeno() {
		return certtypeno;
	}
	public void setCerttypeno(String certtypeno) {
		this.certtypeno = certtypeno;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getPatientsex() {
		return patientsex;
	}
	public void setPatientsex(String patientsex) {
		this.patientsex = patientsex;
	}
	public String getPatientbirthday() {
		return patientbirthday;
	}
	public void setPatientbirthday(String patientbirthday) {
		this.patientbirthday = patientbirthday;
	}
	public String getContactphone() {
		return contactphone;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	public String getFamilyaddress() {
		return familyaddress;
	}
	public void setFamilyaddress(String familyaddress) {
		this.familyaddress = familyaddress;
	}
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getHosid() {
		return hosid;
	}
	public void setHosid(String hosid) {
		this.hosid = hosid;
	}
	public String getGhdesc() {
		return ghdesc;
	}
	public void setGhdesc(String ghdesc) {
		this.ghdesc = ghdesc;
	}
	public String getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}
	public String getPartscheduleid() {
		return partscheduleid;
	}
	public void setPartscheduleid(String partscheduleid) {
		this.partscheduleid = partscheduleid;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getOutpdate() {
		return outpdate;
	}
	public void setOutpdate(String outpdate) {
		this.outpdate = outpdate;
	}
	public String getOrderconfirmsms() {
		return orderconfirmsms;
	}
	public void setOrderconfirmsms(String orderconfirmsms) {
		this.orderconfirmsms = orderconfirmsms;
	}
	public Date getCanceltime() {
		return canceltime;
	}
	public void setCanceltime(Date canceltime) {
		this.canceltime = canceltime;
	}
	public String getCancelreason() {
		return cancelreason;
	}
	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getParttime() {
		return parttime;
	}
	public void setParttime(String parttime) {
		this.parttime = parttime;
	}
	
	
	
	
}