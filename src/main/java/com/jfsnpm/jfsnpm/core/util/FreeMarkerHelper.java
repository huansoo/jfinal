package com.jfsnpm.jfsnpm.core.util;

import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.service.MenuService;

public class FreeMarkerHelper {
	public static boolean isStartWith(String str,String prefix){
		return str.startsWith(prefix);
	}
	public static boolean isStartWithOnly(String str,String prefix){
		return str.startsWith(prefix)&&(!str.equals(prefix));
	}
	public static boolean isEmpty(String str){
		return AppHelper.isEmpty(str);
	}
	public static String getuuid(){
		return AppHelper.getUUID();
	}
	public static boolean getauth(String userNo,String url){
		return MenuService.checkMenuByUserNo(userNo,url);
	}
	public static String getDefault(String key,String userId){
		if("__today".equals(key)) return AppHelper.getNow();
		String data = AppConfig.flowArgsimpl.getFlowAssignee(userId, key);
		if(!AppHelper.isEmpty(data)) return data;
		return key;
	}
}
