package com.jfsnpm.jfsnpm.core.service;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.util.AppHelper;


public class ApiService {
	public static void open(Controller c){
		String openType = c.getPara("type");
		String openUrl = c.getPara("url");
		if(AppHelper.isEmpty(openType)||AppHelper.isEmpty(openUrl)){
			c.renderText("传递的参数有误！\nApi/open?type={dialog|navtab}&url={xxxx}");
		}else{
			set(c, openType, openUrl);
		}
	}
	
	
	
	
	public static void set(Controller c,String openType,String openUrl){
		c.setSessionAttr("__openType", openType);
		c.setSessionAttr("__openUrl", openUrl);
		c.redirect("/");
	}
	public static void get(Controller c){
		String openType = c.getSessionAttr("__openType");
		String openUrl = c.getSessionAttr("__openUrl");
		c.setSessionAttr("__openType", "");
		c.setSessionAttr("__openUrl", "");
		if(AppHelper.isEmpty(openType)||AppHelper.isEmpty(openUrl)) return;
		c.setAttr("__openType", openType);
		c.setAttr("__openUrl", openUrl);
	}
}
