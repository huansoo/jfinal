package com.jfsnpm.jfsnpm.plugin.bjui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class BjuiHelper {
	/**
	 * 获得过滤条件
	 * @param c
	 * @param updateCookie 
	 * @return
	 */
	public static List<BjuiFilter> getFilter(Controller c, boolean updateCookie){
		List<BjuiFilter> filter = new ArrayList<BjuiFilter>();
		//1.获取cookie中的过滤条件
		String cookieKey = c.getAttrForStr("__url").replaceAll("/", "-")+"-filter";
		String cookie = c.getCookie(cookieKey, "");
		if(!AppHelper.isEmpty(cookie)){
			filter = AppHelper.getObjectsFromJson(cookie, BjuiFilter.class);
		}
		if(!updateCookie) return filter;
		//2.获取请求中的过滤条件
		BjuiFilter filterNew = getFilterFromController(c);
		//3.对记录的处理
		String pageNumber = c.getPara("pageNumber", "");
		if(AppHelper.isEmpty(pageNumber)){
			//3.1.请求无页码，清除所有记录的过滤条件
			c.setCookie(cookieKey, "", 0);
			return null;
		}else{
			//3.2.将请求中的过滤条件合并到cookie中。
			if(filterNew==null){
				return filter;
			}else{
				for(int i=0;i<filter.size();i++){
					if(filter.get(i).columnName.equals(filterNew.columnName)){
						filter.remove(i);
					}
				}
				filter.add(filterNew);
				String json = AppHelper.object2Json(filter);
				c.setCookie(cookieKey, json, Integer.valueOf(AppConfig.properties.getProperty("timeout")));
				return filter;
			}
		}
		
	}
	//提取controller中的过滤条件
	private static BjuiFilter getFilterFromController(Controller c){
		//获取参数MAP
		Map<String,String[]> paras = c.getParaMap();
		for (Map.Entry<String, String[]> entry : paras.entrySet()) {
			   if(entry.getKey().equals("pageNumber")) continue;
			   if(entry.getKey().equals("pageSize")) continue;
			   if(entry.getKey().equals("orderField")) continue;
			   if(entry.getKey().equals("orderDirection")) continue;
			   if(entry.getKey().indexOf(".operator") > 0) continue;
			   String columnName = entry.getKey();
			   String columnOperator = columnName + ".operator";
			   if(paras.containsKey(columnOperator)){
				   BjuiFilter columnFilter = new BjuiFilter();
				   columnFilter.columnName = columnName;
				   columnFilter.columnOperator = c.getPara(columnOperator, "like");
				   columnFilter.columnValue = c.getPara(columnName, "");
				   return columnFilter;
			   }
		}
		return null;
	}
	/**
	 * 获取order
	 * @param c
	 * @param updateCookie 
	 * @return
	 */
	public static BjuiOrder getOrder(Controller c, boolean updateCookie){
		BjuiOrder order = new BjuiOrder();
		//1.获取cookie中的排序
		String cookieKey = c.getAttrForStr("__url").replaceAll("/", "-")+"-order";
		String cookie = c.getCookie(cookieKey, "");
		if(!AppHelper.isEmpty(cookie)){
			order = AppHelper.json2Object(cookie, BjuiOrder.class);
		}
		if(!updateCookie) return order;
		//2.获取请求中的排序
		BjuiOrder orderNew = getOrderFromController(c);
		//3.对记录的处理
		String pageNumber = c.getPara("pageNumber", "");
		if(AppHelper.isEmpty(pageNumber)){
			//3.1.请求无页码，清除所有记录的排序条件。
			c.setCookie(cookieKey, "", 0);
			return null;
		}else{
			//3.2.将请求中的排序条件合并到cookie中。
			if(orderNew!=null&&!AppHelper.isEmpty(orderNew.orderField)){
				String json = AppHelper.object2Json(orderNew);
				c.setCookie(cookieKey, json, Integer.valueOf(AppConfig.properties.getProperty("timeout")));
				return orderNew;
			}else{
				return order;
			}
		}
	}
	//提取controller中的排序条件
	private static BjuiOrder getOrderFromController(Controller c){
		String orderField = c.getPara("orderField", "");
		String orderDirection = c.getPara("orderDirection","asc");
		if(AppHelper.isEmpty(orderField)) return null;
		BjuiOrder order = new BjuiOrder();
		order.orderField = orderField;
		order.orderDirection = orderDirection;
		return order;
	}
}
