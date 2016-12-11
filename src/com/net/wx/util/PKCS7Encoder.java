package com.net.wx.util;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Provide basedPKCS7Encryption and decryption interface
 */
public class PKCS7Encoder {

    private static Charset CHARSET = Charset.forName("utf-8");
	private static int BLOCK_SIZE = 32;

	/**
	 * To receive a fill in the text to fill the bytes
	 * 
	 * @param count Number of plain text bytes required to fill in
	 * @return Filled with an array of bytes
	 */
	public static byte[] encode(int count) {

		// 计算需要填充的位数
		int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
		if (amountToPad == 0) {
			amountToPad = BLOCK_SIZE;
		}
		// 获得补位所用的字符
		char padChr = chr(amountToPad);
		String tmp = new String();
		for (int index = 0; index < amountToPad; index++) {
			tmp += padChr;
		}
		return tmp.getBytes(CHARSET);
	}

	/**
	 * Remove and decrypt the encrypted character
	 * 
	 * @param decrypted After the decryption of the text
	 * @return Clear text after the deletion of the fill character
	 */
	public static byte[] decode(byte[] decrypted) {
		int pad = (int) decrypted[decrypted.length - 1];
		if (pad < 1 || pad > 32) {
			pad = 0;
		}
		return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
	}

	/**
	 * Convert the number intoASCIICode corresponding to the character，Used to complement the plaintext
	 * 
	 * @param a Need to transform the number
	 * @return Transformed character
	 */
	public static char chr(int a) {
		byte target = (byte) (a & 0xFF);
		return (char) target;
	}

}