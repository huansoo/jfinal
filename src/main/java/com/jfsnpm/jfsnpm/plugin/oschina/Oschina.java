package com.jfsnpm.jfsnpm.plugin.oschina;

import java.util.HashMap;
import java.util.Map;

import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.HttpClient;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public class Oschina {
	/**
	 * 用户登录
	 * @param user
	 * @param sign
	 * @return
	 */
	public static String signAuth(String code){
		HttpClient client = new HttpClient();
		/* Post Request */
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("client_id", AppHelper.getEnv(AppConfig.properties.getProperty("oschina_client_id")));
        dataMap.put("client_secret", AppHelper.getEnv(AppConfig.properties.getProperty("oschina_client_secret")));
        dataMap.put("grant_type", "authorization_code");
        dataMap.put("redirect_uri", AppHelper.getEnv(AppConfig.properties.getProperty("oschina_redirect_uri")));
        dataMap.put("code", code);
        dataMap.put("dataType", "json");
        
        try {
			String result = client.doPost("http://www.oschina.net/action/openapi/token", dataMap);
			Map<?, ?> jsonmap = AppHelper.json2Object(result, Map.class);
			return (String) jsonmap.get("access_token");
		} catch (Exception e) {
			throw new JfsnpmException(e.getMessage());
		}
	}
	
	public static Map<?, ?> GetUserInfor(String access_token){
		
		HttpClient client = new HttpClient();
		/* Post Request */
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("access_token", access_token);
        dataMap.put("dataType", "json");
        try {
			String result = client.doPost("http://www.oschina.net/action/openapi/user", dataMap);
			Map<?, ?> jsonmap = AppHelper.json2Object(result, Map.class);
			return jsonmap;
		} catch (Exception e) {
			throw new JfsnpmException(e.getMessage());
		}
	}
	
}
