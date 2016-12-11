package com.net.wx.util;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.kit.StrKit;

public class StringTools {

	public static final int SALT_SIZE = 8;
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;

	/**
	 * 生成复杂的邀请码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptCode(String plainCode) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainCode.getBytes(), salt,
				HASH_INTERATIONS);
		String temp = Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
		return temp.substring(0, SALT_SIZE).toUpperCase();
	}

	/**
	 * 生成简单的邀请码
	 * @param plainCode
	 * @return
	 */
	public static String entrytCodeSimple(String plainCode){
		return plainCode.substring(0, SALT_SIZE).toUpperCase();
	}
	
	/**
	 * 获取随机生成UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 判断邀请码匹配
	 * 
	 * @param password
	 * @param saved
	 * @return
	 */
	public static boolean codeMatch(String code, String compareCode) {
		return StringUtils.equals(entryptCode(code.toString()),
				compareCode);
	}
	
	/**
	 * 逗号字符串文本转换增加单引号
	 * @param str
	 * @return
	 */
	public static String transformInStr(String str){
		StringBuilder sb = new StringBuilder();
		if (StrKit.notBlank(str)) {
			String[] arr = str.split(",");
			for (int i = 0; i < arr.length; i++) {
				sb.append("'").append(escapeStr(arr[i])).append("'");
				if (i != arr.length - 1) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 替换字符串 中的单引号字符
	 * @param str
	 * @return
	 */
	public static String escapeStr(String str){
		if (StrKit.notBlank(str) && str.contains("'")) {
			str = str.replace("'", "\\'");
		}
		return str;
	}
	
	/**
	 * 
	 * hiddenPhone:隐藏手机号码. <br/>
	 * @author Administrator
	 * @param phone
	 * @return
	 * @since JDK 1.6
	 */
	public static String hiddenPhone(String phone){
		String ret = "";
		if(StrKit.notBlank(phone) && phone.length() >= 7){
			ret = phone.substring(0, phone.length() - (phone.substring(3)).length())+"****"+phone.substring(7);
		}else if(StrKit.isBlank(phone)||"null".equals(phone)||"NULL".equals(phone)){
		}else{
			ret = phone;
		}
		return ret;
	}
	
	/**
	 * 
	 * hiddenIdCard:隐藏身份证号码. <br/>
	 * @author Administrator
	 * @param idCard
	 * @return
	 * @since JDK 1.6
	 */
	public static String hiddenIdCard(String idCard){
		String ret = "";
		if(StrKit.notBlank(idCard) && idCard.length() >= 15){
			ret = idCard.substring(0, idCard.length() - (idCard.substring(4)).length())+"***********"+idCard.substring(idCard.length()-3);
		}else{
			ret = idCard;
		}
		return ret;
	}
	
	/**
	 * isContainsStr:判断目标字符串是否在in字符串中
	 * @author lujianmin
	 * @param inStr
	 * @param targetStr
	 * @return
	 */
	public static boolean isContainsStr(String inStr,String targetStr){
		if (StrKit.notBlank(inStr) && StrKit.notBlank(targetStr)) {
			String[] arr = inStr.split(",");
			for (int i = 0; i < arr.length; i++) {
				if (targetStr.equals(arr[i])) {
					return true;
				}
			}
		}
		return false;
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
	
	public static void main(String[] args) {
		//System.out.println(hiddenPhone("--"));
		//System.out.println(hiddenIdCard("420119650366611"));
		boolean result = isContainsStr("1,2,3,4", "1");
		System.out.println(result);
	}
}
