package com.jfsnpm.jfsnpm.core.service;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
	/**
	 * 获取组的成员用户
	 * @param orgId
	 * @return
	 */
	public static List<String> getOrgMember(String orgId){
		List<String> member = new ArrayList<String>();
		member.addAll(OrgService.getOrgMember(orgId));
		
		return member;
	}
	/**
	 * 获取组的所有成员用户，包括子组织
	 * @param orgId
	 * @return
	 */
	public static List<String> getOrgMemberAll(String orgId){
		List<String> member = new ArrayList<String>();
		member.addAll(OrgService.getOrgMemberAll(orgId));
		return member;
	}
	/**
	 * 获取当前组织的上级组织用户
	 * @param orgId
	 * @return
	 */
	public static List<String> getOrgMemberParent(String orgId){
		List<String> member = new ArrayList<String>();
		member.addAll(OrgService.getOrgMemberParent(orgId));
		return member;
	}
}
