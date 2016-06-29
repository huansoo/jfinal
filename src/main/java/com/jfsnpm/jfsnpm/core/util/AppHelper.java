package com.jfsnpm.jfsnpm.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.Tree;
import com.jfsnpm.jfsnpm.core.dao.TreeOrg;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.plugin.redis.RedisPlugin;

import redis.clients.jedis.Jedis;

public class AppHelper {
	private static final Log log = Log.getLog(AppHelper.class);
	/**
	 * 根据指定的类名称加载类
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class<?> loadClass(String className) throws ClassNotFoundException {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(className);
		} catch (ClassNotFoundException  e) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException  ex) {
				try {
					return ClassLoader.class.getClassLoader().loadClass(className);
				} catch (ClassNotFoundException  exc) {
					throw exc;
				}
			}
		}
	}

	/**
	 * 将sheet_num转化为固定数据格式
	 * @param sheet_num
	 * @return
     */
	public static String parseObjectToFormatNum(Integer sheet_num){
		sheet_num = (sheet_num%9999) ==0?9999:(sheet_num%9999);
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		String linked_list_num = decimalFormat.format(sheet_num);
		return linked_list_num;
	}
	/**
	 * 实例化指定的类名称（全路径）
	 * @param clazzStr
	 * @return
	 * @throws Exception
	 */
	public static Object newInstance(String clazzStr) {
		try {
			log.debug("loading class:" + clazzStr);
			Class<?> clazz = loadClass(clazzStr);
			return instantiate(clazz);
		} catch (ClassNotFoundException e) {
			log.error("Class not found.", e);
		} catch (Exception ex) {
			log.error("类型实例化失败[class=" + clazzStr + "]\n" + ex.getMessage());
		}
		return null;
	}

	/**
	 * 根据类的class实例化对象
	 * @param clazz
	 * @return
	 */
	public static <T> T instantiate(Class<T> clazz) {
		if (clazz.isInterface()) {
			log.error("所传递的class类型参数为接口，无法实例化");
			return null;
		}
		try {
			return clazz.newInstance();
		} catch (Exception ex) {
			log.error("检查传递的class类型参数是否为抽象类?", ex.getCause());
		}
		return null;
	}

	/**
	 * 判断字符串是否为空
	 * @param str 字符串
	 * @return 是否为空标识
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	/**
	 * 断言不为空
	 * @param str
	 */
	public static void assertNotEmpty(String str){
		if(isEmpty(str)){
			throw new JfsnpmException("cannot empty");
		}
	}
	/**
	 * 字符串是否相等
	 * @param str1
	 * @param str2
	 * @return 都为null或相等返回true
	 */
	public static boolean isSame(String str1,String str2){
		if(str1==null&&str2==null) return true;
		if(str1==null) return false;
		return str1.equals(str2);
	}

