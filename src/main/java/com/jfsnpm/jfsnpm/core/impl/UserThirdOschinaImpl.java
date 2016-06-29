package com.jfsnpm.jfsnpm.core.impl;

import java.util.Map;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.global.IUserThird;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.oschina.Oschina;

public class UserThirdOschinaImpl implements IUserThird {

	public User checkToken(String token , Controller c) {
		/**
		 * 此处的代码为实现实现oschina的第三方登录
		 */
		User user = null;
		String code = c.getPara("code");
		String state = c.getPara("state");
		//验证是否超时
		if(!AppHelper.checkAccToken(state)) return null;
		
		if(AppHelper.isEmpty(code)) return user;
		//用code获得access_token
		String access_token = Oschina.signAuth(code);
		if(!AppHelper.isEmpty(access_token)){
			String userNo="",userMail="",userName="";
			//用access_token获得用户信息
			Map<?, ?> userinfor = Oschina.GetUserInfor(access_token);
			userNo = (String) userinfor.get("id").toString();
			userMail = (String) userinfor.get("email");
			userName = (String) userinfor.get("name");
			user = new User();
			user.setUserNo("oschina_"+userNo);
			user.setUserMail(userMail);
			user.setUserName(userName);
		}
		return user;
	}

}
