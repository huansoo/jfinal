package com.jfsnpm.jfsnpm.core.service;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class LoginService {
	/**
	 * 验证请求中的token
	 * @param c
	 * @return
	 */
	public static boolean checkToken(Controller c){
		boolean flag=true;
		String token = c.getPara("token");
		if(AppHelper.isEmpty(token)){
			flag = false;
			token = c.getCookie("token");
		}
		if(AppHelper.isEmpty(token)){
			flag = true;//远程认证
			token = "NeedRemote";
		}
		User user = AppConfig.userimpl.checkToken(token,c,flag);
		if(user != null){
			if(flag){//token从参数中检出,需写入cookie
				c.setCookie("token", user.getToken(), Integer.valueOf(AppConfig.properties.getProperty("timeout")));
			}
			return true;
		}
		return false;
	}
	/**
	 * 登录验证
	 * @param c
	 * @return
	 */
	public static boolean checkUserPassword(Controller c){
		String username = c.getPara("username");
		String psw = c.getPara("password");
		User user = AppConfig.userimpl.checkUser(username, psw);
		if(user != null){
			String mobile = c.getPara("mobile");
			if(!AppHelper.isEmpty(mobile)){
				c.setCookie("mobile", "mobile", Integer.valueOf(AppConfig.properties.getProperty("timeout")));
			}
			c.setCookie("token", user.getToken(), Integer.valueOf(AppConfig.properties.getProperty("timeout")));
			return true;
		}
		return false;
	}
	/**
	 * 注销
	 * @param c
	 * @return
	 */
	public static boolean logout(Controller c){
		String token = c.getCookie("token");
		if(AppHelper.isEmpty(token)) return true;
		c.setCookie("token", "", 0);
		return AppConfig.userimpl.clearToken(token);
	}
	
}
