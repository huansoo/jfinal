package com.jfsnpm.jfsnpm.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.global.IMessage;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;
import com.jfsnpm.jfsnpm.core.util.Mail;
import com.jfsnpm.jfsnpm.plugin.mail.MailPlugin;
import com.jfsnpm.jfsnpm.plugin.rtx.RTX;

public class MessageImpl implements IMessage {
	private static Executor executor = Executors.newFixedThreadPool(50);
	@Override
	public boolean sendNotify(String title, String to, String msg) {
		String[] tos = to.split(";");
		for(String item:tos){
			User user = UserService.getUserById(item);
			if(user == null){
				return false;
			}else{
				String toNo = user.getUserNo();
				if(toNo.indexOf("third_rtx_") > 0){
					toNo = toNo.substring("__third_rtx_".length());
					RTX.sendNotify(toNo, msg, title);
				}else{
					String usermail = user.getUserMail();
					toNo = usermail.substring(0, usermail.indexOf("@"));
					RTX.sendNotify(toNo, msg, title);
					System.out.println("notice:"+toNo+","+title);
					System.out.println(msg);
				}
			}
		}
		return true;
	}

	@Override
	public boolean sendMail(final String title, String to, final String msg) {
		final List<String> toAdd = new ArrayList<String>();
		String[] tos = to.split(";");
		for(String item:tos){
			User user = UserService.getUserById(item);
			if(user == null){
				toAdd.add(item);
			}else{
				toAdd.add(user.getUserMail());
			}
		}
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					Mail mail = MailPlugin.getMail();
					Message message = mail.createMessage(AppHelper.getEnv(AppConfig.properties.getProperty("mail_userName")),toAdd.toArray(new String[toAdd.size()]), title, msg, null, true, false, true, null, "");
					mail.send(message);
					mail.close();
				} catch (AddressException e) {
					throw new JfsnpmException(e.getMessage());
				} catch (MessagingException e) {
					throw new JfsnpmException(e.getMessage());
				}
			}
		};
		executor.execute(task);
		return true;
	}

}
