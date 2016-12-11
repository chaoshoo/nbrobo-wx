package com.net.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.huilet.util.DateUtil;
import com.huilet.util.PubMethod;
import com.jfinal.plugin.activerecord.Record;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;



public class JsonUtil {
	
	public static ValueFilter filter = new ValueFilter() {
	    @Override
	    public Object process(Object obj, String s, Object v) {
	    if(v==null)
	        return "";
	    return v;
	    }
	};
	/**
     * From oneJSON Object character format to get ajavaobject
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    public static Object getObject4JsonString(String jsonString,Class pojoCalss){
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject( jsonString );  
        pojo = JSONObject.toBean(jsonObject,pojoCalss);
        return pojo;
    }
    /**
     * fromjson HASHObtain an expression in themap，changemapSupport nested functions
     * @param jsonString
     * @return
     */
    public static Map getMap4Json(String jsonString){
        JSONObject jsonObject = JSONObject.fromObject( jsonString );  
        Iterator  keyIter = jsonObject.keys();
        String key;
        Object value;
        Map valueMap = new HashMap();

        while( keyIter.hasNext())
        {
            key = (String)keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }
        
        return valueMap;
    }
    
    
    /**
     * fromjsonArray to get the correspondingjavaarray
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
        
    }
    
    
    /**
     * fromjsonObject set expression to get ajavaObject list
     * @param jsonString
     * @param pojoClass
     * @return
     */
    public static List getList4Json(String jsonString, Class pojoClass){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;
        
        List list = new ArrayList();
        for ( int i = 0 ; i<jsonArray.size(); i++){
            
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject,pojoClass);
            list.add(pojoValue);
            
        }
        return list;

    }
    
    /**
     * fromjsonArray analysisjavaString array
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            stringArray[i] = jsonArray.getString(i);
            
        }
        
        return stringArray;
    }
    
    /**
     * fromjsonArray analysisjavaLongType object array
     * @param jsonString
     * @return
     */
    public static Long[] getLongArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            longArray[i] = jsonArray.getLong(i);
            
        }
        return longArray;
    }
    
    /**
     * fromjsonArray analysisjava IntegerType object array
     * @param jsonString
     * @return
     */
    public static Integer[] getIntegerArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            integerArray[i] = jsonArray.getInt(i);
            
        }
        return integerArray;
    }
    
    /**
     * fromjsonArray analysisjava Date Type object array，The use of this method must be guaranteed
     * @param jsonString
     * @return
     */
    public static Date[] getDateArray4Json(String jsonString,String DataFormat){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Date[] dateArray = new Date[jsonArray.size()];
        String dateString;
        Date date;
        
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            dateString = jsonArray.getString(i);
            date = DateUtil.strForDate(dateString, DataFormat);
            dateArray[i] = date;
            
        }
        return dateArray;
    }
    
    /**
     * fromjsonArray analysisjava IntegerType object array
     * @param jsonString
     * @return
     */
    public static Double[] getDoubleArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            doubleArray[i] = jsonArray.getDouble(i);
            
        }
        return doubleArray;
    }
    
    
    /**
     * takejavaConvert objectjsonCharacter string
     * @param javaObj
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj){
        
        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();
        
    }
    
    
    
    
    /**
     * takejavaConvert objectjsonCharacter string,And set date format
     * @param javaObj
     * @param dataFormat
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj , String dataFormat){
        
        JSONObject json;
        JsonConfig jsonConfig = configJson(dataFormat);
        json = JSONObject.fromObject(javaObj,jsonConfig);
        return json.toString();
        
        
    }
    
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自动生成方法存根

    }
    
    /**
     * JSON Time resolution tool
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String datePattern) {   
            JsonConfig jsonConfig = new JsonConfig();   
            jsonConfig.setExcludes(new String[]{""});   
            jsonConfig.setIgnoreDefaultExcludes(false);   
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
            jsonConfig.registerJsonValueProcessor(Date.class,   
                new DateJsonValueProcessor(datePattern));   
          
            return jsonConfig;   
        }  
    
    /**
     * 
     * @param excludes
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String[] excludes,   
            String datePattern) {   
            JsonConfig jsonConfig = new JsonConfig();   
            jsonConfig.setExcludes(excludes);   
            jsonConfig.setIgnoreDefaultExcludes(false);   
            jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
            jsonConfig.registerJsonValueProcessor(Date.class,   
                new DateJsonValueProcessor(datePattern));   
          
            return jsonConfig;
        }
    
   /**
    * takeJSONObject Convert to   Map
    * @param json
    * @return
    * @author yuanhuaihao
    * company huilet
    * 2013-5-6
    */
	public static HashMap<String, Object> getMapByJsonObject(JSONObject json){
		HashMap<String, Object> map = new HashMap<String, Object>();
		Set<?> keys = json.keySet();
		for(Object key : keys){
			Object o = json.get(key);
			if(o instanceof JSONArray)
				map.put((String) key, getListByJsonArray((JSONArray) o));
			else if(o instanceof JSONObject)
				map.put((String) key, getMapByJsonObject((JSONObject) o));
			else
				map.put((String) key, o);
		}
		return map;
	}
	
	
	/**
	 * takeJSONArray Convert to List
	 * @param json
	 * @return
	 * @author yuanhuaihao
	 * company huilet
	 * 2013-5-6
	 */
	public static List getListByJsonArray(JSONArray json){
		List<Object> list = new ArrayList<Object>();
		for(Object o : json){
			if(o instanceof JSONArray)
				list.add(getListByJsonArray((JSONArray) o));
			else if(o instanceof JSONObject)
				list.add(getMapByJsonObject((JSONObject) o));
			else
				list.add(o);
		}
		return list;
	}
	
	/**
	 * List translate intoJSONArray
	 * @param list
	 * @return
	 * @author yuanhuaihao
	 * company huilet
	 * 2013-5-6
	 */
	public static JSONArray getJsonArrayByList(List list){
		JSONArray obj = new JSONArray();
		//System.out.println(JSONArray.fromObject(list));
		return JSONArray.fromObject(list);
	}
	
	/**
	 * Map translate intoJSONObject
	 * @param map
	 * @return
	 * @author yuanhuaihao
	 * company huilet
	 * 2013-5-6
	 */
	public static JSONObject getJsonObjByMap(Map map){
		return JSONObject.fromObject(map);
	}
	
	public static HashMap getMapByJfinalRecord(Record rc){
		HashMap map = new HashMap();
		String[] names = rc.getColumnNames();
		for (String name :names) {
			String  v = rc.get(name)+"";
			if(PubMethod.isEmpty(v)|| "<null>".equals(v)){
				v = "";
			}
			map.put(name.toLowerCase(), v);
		}
		
		return map;
	}
	public static JSONArray getJsonObjByjfinalList(List<Record> rec) {
		// TODO Auto-generated method stub
		List<HashMap> list =  new ArrayList<HashMap>();
		for(Record r: rec){
			list.add(getMapByJfinalRecord(r));
		}
		return getJsonArrayByList(list);
	}
	
	public static List<Map> getList(List<Record> list){
		List<Map> lis = new ArrayList<Map>();
		for(Record rec:list){
			lis.add(getMapByJfinalRecordToLowerCase(rec));
		}
		return lis;
	}
	
	public static HashMap getMapByJfinalRecordToLowerCase(Record rc){
		HashMap map = new HashMap();
		String[] names = rc.getColumnNames();
		for (String name :names) {
			map.put(name.toLowerCase(), rc.get(name)+"");
		}
		
		return map;
	}
	
	
	public static String toJSONString(Object obj){
		return JSON.toJSONString(obj,filter);
	}
}