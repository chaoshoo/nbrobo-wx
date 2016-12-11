package com.nky.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.net.ServiceConstants;
import com.net.singleton.DicSingleton;
import com.net.util.HttpClient;
import com.net.util.JsonUtil;
import com.net.util.PubMethod;
import com.nky.entity.VipEntity;
import com.nky.entity.VipRegEntity;

public class ApiInterface {
	static Logger log = LoggerFactory.getLogger(ApiInterface.class);
	
	private static String getApi(String method,Map<String,Object> params){
		JSONObject jb = new JSONObject();
		jb.put("type", method);
		jb.putAll(params);
		log.info("content="+jb.toString());
		Map<String, String> send = new HashMap<String, String>();
		send.put("content", jb.toString());
		String info = null;
		try {
			info = HttpClient.httpPost(ServiceConstants.nkyapi_url, send);
			log.info("info:"+info);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		return info;
	}
	
	public static  Map<String,Object> getGuaHaoApi(String method,Map<String,Object> params){
		Map<String,Object> map = new HashMap<String,Object>();
		try {			
			String result = getApi(method, params);
			JSONObject obj = JSON.parseObject(result);
			if(obj.getBooleanValue("success")){
				String ret = obj.getJSONObject("message").getString("ret");
				if(ret.equals("0")){
					map.put("success", "true");
					map.put("list", obj.getJSONObject("message").getJSONArray("li"));
				}else{
					map.put("success", "false");
				}
				map.put("ret", ret);
				map.put("msg", obj.getJSONObject("message").getString("msg"));
			}else{
				map.put("success", "false");
				map.put("msg", "系统异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
		}
		return map;
	}
	
	public static boolean isSuccess(String result){
		if(result != null && "success".equals(result)){
			return true;
		}
		return false;
	}
	public static String analyze(String result){
		if(!PubMethod.isEmpty(result)){
			JSONObject jb = JSON.parseObject(result);
			Boolean flag = Boolean.valueOf(jb.get("success").toString());
			if(flag){
				return "success";
			}else{
				return jb.get("message").toString();
			}
		}else{
			return "系统异常";
		}
	}
	/**
	 * 注册
	 * @param tel
	 * @return
	 */
	public static String registMessage(String tel) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tel", tel.trim());
		String result = getApi("registMessage", map);
		return analyze(result);
	}
	/**
	 * 修改密码
	 * @param tel
	 * @return
	 */
	public static String changepwdmessage(String tel) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tel", tel.trim());
		String result = getApi("changepwdmessage", map);
		return analyze(result);
	}
	/**
	 * 验证短信码
	 * @param tel
	 * @return
	 */
	public static String checkmessage(String tel,String code) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tel", tel.trim());
		map.put("code", code.trim());
		String result = getApi("checkmessage", map);
		return analyze(result);
	}
	
