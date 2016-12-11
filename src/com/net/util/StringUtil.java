package com.net.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	
	final static Pattern INT_PATTERN = Pattern.compile("^[-\\+]?[\\d]+$");    
	final static SimpleDateFormat DATE_TIME_PATTERN = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * To determine whether a string is an integer
	 * @param str
	 * @return boolean If it is an integer，Returntrue；Otherwise returnfalse
	 */
	public static boolean isInteger(String str) {    
		 return INT_PATTERN.matcher(str).matches();    
	}  
	
	public static String now(){
		return DATE_TIME_PATTERN.format(Calendar.getInstance().getTime());
	}

	public static String timestamp(){
		Calendar cal = Calendar.getInstance();
		return DATE_TIME_PATTERN.format(cal.getTime()) + cal.getTimeInMillis() ;
	}
	/**
	 * format conversion
	 * @param usesecond  Number of seconds
	 * @return  Return  mm:dd  Format use
	 */
	public static String getTimeStr(int usesecond){
		int minute = usesecond/60;
		int second = usesecond%60;
		String usertimestr = minute<10?"0"+minute:minute+"";
		usertimestr += ":";
		usertimestr += second<10?"0"+second:second+"";
		return usertimestr;
	}
	
	/**
	 * 
	 * removeDuplicate:adoptHashSetKick in addition to repeat elements
	 * @author lujianmin
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicate(List list) { 
		HashSet h = new HashSet(list); 
		list.clear(); 
		list.addAll(h); 
		return list; 
	} 
	
	/**
	 * getRandomString:Generates a random string of the specified length
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // lengthRepresents the length of the generated string
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * Order from small to large Comma separated list of components idsTemporary support number
	 * 
	 * @param ids
	 * @return
	 */
	public static String sort(String[] ids) {
		if(ids == null || ids.length == 0){
			return "";
		}else if(ids.length ==1){
			return ids[0];
		}
		TreeSet<Integer> tset = new TreeSet<Integer>();
		for (int j = 0; j < ids.length; j++) {
			if (!StringUtils.isEmpty(ids[j])) {
				tset.add(Integer.parseInt(ids[j]));
			}
		}
		Integer[] id_ = new Integer[tset.size()];
		id_ = tset.toArray(id_);
		StringBuffer id = new StringBuffer();
		for (int i = 0; i < id_.length; i++) {
			id.append(",").append(id_[i]);
		}
		if (id.length() > 1) {
			return id.substring(1);
		} else {
			return id.toString();
		}
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		list.add("111");
		System.out.println(list);
		List<String> list2 = StringUtil.removeDuplicate(list);
		System.out.println(list2);
		
		System.out.println(sort(",1,5,2,30,20,11,33,".split(",")));
	}
}