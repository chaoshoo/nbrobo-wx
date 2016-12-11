package com.net.util;

import java.util.Random;

/**
 * 
 * ClassName: RandomUtil <br/>
 * Function: Generating random number of tools. <br/>
 * date: 2016year6month20day Afternoon4:38:40 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.6
 */
public class RandomUtil {
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";

	/**
	 * Returns a fixed length random string(Only contains letters、number)
	 * 
	 * @param length
	 *            Random string length
	 * @return Random string
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * Returns a fixed length random string(Only contains letters)
	 * 
	 * @param length
	 *            Random string length
	 * @return Random string
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}
	
	/**
	 * Returns a fixed length random string(Only contains numbers)
	 * 
	 * @param length
	 *            Random string length
	 * @return Random string
	 */
	public static String generateNumber(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * Returns a fixed length random pure capital letter string(Only contains letters)
	 * 
	 * @param length
	 *            Random string length
	 * @return Random string
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * Returns a fixed length of the random lowercase character string(Only contains letters)
	 * 
	 * @param length
	 *            Random string length
	 * @return Random string
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * Generating a fixed length of pure0Character string
	 * 
	 * @param length
	 *            String length
	 * @return pure0Character string
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * Generates a fixed length string based on the number，The length is not enough to fill up0
	 * 
	 * @param num
	 *            number
	 * @param fixdlenth
	 *            String length
	 * @return Fixed length string
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("Will number" + num + "Transform to length" + fixdlenth
					+ "String exception！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * Generated each timelenBits are not the same
	 * 
	 * @param param
	 * @return Fixed length number
	 */
	public static int getNotSimple(int[] param, int len) {
		Random rand = new Random();
		for (int i = param.length; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = param[index];
			param[index] = param[i - 1];
			param[i - 1] = tmp;
		}
		int result = 0;
		for (int i = 0; i < len; i++) {
			result = result * 10 + param[i];
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(10));
		System.out
				.println("Returns a fixed length random string(Only contains letters):" + generateMixString(10));
		System.out
		.println("Returns a fixed length of a random string.:" + generateNumber(10));
		System.out.println("返回一个定长的随机纯大写字母字符串(只包含大小写字母):"
				+ generateLowerString(10));
		System.out.println("返回一个定长的随机纯小写字母字符串(只包含大小写字母):"
				+ generateUpperString(10));
		System.out.println("生成一个定长的纯0字符串:" + generateZeroString(10));
		System.out.println("根据数字生成一个定长的字符串，长度不够前面补0:"
				+ toFixdLengthString(123, 10));
		int[] in = { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println("每次生成的len位数都不相同:" + getNotSimple(in, 3));
	}
}