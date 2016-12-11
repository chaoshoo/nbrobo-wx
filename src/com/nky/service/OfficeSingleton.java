package com.nky.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.nky.entity.OfficeEntity;

/**
 * 科室单例.
 * @author Ken
 * @version 2016年8月24日
 */
public class OfficeSingleton {

//	private static final Logger LOG = LoggerFactory.getLogger(OfficeSingleton.class);

	private static OfficeSingleton instance = null;

	//科室	 
	private static ConcurrentHashMap<String, OfficeEntity> OFFICE_MAP = new ConcurrentHashMap<String, OfficeEntity>();

	private static Boolean LOCK = new Boolean(true);

	/**
	 * 获取单例.
	 */
	public static OfficeSingleton getInstance() {
		if (instance == null) {
			instance = new OfficeSingleton();
		}
		return instance;
	}

	/**
	 * 获取所有科室.
	 */
	public Map<String, OfficeEntity> getAll() {
		return OFFICE_MAP;
	}

	/**
	 * 获取某一个科室.
	 */
	public OfficeEntity getEntitybykey(String code) {
		OfficeEntity office = OFFICE_MAP.get(code);
		if(office == null){
			loadData();
			return OFFICE_MAP.get(code);
		}else{
			return office;
		}
	}

	private OfficeSingleton() {
		loadData();
	}

	/**
	 * 加载数据
	 */
	private void loadData() {
		synchronized (LOCK) {
			List<Record> list = Db.find("select CODE,NAME,PIC,DES,DESCRIPTION from office order by code asc ");
			for (Record r : list) {
				OfficeEntity tmp = new OfficeEntity();
				tmp.setCode(r.getStr("CODE"));
				tmp.setName(r.getStr("NAME"));
				tmp.setPic(r.getStr("PIC"));
				tmp.setDes(r.getStr("DES"));
				tmp.setDescription(r.getStr("DESCRIPTION"));
				OFFICE_MAP.put(r.getStr("CODE"), tmp);
			}
		}
	}

	/**
	 * 重新加载
	 */
	public void reload() {
		loadData();
	}

}