	/**
	 * User转Map
	 * @param user
	 * @return
	 */
	public static Map<String, String> user2Map(User user){
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", isEmpty(user.getToken())?"":user.getToken());
		map.put("userId", isEmpty(user.getUserId())?"":user.getUserId());
		map.put("userNo", isEmpty(user.getUserNo())?"":user.getUserNo());
		map.put("userName", isEmpty(user.getUserName())?"":user.getUserName());
		map.put("userMail", isEmpty(user.getUserMail())?"":user.getUserMail());
		map.put("userOtherKey", isEmpty(user.getUserOtherKey())?"":user.getUserOtherKey());
		map.put("city", isEmpty(user.getCity())?"":user.getCity());
		map.put("province", isEmpty(user.getProvince())?"":user.getProvince());
		map.put("county", isEmpty(user.getCounty())?"":user.getCounty());
		return map;
	}
	/**
	 * Map转User
	 * @return
	 */
	public static User map2User(Map<String, String> map){
		User user = new User();
		user.setToken(map.get("token"));
		user.setUserId(map.get("userId"));
		user.setUserNo(map.get("userNo"));
		user.setUserName(map.get("userName"));
		user.setUserMail(map.get("userMail"));
		user.setUserOtherKey(map.get("userOtherKey"));
		user.setCity(map.get("city"));
		user.setProvince(map.get("province"));
		user.setCounty(map.get("county"));

		return user;
	}
	/**
	 * 生成新token
	 * @return
	 */
	public static String getNewToken(){
		return "pm"+getUUID();
	}
	/**
	 * 生成UUID
	 * @return
	 */
	public static String getUUID(){
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
	/**
	 * 获取SQL
	 * @param sqlkey
	 * @return
	 */
	public static String getSql(String sqlkey){
		return AppConfig.sqlimpl.getSql(sqlkey);
	}
	/**
	 * 通用json字符串转Object
	 * @param jsonstring
	 * @param clazz
	 * @return
	 */
	public static <T> T json2Object(String jsonstring,Class<T> clazz){
		if (isEmpty(jsonstring)) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(jsonstring, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String jsonstring = "[{\"no\":\"204\",\"name\":\"上海美团\"}]";
		List<HashMap> list = (List<HashMap>) getObjectsFromJson(jsonstring, HashMap.class);
		System.out.println("===="+list);
	}

	public static <T> List<T>  getObjectsFromJson(String jsonstring, Class<T> clsT){
		if (isEmpty(jsonstring)) {
			return null;
		}
		if (!(jsonstring.startsWith("[") && jsonstring.endsWith("]"))){
			jsonstring = "[" + jsonstring + "]";
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			JsonParser parser = mapper.getJsonFactory().createJsonParser(jsonstring);
			JsonNode nodes = parser.readValueAsTree();
			List<T> list = new ArrayList<T>(nodes.size());
			for(JsonNode node:nodes){
				list.add(mapper.readValue(node, clsT));
			}
			return list;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Object转换为json字符创
	 * @param object
	 * @return
	 */
	public static String object2Json(Object object){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * json转Tree
	 * @param json
	 * @return
	 */
	public static Tree json2Tree(String json){
		return json2Object(json, Tree.class);
	}
	public static TreeOrg json2TreeOrg(String json){
		return json2Object(json, TreeOrg.class);
	}
	/** 将异常信息转化成字符串
	 * @param t
	 * @return
	 * @throws IOException
	 */
	public static String printStackTraceToString(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw, true));
		return sw.getBuffer().toString();
	}
	/**
	 * 发起URL请求
	 * @param strURL
	 * @return
	 * @throws IOException
	 */
	public static String sendUrl(String strURL) throws IOException{
		java.net.URL url = new URL(strURL);
		HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"gbk"));
		String result = reader.readLine();
		if(result == null) result = "";
		return result;
	}
	/**
	 * 建立acc令牌
	 * @return
	 */
	public static String createAccToken(){
		String acctoken = "ACC"+getUUID();
		Jedis jedis = RedisPlugin.getJedis();
		jedis.setex(acctoken, 900, "");
		jedis.close();
		return acctoken;
	}
	/**
	 * 验证acc令牌
	 * @param accToken
	 * @return
	 */
	public static boolean checkAccToken(String accToken){
		if(isEmpty(accToken)) return false;
		Jedis jedis = RedisPlugin.getJedis();
		if(jedis.exists(accToken)){
			jedis.del(accToken);
			jedis.close();
			return true;
		}
		jedis.close();
		return false;
	}
	/**
	 * 获取当前时间点的字符串yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNow(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public static String getNow(String format){
		if(isEmpty(format)) format = "yyyy-MM-dd HH:mm:ss";
		return new SimpleDateFormat(format).format(new Date());
	}
	/**
	 * 字符串转日期
	 * @param str
	 * @return
	 */
	public static Date string2Date(String str){
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		} catch (ParseException e) {
			throw new JfsnpmException(e.getMessage());
		}
	}
	/**
	 * 记录访问日志
	 * @param userId
	 * @param url
	 * @return
	 */
	public static boolean setLogs(String userId,String url){
		return Db.update(getSql("jfsnpm.instertLogs"),getNow("yyyyMMddHHmmss")+getUUID(),
				userId,url) > 0;
	}
	/**
	 * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询
	 */
	public static String converToString(String[] ig) {
		String str = "";
		if (ig != null && ig.length > 0) {
			for (int i = 0; i < ig.length; i++) {
				str += ig[i] + ",";
			}
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * @Description:把list转换为一个用逗号分隔的字符串
	 */
	public static String listToString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i) + ",");
				} else {
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 替换str中的环境变量,嵌套一次
	 * "${cat} really needs some ${${beverage}}."
	 * @param str
	 * @return
	 */
	public static String getEnv(String str){
		if(null == str) return null;
		Map<String,String> envMap = System.getenv();
		//匹配类似velocity规则的字符串
		//String template = "${cat} really needs some ${beverage}.";
		String template = str;
		//生成匹配模式的正则表达式
		String patternString = "\\$\\{(" + StringUtils.join(envMap.keySet(), "|") + ")\\}";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(template);
		//两个方法：appendReplacement, appendTail
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			matcher.appendReplacement(sb, envMap.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		matcher = pattern.matcher(sb.toString());
		sb.setLength(0);//清空
		while(matcher.find()) {
			matcher.appendReplacement(sb, envMap.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
