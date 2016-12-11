package com.net;

import java.util.ResourceBundle;

/**
 * Service constant
 * @author hiveview
 *
 */
public class ServiceConstants {
	private static ResourceBundle conf = ResourceBundle.getBundle("conf");

	public static String jdbc_driver = conf.getString("jdbc.driver");
	public static String jdbc_url = conf.getString("jdbc.url");
	public static String jdbc_user = conf.getString("jdbc.user");
	public static String jdbc_password = conf.getString("jdbc.password");	
	
	public static String nkyapi_url = conf.getString("nkyapi_url");
	
	public static String default_heard_img_url = conf.getString("default_heard_img_url");
	
	public static boolean dev_debug = Boolean.parseBoolean(conf.getString("dev_debug"));
	public static String chart_http = conf.getString("chart_http");
	public static String datagrid_http = conf.getString("datagrid_http");
	public static String default_img_keshi = conf.getString("default_img_keshi");
	public static String default_img_yiyuan = conf.getString("default_img_yiyuan");
	public static int defaultPageSize = 5;

	
}