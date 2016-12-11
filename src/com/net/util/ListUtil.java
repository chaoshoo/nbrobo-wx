package com.net.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jfinal.plugin.activerecord.Record;

public class ListUtil {
	//数据进行左连接 a的数据集左连接数据集b  用key连接,fields需要连接的字段
	public static void leftjoin(List<Record> a,List<Record> b,String key,String[] fields){
		long starttime = System.currentTimeMillis();
		if(a.isEmpty()){
			return;
		}
		Map<String,Record> map = new HashMap<String,Record>();
		if(!b.isEmpty()){
				for (Record r: b) {
					map.put(r.get(key)+"", r);
				}
			}
		for (Record r: a) {
			Record br = map.get(r.get(key)+"");
			if(br == null){
				br = new Record();
			}
			for (String f: fields) {
				r.set(f, br.get(f));
			}
		}
		System.out.println("用时毫秒："+(System.currentTimeMillis()-starttime));
	}
	public static void leftjoin2(List<Map<String,Object>> a,List<Record> b,String key,String[] fields){
		long starttime = System.currentTimeMillis();
		if(a.isEmpty()){
			return;
		}
		Map<String,Record> map = new HashMap<String,Record>();
		if(!b.isEmpty()){
				for (Record r: b) {
					map.put(r.get(key)+"", r);
				}
			}
		for (Map<String,Object> r: a) {
			Record br = map.get(r.get(key)+"");
			if(br == null){
				br = new Record();
			}
			for (String f: fields) {
				r.put(f, br.get(f));
			}
		}
		System.out.println("用时毫秒："+(System.currentTimeMillis()-starttime));
	}
	
	// a的数据集不在数据集b中  用key去重
	public static List<Record> notin(List<Record> a,List<Record> b,String key){
		 long starttime = System.currentTimeMillis();
			if(a.isEmpty()){
				return new ArrayList<Record>();
			}
			Set<String> set = new HashSet<String>();
			if(!b.isEmpty()){
					for (Record r: b) {
						set.add(r.get(key)+"");
					}
				}
			List<Record> list = new ArrayList<Record>();
			for (Record r: a) {
				if(!set.contains(r.get(key)+"")){
					list.add(r);
				}
			}
			System.out.println("用时毫秒："+(System.currentTimeMillis()-starttime));
			return list;
		}
	public static String  getListMapIDS(List<Map<String,Object>> a,String field){
		String str = "";
		if(!a.isEmpty()){
			for (Map<String,Object> r: a) {
				str += ","+r.get(field);
			}
		}
		if(str.startsWith(",")){
			str = str.substring(1);
		}
		return str;
	}
	
	public static String  getIDS(List<Record> a,String field){
		String str = "";
		if(!a.isEmpty()){
			for (Record r: a) {
				str += ","+r.get(field);
			}
		}
		if(str.startsWith(",")){
			str = str.substring(1);
		}
		return str;
	}
	public static String  getCodeS(List<Record> a,String field){
		String str = "";
		if(!a.isEmpty()){
			for (Record r: a) {
				str += ",'"+r.get(field)+"'";
			}
		}
		if(str.startsWith(",")){
			str = str.substring(1);
		}
		return str;
	}
	
	public static Map<String,Record> listToMap(List<Record> list,String key){
		if(list.isEmpty()){
			return new HashMap<String, Record>();
		}else{
			 Map<String,Record> map = new HashMap<String, Record>();
			for (Record r: list) {
				map.put(r.get(key)+"",r );
			}
			return map;
		}
	}
}
