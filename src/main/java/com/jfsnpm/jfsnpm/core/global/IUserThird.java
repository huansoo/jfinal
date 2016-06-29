package com.jfsnpm.jfsnpm.core.global;

import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.dao.User;

public interface IUserThird {
	/**
	 * 验证令牌
	 * @param token 令牌
	 * @return User
	 */
	public User checkToken(String token,Controller c);
}
