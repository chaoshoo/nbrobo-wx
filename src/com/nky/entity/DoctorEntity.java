package com.nky.entity;

import java.util.Date;

import com.net.jfinal.JFinalEntity;
import com.net.jfinal.TableBind;
@TableBind(name="doctor")
public class DoctorEntity extends JFinalEntity{
	private	String	code	;//	医生编码
	private	String	name	;//	医生名字
	private	String	area	;//	省市区
	private	String	address	;//	详细地址
	private	String	tel	;//	电话
	private	Integer	sex	;//	性别,1男，0女
	private	Date	birthday	;//	生日
	private	String	hospital_code	;//	医院编号
	private	String	office_code	;//	科室编号
	private	String	edu	;//	学历
	private	String	title	;//	职称
	private	String	special	;//	特长
	private	String	info	;//	简介
	private	Integer	isvalid	;//	是否有效,1有效，0无效
	private	Date	create_time	;//	创建时间
	private String password;
	private String check_desc;
	private Date update_time;
	private String pic;
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getHospital_code() {
		return hospital_code;
	}
	public void setHospital_code(String hospital_code) {
		this.hospital_code = hospital_code;
	}
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheck_desc() {
		return check_desc;
	}
	public void setCheck_desc(String check_desc) {
		this.check_desc = check_desc;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
}
