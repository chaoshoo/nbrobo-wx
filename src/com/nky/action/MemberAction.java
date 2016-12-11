package com.nky.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.ServiceConstants;
import com.net.entity.bo.Data;
import com.net.jfinal.JFinalDb;
import com.net.util.DateUtil;
import com.net.util.JsonUtil;
import com.net.util.MD5Util;
import com.net.util.PubMethod;
import com.nky.entity.VipEntity;
import com.nky.service.InspectService;
import com.nky.service.VipService;

@RequestMapping("/member")
@Controller
public class MemberAction extends WxBaseAction{

	@Autowired
	private VipService vipService;
	@Autowired
	private InspectService inspectService;


	

	@RequestMapping("/member")
	public String member(HttpServletRequest request){	
		return "member_list";
	}
	@RequestMapping("/memberadd")
	public String memberadd(HttpServletRequest request){	
		VipEntity vip = getVip(request);
		request.setAttribute("mobile", vip.getMobile());
		return "member_add";
	}
	@RequestMapping("/memberinfo")
	public String memberinfo(HttpServletRequest request){	
		String vip_code=request.getParameter("vip_code");
		if(StringUtils.isEmpty(vip_code)){
			return "";
		}
		VipEntity vip = new VipEntity ();
		vip.setVip_code(vip_code);
		try {
			vip = JFinalDb.findFirst(vip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("vip", vip);
		return "member_info";
	}

	@RequestMapping("/memberlist")
	@ResponseBody
	public String memberlist(HttpServletRequest request){
		List<Map<String,Object>> list = Lists.newArrayList();
		try {
			VipEntity vip = getVip(request);
			List<Record> listr = JFinalDb.findBySqlid("VipSql_memberlist", vip.getVip_code());
			for (Record r: listr) {
				if(PubMethod.isEmpty(r.get("heard_img_url"))){
					r.set("heard_img_url", ServiceConstants.default_heard_img_url);
				}
				r.set("status", "ok");
				if(!r.getStr("login_password").toLowerCase().equals(r.getStr("family_pwd").toLowerCase())){
					r.set("status", "失效");
//					r.set("inspect_time","");
//					r.set("inspect_name","");
//					r.set("inspect_value","");
				}else{
//					List<Map<String,Object>> li = inspectService.lastInspect(r.get("card_code")+"");
//					if(li.isEmpty()){
//						r.set("inspect_time","");
//						r.set("inspect_name","");
//						r.set("inspect_value","");
//					}else{
//						System.out.println(li);
//						Map<String,Object> m = li.get(0);
//						r.set("inspect_time",DateUtil.dateForString((Date)m.get("inspect_time"), "yyyy-MM-dd HH:mm:ss"));
//						r.set("inspect_name",m.get("inspect_name"));
//						r.set("inspect_value",m.get("inspect_value"));
//					}
				}
			}
			if(!listr.isEmpty()){
				return JsonUtil.toJSONString(JsonUtil.getList(listr));
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}	
		return JsonUtil.toJSONString(list);
	}
	@RequestMapping("/memberdel")
	@ResponseBody
	public Data memberdel(HttpServletRequest request,String vip_code,String card_code){
		Data d = new Data();
		try {
			VipEntity vip = getVip(request);
			List<String> list = Lists.newArrayList();
			String sql0 = "delete from vip_family where vip_code='"+vip.getVip_code()+"' and family_account='"+card_code+"'";
			String sql1 = "delete from vip_family where vip_code='"+vip_code+"' and family_account='"+vip.getCard_code()+"'";
			list.add(sql0);
			list.add(sql1);
			int[] x = Db.batch(list, 2);
			d.setCode(1);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		}
		d.setCode(0);
		return d;
	}
	@RequestMapping("/membersave")
	@ResponseBody
	public Data membersave(HttpServletRequest request,String mobile,String password){
		Data d = new Data();
		try {
			VipEntity vip = getVip(request);
			String md5pwd = MD5Util.MD5(password, "utf-8").toLowerCase();
			List<Record> li = Db.find("select * from t_vip where (isvalid=1 and ( mobile=? or card_code=?) and login_password=?) "
					+ " union all select * from t_vip where  vip_code=?",mobile,mobile,md5pwd,vip.getVip_code());
			if(li == null || li.size() !=2){
				d.setCode(0);
				d.setMsg("手机号、卡号或密码错误！");
				return d;
			}
			Record r1 = li.get(0);
			Record r2 = li.get(1);
			if(r1.get("vip_code").equals(r2.get("vip_code"))){
				d.setCode(0);
				d.setMsg("不能绑定自己的账号！");
				return d;
			}
			//判断是否已经做过绑定 做个绑定就删除
			String delsql = "delete from vip_family where (vip_code=? and family_account=?) or (vip_code=? and family_account=?)";
			Db.update(delsql,r1.get("vip_code"),r2.get("card_code"),r2.get("vip_code"),r1.get("card_code"));
			//做插入;
			Record in1 = new Record();
			in1.set("vip_code", r1.get("vip_code")).set("family_account",r2.get("card_code"))
			.set("family_pwd", r2.get("login_password")).set("create_time", new Date());
			Record in2= new Record();
			in2.set("vip_code", r2.get("vip_code")).set("family_account",r1.get("card_code"))
			.set("family_pwd", r1.get("login_password")).set("create_time", new Date());
			boolean flag = Db.save("vip_family", in1);
			if(flag){
				Db.save("vip_family", in2);
			}
			d.setCode(1);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		}
		d.setCode(0);
		d.setMsg("绑定失败！");
		return d;
	}
}
