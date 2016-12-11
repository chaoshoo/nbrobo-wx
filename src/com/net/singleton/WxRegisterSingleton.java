package com.net.singleton;

import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 下拉列表字典--单例模式
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
	 * 加载数据
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
	 * 重新加载
	 */
	public void reloadData() {

		map.clear();

		loadData();
	}

	/**
	 * 根据type获取下拉列表
	 * 
	 * @param type
	 * @return
	 */
	public Record getInfoByDpid(String dpid) {
		return map.get(dpid);
	}


}
