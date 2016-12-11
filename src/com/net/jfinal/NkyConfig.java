package com.net.jfinal;

import java.util.HashMap;

import com.alibaba.druid.filter.stat.StatFilter;
import com.huilet.util.FileUtil;
import com.huilet.util.xml.ReadXml;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.net.ServiceConstants;

/**
 * APIBoot configuration
 * @author yuanhuaihao
 * company huilet
 * 2013-3-12
 */
public class NkyConfig extends JFinalConfig{
	
	/**
	 * Configuration constants
	 */
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);  // Set view typeJsp，Otherwise the default isFreeMarker
	}

	/**
	 * Configuration route
	 */
	public void configRoute(Routes me) {
		MyRoutesUtil.add(me);
	}

	/**
	 * Configuration plug-in
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(ServiceConstants.jdbc_url, ServiceConstants.jdbc_user,  ServiceConstants.jdbc_password);
		me.add(c3p0Plugin);
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(c3p0Plugin, TableNameStyle.UP);
		autoTableBindPlugin.setShowSql(true);
		autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
		
		me.add(autoTableBindPlugin);
	}

	/**
	 * Configure global interceptor
	 */
	public void configInterceptor(Interceptors me) {
		
	}

	/**
	 * Configuration processor
	 */
	public void configHandler(Handlers me) {
		me.add(new JFinalMy());
	}

}