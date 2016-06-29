package com.jfsnpm.jfsnpm.core.global;

public interface IMessage {
	public boolean sendNotify(String title,String to,String msg);
	public boolean sendMail(String title,String to,String msg);
}
