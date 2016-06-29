package com.jfsnpm.jfsnpm.core.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.Org;
import com.jfsnpm.jfsnpm.core.dao.TreeOrg;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public class OrgService {
	/**
	 * 获取所有组织
	 * @return
	 */
	public static List<Org> getOrgAll(){
		return AppConfig.orgimpl.getOrgAll();
	}
	
	/**
	 * 保存组织
	 * @param menus JSON树
	 * @param deleteIds 需要删除的ids
	 * @return
	 * @throws JfsnpmException 
	 * @throws Exception 
	 */
	public static boolean updateOrg(String menus,String deleteIds) throws JfsnpmException{
		TreeOrg tree = AppHelper.json2TreeOrg(menus);
		String[] deleteidlist = AppHelper.json2Object(deleteIds, String[].class);
		return AppConfig.orgimpl.updateOrg(tree)&&AppConfig.orgimpl.deleteOrg(deleteidlist);
	}
	/**
	 * 获取指定组织的成员
	 * @param orgId
	 * @return
	 */
	public static List<String> getOrgMember(String orgId){
		if(AppHelper.isEmpty(orgId)) return null;
		List<String> member = new ArrayList<String>();
		List<Record> memberRecordList = Db.find(AppHelper.getSql("org.getOrgUser"), orgId);
		for(Record memberRecord:memberRecordList){
			member.add(memberRecord.getStr("userId"));
		}
		return member;
	}
	/**
	 * 获取指定组织的全部成员，包括下级组织
	 * @param orgId
	 * @return
	 */
	public static List<String> getOrgMemberAll(String orgId){
		List<String> member = new ArrayList<String>();
		List<Org> orgList = AppConfig.orgimpl.getOrgChildren(orgId);
		for(Org org:orgList){
			member.addAll(getOrgMember(org.getOrgId()));
		}
		return member;
	}
	/**
	 * 获取指定组织的上级组织成员
	 * @param orgId
	 * @return
	 */
	public static List<String> getOrgMemberParent(String orgId){
		List<String> member = new ArrayList<String>();
		List<Org> orgList = AppConfig.orgimpl.getOrgParent(orgId);
		for(Org org:orgList){
			member.addAll(getOrgMember(org.getOrgId()));
		}
		return member;
	}
}
