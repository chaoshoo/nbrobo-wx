package com.net.wx.util;

/**
 * AESEncryption exception
 */
public class AesException extends Exception {

	private static final long serialVersionUID = 1L;
	public final static int OK = 0;
	public final static int ValidateSignatureError = -40001;
	public final static int ParseXmlError = -40002;
	public final static int ComputeSignatureError = -40003;
	public final static int IllegalAesKey = -40004;
	public final static int ValidateAppidError = -40005;
	public final static int EncryptAESError = -40006;
	public final static int DecryptAESError = -40007;
	public final static int IllegalBuffer = -40008;
	public final static int EncodeBase64Error = -40009;
	public final static int DecodeBase64Error = -40010;
	public final static int GenReturnXmlError = -40011;

	private int code;

    public AesException(int code) {
        super(getMessage(code));
        this.code = code;
    }

	private static String getMessage(int code) {
		switch (code) {
		case ValidateSignatureError:
			return "Signature verification error";
		case ParseXmlError:
			return "xmlAnalytic failure";
		case ComputeSignatureError:
			return "shaEncryption generation signature failed";
		case IllegalAesKey:
			return "SymmetricKeyillegal";
		case ValidateAppidError:
			return "appidCheck failed";
		case EncryptAESError:
			return "aesEncryption failed";
		case DecryptAESError:
			return "aesDecryption failure";
		case IllegalBuffer:
			return "Obtained after decryptionbufferillegal";
		case EncodeBase64Error:
			return "base64Encryption error";
		case DecodeBase64Error:
			return "base64Decryption error";
		case GenReturnXmlError:
			return "xmlGeneration failed";
		default:
			return "Unknown anomaly";
		}
	}

	public int getCode() {
		return code;
	}


}