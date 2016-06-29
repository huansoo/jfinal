package com.jfsnpm.jfsnpm.core.impl;



import com.jfsnpm.jfsnpm.core.global.ISql;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.plugin.sqlinxml.SqlKit;

public class SqlImpl implements ISql {

	public String getSql(String sqlkey) {
		//TODO:demo从XML文件获取
		if(AppHelper.isEmpty(sqlkey)) return null;
		return SqlKit.sql(sqlkey);
	}
}
