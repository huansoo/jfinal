package com.jfsnpm.jfsnpm.core.impl;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.global.IUserRemote;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class UserRemoteImpl implements IUserRemote {
	public User checkToken(String token,Controller c) {
		User user = null;
		//第三方验证成功后赋值给user
		user = AppConfig.userThirdimpl.checkToken(token,c);
		if(user != null){
			//写入本地用户系统-默认No加前缀
			String pre = "_third_";
			user.setUserNo(pre+user.getUserNo());
			Record userRecord = Db.findFirst(AppHelper.getSql("user.getUserByUserNo"),user.getUserNo());
			if(userRecord != null){
				user.setUserId(userRecord.getStr("id"));
				userRecord.set("userName", user.getUserName())
						.set("userMail", user.getUserMail());
				Db.update("jfsnpm_user", userRecord);
			}else{
				user.setUserId(AppHelper.getUUID());
				userRecord = new Record().set("id", user.getUserId()).set("userNo", user.getUserNo());
				userRecord.set("userName", user.getUserName())
						.set("userMail", user.getUserMail()).set("password", "Third party users can not log in by user password")
						.set("status", "第三方");
				Db.save("jfsnpm_user", userRecord);
			}
			return user;
		}
		return null;
	}

	public User checkUser(String user, String psw) {
		//本地用户系统的验证
		Record userRecord = Db.findFirst(AppHelper.getSql("user.checkUser"),user,psw,"1");
		if(userRecord != null){
			User useru = new User();
			useru.setUserId(userRecord.getStr("id"));
			useru.setUserNo(userRecord.getStr("userNo"));
			useru.setUserMail(userRecord.getStr("userMail"));
			useru.setUserName(userRecord.getStr("userName"));
			useru.setProvince(userRecord.getStr("province"));
			useru.setCity(userRecord.getStr("city"));
			useru.setCounty(userRecord.getStr("county"));
			return useru;
		}
		return null;
	}

	public User getUser(String userNo) {
		Record userRecord = Db.findFirst(AppHelper.getSql("user.getUserByUserNo"),userNo);
		if(userRecord != null){
			User useru = new User();
			useru.setUserId(userRecord.getStr("id"));
			useru.setUserNo(userRecord.getStr("userNo"));
			useru.setUserMail(userRecord.getStr("userMail"));
			useru.setUserName(userRecord.getStr("userName"));
			useru.setProvince(userRecord.getStr("province"));
			useru.setCity(userRecord.getStr("city"));
			useru.setCounty(userRecord.getStr("county"));
			return useru;
		}
		return null;
	}

	public User getUserById(String userId) {
		if(AppHelper.isEmpty(userId)) return null;
		Record userRecord = Db.findFirst(AppHelper.getSql("user.getUserByUserId"),userId);
		if(userRecord == null) return null;
		User user = new User();
		user.setUserId(userRecord.getStr("id"));
		user.setUserMail(userRecord.getStr("userMail"));
		user.setUserName(userRecord.getStr("userName"));
		user.setUserNo(userRecord.getStr("userNo"));
		user.setProvince(userRecord.getStr("province"));
		user.setCity(userRecord.getStr("city"));
		user.setCounty(userRecord.getStr("county"));
		return user;
	}

}
