package com.net.util;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.plugin.activerecord.Record;

public class JfinalUtil {

	public static HashMap getMapByJfinalRecord(Record rc){
		HashMap map = new HashMap();
		String[] names = rc.getColumnNames();
		for (String name :names) {
			map.put(name, rc.get(name)+"");
		}
		
		return map;
	}
	
	public static Map<String,Object> getMapByRecord(Record rc){
		Map<String,Object> map = new HashMap<String,Object>();
		String[] names = rc.getColumnNames();
		for (String name :names) {
			map.put(name, rc.get(name));
		}
		
		return map;
	}
	
	/**
	 * The key for the hump。as:dp_id---->dpId
	 * @param rc
	 * @return
	 */
	public static Map<String,Object> getMapByRecord2(Record rc){
		Map<String,Object> map = new HashMap<String,Object>();
		String[] names = rc.getColumnNames();
		int index = -1;
		String indexStr = null;
		String newName = null;
		for (String name :names) {
			newName = name;
			//将dp_id转换为dpId
			if((index = name.indexOf("_"))>-1 && name.length()>=(index+1)){
				indexStr = (name.charAt(index+1)+"").toUpperCase();
				newName = name.substring(0, index)
						+indexStr
						+(name.length()>=index+2?name.substring(index+2):"");
			}
			map.put(newName, rc.get(name));
		}
		
		return map;
	}
	
	/**
	 * The key to lowercase。as:dp_id---->dpid
	 * @param rc
	 * @return
	 */
	public static Map<String,Object> getMapByRecord3(Record rc){
		Map<String,Object> map = new HashMap<String,Object>();
		String[] names = rc.getColumnNames();
		
		for (String name :names) {
			//将dp_id---->dpid
			map.put(name.replace("_", "").toLowerCase(), rc.get(name));
		}
		
		return map;
	}
}