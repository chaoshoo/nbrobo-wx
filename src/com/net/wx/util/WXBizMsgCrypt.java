package com.net.wx.util;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Provide the encryption and decryption interface to receive and push information to the public platform(UTF8Coded string).
 * <ol>
 * 	<li>Third party reply to the public platform to encrypt messages to the public</li>
 * 	<li>Third party received a message from the public platform，Verify the security of the message，And decrypt the message。</li>
 * </ol>
 * Explain：abnormaljava.security.InvalidKeyException:illegal Key SizeSolution of
 * <ol>
 * 	<li>Download at the official websiteJCEUnlimited access policy file（JDK7Download address：
 *      http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html</li>
 * 	<li>Download after decompression，Can seelocal_policy.jarandUS_export_policy.jaras well asreadme.txt</li>
 * 	<li>If installedJRE，Will twojarFile to%JRE_HOME%\lib\securityCover the original file directory</li>
 * 	<li>If installedJDK，Will twojarFile to%JDK_HOME%\jre\lib\securityDirectory to cover the original document</li>
 * </ol>
 */
public class WXBizMsgCrypt {

	private static Charset CHARSET = Charset.forName("utf-8");
	private Base64 base64 = new Base64();
	private byte[] aesKey;
	private String token;
	private String appId;
    private String fromAppId;

	/**
	 * Constructor
	 * @param token Public platform，Developer settoken
	 * @param encodingAesKey Public platform，Developer setEncodingAESKey
	 * @param appId Public platformappid
	 * 
	 * @throws AesException Execution failed，Please check the exception error code and the specific error message
	 */
	public WXBizMsgCrypt(String token, String encodingAesKey, String appId) throws AesException {
		if (encodingAesKey.length() != 43) {
			throw new AesException(AesException.IllegalAesKey);
		}

		this.token = token;
		this.appId = appId;
		aesKey = Base64.decodeBase64(encodingAesKey + "=");
	}

