package com.jfsnpm.jfsnpm.plugin.mail;


import com.jfinal.plugin.IPlugin;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.Mail;

public class MailPlugin implements IPlugin {
	@Override
	public boolean start() {
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}

	public static Mail getMail() {
		return new Mail(AppHelper.getEnv(AppConfig.properties.getProperty("mail_userName")),
				AppHelper.getEnv(AppConfig.properties.getProperty("mail_userPwd")),
				AppHelper.getEnv(AppConfig.properties.getProperty("mail_host")),
				AppConfig.properties.getProperty("mail_needAuth").equals("true"));
	}

}
