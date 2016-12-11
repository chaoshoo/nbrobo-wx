package com.net.singleton;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * Drop down list dictionary--Singleton pattern
 * 
 * @author yc 2011-3-28
 * @Version CECTManegeServer
 */
public class WxRegisterSingleton {

	private static WxRegisterSingleton instance = null;

	private HashMap<String,Record> map = new HashMap<String,Record>();
	
	public static WxRegisterSingleton getInstance() {
		if (instance == null) {
			instance = new WxRegisterSingleton();
		}
		return instance;
	}

	private WxRegisterSingleton() {
		

		loadData();

	}

	/**
	 * Load data
	 */
	private void loadData() {
		// TODO Auto-generated method stub
		String sql = "select * from  t_wx_register";
		List<Record> li = Db.find(sql);
		for(Record r: li){
			String dpid = r.getStr("dp_id");
			map.put(dpid, r);
		}
		
	}

	/**
	 * Reload
	 */
	public void reloadData() {

		map.clear();

		loadData();
	}

	/**
	 * according totypeGets the drop - down list
	 * 
	 * @param type
	 * @return
	 */
	public Record getInfoByDpid(String dpid) {
		return map.get(dpid);
	}


}