/**
 * Copyright (c) 2005-2012 springside.org.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.net.wx.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Package of various formats of encoding and decoding tools. 1.Commons-CodecThe hex/base64 Code 2.Self madebase62 Code
 * 3.Commons-LangThexml/html escape 4.JDKProvidedURLEncoder
 * 
 * @author calvin
 * @version 2013-01-15
 */
public class Encodes {

 private static final String DEFAULT_URL_ENCODING = "UTF-8";
 private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

 /**
  * HexCode.
  */
 public static String encodeHex(byte[] input) {
  return Hex.encodeHexString(input);
 }

 /**
  * HexDecode.
  */
 public static byte[] decodeHex(String input) {
  try {
   return Hex.decodeHex(input.toCharArray());
  }
  catch (DecoderException e) {
   e.printStackTrace();
   return null;
  }
 }

 /**
  * Base64Code.
  */
 public static String encodeBase64(byte[] input) {
  return Base64.encodeBase64String(input);
 }

 /**
  * Base64Code, URLsecurity(takeBase64InURLIllegal character'+'and'/'Turn'-'and'_', seeRFC3548).
  */
 public static String encodeUrlSafeBase64(byte[] input) {
  return Base64.encodeBase64URLSafeString(input);
 }

 /**
  * Base64Decode.
  */
 public static byte[] decodeBase64(String input) {
  return Base64.decodeBase64(input);
 }

 /**
  * Base62Code。
  */
 public static String encodeBase62(byte[] input) {
  char[] chars = new char[input.length];
  for (int i = 0; i < input.length; i++) {
   chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
  }
  return new String(chars);
 }

 /**
  * Html transcoding.
  */
 public static String escapeHtml(String html) {
  return StringEscapeUtils.escapeHtml4(html);
 }

 /**
  * Html Decode.
  */
 public static String unescapeHtml(String htmlEscaped) {
  return StringEscapeUtils.unescapeHtml4(htmlEscaped);
 }

 /**
  * Xml transcoding.
  */
 public static String escapeXml(String xml) {
  return StringEscapeUtils.escapeXml(xml);
 }

 /**
  * Xml Decode.
  */
 public static String unescapeXml(String xmlEscaped) {
  return StringEscapeUtils.unescapeXml(xmlEscaped);
 }

 /**
  * URL Code, EncodeBy defaultUTF-8.
  */
 public static String urlEncode(String part) {
  try {
   return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
  }
  catch (UnsupportedEncodingException e) {
   e.printStackTrace();
   return null;
  }
 }

 /**
  * URL Decode, EncodeBy defaultUTF-8.
  */
 public static String urlDecode(String part) {

  try {
   return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
  }
  catch (UnsupportedEncodingException e) {
	  e.printStackTrace();
	   return null;
  }
 }
}