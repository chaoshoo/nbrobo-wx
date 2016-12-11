package com.net.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.util.SpringUtil;
import com.net.wx.Constants;
import com.net.wx.service.RedisService;


/**
 * Drop down list dictionary--Singleton pattern
 * 
 * @author yc 2011-3-28
 * @Version CECTManegeServer
 */
public class DicSingleton {

	private static DicSingleton instance = null;

	private static RedisService redisService = (RedisService) SpringUtil.getBean("redisService");

	public static DicSingleton getInstance() {
		if (instance == null) {
			instance = new DicSingleton();
		}
		return instance;
	}

	private DicSingleton() {

		loadData(0);

	}

	/**
	 * Load data
	 */
	private void loadData(int flag) {
		if(flag ==0 && redisService.exists(Constants.KEY_DIC)){
			Object obj = redisService.getObj(Constants.KEY_DIC.getBytes());
			if("error".equals(obj)){
				setData();
			}
		}else{
			setData();
		}

	}
	

	

	/**
	 * Reload
	 */
	public void reloadData() {

		loadData(1);
	}

	
		
	/**
	 * assignment
	 */
	public void setData(){
		setData(null);
	}
	public void setData(String dic_type){
		String sql = "select dic_type,dic_name,dic_value from dic where 1=1";
		if(dic_type != null){
			sql += " and dic_type='"+dic_type+"'";
		}
		List<Record>   list = Db.find(sql);
		for (Record r: list) {
			redisService.hSet(Constants.KEY_DIC+"_"+r.getStr("dic_type"), r.getStr("dic_name"),r.getStr("dic_value"));
		}
	}

	/**
	 * Reload
	 */
	public void reload() {

		loadData(1);
	}

	/**
	 * according totypeGets the drop - down list
	 * 
	 * @param type
	 * @return
	 */
	public List<Map<String,String>> getDic(String type) {
		String key = Constants.KEY_DIC+"_"+type;
		Map<String,String> map =  redisService.hMGetAll(key.getBytes());
		if(map == null || map.isEmpty()){
			setData(type);
			map =  redisService.hMGetAll(key.getBytes());
			if(map == null){
				//还是为空 
				return  new ArrayList<Map<String,String>>();
			}
		}
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (String k:map.keySet()) {
				HashMap m = new HashMap();
				m.put("dic_name", k);
				m.put("dic_value",map.get(k));
				list.add(m);
		}
		return list;
	}

	/**
	 * according todictype，pluskeyValue acquisitionvalue
	 * 
	 * @param dicType
	 *            type
	 * @param keyValue
	 *            Key
	 * @return huilet 2013-4-17
	 * @author yuanc
	 */
	public String getValueBykeyDic(String dicType,String keyValue){
		String value = redisService.hGet(Constants.KEY_DIC+"_"+dicType, keyValue);
		if(StringUtils.isEmpty(value)){
			//重新加载一次
			setData(dicType);
			value = redisService.hGet(Constants.KEY_DIC+"_"+dicType, keyValue);
		}
		return value;
	}
	
	public Map<String,String> getDicMap(String type) {
		String key = Constants.KEY_DIC+"_"+type;
		Map<String,String> map =  redisService.hMGetAll(key.getBytes());
		if(map == null){
			setData(type);
			map =  redisService.hMGetAll(key.getBytes());
			if(map == null){
				return new HashMap<String, String>();
			}
		}
		return map;
	}


}