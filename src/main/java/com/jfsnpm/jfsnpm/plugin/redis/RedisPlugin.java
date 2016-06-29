package com.jfsnpm.jfsnpm.plugin.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.jfinal.plugin.IPlugin;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class RedisPlugin implements IPlugin {
	private static JedisPool pool; 
	private static Jedis jedis;
	public boolean start() {
		pool = new JedisPool(
				new JedisPoolConfig(),
				AppHelper.getEnv(AppConfig.properties.getProperty("jedis_host", "127.0.0.1")),
				Integer.valueOf(AppHelper.getEnv(AppConfig.properties.getProperty("jedis_port", "6739"))),
				Integer.valueOf(AppHelper.getEnv(AppConfig.properties.getProperty("jedis_timeout", "2000"))),
				AppHelper.getEnv(AppConfig.properties.getProperty("jedis_password", "")),
				Integer.valueOf(AppHelper.getEnv(AppConfig.properties.getProperty("jedis_database", "0"))),
				AppHelper.getEnv(AppConfig.properties.getProperty("jedis_clientname", null))
				);
		//jedis = pool.getResource();
		//jedis.auth();
//		pool = new JedisPool(AppConfig.properties.getProperty("jedis_host", "127.0.0.1"),AppHelper.getEnv(AppConfig.properties.getProperty("jedis_port", "6739")));
		return true;
	}

	public boolean stop() {
		if(pool!=null){
			pool.destroy();
		}
		return true;
	}
	
	public static Jedis getJedis(){
		return pool.getResource();
	}

}
