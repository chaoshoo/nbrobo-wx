package com.nky.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.net.util.DateUtil;
import com.net.util.JsonUtil;
import com.net.util.PubMethod;
import com.nky.Constants;
import com.nky.entity.VipEntity;
import com.nky.service.InspectService;
import com.nky.service.VipService;
import com.nky.vo.Vip;

@RequestMapping("/vip")
@Controller
public class VipAction extends WxBaseAction{

	@Autowired
	private VipService vipService;
	@Autowired
	private InspectService inspectService;

	/**
	 * Home recently3Secondary test record
	 * @param request
	 * @param name
	 * @return
	 */
	@RequestMapping("/inspectnear")
	@ResponseBody
	public String inspectnear(HttpServletRequest request,String name){
		List<Map<String,Object>> list = Lists.newArrayList();
		try {
			VipEntity vip = getVip(request);
			String card_code = request.getParameter("card_code");
			if(StringUtils.isEmpty(card_code)){
				card_code = vip.getCard_code();
			}
			
			List<Record> list1 = Db.find("select * from vip_inspect_data where card_code=? order by inspect_time desc limit 3",card_code);
			Set<String> sets= inspectService.getInspectConfigMap().keySet();
			for (Record r : list1) {
				Map<String,Object> m = Maps.newHashMap();
				if(!PubMethod.isEmpty(r.get("SYS")) && !PubMethod.isEmpty(r.get("DIA"))){
					//如果是血压 则显示在一起	
					m.put("code","DIASYS");
					m.put("name", "blood pressure");
					m.put("value",r.get("DIA")+"/"+r.get("SYS"));
				}else{
					for (String s :sets) {
						if("SYS".equals(s.toUpperCase())||"DIA".equals(s.toUpperCase()) ){
							continue;
						}
						if(!PubMethod.isEmpty(r.get(s))){
							m.put("name", inspectService.getInspectConfig(s).get("name"));
							m.put("value",r.get(s)+"");
							m.put("code",s);
							break;
						}
					}
				}
				list.add(m);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
	
	@RequestMapping("/inspectlist")
	@ResponseBody
	public String inspectlist(HttpServletRequest request){
		List<Map<String,Object>> list = Lists.newArrayList();
		try {
			List<Record> listr = Db.find("select id,dic_type,dic_name,dic_value,dic_remark from dic where dic_type='inspect_code'");
			if(!listr.isEmpty()){
				return JsonUtil.toJSONString(JsonUtil.getList(listr));
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
	
	

	
	@RequestMapping("/userhome")
	public String homePage(HttpServletRequest request){
		try {
			String openId = openIdExists(request);
			if(PubMethod.isEmpty(openId)){
				return "";
			}
			Vip vip = vipService.getVipInfo(openId);
			if(vip.getBirthday() != null){
				try {
					int age = DateUtil.getAgeByBirthday(DateUtil.strToDate(vip.getBirthday()));
					vip.setAge(age);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("vip", vip);
			String url = (String)request.getSession().getAttribute(Constants.WX_USER_HEAD_IMG);
			if(StringUtils.isEmpty(url)){
				url = ServiceConstants.default_heard_img_url;
			}
			request.setAttribute("heard_img_url", url);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}		
		return "user_home";
	}
	@RequestMapping("/userinfo")
	public String userinfo(HttpServletRequest request){
		try {
			VipEntity vipsession = getVip(request);
			if(PubMethod.isEmpty(vipsession)){
				return "";
			}
			VipEntity vip = JFinalDb.findFirst(VipEntity.class, "select * from t_vip where vip_code=?", vipsession.getVip_code());
			request.setAttribute("vip", vip);
			if(vip.getPhone() == null){
				vip.setPhone(vip.getMobile());
			}
			if(vip.getCreate_time() == null){
				request.setAttribute("create_timestr", "");
			}else{
				request.setAttribute("create_timestr", DateUtil.dateForString(vip.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
			}
			String url = (String)request.getSession().getAttribute(Constants.WX_USER_HEAD_IMG);
			if(StringUtils.isEmpty(url)){
				url = ServiceConstants.default_heard_img_url;
			}
			request.setAttribute("heard_img_url", url);

		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}		
		return "user_info";
	}
	@RequestMapping("/saveUserinfo")
	@ResponseBody
	public Data saveUserinfo(HttpServletRequest request,VipEntity entity){
		Data d = new Data();
		try {
			boolean flag = JFinalDb.update(entity);
			if(flag){
				d.setCode(1);
				return d;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		d.setCode(0);
		return d;
	}
	@RequestMapping("/message")
	public String message(HttpServletRequest request){		
		return "message_history";
	}
	@RequestMapping("/messagelist")
	@ResponseBody
	public String messagelist(HttpServletRequest request){
		VipEntity vip = getVip(request);
		if(vip == null){
			//  说明session失效  需要跳转到登录页面
		}
		List<Map<String,Object>> list = Lists.newArrayList();
		try {
			//每页大小为5 暂时写死
			int pageNo = getPageNo(request);
			Map<String,Object> map = Maps.newHashMap();
			if(!StringUtils.isEmpty(request.getParameter("content"))){
				map.put("LIKE-content", request.getParameter("content"));
			}
			System.out.println(vip.getVip_code());
			ScriptPage sp = JFinalDb.findPageBySqlid(pageNo, 15, "VipSql_messagelist", map, null,vip.getVip_code());			
			List<Map<String,Object>> listr = sp.getRows();
			if(!listr.isEmpty()){
				for (Map<String,Object> m: listr) {
					m.put("SENDTIMESTR", DateUtil.dateForString((Date)m.get("SEND_TIME"),"yyyy-MM-dd HH:mm:ss")) ;
				}
				return JSON.toJSONString(listr);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
	@RequestMapping("/chart")
	public String chart(HttpServletRequest request,String card_code,String code){
		if(code.equals("C06")){
			request.setAttribute("url", ServiceConstants.datagrid_http+"/"+card_code+"/"+code+"/ALL/0-0/0.html?type=wx");

		}else{
			request.setAttribute("url", ServiceConstants.chart_http+"/"+card_code+"/"+code+"/ALL/0-0/0.html");

		}
		
		return "chart";
	}
	
	@RequestMapping("/bingli")
	public String bingli(HttpServletRequest request){
	
		return "bingli_history";
	}
	@RequestMapping("/binglilist")
	@ResponseBody
	public String binglilist(HttpServletRequest request){
		VipEntity vip = getVip(request);
		if(vip == null){
			//  说明session失效  需要跳转到登录页面
		}
		List<Map<String,Object>> list = Lists.newArrayList();
		try {
			//每页大小为5 暂时写死
			int pageNo = getPageNo(request);
			Map<String,Object> map = Maps.newHashMap();
			String sql = "select * from vip_exam where vip_code='"+vip.getVip_code()+"'";
			if(!StringUtils.isEmpty(request.getParameter("exam_date"))){
				sql += " and exam_date like '"+request.getParameter("exam_date")+"%'";
			}
			sql += " order by exam_date desc";
			System.out.println(vip.getVip_code());
			ScriptPage sp = JFinalDb.findPage(pageNo, 10, sql, null, null);			
			List<Map<String,Object>> listr = sp.getRows();
			if(!listr.isEmpty()){
				return JSON.toJSONString(listr);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
}