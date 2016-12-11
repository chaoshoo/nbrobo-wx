package com.nky.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.huilet.util.DateUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.entity.bo.ScriptPage;
import com.net.jfinal.JFinalDb;
import com.net.singleton.DicSingleton;
import com.net.util.JsonUtil;
import com.nky.entity.VipEntity;
import com.nky.entity.VipRegEntity;
import com.nky.service.ApiInterface;

@RequestMapping("/guahao")
@Controller
public class GuaHaoAction extends WxBaseAction{

	@RequestMapping("/yiyuan")
	public String yiyuan(HttpServletRequest request){
		String search_input = request.getParameter("search_input");
		if(StringUtils.isEmpty(search_input)){
			request.setAttribute("search_input", "");
		}else{
			request.setAttribute("search_input", search_input);
		}
		return "gh_yiyuanlist";
	}

	@RequestMapping("/keshi")
	public String keshi(HttpServletRequest request){
		String search_input = request.getParameter("search_input");
		if(StringUtils.isEmpty(search_input)){
			request.setAttribute("search_input", "");
		}else{
			request.setAttribute("search_input", search_input);
		}
		String hosid = request.getParameter("hosid");
		if(StringUtils.isEmpty(hosid)){
			request.setAttribute("hosid", "");
		}else{
			request.setAttribute("hosid", hosid);
		}
		return "gh_keshilist";
	}
	@RequestMapping("/doctor")
	public String doctor(HttpServletRequest request){
		String search_input = request.getParameter("search_input");
		if(StringUtils.isEmpty(search_input)){
			request.setAttribute("search_input", "");
		}else{
			request.setAttribute("search_input", search_input);
		}
		String hosid = request.getParameter("hosid");
		if(StringUtils.isEmpty(hosid)){
			request.setAttribute("hosid", "");
		}else{
			request.setAttribute("hosid", hosid);
		}
		String deptid = request.getParameter("deptid");
		if(StringUtils.isEmpty(deptid)){
			request.setAttribute("deptid", "");
		}else{
			request.setAttribute("deptid", deptid);
		}
		return "gh_doctorlist";
	}
	@RequestMapping("/doctorschedule")
	public String doctorschedule(HttpServletRequest request){
		
		String hosid = request.getParameter("hosid");
		if(StringUtils.isEmpty(hosid)){
			request.setAttribute("hosid", "");
		}else{
			request.setAttribute("hosid", hosid);
		}
		String deptid = request.getParameter("deptid");
		if(StringUtils.isEmpty(deptid)){
			request.setAttribute("deptid", "");
		}else{
			request.setAttribute("deptid", deptid);
		}
		request.setAttribute("docid", request.getParameter("docid"));
		return "gh_doctorschedule";
	}
	@RequestMapping("/parttime")
	public String parttime(HttpServletRequest request){
		
		String hosid = request.getParameter("hosid");
		if(StringUtils.isEmpty(hosid)){
			request.setAttribute("hosid", "");
		}else{
			request.setAttribute("hosid", hosid);
		}
		setAttributeGHInfo(request);
		return "gh_parttime";
	}
	@RequestMapping("/patient")
	public String patient(HttpServletRequest request){
		
		String hosid = request.getParameter("hosid");
		if(StringUtils.isEmpty(hosid)){
			request.setAttribute("hosid", "");
		}else{
			request.setAttribute("hosid", hosid);
		}
		setAttributeGHInfo(request);
		if( request.getParameter("parttime") == null){
			request.setAttribute("parttime", "");
		}else{
			request.setAttribute("parttime", request.getParameter("parttime"));
		}
		return "gh_patient";
	}
	private void setAttributeGHInfo(HttpServletRequest request){
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("docid", request.getParameter("docid"));
		request.setAttribute("scheduleid", request.getParameter("scheduleid"));
		request.setAttribute("outpdate", request.getParameter("outpdate"));
		request.setAttribute("timeinterval", request.getParameter("timeinterval"));
		request.setAttribute("orderfee", request.getParameter("orderfee"));
		request.setAttribute("partscheduleid", request.getParameter("partscheduleid"));
		//病人信息默认取本人的
		VipEntity vip = getVip(request);
		//性别定义要做转换 
		if(vip.getSex() != null && "1".equals(vip.getSex())){
			request.setAttribute("patientsex", 2);
		}else{
			request.setAttribute("patientsex", "1");
		}
		request.setAttribute("patientname", vip.getReal_name());
		request.setAttribute("idcard", vip.getPapers_num());
		request.setAttribute("patientbirthday", vip.getBirthday());
		request.setAttribute("contactphone", vip.getMobile());
		request.setAttribute("familyaddress", vip.getAddress());
	}
	@RequestMapping("/ghlock")
	@ResponseBody
	public String ghlock(HttpServletRequest request,VipRegEntity vipreg){
		Map<String,Object> map = Maps.newHashMap();
		vipreg.setVip_code(getVip(request).getVip_code());
		map = ApiInterface.ghlock(vipreg);
		return JsonUtil.toJSONString(map);
	}
	@RequestMapping("/history")
	public String history(HttpServletRequest request){
		return "gh_history";
	}
	@RequestMapping("/historylist")
	@ResponseBody
	public String historylist(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		try {
			VipEntity vip = getVip(request);
			String rowstart = request.getParameter("rowstart");
			int pageNo = 1;
			if(!StringUtils.isEmpty(rowstart)){
				pageNo = Integer.parseInt(rowstart);
			}
			DicSingleton.getInstance().reload();
			Map<String, String> statusdic = DicSingleton.getInstance().getDicMap("gh_status");
			String sql = "select * from vip_reg where vip_code=? order by outpdate desc";
			ScriptPage page = JFinalDb.findPage(pageNo, 15, sql, null, null, vip.getVip_code());
			List list = page.getRows();
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> m = (Map<String,Object>)list.get(i);
				if(statusdic.get(m.get("status")) ==null){
					m.put("statusstr", "");
				}else{
					m.put("statusstr", statusdic.get(m.get("status")));
				}
			}
			map.put("success","true");
			map.put("list",list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success","false");
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping("/orderinfo")
	public String orderinfo(HttpServletRequest request,String orderid){
		try {
			VipEntity vip = getVip(request);
			String code = request.getParameter("code");
			if(code != null){
				log.info("code=="+code);
//				codeSess(request, code);
//				Db.update("update t_vip set wx_code=? where vip_code=?",code,vip.getVip_code());
			}
			VipRegEntity vipreg = new VipRegEntity();
			vipreg.setOrderid(orderid);
			vipreg = JFinalDb.findFirst(vipreg);
			Map<String, String> statusdic = DicSingleton.getInstance().getDicMap("gh_status");
			if(statusdic.get(vipreg.getStatus()) ==null){
				request.setAttribute("statusstr", "");
			}else{
				request.setAttribute("statusstr", statusdic.get(vipreg.getStatus()));
			}
			request.setAttribute("vipreg", vipreg);
			//
			String sql = "select hosid as id,hosname as name from gh_hospital_All where hosid=?"
					+ " union all select deptid as id,deptname as name from gh_dept_All where deptid=?"
					+" union all select docid as id,docname as name from gh_doctor_All where docid=?";
			List<Record> list = Db.find(sql,vipreg.getHosid(),vipreg.getDeptid(),vipreg.getDocid());
			Map<String,String> values = Maps.newHashMap();
			for (int i = 0; i < list.size(); i++) {
				values.put(list.get(i).getStr("id"), list.get(i).getStr("name"));
			}
			request.setAttribute("hosname", values.get(vipreg.getHosid()));
			request.setAttribute("deptname", values.get(vipreg.getDeptid()));
			request.setAttribute("docname", values.get(vipreg.getDocid()));
			return "gh_orderinfo";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("/pay")
	@ResponseBody
	public String pay(HttpServletRequest request,String orderid){
		String openid = getVip(request).getWxopenid();
		JSONObject obj = ApiInterface.orderpay(orderid, openid);
		return obj.toJSONString();
	}
	@RequestMapping("/paysuccess")
	@ResponseBody
	public String paysuccess(HttpServletRequest request,String orderid){
		log.info("支付成功订单号："+orderid);
		try {
			Db.update("update vip_reg set status='3' ,payrtime=? where orderid=?",DateUtil.nowDateForStr("yyyy-MM-dd HH:mm:ss"),orderid);
		} catch (Exception e) {
			e.printStackTrace();
			return "2";
		}
		return "1";
	}
	@RequestMapping("/api")
	@ResponseBody
	public String api(HttpServletRequest request,String type){
		Map<String,Object> map = Maps.newHashMap();
		try {
			if(type.equals("hospitalAllList")){
				map = ApiInterface.hospitalAllList(request.getParameter("hosname"), request.getParameter("rowstart"));
			}else if(type.equals("deptalllist")){
				map = ApiInterface.deptalllist(request.getParameter("hosid"), request.getParameter("keshi"));
			}else if(type.equals("doctorlist")){
				map = ApiInterface.doctorlist(request.getParameter("hosid"), request.getParameter("deptid"), request.getParameter("rowstart"), request.getParameter("doctor"));
			}else if(type.equals("doctorschedule")){
				map = ApiInterface.doctorschedule(request.getParameter("hosid"), request.getParameter("deptid"), request.getParameter("docid"), request.getParameter("rowstart"));
			}else if(type.equals("parttime")){
				map = ApiInterface.parttime(request.getParameter("hosid"), request.getParameter("scheduleid"), request.getParameter("rowstart"));
			}else if(type.equals("confirmorder")){
				map = ApiInterface.confirmorder(request.getParameter("orderid"));
			}else if(type.equals("cancelorder")){
				String vip_code = getVip(request).getVip_code();
				map = ApiInterface.cancelorder(request.getParameter("orderid"),request.getParameter("cancelreason"),vip_code);
			}else{
				map.put("success", "false");
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			map.put("success", "false");
		}
		return JsonUtil.toJSONString(map);
	}
}
