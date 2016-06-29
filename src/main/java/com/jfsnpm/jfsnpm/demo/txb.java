package com.jfsnpm.jfsnpm.demo;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class txb {
	public static void saveb() throws Exception{
		Db.save("jfsnpm_demo", new Record().set("id", "bbb1").set("name", "bbb1"));
		throw new Exception("这里是异常！");
	}
}
