package com.nky.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.util.ListUtil;
@Service
public class InspectService {
	public static Map<String,Record> map = null;
	public static Map<String,Record> getInspectConfigMap(){
		if(map == null){
			List<Record> list = Db.find("select code,name,isfz,unit,inspect_code,kpi_max,kpi_min,kpi_pic from inspect_kpi_config");
			map = ListUtil.listToMap(list, "code");
		}
		return map;
	}
	
	public static Record getInspectConfig(String code){
		return getInspectConfigMap().get(code);
	}
	
	public List<Map<String,Object>> lastInspect(String card_code){
		List<Record> list = Db.find("select * from vip_inspect_latest where card_code=? order by inspect_time",card_code);
		Map<String,Object> temp = Maps.newHashMap();
		List<Map<String,Object>> list1 = Lists.newArrayList();
		for (Record r : list) {
			if(temp.get("inspect_code") != null && !temp.get("inspect_code").equals(r.get("inspect_code")+"")){
				Map<String,Object> t = Maps.newHashMap();
				t.putAll(temp);
				list1.add(t);
				temp = Maps.newHashMap();
			}
			temp.put("inspect_code",r.get("inspect_code")+"");
			if(temp.get("kpi_code") == null){
				temp.put("kpi_code", r.getStr("kpi_code"));
			}else{
				temp.put("kpi_code", temp.get("kpi_code")+"/"+r.getStr("kpi_code"));
			}
			if(temp.get("inspect_name") == null){
				temp.put("inspect_name", r.getStr("inspect_name"));
			}else{
				temp.put("inspect_name", temp.get("inspect_name")+"/"+r.getStr("inspect_name"));
			}
			if(temp.get("inspect_value") == null){
				temp.put("inspect_value", r.getStr("inspect_value"));
			}else{
				temp.put("inspect_value", temp.get("inspect_value")+"/"+r.getStr("inspect_value"));
			}
			temp.put("inspect_time",r.get("inspect_time"));
						
		}
		if(!temp.isEmpty()){
			list1.add(temp);
		}
		return list1;
	}
}
