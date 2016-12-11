package com.nky.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.net.entity.Area;
import com.net.singleton.AreaSingleton;

@Controller
@RequestMapping(value = "/areatree")
public class AreaTreepathController {
	
	@RequestMapping(value = "/show")
	public String show() {
		return "area/area_info";
	}

	
	/**
	 * 三级区域查询
	 * 地区
	 */
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public @ResponseBody
	Map<Long, String> area(String parentId) {
		Map<Long, String> options = new HashMap<Long, String>();
		try {
			
			List<Area> areas = new ArrayList<Area>();
			Area parent = AreaSingleton.getInstance().getArea(parentId);
			if (parent != null) {
				areas = AreaSingleton.getInstance().getChildren(parentId);
			} else if (parent == null && "0".equals(parentId)){
				areas = AreaSingleton.getInstance().getProvince();
			}
			
			for (Area area : areas) {
				options.put(area.getId(), area.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return options;
	}
	
	/**
	 * 三级区域查询
	 * 地区
	 */
	@RequestMapping(value = "/getTreepath", method = RequestMethod.GET)
	public @ResponseBody
	String getTreepath(String areaId) {
		return AreaSingleton.getInstance().getArea(areaId).getTree_path();
	}

}
