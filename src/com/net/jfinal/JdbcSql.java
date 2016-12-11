package com.net.jfinal;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class JdbcSql {
	protected static Map<String,String> jdbcSql = Maps.newHashMap();
	
	public static String getSql(String sqlid){
		return jdbcSql.get(sqlid);
	}
	
	public static String getAutoSql(String sqlid,String ...fields){
		String sql =  jdbcSql.get(sqlid);
		String where = "";
		for (int i = 0; i < fields.length; i++) {
			where += " and "+ fields[i]+" = ? ";
		}
		sql = sql.replace("${where}", where);
		return sql;
	}
	
	public static String getReplaceSql(String sqlid,String where){
		String sql =  jdbcSql.get(sqlid);
		sql = sql.replace("${where}", where);
		return sql;
	}
	/**
	 * 
	 * @param sqlid
	 * @param map
	 * @param returnParams  Returned parameter result set
	 * @return
	 */
	public static String getAutoSql(String sqlid,Map<String,Object> map,List<Object> returnParams){
		String sql =  jdbcSql.get(sqlid);
		String where = "";
		for (String key : map.keySet()) {
			if(map.get(key) != null && !"".equals(map.get(key).toString())){
				where += " and "+ key+" = ? ";
				returnParams.add(map.get(key));
			}
		}
		sql = sql.replace("${where}", where);
		return sql;
	}
	
	static{
		//sqlid  全名要规范  类名+名字
		//jdbcSql.put("jdbcsql_selecttest", "select * from test");
	}
}