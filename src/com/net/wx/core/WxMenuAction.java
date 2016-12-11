package com.net.wx.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.net.wx.vo.MPAct;
import com.net.wx.vo.Menu;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wxMenu")
public class WxMenuAction  {


	
	@RequestMapping(value = "/sysnc")
	public void  sysnc(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try {
//			WxRegisterSingleton.getInstance().reloadData();
			String dpId = request.getParameter("dpid");
			Menu[] menuArr = getMenu(dpId);
			
			MPAct mpact = getMPAct(dpId);
			if(mpact == null){
				System.out.println("create menu fail!");
				result.put("flag", "fail");
				response.getWriter().write(result.toString());
				return;
			}
			
			WxApi wxApi = new WxApiImpl(mpact);
			System.out.println("wx--------->");
			//先删除菜单
//			if(wxApi.deleteMenu()){
				//创建菜单
				if(wxApi.createMenu(menuArr)){
					System.out.println("create menu success!");
					result.put("flag", "success");
					
				}else{
					System.out.println("create menu fail ccccc!");
					result.put("flag", "fail");
				}
				response.getWriter().write(result.toString());
//			}else{
//				System.out.println("create menu fail!");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("create menu fail!");
			result.put("flag", "fail");
			try {
				response.getWriter().write(result.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private MPAct getMPAct(String dpId) {
		// TODO Auto-generated method stub
		
		String sql = "select * from  t_wx_register where dp_id = '"+dpId+"'";
		Record r = Db.findFirst(sql);
		if(r != null ){
			String mpId = r.get("id")+"";
			String nickName = r.get("nickname")+"";
			String appId = r.get("appid")+"";
			String appSecret = r.get("appsecret")+"";
			String mpType = r.get("mptype")+"";
			boolean pass = true;
			String token = r.get("token")+"";
			String AESKey = r.get("aeskey")+"";
			MPAct  mpact =  new MPAct(mpId,nickName,appId,appSecret,mpType,pass,token,AESKey, dpId);
			return mpact;
		}
		return null ;
	}

	public Menu[] getMenu(String dpId){
		Menu[] menuArr = null;
 		List<Record> wxMenus = null;
		List<Record> wxMenuSubs = null;
		try {
			wxMenus =  Db.find("SELECT *   FROM  t_wx_menu  WHERE  parent_menu_id = '' and dp_id = '"+dpId+"' ");
			if(null != wxMenus && wxMenus.size() > 0){
				menuArr = new Menu[wxMenus.size()];
				Menu menu = null;
				Menu subMenu = null;
				List<Menu> subMenus = null;
				int index = 0;
				for(Record wxmenu : wxMenus){
					menu = new Menu();
					setMenu(wxmenu,menu);
					
					//添加子菜单
					wxMenuSubs = Db.find("SELECT *   FROM  t_wx_menu  WHERE PARENT_MENU_ID =  '"+wxmenu.getStr("menu_id")+"'");
					if(null != wxMenuSubs && wxMenuSubs.size() > 0){
						subMenus = new ArrayList<Menu>();
						for(Record wxmenuSub : wxMenuSubs){
							subMenu = new Menu();
							setMenu(wxmenuSub,subMenu);
							subMenus.add(subMenu);
						}
						menu.setSubButtons(subMenus);
					}
					menuArr[index] = menu;
					index++;
				}
			}
			return menuArr;
		} catch (Exception e) {
			e.printStackTrace();
			return menuArr;
		}
	}
	
	public void setMenu(Record wxmenu,Menu menu){
		menu.setName(wxmenu.get("menu_name")+"");
		menu.setType(wxmenu.get("menu_type")+"");
		if(StringUtils.isNotBlank(wxmenu.get("menu_key")+"")){
			menu.setKey(wxmenu.get("menu_key")+"");
		}else if(StringUtils.isNotBlank(wxmenu.get("menu_url")+"")){
			menu.setUrl(wxmenu.get("menu_url")+"");
		}
	}
	
	
}