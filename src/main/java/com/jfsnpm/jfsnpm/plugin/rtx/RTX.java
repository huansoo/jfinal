package com.jfsnpm.jfsnpm.plugin.rtx;

import java.io.IOException;

import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public class RTX {
	private static String serverIp = "192.168.10.203";
	private static String serverPort = "8012";
	/**
	 * 用户反向登录
	 * @param user
	 * @param sign
	 * @return
	 */
	public static boolean signAuth(String user,String sign){
		boolean bRet = false;
		String result = sendUrl("SignAuth.cgi", "user",user,"sign",sign);
		if(result.compareToIgnoreCase("success!") == 0)
    	{
    		bRet = true;
    	}else{
    		throw new JfsnpmException("错误信息：["+result+"]");
    	}
		return bRet;
	}
	public static boolean sendNotify(String to,String msg,String title){
		boolean bRet = false;
		String result = sendUrl("sendNotify.cgi", "receiver",to,"msg",msg,"title",title);
		if(result.compareToIgnoreCase("success!") == 0)
    	{
    		bRet = true;
    	}else{
    		throw new JfsnpmException("错误信息：["+result+"]");
    	}
		return bRet;
	}
	public static String GetUserInfor(String user){
		return sendUrl("GetUserInfor.cgi", "receiver",user);
	}
	
	private static String sendUrl(String api,String ...args){
		try{
			String strURL = "http://"+serverIp+":"+serverPort+"/"+api;
			for(int i=0;i<args.length;i++){
				String para = args[i];
				if(i+1 >= args.length) return "参数数量不符合要求";
				String paraValue = args[i+1];
				if(AppHelper.isEmpty(para)) return "参数不能为空";
				if(paraValue == null) paraValue = "";
				if(i==0){
					strURL = strURL + "?";
				}else{
					strURL = strURL + "&";
				}
				strURL = strURL + java.net.URLEncoder.encode(para,"GB2312") + "="+
						java.net.URLEncoder.encode(paraValue,"GB2312");
				i=i+1;
			}
			return AppHelper.sendUrl(strURL);
		}catch(IOException e){
			throw new JfsnpmException("RTX处理程序失败");
		}
	}
}
