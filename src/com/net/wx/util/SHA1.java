package com.net.wx.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Formatter;

/**
 * SHA1A message signature interface for a public platform.
 */
public class SHA1 {

    /**
     * useSHA1Algorithm generating secure signature
     *
     * @param params [token, timestamp, nonce, encrypt]
     * @return Secure signature
     * @throws com.qq.weixin.mp.aes.AesException
     */
    public static String calculate(String... params) throws AesException {
        try {
            String[] array = params;
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            int len = params.length;
            for (int i = 0; i < len; i++) {
                sb.append(array[i]);
            }

            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(new String(sb).getBytes("UTF-8"));

            // HEX输出
            byte[] hash = md.digest();
            Formatter formatter = new Formatter();
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            String hex = formatter.toString();
            formatter.close();
            return hex;
        } catch (Exception e) {
            throw new AesException(AesException.ComputeSignatureError);
        }
    }
}