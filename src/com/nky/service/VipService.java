package com.nky.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.ServiceConstants;
import com.net.entity.Area;
import com.net.jfinal.JFinalDb;
import com.net.singleton.AreaSingleton;
import com.net.singleton.DicSingleton;
import com.net.util.MD5Util;
import com.net.util.PubMethod;
import com.nky.entity.VipEntity;
import com.nky.entity.VipLogEntity;
import com.nky.vo.Vip;

@Service
public class VipService {

	// 排行榜上榜人数
	private static final int paihangCount = 5;

	/**
	 * 用户登录
	 * 
	 * @param tel
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public VipEntity isLogin(String tel, String password, String openId) throws Exception {
		VipEntity vip = new VipEntity();
		vip.setMobile(tel);
		String pwdmd5 = null;
		if (password != null) {
			pwdmd5 = MD5Util.MD5(password, "utf-8").toLowerCase();
		}
		vip.setLogin_password(pwdmd5);
		vip.setWxopenid(openId);
//		vip.setIsvalid(1);
		vip = JFinalDb.findFirst(vip);
		return vip;
	}
	

	public VipEntity getVip(String openId) throws Exception {
		VipEntity vip = new VipEntity();
		vip.setWxopenid(openId);
		vip = JFinalDb.findFirst(vip);
		return vip;
	}

	/**
	 * 修改openId
	 * 
	 * @param pe
	 * @return
	 * @throws Exception
	 */
	public boolean setOpenId(Long id, String openId,String code) throws Exception {
		//微信只能绑定一个账号  如果已经有了绑定  先解除
		Db.update("update t_vip set wxopenid='' where wxopenid ='"+openId+"' or wxopenid ='"+openId+"-' ");
		VipEntity entity = new VipEntity();
		entity.setId(id);
		entity.setWxopenid(openId);
		entity.setWx_code(code);
		return JFinalDb.update(entity);
	}

	/**
	 * 判断手机号是否存在登录表里面
	 */
	public boolean isExistTel(String tel) {
		List<Record> lits = Db.find("select * from t_vip where isvalid=1 and mobile ='" + tel + "' ");
		return lits.size() > 0 ? true : false;
	}

	/**
	 * 修改密码
	 */
	public void updatePWD(String pwd, String tel, String pwdold) {
		String pwdmd5 = MD5Util.MD5(pwd, "utf-8");
		String pwdoldmd5 = MD5Util.MD5(pwdold, "utf-8");
		Db.update("update t_vip set login_password='" + pwdmd5 + "' where mobile='" + tel + "' and login_password='"
				+ pwdoldmd5 + "'");
	}

	/**
	 * 获取验证码 6位数
	 */
	public static String randString(int length) {
		Random r = new Random();
		String ssource = "0123456789";
		char[] src = ssource.toCharArray();
		char[] buf = new char[length];
		int rnd;
		for (int i = 0; i < length; i++) {
			rnd = Math.abs(r.nextInt()) % src.length;
			buf[i] = src[rnd];
		}
		return new String(buf);
	}

	public static String runVerifyCode() {
		String VerifyCode = randString(6);
		return VerifyCode;
	}

	/**
	 * 修改最后登录时间，新增药师登录日志
	 * 
	 * @param id
	 */
	public void updatelasttime(String vip_code) {
		try {
			// 修改最后登录时间
			// Db.update("update t_vip set last_time=now() where id="+id);
			// 新增登录日志
			VipLogEntity log = new VipLogEntity();
			log.setVip_code(vip_code);
			log.setCreate_time(new Date());
			log.setOper("login in");
			JFinalDb.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vip getVipInfo(String openId) {
		// 取用户个人信息
		Record record = JFinalDb.findBySqlid("VipSql_index_getvip", openId).get(0);
		Vip vip = JFinalDb.getEntityByRecordColumn(record, Vip.class);
		if (StringUtils.isEmpty(vip.getHeard_img_url())) {
			vip.setHeard_img_url(ServiceConstants.default_heard_img_url);
		}
		if (StringUtils.isEmpty(vip.getSex())) {
			vip.setSexstr("");
		} else {
			vip.setSexstr(DicSingleton.getInstance().getValueBykeyDic("gender", vip.getSex()));
		}
		if (StringUtils.isEmpty(vip.getArea())) {
			vip.setAreaname("");
		} else {
			Area a = AreaSingleton.getInstance().getArea(vip.getArea());
			if (a != null) {
				vip.setAreaname(a.getFull_name());
			} else {
				vip.setAreaname("");
			}
		}
		return vip;
	}
	
	public boolean saveDoctorVip(String doctorcode,String vipcode){
		List<Record> list = Db.find("select * from doctor_vip where doctor_code=? and vip_code=?",doctorcode,vipcode);
		if(!list.isEmpty()){
			return true;
		}
		Record record = new Record();
		record.set("doctor_code",doctorcode);
		record.set("vip_code", vipcode);
		record.set("create_time",new Date());
		return Db.save("doctor_vip", record);
	}
}
