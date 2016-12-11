package com.nky.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.ServiceConstants;
import com.net.entity.bo.Data;
import com.net.entity.bo.ScriptPage;
import com.net.jfinal.JFinalDb;
import com.net.jfinal.JdbcSql;
import com.net.singleton.DicSingleton;
import com.net.singleton.SysId;
import com.net.util.DateUtil;
import com.net.util.JsonUtil;
import com.net.util.ListUtil;
import com.nky.entity.DoctorEntity;
import com.nky.entity.HospitalEntity;
import com.nky.entity.OfficeEntity;
import com.nky.entity.RemoteInspectEntity;
import com.nky.entity.VipEntity;
import com.nky.entity.VipQuestionsEntity;
import com.nky.service.OfficeSingleton;
import com.nky.service.VipService;

@RequestMapping("/yuyue")
@Controller
public class YuYueAction extends WxBaseAction{

	@Autowired
	private VipService vipService;

	@RequestMapping("/keshi")
	public String keshi(HttpServletRequest request){
		String search_input = request.getParameter("search_input");
		if(StringUtils.isEmpty(search_input)){
			request.setAttribute("search_input", "");
		}else{
			request.setAttribute("search_input", search_input);
		}
		String search_input2 = request.getParameter("search_input2");
		if(StringUtils.isEmpty(search_input2)){
			request.setAttribute("search_input2", "");
		}else{
			request.setAttribute("search_input2", search_input2);
		}
		String type = request.getParameter("type");
		if(StringUtils.isEmpty(type)){
			request.setAttribute("type", "keshi");
		}else{
			request.setAttribute("type", type);
		}
		return "yuyue_list";
	}
	
