package com.jfsnpm.jfsnpm.core.service;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class UserService {
	//获取用户信息
	public static User getUserInfor(Controller c){
		String token = c.getCookie("token");
		if(AppHelper.isEmpty(token)) return null;
		return AppConfig.userimpl.getUserByToken(token);
	}
	public static User getUserById(String userId){
		return AppConfig.userimpl.getUserById(userId);
	}
	
	public static List<Record> getUserAll(){
		return Db.find(AppHelper.getSql("user.getUserAll"));
	}
	
	public static boolean updatePassWord(String id, String password){
		return Db.update(AppHelper.getSql("user.updatePassword"), password, id) > 0;
	}
}
