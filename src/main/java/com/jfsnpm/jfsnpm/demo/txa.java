package com.jfsnpm.jfsnpm.demo;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class txa {
	public static void savea(){
		Db.save("jfsnpm_demo", new Record().set("id", "aaa1").set("name", "aaa1"));
	}
}
