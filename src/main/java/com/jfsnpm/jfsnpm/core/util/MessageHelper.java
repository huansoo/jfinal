package com.jfsnpm.jfsnpm.core.util;

import com.jfsnpm.jfsnpm.core.AppConfig;

public class MessageHelper {
	/**
	 * 发送通知
	 * @param title 标题
	 * @param to 接收人ID
	 * @param msg 内容
	 * @return
	 */
	public static boolean sendNotify(String title,String to,String msg){
		return AppConfig.messageimpl.sendNotify(title, to, msg);
	}
	/**
	 * 发送邮件
	 * @param title 主题
	 * @param to 收件人ID或地址组成，以分号间隔
	 * @param msg 内容
	 * @return
	 */
	public static boolean sendMail(String title,String to,String msg){
		return AppConfig.messageimpl.sendMail(title, to, msg);
	}
}
