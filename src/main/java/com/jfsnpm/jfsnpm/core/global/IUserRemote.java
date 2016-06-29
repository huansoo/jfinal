package com.jfsnpm.jfsnpm.core.global;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.dao.User;

public interface IUserRemote {
	/**
	 * 验证令牌
	 * @param token 令牌
	 * @return User
	 */
	public User checkToken(String token,Controller c);
	/**
	 * 验证账号密码
	 * @param user 账号
	 * @param psw 密码
	 * @return User
	 */
	public User checkUser(String user,String psw);
	/**
	 * 获取用户信息
	 * @param userid
	 * @return
	 */
	public User getUser(String userNo);
	public User getUserById(String userId);
}
