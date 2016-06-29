package com.jfsnpm.jfsnpm.core.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class RoleService {
	public static List<Record> getRoleAll(){
		return Db.find(AppHelper.getSql("role.getRoleAll"));
	}
	public static List<String> getRoleMember(String roleId){
		if(AppHelper.isEmpty(roleId)) return null;
		List<String> member = new ArrayList<String>();
		List<Record> memberRecordList = Db.find(AppHelper.getSql("role.getRoleUser"), roleId);
		for(Record memberRecord:memberRecordList){
			member.add(memberRecord.getStr("userId"));
		}
		return member;
	}
}
