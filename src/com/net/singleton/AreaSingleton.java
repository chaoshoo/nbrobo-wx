package com.net.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.entity.Area;
import com.net.entity.RedisTypeEnum;
import com.net.util.SpringUtil;
import com.net.wx.service.RedisService;
/**
 * Three level region--Singleton pattern
 * @author liuchang
 *
 */
public class AreaSingleton {

	
	private static RedisService redisService = (RedisService) SpringUtil.getBean("redisService");
	
	private static AreaSingleton instance = null;
	
	public static AreaSingleton getInstance() {
		if (instance == null) {
			instance = new AreaSingleton();
		}
		return instance;
	}

	private AreaSingleton() {		
		if(!redisService.exists(RedisTypeEnum.AREAPARENT.toId())){
			loadData();	
		}
	}
	
	/**
	 * assignment
	 */
	private void loadData(){
		try {
			//设置省 市县
			String sql = "select id,full_name,name,tree_path,IFNULL(parent,0) parent from t_area order by parent,orders";
			List<Record>  list1    = Db.find(sql);
			Map<String,Map<byte[],byte[]>> area = Maps.newHashMap();
			String parent = "";
			for (Record r: list1) {
				parent = r.get("parent")+"";
				Map<byte[],byte[]> m = area.get(parent);
				if(m == null){
					m = Maps.newHashMap();
				}
				m.put((r.get("id")+"").getBytes(), r.getStr("name").getBytes("UTF-8"));
				area.put(parent, m);
				//数据存入redis
				Area a = new Area();
				a.setName(r.get("name")+"");
				a.setFull_name(r.get("full_name")+"");
				a.setParent(Integer.parseInt(r.get("parent")+""));
				a.setTree_path(r.get("tree_path")+"");
				redisService.set(RedisTypeEnum.AREAMAP.toId()+"_"+r.get("id"), JSON.toJSONString(a), 0L);
			}
			//设置市县
			for (String key: area.keySet()) {
				redisService.hMSet((RedisTypeEnum.AREAPARENT.toId()+"_"+key).getBytes(), area.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	/**
	 * Reload  Parameters fortrue To clean upredisdata
	 */
	public void reload() {
			redisService.delByReg(RedisTypeEnum.AREAPARENT.toId());
			redisService.delByReg(RedisTypeEnum.AREAMAP.toId());
		loadData();
	}
	/**
	 * Get province
	 * @return
	 */
	public List<Area> getProvince() {
		return getChildren("0");
	}
	
	public static Area getArea(String areaid){
		String obj = redisService.get(RedisTypeEnum.AREAMAP.toId()+"_"+areaid);
		if(StringUtils.isEmpty(obj)){
			return null;
		}else{
			return JSON.parseObject(obj, Area.class);
		}
	}
	/*
	 * according toidAcquisition sub region
	 * @param id
	 * @return
	 */
	public List<Area> getChildren(String areaid) {
		String key = RedisTypeEnum.AREAPARENT.toId()+"_"+areaid ;
		Map<String,String> map =  redisService.hMGetAll(key.getBytes());
		List<Area> lists = new ArrayList<Area>();
		for (String b: map.keySet()) {
			Area a = new Area();
			a.setId(Long.parseLong(b));
			try {
				a.setName(map.get(b));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lists.add(a);
		}
		return lists;
	}
	

}
