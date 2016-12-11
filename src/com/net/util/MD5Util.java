package com.net.util;

import java.security.MessageDigest;

public class MD5Util {

//	private static String byteArrayToHexString(byte b[]) {
//		StringBuffer resultSb = new StringBuffer();
//		for (int i = 0; i < b.length; i++)
//			resultSb.append(byteToHexString(b[i]));
//
//		return resultSb.toString();
//	}
//
//	private static String byteToHexString(byte b) {
//		int n = b;
//		if (n < 0)
//			n += 256;
//		int d1 = n / 16;
//		int d2 = n % 16;
//		return hexDigits[d1] + hexDigits[d2];
//	}
//
//	public static String MD5Encode(String origin, String charsetname) {
//		String resultString = null;
//		try {
//			resultString = new String(origin);
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			if (charsetname == null || "".equals(charsetname))
//				resultString = byteArrayToHexString(md.digest(resultString
//						.getBytes()));
//			else
//				resultString = byteArrayToHexString(md.digest(resultString
//						.getBytes(charsetname)));
//		} catch (Exception exception) {
//		}
//		return resultString;
//	}
//
//	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
//			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
//
//	public static String Sign(String content, String md5Key) {
//        try {
//            return Sign(content, md5Key,"GBK");
//        } catch (IllegalArgumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static String Sign(String content, String md5Key,String charset)
//            throws IllegalArgumentException {
//        String signStr = "";
//
//        if ("" == md5Key) {
//            throw new IllegalArgumentException("财付通签名key不能为空！");
//        }
//        if ("" == content) {
//            throw new IllegalArgumentException("财付通签名内容不能为空");
//        }
//        signStr = content + "&key=" + md5Key;
//
//        return MD5Util.MD5(signStr,charset).toUpperCase();
//
//    }
    public final static String MD5(String s,String charset) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes(charset);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
       }
    }
    
    public static void main(String arg[]){
    	String  sss  = "JH81f8c5a2e5b2ff903b8b984665560d2b6415700110001000114200004926960902015092404691247";
    	System.out.println(MD5(sss,"UTF-8").toLowerCase());
    	System.out.println(MD5("123456","UTF-8").toLowerCase());
    }

}