package com.net.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.net.ServiceConstants;



public class HttpClient {
	
	public static final String GET_URL = ServiceConstants.nkyapi_url; 
	/**
	 * 拼接成一个url然后自动请求该url
	 * @param content
	 * @param wxid
	 * @return
	 * @throws IOException 
	 */
	public static  void PushForHttp(String content, String wxid) {
		// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码 
		//http://duanzihui.com/coreServlet?content="+content+"&wxid="+wxid+"
		//http://duanzihui.com/coreServlet?content=你好&wxid=oko4Wt1jqHnxD4RJo5hF-deM5gjU
		try {
			String contens = URLEncoder.encode(content, "UTF-8");
			String getURL = GET_URL+ "?content="+contens+"&wxid="+wxid+""; 
			URL getUrl = new URL(getURL); 
		// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据 URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection 
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			// 建立与服务器的连接，并未发送数据 
			connection.connect();
			// 发送数据到服务器并使用Reader读取返回的数据 
			 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			 String lines; //定义返回值
			while ((lines = reader.readLine()) != null) {
				System.out.println(lines);
			}
			// 断开连接
			reader.close();
			connection.disconnect(); 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String httpPost(String url, Map<String, String> parms)
			throws IOException {
		URL postUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		out.writeBytes(mapToLink(parms));
		out.flush();
		out.close();
		String str = readInputStream(connection.getInputStream());
		// reader.close();
		connection.disconnect();
		return str;
	}
	
	public static String readInputStream(InputStream inStream) {
		String dataStr = null;
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			dataStr = new String(outStream.toByteArray(), "utf-8");
			outStream.close();
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataStr;
	}
	
	public static String mapToLink(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key);
			if (StringUtils.isNotEmpty(value)) {// 去除空值
				try {
					prestr = prestr + "&" + key + "=" + java.net.URLEncoder.encode(value , "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return prestr.substring(1);
	}
	
	public static  String PushForHttps(String content,String url) {
		// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码 
		try {
			String contens = URLEncoder.encode(content, "UTF-8");
			String getURL = url + "?content="+contens; 
			URL getUrl = new URL(getURL); 
			System.out.println("request---->"+getURL);
		// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据 URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection 
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			// 建立与服务器的连接，并未发送数据 
			connection.connect();
			// 发送数据到服务器并使用Reader读取返回的数据 
			 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8")); 
			 String lines; //定义返回值
			 String result = "";
			while ((lines = reader.readLine()) != null) {
				result+=lines;
			}
			// 断开连接
			reader.close();
			connection.disconnect(); 
			System.out.println("---->"+result);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	/**
	 * 拼接成一个url然后自动请求该url
	 * @param content
	 * @param wxid
	 * @return
	 * @throws IOException 
	 */
	public static  String httpToCjk(String url) {
		// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码 
		try {
			URL getUrl = new URL(url); 
		// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据 URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection 
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			// 建立与服务器的连接，并未发送数据 
			connection.connect();
			// 发送数据到服务器并使用Reader读取返回的数据 
			 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8")); 
			 String lines; //定义返回值
			 String result = "";
			while ((lines = reader.readLine()) != null) {
				result+=lines;
			}
			// 断开连接
			reader.close();
			connection.disconnect(); 
			System.out.println("---->"+result);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "";
	}
}