	// 生成4个字节的网络字节序
	private byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}

	// 还原4个字节的网络字节序
	private int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}

	// 随机生成16位字符串
	private String getRandomStr() {
        StringBuffer sb = new StringBuffer();
        Random ran = new Random();
        for(int i=0; i<16; i++) {
            boolean flag = ran.nextInt(2) % 2 == 0;
            if(flag) {
                char c = (char) (int) (Math.random() * 26 + 97);
                sb.append(c);
            } else {
                sb.append(ran.nextInt(10));
            }
        }
        return sb.toString();
	}

	/**
	 * Encrypt the plain text.
	 *
	 * @param text Need to encrypt the text
	 * @return After encryptionbase64Coded string
	 * @throws AesException aesEncryption failed
	 */
	private String encrypt(String randomStr, String text) throws AesException {
		ByteGroup byteCollector = new ByteGroup();
		byte[] randomStrBytes = randomStr.getBytes(CHARSET);
		byte[] textBytes = text.getBytes(CHARSET);
		byte[] networkBytesOrder = getNetworkBytesOrder(textBytes.length);
		byte[] appidBytes = appId.getBytes(CHARSET);

		// randomStr + networkBytesOrder + text + appid
		byteCollector.addBytes(randomStrBytes);
		byteCollector.addBytes(networkBytesOrder);
		byteCollector.addBytes(textBytes);
		byteCollector.addBytes(appidBytes);

		// ... + pad: 使用自定义的填充方式对明文进行补位填充
		byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
		byteCollector.addBytes(padBytes);

		// 获得最终的字节流, 未加密
		byte[] unencrypted = byteCollector.toBytes();

		try {
			// 设置加密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

			// 加密
			byte[] encrypted = cipher.doFinal(unencrypted);

			// 使用BASE64对加密后的字符串进行编码
			String base64Encrypted = base64.encodeToString(encrypted);

			return base64Encrypted;
		} catch (Exception e) {
			throw new AesException(AesException.EncryptAESError);
		}
	}

	/**
	 * To decrypt the cipher text.
	 *
	 * @param text Need to decrypt the encrypted
	 * @return The decryption is clear
	 * @throws AesException aesDecryption failure
	 */
	private String decrypt(String text) throws AesException {
		byte[] original;
		try {
			// 设置解密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

			// 使用BASE64对密文进行解码
			byte[] encrypted = Base64.decodeBase64(text);

			// 解密
			original = cipher.doFinal(encrypted);
		} catch (Exception e) {
			throw new AesException(AesException.DecryptAESError);
		}

		String xmlContent;
		try {
			// 去除补位字符
			byte[] bytes = PKCS7Encoder.decode(original);

			// 分离16位随机字符串,网络字节序和AppId
			byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

			int xmlLength = recoverNetworkBytesOrder(networkOrder);

			xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
            fromAppId = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
					CHARSET);
		} catch (Exception e) {
			throw new AesException(AesException.IllegalBuffer);
		}

		// appid不相同的情况
		if (!fromAppId.equals(appId)) {
			throw new AesException(AesException.ValidateAppidError);
		}
		return xmlContent;

	}

	/**
	 * The public platform to reply to the user's message encryption package.
	 * <ol>
	 * 	<li>The message to be sentAES-CBCencryption</li>
	 * 	<li>Secure signature generation</li>
	 * 	<li>The message is encrypted and secure signatures packaged intoxmlformat</li>
	 * </ol>
	 *
	 * @param replyMsg Public platform to reply to the user's message，xmlFormat string
	 * @param timeStamp time stamp，Can generate their own，Can also be usedURLParametertimestamp
	 * @param nonce Random string，Can generate their own，Can also be usedURLParameternonce
	 *
	 * @return Encrypted can be directly reply to the user's encrypted，Includemsg_signature, timestamp, nonce, encryptThexmlFormat string
	 * @throws AesException Execution failed，Please check the exception error code and the specific error message
	 */
	public String encryptMsg(String replyMsg, String timeStamp, String nonce) throws AesException {
		// 加密
		String encrypt = encrypt(getRandomStr(), replyMsg);

		// 生成安全签名
		if ("".equals(timeStamp)) {
			timeStamp = Long.toString(System.currentTimeMillis());
		}

		String signature = SHA1.calculate(token, timeStamp, nonce, encrypt);

		// 生成发送的xml
        String result = XmlMsgBuilder.create().encrypt(encrypt, signature, timeStamp, nonce);
		return result;
	}

	/**
	 * Check the authenticity of the message.，And get the decryption of the text.
	 * <ol>
	 * 	<li>Secure signature generation using received encrypted，Signature verification</li>
	 * 	<li>If verified by，Then extractxmlEncrypted messages in</li>
	 * 	<li>Decrypt the message</li>
	 * </ol>
	 *
	 * @param msgSignature Signature string，CorrespondingURLParametermsg_signature
	 * @param timeStamp time stamp，CorrespondingURLParametertimestamp
	 * @param nonce Random string，CorrespondingURLParameternonce
	 * @param postData ciphertext，CorrespondingPOSTRequested data
	 *
	 * @return Decrypt the original text
	 * @throws AesException Execution failed，Please check the exception error code and the specific error message
	 */
	public String decryptMsg(String msgSignature, String timeStamp, String nonce, String postData)
			throws AesException {

		// 密钥，公众账号的app secret
		// 提取密文
		Object[] encrypt = XMLParse.extract(postData);

		// 验证安全签名
		String signature = SHA1.calculate(token, timeStamp, nonce, encrypt[1].toString());

		// 和URL中的签名比较是否相等
		if (!signature.equals(msgSignature)) {
			throw new AesException(AesException.ValidateSignatureError);
		}

		// 解密
		String result = decrypt(encrypt[1].toString());
		return result;
	}

	/**
	 * VerificationURL
	 * @param msgSignature Signature string，CorrespondingURLParametermsg_signature
	 * @param timeStamp time stamp，CorrespondingURLParametertimestamp
	 * @param nonce Random string，CorrespondingURLParameternonce
	 * @param echoStr Random string，CorrespondingURLParameterechostr
	 *
	 * @return After the decryptionechostr
	 * @throws AesException Execution failed，Please check the exception error code and the specific error message
	 */
	public String verifyUrl(String msgSignature, String timeStamp, String nonce, String echoStr)
			throws AesException {
		String signature = SHA1.calculate(token, timeStamp, nonce, echoStr);

		if (!signature.equals(msgSignature)) {
			throw new AesException(AesException.ValidateSignatureError);
		}

		String result = decrypt(echoStr);
		return result;
	}

    /**
     * Gets the encrypted messageAPPID[Provide a subscription number]
     *
     * @return APPID
     */
    public String getFromAppid() {
        return this.fromAppId;
    }
}