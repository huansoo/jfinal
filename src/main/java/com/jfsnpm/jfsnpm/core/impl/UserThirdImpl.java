package com.jfsnpm.jfsnpm.core.impl;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.global.IUserThird;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.rtx.RTX;

public class UserThirdImpl implements IUserThird {

	@Override
	public User checkToken(String token , Controller c) {
		// TODO 自定义实现第三方token验证
		/**
		 * 此处的代码为实现RTX Server的自动登录
		 */
		User user = null;
		String userno = c.getPara("userid");
		String acctoken = c.getPara("acctoken");
		if(!AppHelper.checkAccToken(acctoken)) return null;
		if(AppHelper.isEmpty(userno)||AppHelper.isEmpty(token)) return user;
		if(RTX.signAuth(userno, token)){
			user = new User();
			user.setUserNo("rtx_"+userno);
			String[] userinfor = RTX.GetUserInfor(userno).split("\\*,\\*");
			user.setUserMail(userinfor[1]);
			user.setUserName(userinfor[0]);
		}
		return user;
	}

}
