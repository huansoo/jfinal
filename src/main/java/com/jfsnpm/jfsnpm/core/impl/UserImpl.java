package com.jfsnpm.jfsnpm.core.impl;


import com.jfinal.core.Controller;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.global.IUser;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.redis.RedisPlugin;

import redis.clients.jedis.Jedis;

public class UserImpl implements IUser {

	public User checkToken(String token,Controller c,boolean remote) {
		if(AppHelper.isEmpty(token)) return null;
		User user = null;
		Jedis jedis = RedisPlugin.getJedis();
		if(remote){
			//远程token认证
			user = AppConfig.userRemoteimpl.checkToken(token,c);
			if(user != null){//远程验证成功
				//需要写入本地认证数据
				String userNo = user.getUserNo();
				//单点登录，清除原有的本地token
				if(AppConfig.properties.getProperty("SSO", "true").equals("true")){
					if(jedis.exists(userNo)){
						String oldToken = jedis.get(userNo);
						if(jedis.exists(oldToken)){
							jedis.expire(oldToken, 0);
						}
					}
				}
				user.setToken(AppHelper.getNewToken());//生成使用本地token
				jedis.hmset(user.getToken(), AppHelper.user2Map(user));
				jedis.expire(user.getToken(), Integer.valueOf(AppConfig.properties.getProperty("timeout")));
				jedis.setex(user.getUserNo(), Integer.valueOf(AppConfig.properties.getProperty("timeout")), user.getToken());
			}
		}else{
			//本地token验证
			if(jedis.exists(token)){
				user = getUserByToken(token);
			}
		}
		jedis.close();
		return user;
	}

	public User getUser(String userNo) {
		if(AppHelper.isEmpty(userNo)) return null;
		Jedis jedis = RedisPlugin.getJedis();
		String token = jedis.get(userNo);
		User user = null;
		if(!AppHelper.isEmpty(token)){
			user = AppHelper.map2User(jedis.hgetAll(token));
			if(user != null){
				jedis.close();
				return user;
			}
		}
		jedis.close();
		return AppConfig.userRemoteimpl.getUser(userNo);
	}
	public User checkUser(String user, String psw) {
		if(AppHelper.isEmpty(user)||AppHelper.isEmpty(psw)) return null;
		Jedis jedis = RedisPlugin.getJedis();
		User u = AppConfig.userRemoteimpl.checkUser(user, psw);
		if(u != null){//验证通过后写入本地认证数据
			String userNo = u.getUserNo();
			//单点登录，清除原有的本地token
			if(AppConfig.properties.getProperty("SSO", "true").equals("true")){
				if(jedis.exists(userNo)){
					String oldToken = jedis.get(userNo);
					if(jedis.exists(oldToken)){
						jedis.expire(oldToken, 0);
					}
				}
			}
			u.setToken(AppHelper.getNewToken());//生成使用本地token
			jedis.hmset(u.getToken(), AppHelper.user2Map(u));
			jedis.expire(u.getToken(), Integer.valueOf(AppConfig.properties.getProperty("timeout")));
			jedis.setex(u.getUserNo(), Integer.valueOf(AppConfig.properties.getProperty("timeout")), u.getToken());
		}
		jedis.close();
		return u;
	}
	public User getUserByToken(String token){
		if(AppHelper.isEmpty(token)) return null;
		Jedis jedis = RedisPlugin.getJedis();
		User user = AppHelper.map2User(jedis.hgetAll(token));
		jedis.close();
		return user;
	}

	public boolean clearToken(String token) {
		if(AppHelper.isEmpty(token)) return true;
		Jedis jedis = RedisPlugin.getJedis();
		if(jedis.exists(token)){
			User u = getUserByToken(token);
			if(u!=null){
				String userNo = u.getUserNo();
				if(!AppHelper.isEmpty(userNo)){
					jedis.expire(userNo, 0);
				}
			}
			jedis.expire(token, 0);
		}
		jedis.close();
		return true;
	}

	public User getUserById(String userId) {
		return AppConfig.userRemoteimpl.getUserById(userId);
	}
	

}