	public static Map<String,Object> hospitalAllList(String hosname,String rowstart) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hosid", "0");
		if(StringUtils.isEmpty(hosname)){
			map.put("hosname", "");
		}else{
			map.put("hosname", hosname);
		}
		map.put("rowcount", "15");
		map.put("rowstart", rowstart);
		map.put("flag", "0");
		return getGuaHaoApi("hospitalAllList", map);
	}
	public static Map<String,Object> deptalllist(String hosid,String keshi) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isEmpty(hosid)){
			map.put("hosid", "0");
		}else{
			map.put("hosid", hosid);
		}
		if(StringUtils.isEmpty(keshi)){
			map.put("deptname", "");
		}else{
			map.put("deptname", keshi);
		}
		map.put("rowstart", "1");
		map.put("rowcount", "300");
		map.put("flag", "0");
		try {
			String result = getApi("deptalllist", map);
			JSONObject obj = JSON.parseObject(result);
			if(obj.getBooleanValue("success")){
				map.put("success", "true");
				map.put("list", obj.getJSONObject("message").getJSONArray("li"));
			}else{
				map.put("success", "false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
		}
		return map;
	}
	public static Map<String,Object> doctorlist(String hosid,String deptid,String rowstart,String doctor) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isEmpty(hosid)){
			map.put("hosid", "0");
		}else{
			map.put("hosid", hosid);
		}
		if(StringUtils.isEmpty(doctor)){
			map.put("docname", "");
		}else{
			map.put("docname", doctor);
		}
		map.put("deptid", deptid);
		map.put("rowstart", rowstart);
		map.put("rowcount", "15");
		map.put("flag", "0");
		return getGuaHaoApi("doctor", map);		
	}
	
	public static Map<String,Object> doctorschedule(String hosid,String deptid,String docid,String rowstart){
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isEmpty(hosid)){
			map.put("hosid", "0");
		}else{
			map.put("hosid", hosid);
		}
		map.put("deptid", deptid);
		map.put("docid", docid);
		map.put("rowstart", 1);
		map.put("rowcount", "30");
		map = getGuaHaoApi("doctorschedule", map);	
		try {
			Map<String,String> dic = DicSingleton.getInstance().getDicMap("guahao_validflag");
//			/********************模拟数据***********************************/
//			map.put("success", "true");
//			String  str = "[{\"orderfee\":6.5,\"outpdate\":\"2016-09-17\",\"scheduleid\":\"67b7ed8c-9ab7-41e1-bd16-6f7233578550\",\"timeinterval\":\"AM\",\"validflag\":1},"
//					+ "{\"orderfee\":8.5,\"outpdate\":\"2016-09-18\",\"scheduleid\":\"67b7ed8c-9ab7-41e1-bd16-6f7233578551\",\"timeinterval\":\"AM\",\"validflag\":7}]";;
//			map.put("list", JSONArray.parseArray(str));
//			/********************模拟数据**********************************/

			if("true".equals(map.get("success"))){
				JSONArray ar = (JSONArray)map.get("list");
				for (int i = 0; i < ar.size(); i++) {
					JSONObject obj  = ar.getJSONObject(i);
					obj.put("validflagstr", dic.get(obj.get("validflag")+""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	public static Map<String,Object> parttime(String hosid,String scheduleid,String rowstart){
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isEmpty(hosid)){
			map.put("hosid", "0");
		}else{
			map.put("hosid", hosid);
		}
		map.put("scheduleid", scheduleid);
		map.put("rowstart", 1);
		map.put("rowcount", "0");
		map = getGuaHaoApi("parttime", map);	
//		/********************模拟数据***********************************/
//		map.put("success", "true");
//		String  str = "[{\"bstp\":\"08:00\",\"estp\":\"09:00\",\"partscheduleid\":\"443292\",\"regflag\":1,\"scheduleid\":\"107755\"},"
//			+"{\"bstp\":\"09:00\",\"estp\":\"10:00\",\"partscheduleid\":\"443293\",\"regflag\":1,\"scheduleid\":\"107755\"},"
//			+"{\"bstp\":\"10:00\",\"estp\":\"11:00\",\"partscheduleid\":\"443294\",\"regflag\":1,\"scheduleid\":\"107755\"},"
//			+"{\"bstp\":\"11:00\",\"estp\":\"11:30\",\"partscheduleid\":\"443295\",\"regflag\":0,\"scheduleid\":\"107755\"}]";
//		map.put("list", JSONArray.parseArray(str));
//		/********************模拟数据**********************************/

		return  map;	
	}
	public static Map<String,Object> ghlock(VipRegEntity vip){
		Map<String,Object> params = JsonUtil.getMap4Json(JSON.toJSONString(vip));
		params.put("certtypeno", "1");
		params.put("vipcode", vip.getVip_code());
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String result = getApi("ghlock", params);
			JSONObject obj = JSON.parseObject(result);
			if(obj.getBooleanValue("success")){
				String ret = obj.getJSONObject("message").getString("ret");
				if(ret.equals("0")){
					map.put("success", "true");
					map.put("orderfee", obj.getJSONObject("message").getString("orderfee"));
					map.put("orderid", obj.getJSONObject("message").getString("orderid"));
				}else{
					map.put("success", "false");
				}
				map.put("ret", ret);
				map.put("msg", obj.getJSONObject("message").getString("msg"));
			}else{
				map.put("success", "false");
				map.put("msg", obj.getJSONObject("message").getString("msg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("msg", "系统异常");
		}
		return map;
	}
	public static Map<String,Object> confirmorder(String orderid){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orderid", orderid);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String result = getApi("confirmorder", params);
			JSONObject obj = JSON.parseObject(result);
			if(obj.getBooleanValue("success")){
				String ret = obj.getJSONObject("message").getString("ret");
				if(ret.equals("0")){
					map.put("success", "true");
					map.put("serial", obj.getJSONObject("message").getString("serial"));
					map.put("outpdate", obj.getJSONObject("message").getString("outpdate"));
					map.put("orderconfirmsms", obj.getJSONObject("message").getString("orderconfirmsms"));
				}else{
					map.put("success", "false");
				}
				map.put("ret", ret);
				map.put("msg", obj.getString("msg"));
			}else{
				map.put("success", "false");
				map.put("msg", "系统异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
		}
		return map;
	}
	public static Map<String,Object> cancelorder(String orderid,String cancelreason,String vip_code){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orderid", orderid);
		params.put("operator", vip_code);
		if(StringUtils.isEmpty(cancelreason)){
			params.put("cancelreason", "无理由");
		}else{
			params.put("cancelreason", cancelreason);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String result = getApi("cancelorder", params);
			JSONObject obj = JSON.parseObject(result);
			if(obj.getBooleanValue("success")){
				String ret = obj.getJSONObject("message").getString("ret");
				if(ret!=null && ret.equals("0")){
					map.put("success", "true");
				}else{
					map.put("success", "false");
				}
				map.put("ret", ret);
				map.put("msg", obj.getString("msg"));
			}else{
				map.put("success", "false");
				map.put("msg", "系统异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
		}
		return map;	
	}
	public static Map<String,Object> doctorstop(String hosid,String rowstart){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hosid", hosid);
		map.put("rowstart", "0");
		map.put("rowcount", "0");
		map.put("bstp", "0");
		map.put("estp", "0");
		if("true".equals(map.get("success"))){
			Map<String,Object> orderids = new HashMap<String,Object>();
			JSONArray array = (JSONArray)map.get("list");
			for (int i = 0; i < array.size(); i++) {
				//orderids.put(array.orderid,)
				//.........
			}
			return map;
		}else{
			return map;
		}
	}
	public static JSONObject orderpay(String orderid,String openid){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orderid", orderid);
		params.put("openid", openid);
		try {
			String result = getApi("getwxpreOrder", params);
			JSONObject json = JSONObject.parseObject(result);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json = new JSONObject();
			json.put("code", "1");
			json.put("message", "调用接口异常");
			return json;
		}
	}
}