	@RequestMapping("/keshilist")
	@ResponseBody
	public String keshilist(HttpServletRequest request,String name){
		List list = Lists.newArrayList();
		try {
			String sql = "select id,name,code,pic from office";
			if(!StringUtils.isEmpty(name)){
				sql += " where name like '%"+name+"%'";
			}
			Map<String,Object> param = Maps.newHashMap();
			//每页大小为16 暂时写死
			int pageNo = getPageNo(request);
			ScriptPage sp = JFinalDb.findPage(pageNo, 16, sql, param, null);
			List<Map<String,Object>> listr = sp.getRows();
			if(!listr.isEmpty()){
				for (Map<String,Object> m: listr) {
					if(m.get("pic")==null || StringUtils.isEmpty(m.get("pic")+"")){
						m.put("pic", ServiceConstants.default_img_keshi);
					}
				}
				return JSON.toJSONString(listr);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
	
	@RequestMapping("/yiyuanlist")
	@ResponseBody
	public String yiyuanlist(HttpServletRequest request,String search){
		List list = Lists.newArrayList();
		try {
			String sql = "select id,name,code,pic,lever,info from hospital";
			if(!StringUtils.isEmpty(search)){
				sql += " where name like '%"+search.trim()+"%'";
			}
			Map<String,Object> param = Maps.newHashMap();
			//每页大小为16 暂时写死
			int pageNo = getPageNo(request);
			ScriptPage sp = JFinalDb.findPage(pageNo, 8, sql, param, null);
			List<Map<String,Object>> listr = sp.getRows();
			Map<String, String> hospital_lever = DicSingleton.getInstance().getDicMap("hospatil_lever");
			if(!listr.isEmpty()){
				for (Map<String, Object> map : listr) {
					map.put("LEVERSTR", hospital_lever.get(map.get("LEVER") + ""));
					if(map.get("pic")==null || StringUtils.isEmpty(map.get("pic")+"")){
						map.put("pic", ServiceConstants.default_img_yiyuan);
					}
				}
				return JSON.toJSONString(listr);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
		
	@RequestMapping("/doctor")
	public String doctor(HttpServletRequest request){
		String office_code = request.getParameter("office_code");
		String hospital_code = request.getParameter("hospital_code");
		String my = request.getParameter("my");
		if(StringUtils.isEmpty(my)){
			request.setAttribute("my", "");
		}else{
			request.setAttribute("my", "yes");
		}
		String search_input = request.getParameter("search_input");
		if(StringUtils.isEmpty(search_input)){
			request.setAttribute("search_input", "");
		}else{
			request.setAttribute("search_input", search_input);
		}
		request.setAttribute("office_code", office_code==null?"":office_code);
		request.setAttribute("hospital_code", hospital_code==null?"":hospital_code);
		return "doctor_list";
	}

	@RequestMapping("/doctorlist")
	@ResponseBody
	public String doctorlist(HttpServletRequest request,String office_code,String hospital_code){
		List list = Lists.newArrayList();
		try {
			Map<String,Object> param = Maps.newHashMap();
			if(!StringUtils.isEmpty(office_code)){
				param.put("d.office_code", office_code);
			}
			if(!StringUtils.isEmpty(hospital_code)){
				param.put("d.hospital_code", hospital_code);
			}
			String search = request.getParameter("search");
			if(search != null && search.startsWith("D")){
				param.put("LIKE-d.doctor_code", search);
			}else if(!StringUtils.isEmpty(search)){
				param.put("LIKE-d.name", search.trim());
			}
			//每页大小为16 暂时写死
			int pageNo = getPageNo(request);
			ScriptPage sp = null;
			String my = request.getParameter("my");
			if(!StringUtils.isEmpty("my") && "yes".equals(my)){
				VipEntity vip = getVip(request);
				sp = JFinalDb.findPageBySqlid(pageNo, 8, "DoctorSql_mylist", param, null,vip.getVip_code());
			}else{
				sp = JFinalDb.findPageBySqlid(pageNo, 8, "DoctorSql_list", param, null);
			}	
			List<Map<String,Object>> listr = sp.getRows();
			//需要对数据做下处理
			Map<String, String> sexdic = DicSingleton.getInstance().getDicMap("gender");
			Map<String, String> doctor_title = DicSingleton.getInstance().getDicMap("doctor_title");
			if(!listr.isEmpty()){
				for (Map<String, Object> map : listr) {
					map.put("SEXSTR", sexdic.get(map.get("SEX") + ""));
					map.put("TITLESTR", doctor_title.get(map.get("TITLE") + ""));
					if(map.get("PIC") == null || "".equals(map.get("PIC").toString())){
						map.put("PIC", ServiceConstants.default_heard_img_url);
					}
					OfficeEntity office = OfficeSingleton.getInstance().getEntitybykey(map.get("OFFICE_CODE") + "");
					if(office != null){
						map.put("OFFICENAME", office.getName());
					}else{
						map.put("OFFICENAME", "");
					}
				}
				return JsonUtil.toJSONString(listr);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JSON.toJSONString(list);
	}
		
	@RequestMapping("/doctordetail")
	public String doctordetail(HttpServletRequest request,Long id){
		try {
			DoctorEntity d = new DoctorEntity();
			d.setId(id);
			d = JFinalDb.findFirst(d);
			if(StringUtils.isEmpty(d.getPic())){
				d.setPic(ServiceConstants.default_heard_img_url);
			}
			String sexstr = DicSingleton.getInstance().getValueBykeyDic("gender", d.getSex()+"") ;
			String titlename = DicSingleton.getInstance().getValueBykeyDic("doctor_title", d.getTitle()+"") ;
			OfficeEntity office = OfficeSingleton.getInstance().getEntitybykey(d.getOffice_code());
			HospitalEntity hospital = new HospitalEntity();
			hospital.setCode(d.getHospital_code());
			hospital = JFinalDb.findFirst(hospital);
			if(hospital == null){
				request.setAttribute("hospitalname", "");
			}else{
				request.setAttribute("hospitalname", hospital.getName());
			}
			request.setAttribute("sexstr", sexstr == null?"":sexstr);
			request.setAttribute("titlestr", sexstr == null?"":sexstr);
			request.setAttribute("titlename", titlename);
			request.setAttribute("officename", office.getName());
			request.setAttribute("doctor", d);
			//查询是否关注
			String sql = "Select * from doctor_vip where doctor_code=? and vip_code=?";
			Record re = Db.findFirst(sql,d.getCode(),getVip(request).getVip_code());
			if(re == null){
				request.setAttribute("guanzhu", "no");
			}else{
				request.setAttribute("guanzhu", "yes");
			}
			//拼接历史条件
//			String office_code = request.getParameter("office_code");
//			String hospital_code = request.getParameter("hospital_code");
//			office_code = office_code==null?"":office_code;
//			hospital_code = hospital_code==null?"":hospital_code;
//			String param = "office_code="+office_code+"&hospital_code="+hospital_code;
//			request.setAttribute("param", param);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "doctor_detail";
	}
	@RequestMapping("/dialog")
	public String dialog(HttpServletRequest request,String doctorcode,String type){
		request.setAttribute("doctorcode", doctorcode);
		if(type != null && type.equals("liuyan")){
			return "liuyan_detail";
		}else if(type != null && type.equals("yeyue")){
			return "yuyue_detail";
		}
		return "";
	}
	
	@RequestMapping("/saveliuyan")
	@ResponseBody
	public Data saveliuyan(HttpServletRequest request,VipQuestionsEntity entity){
		Data d = new Data();
		try {
			entity.setCreate_time(new Date());
			VipEntity vip = getVip(request);
			vipService.saveDoctorVip(entity.getDoctor_code(), vip.getVip_code());
			entity.setVip_code(vip.getVip_code());
			JFinalDb.save(entity);
			d.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			d.setCode(0);
			d.setMsg("Save failed");
		}
		return d;
	}
	
	@RequestMapping("/zhuijliuyan")
	@ResponseBody
	public Data zhuijliuyan(HttpServletRequest request,String questionid,String answer){
		Data d = new Data();
		try {
			VipEntity vip = getVip(request);
			Record r = new Record();
			r.set("answer_code",vip.getVip_code());
			r.set("create_time",new Date());
			r.set("vip_questions_id",questionid);
			r.set("answer_content", answer);
			boolean flag = Db.save("vip_questions_log", r);
			if(flag){
				d.setCode(1);
			}else{
				d.setCode(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			d.setCode(0);
			d.setMsg("Save failed");
		}
		return d;
	}
	
	@RequestMapping("/saveyuetime")
	@ResponseBody
	public Data saveyuetime(HttpServletRequest request,RemoteInspectEntity entity){
		Data d = new Data();
		try {
			vipService.saveDoctorVip(entity.getDoctor_code(), getVip(request).getVip_code());
			String yuetimestr = request.getParameter("order_time_str");
			System.out.println(yuetimestr);
			entity.setOrder_time(DateUtil.strToDateTime(yuetimestr+":00"));
			entity.setCreate_time(new Date());
			entity.setCode(SysId.getNextYueCode());
			VipEntity vip = getVip(request);
			entity.setVip_code(vip.getVip_code());
			//查询医院的code
			Record record = Db.findFirst("select * from doctor where code='"+entity.getDoctor_code()+"'");
			if(record != null){
				entity.setHospital_code(record.get("hospital_code")+"");
			}
			JFinalDb.save(entity);
			d.setCode(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			d.setCode(0);
			d.setMsg("Save failed");
		}
		return d;
	}
	
	@RequestMapping("/history")
	public String history(HttpServletRequest request,String type){	
		String search_input = request.getParameter("search_input");
		if(StringUtils.isEmpty(search_input)){
			request.setAttribute("search_input", "");
		}else{
			request.setAttribute("search_input", search_input);
		}
		if(type != null && type.equals("liuyan")){
			return "replay_history";
		}else{
			return "yuyue_history";
		}
	}
	@RequestMapping("/historydetail")
	public String historydetail(HttpServletRequest request,String type,String doctorcode) throws Exception{
		request.setAttribute("doctorcode", doctorcode);
		DoctorEntity d = new DoctorEntity();
		d.setCode(doctorcode);
		d = JFinalDb.findFirst(d);
		if(type != null && type.equals("liuyan")){
			String questionid = request.getParameter("questionid");
			Record r = Db.findFirst("select * from vip_questions where id=?",questionid);
			request.setAttribute("questionid", questionid);
			request.setAttribute("title", r.get("title"));
			request.setAttribute("content", r.get("content"));
			request.setAttribute("doctorid", d.getId());
			request.setAttribute("doctorname", d.getName());
			String titlename = DicSingleton.getInstance().getValueBykeyDic("doctor_title", d.getTitle()+"") ;
			request.setAttribute("titlename", titlename);
			request.setAttribute("createtime", DateUtil.dateForString(r.getDate("create_time"), "yyyy-MM-dd HH:mm:ss"));
			return "replay_his_detail";
		}else{
			if(StringUtils.isEmpty(d.getPic())){
				d.setPic(ServiceConstants.default_heard_img_url);
			}
			String sexstr = DicSingleton.getInstance().getValueBykeyDic("gender", d.getSex()+"") ;
			String titlename = DicSingleton.getInstance().getValueBykeyDic("doctor_title", d.getTitle()+"") ;
			OfficeEntity office = OfficeSingleton.getInstance().getEntitybykey(d.getOffice_code());
			HospitalEntity hospital = new HospitalEntity();
			hospital.setCode(d.getHospital_code());
			hospital = JFinalDb.findFirst(hospital);
			if(hospital == null){
				request.setAttribute("hospitalname", "");
			}else{
				request.setAttribute("hospitalname", hospital.getName());
			}
			request.setAttribute("sexstr", sexstr == null?"":sexstr);
			request.setAttribute("titlestr", sexstr == null?"":sexstr);
			request.setAttribute("hospitalname", titlename);
			request.setAttribute("officename", office.getName());
			request.setAttribute("doctor", d);
			return "yuyue_his_detail";
		}
	}
	
	@RequestMapping("/replaydoctorlist")
	@ResponseBody
	public String replaydoctorlist(HttpServletRequest request,String title){
		List list = Lists.newArrayList();
		VipEntity vip = getVip(request);
		if(vip == null){
			//  说明session失效  需要跳转到登录页面
			return "";
		}
		try {
			//每页大小为5 暂时写死
			int pageNo = getPageNo(request);
			String sql = "select v.*,v.id as vip_questions_id from vip_questions v where vip_code=? ";
			if(!StringUtils.isEmpty(title)){
				sql += " and v.title like '%"+title+"%'";
			}
			sql +=" order by id desc";
			ScriptPage sp = JFinalDb.findPageSql(pageNo, 8, sql, vip.getVip_code());
			List<Map<String,Object>> listr = sp.getRows();
			String ids = ListUtil.getListMapIDS(listr, "id");
			List<Record> listans = null;
			if(!StringUtils.isEmpty(ids)){
				sql = JdbcSql.getReplaceSql("YuYueSql_replaylast", ids);
				listans = JFinalDb.find(sql);
			}
			if(listans == null){
				listans = new ArrayList<Record>();
			}
			//查询最后一次留言
			if(!listr.isEmpty()){
				String[] fields = {"answer_content","create_timestr"};
				ListUtil.leftjoin2(listr, listans, "vip_questions_id", fields);
				return JSON.toJSONString(listr);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
	@RequestMapping("/replayhislist")
	@ResponseBody
	public String replayhislist(HttpServletRequest request,String questionid){
		List list = Lists.newArrayList();
		VipEntity vip = getVip(request);
		if(vip == null){
			//  说明session失效  需要跳转到登录页面
		}
		try {
			//每页大小为5 暂时写死
			int pageNo = getPageNo(request);
			ScriptPage sp = JFinalDb.findPageBySqlid(pageNo, 8, "YuYueSql_replayhis", null, null,questionid);
			List<Map<String,Object>> listr = sp.getRows();
			//查询最后一次留言
			if(!listr.isEmpty()){
				List<Map<String,Object>> list0 = Lists.newArrayList();
				//sql 是按倒序取    返回时调整顺序
				for (int i = listr.size()-1; i >= 0; i--) {
					Map<String,Object> m = listr.get(i);
					if(m.get("ANSWER_CODE") != null && m.get("ANSWER_CODE").toString().equals(vip.getVip_code())){
						m.put("DOCTORNAME","I");
					}
					list0.add(m);
				}
				return JSON.toJSONString(list0);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
	
	@RequestMapping("/yuyuedoctorlist")
	@ResponseBody
	public String yuyuedoctorlist(HttpServletRequest request,String search){
		List list = Lists.newArrayList();
		VipEntity vip = getVip(request);
		if(vip == null){
			//  说明session失效  需要跳转到登录页面
		}
		try {
			Map<String,Object> param = Maps.newHashMap();
			log.info("search:"+search);
			if(search != null && search.startsWith("D")){
				param.put("LIKE-i.doctor_code", search);
			}else if(!StringUtils.isEmpty(search)){
				param.put("LIKE-d.name", search.trim());
			}
			//每页大小为5 暂时写死
			int pageNo = getPageNo(request);
			ScriptPage sp = JFinalDb.findPageBySqlid(pageNo, 8, "YuYueSql_yuyuedoctor", param, null,vip.getVip_code());			
			List<Map<String,Object>> listr = sp.getRows();
			if(!listr.isEmpty()){
				for (Map<String,Object> map: listr) {
					if(map.get("PIC") == null || "".equals(map.get("PIC").toString())){
						map.put("PIC", ServiceConstants.default_heard_img_url);
					}
				}
				return JSON.toJSONString(listr);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
	@RequestMapping("/yuyuehislist")
	@ResponseBody
	public String yuyuehislist(HttpServletRequest request,String doctorcode){
		List list = Lists.newArrayList();
		VipEntity vip = getVip(request);
		if(vip == null){
			//  说明session失效  需要跳转到登录页面
		}		
		try {
			//每页大小为5 暂时写死
			int pageNo = getPageNo(request);
			ScriptPage sp = JFinalDb.findPageBySqlid(pageNo, 8, "YuYueSql_yuyuehis", null, null,vip.getVip_code(),doctorcode);			
			List<Map<String,Object>> listr = sp.getRows();
			if(!listr.isEmpty()){
				return JSON.toJSONString(listr);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}

	@RequestMapping("/guanzhu")
	@ResponseBody
	public Data guanzhu(HttpServletRequest request,String doctorcode){
		Data d = new Data();
		try {
			boolean flag = vipService.saveDoctorVip(doctorcode, getVip(request).getVip_code());
			if(flag){
				d.setCode(1);
				return d;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d.setCode(0);
		return d;
	}
}