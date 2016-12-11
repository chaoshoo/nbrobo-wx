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
	 * 判断一个字符串是不是整数
	 * @param str
	 * @return boolean 如果是整数，返回true；否则返回false
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
	 * 格式转换
	 * @param usesecond  送入秒数
	 * @return  返回  mm:dd  格式用时
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
	 * removeDuplicate:通过HashSet踢除重复元素
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
	 * getRandomString:生成指定长度的随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
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
	 * 从小到大排序 组成字符串用逗号分隔 ids暂只支持数字
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
