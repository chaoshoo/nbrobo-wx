package com.net.jfinal;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.net.ServiceConstants;
import com.net.entity.bo.ScriptPage;
import com.net.util.Reflections;

public class JFinalDb extends Db{
	static Logger log = LoggerFactory.getLogger(JFinalDb.class);
			
	public static <T extends JFinalEntity> boolean save(T  obj) throws Exception {
		//Reflections.get
		Record record = setEntity(obj);
		boolean flag = Db.save(obj.tableName(), record);
		obj.setId(record.getLong("id"));
		return flag;
	}
	public static <T extends JFinalEntity> boolean update(T obj) throws Exception {
		//Reflections.get
		Record record = setEntity(obj);
		return Db.update(obj.tableName(), record);
	}
	public static <T extends JFinalEntity> int delete(T obj) throws Exception {
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(obj.tableName()).append(" where 1=1 ");
		List<Object> paras = Lists.newArrayList();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Object o = Reflections.getFieldValue(obj, fields[i].getName());
			if(o != null){
				paras.add(o);
				sql.append(" and ").append(fields[i].getName().toLowerCase()).append(" = ? ");
			}
		}
		if(paras.size() < 1){
			throw new Exception("Delete must condition");
		}
		return Db.update(sql.toString(), paras.toArray());
	}
	
	public static <T extends JFinalEntity> List<T> find(Class<T> clazz,String sql,Object ...paras){
		List<Record> list = Db.find(sql, paras);
		return changeList(list, clazz);
	}
	public static <T extends JFinalEntity> T findFirst(Class<T> clazz,String sql,Object ...paras){
		List<Record> list = Db.find(sql, paras);
		if(list == null){
			return null;
		}else{
			return getEntity(list.get(0),clazz);
		}
	}
	
	public static <T  extends JFinalEntity> List<T> find(T obj) throws Exception{
		StringBuffer sql = new StringBuffer("select * from ");
//		Object tableName = Reflections.invokeMethodByName(obj, "getTableName", null);
		sql.append(obj.tableName()).append(" where 1=1 ");
		List<Object> paras = Lists.newArrayList();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Object o = Reflections.getFieldValue(obj, fields[i].getName());
			if(o != null){
				paras.add(o);
				sql.append(" and ").append(fields[i].getName().toLowerCase()).append(" = ? ");
			}
		}
		if(obj.getId() != null){
			sql.append(" and ").append("id=").append(obj.getId());
		}
		List<Record> list = Db.find(sql.toString(), paras.toArray());
		return (List<T>) changeList(list, obj.getClass());
	}
	public static <T  extends JFinalEntity> T findFirst(T obj) throws Exception{
		List<T> list = find(obj);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	public static <T  extends JFinalEntity> T findById(Class<T> clazz,Object id) throws Exception{
		try {
			Class<?> cls = Class.forName(clazz.getName());
			Object tableName = Reflections.invokeMethodByName(cls.newInstance(), "getTableName", null);
			Record record = Db.findById((String)tableName, id);
			return getEntity(record, clazz);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public static <T  extends JFinalEntity> List<T> changeList(List<Record> list ,Class<T> clazz){
		if(list.isEmpty()){
			return Lists.newArrayList();
		}else{
			List<T> ls = Lists.newArrayList();
			for (Record record :list) {
				ls.add(getEntity(record, clazz));
			}
			return ls;
		}
	}
	/**
	 * Field using object declarations To reflect
	 * @param record
	 * @param clazz
	 * @return
	 */
	public static <T  extends JFinalEntity> T getEntity(Record record,Class<T> clazz) {
		T t = null;
		try {
			Class<?> cls = Class.forName(clazz.getName());
			t = (T) cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
					Reflections.setFieldValue(t, fields[i].getName(), record.get(fields[i].getName().toLowerCase()));
			}
			if(record.get("id") != null){
				t.setId(Long.parseLong(record.get("id").toString()));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	/**
	 * utilizerecordThecolumn To reflect
	 * @param record
	 * @param clazz
	 * @return
	 */
	public static <T  extends JFinalEntity> T getEntityByRecordColumn(Record record,Class<T> clazz) {
		T t = null;
		try {
			Class<?> cls = Class.forName(clazz.getName());
			t = (T) cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			Map<String,Object> map = record.getColumns();
			for (String key : map.keySet()) {
				if(key.toLowerCase().equals("id")){
					t.setId(Long.parseLong(record.get("id").toString()));	
				}
				try {
					Reflections.setFieldValue(t, key.toLowerCase(), record.get(key));
				} catch (Exception e) {
					log.error(clazz.getName()+"Class variable"+key.toLowerCase()+"NosetMethod");
				}
			}			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	public static <T  extends JFinalEntity> Record setEntity(T obj){
		Record record = new Record();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			//exclude serialNum
			if(fields[i].getName().equalsIgnoreCase("SERIALVERSIONUID")){
				continue;
			}
			Object value = Reflections.getFieldValue(obj, fields[i].getName());
			if(value != null){
				record.set(fields[i].getName(), value);
			}
		}
		record.set("id",obj.getId());
		return record;
	}
	
	public static <T extends JFinalEntity> ScriptPage findPage(T obj,String orderstr) throws Exception {
		return findPage(1, ServiceConstants.defaultPageSize, obj, orderstr);
	}

	
	public static <T extends JFinalEntity> ScriptPage findPage(int pageNo,int pageSize,T obj,String orderstr) 
			throws Exception {
		ScriptPage spage = new ScriptPage();
		String select = "select * ";
		StringBuffer sqlExceptSelect = new StringBuffer(" from ");
		sqlExceptSelect.append(obj.tableName());
		sqlExceptSelect.append(" where 1=1 ");
		List<Object> paras = Lists.newArrayList();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Object o = Reflections.getFieldValue(obj, fields[i].getName());
			if (o != null) {
				paras.add(o);
				sqlExceptSelect.append(" and ").append(fields[i].getName()).append(" = ? ");
			}
		}
		if (!StringUtils.isEmpty(orderstr)) {
			sqlExceptSelect.append(" order by ").append(orderstr);
		}
		Page<Record> pages = null;
		if (paras.isEmpty()) {
			pages = Db.paginate(pageNo, pageSize, select, sqlExceptSelect.toString());
		} else {
			System.out.println("paras::" + paras);
			pages = Db.paginate(pageNo, pageSize, select, sqlExceptSelect.toString(), paras);
		}
		spage.setTotal(pages.getTotalRow());
		spage.setRows(changeList(pages.getList(), obj.getClass()));
		return spage;
	}
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sql   Dynamic variable combination ${where} replace
	 * @param mapwhere
	 * @param orderstr
	 * @return
	 * @throws Exception
	 */
	public static ScriptPage findPage(int pageNo,int pageSize,String sql,Map<String,Object> mapwhere,String orderstr) throws Exception {
		return findPage(pageNo, pageSize, sql, mapwhere, orderstr, null);
	}
	public static ScriptPage findPageSql(int pageNo,int pageSize,String sql,Object... obj) throws Exception {
		return findPage(pageNo, pageSize, sql, null, null, obj);
	}
	/**
	 * 
	 * @param pageNo   The number of pages
	 * @param pageSize  Page size
	 * @param sql  sql Sentence
	 * @param mapwhere  Parameters to be assembled
	 * @param orderstr  sort
	 * @param params  Fixed parameter
	 * @return
	 * @throws Exception
	 */
	public static ScriptPage findPage(int pageNo,int pageSize,String sql,Map<String,Object> mapwhere,String orderstr,Object ...params) throws Exception {
		ScriptPage spage = new ScriptPage();
		int x =  sql.toLowerCase().indexOf(" from ");
		String select = sql.substring(0,x);
		StringBuffer sqlExceptSelect = new StringBuffer(sql.substring(x));
		StringBuffer where = new StringBuffer();
		String key = null;
		if(mapwhere == null){
			mapwhere = new HashMap<String, Object>();
		}
		for (Entry<String, Object> en: mapwhere.entrySet()) {
			if(en.getValue() == null){
				continue;
			}else{
				key = en.getKey();
				if(key.startsWith("LIKE-")){
					where.append(" and ").append(key.substring(5)).append(" like '%").append(en.getValue()).append("%'");
				}else if(key.startsWith("GEQ-")){
					where.append(" and ").append(key.substring(4)).append(" >= '").append(en.getValue()).append("'");
				}else if(key.startsWith("LEQ-")){
					where.append(" and ").append(key.substring(4)).append(" <= '").append(en.getValue()).append("'");
				}else if(key.startsWith("EQ-")){
					where.append(" and ").append(key.substring(3)).append(" = '").append(en.getValue()).append("'");
				}else if(key.startsWith("GRE-")){
					where.append(" and ").append(key.substring(4)).append(" > '").append(en.getValue()).append("'");
				}else if(key.startsWith("LE-")){
					where.append(" and ").append(key.substring(3)).append(" < '").append(en.getValue()).append("'");
				}else{
					where.append(" and ").append(key).append(" = '").append(en.getValue()).append("'");
				}
			}
		}
		if(!StringUtils.isEmpty(orderstr)){
			sqlExceptSelect.append(" order by ").append(orderstr) ; 
		}
		String sqlExceptSelectstr = sqlExceptSelect.toString().replace("${where}", where.toString());
		Page<Record> pages = null;
		if(params == null || params.length < 1){
			pages = Db.paginate(pageNo, pageSize, select, sqlExceptSelectstr);
		}else{
			pages = Db.paginate(pageNo, pageSize, select, sqlExceptSelectstr,params);
		}
		spage.setTotal(pages.getTotalRow());
		spage.setRows(changeList(pages.getList()));
		return spage;
	}
	
	public static List<Map<String,Object>> changeList(List<Record> list){
		List<Map<String,Object>> l = Lists.newArrayList();
		if(list.isEmpty()){
			return l;
		}else{
			for (Record r : list) {
				l.add(r.getColumns());
			}
		}
		return l;
	}

	/**************In the light ofJdbcSqlTreatment***************/
	public static int executeBySqlid(String sqlid,Object paras){
		return Db.update(JdbcSql.getSql(sqlid), paras);
	}
	public static List<Record> findBySqlid(String sqlid,Object ...paras){
		return Db.find(JdbcSql.getSql(sqlid), paras);
	}
	public static List<Record> findByMapSqlid(String sqlid,Map<String,Object> paras){
		List<Object> list = Lists.newArrayList();
		String sql = JdbcSql.getAutoSql(sqlid, paras, list);
		return Db.find(sql, list.toArray());
	}
	public static ScriptPage findPageBySqlid(int pageNo,int pageSize,String sqlid,Map<String,Object> mapwhere,String orderstr) throws Exception {
		return findPage(pageNo, pageSize, JdbcSql.getSql(sqlid), mapwhere, orderstr);
	}
	public static ScriptPage findPageBySqlid(int pageNo,int pageSize,String sqlid,Map<String,Object> mapwhere,String orderstr,Object ...params) throws Exception {
		return findPage(pageNo, pageSize, JdbcSql.getSql(sqlid), mapwhere, orderstr,params);
	}
 
}