package com.jfsnpm.jfsnpm.core.impl;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.dao.User;
import com.jfsnpm.jfsnpm.core.global.IFlowArgs;
import com.jfsnpm.jfsnpm.core.service.GroupService;
import com.jfsnpm.jfsnpm.core.service.OrgService;
import com.jfsnpm.jfsnpm.core.service.RoleService;
import com.jfsnpm.jfsnpm.core.service.UserService;
import com.jfsnpm.jfsnpm.core.util.AppHelper;

public class FlowArgsImpl implements IFlowArgs {

	@Override
	public String getFlowAssignee(String userId, String key) {
		User user = UserService.getUserById(userId);
		if("__userId".equals(key)) return user.getUserId();
		if("__userName".equals(key)) return user.getUserName();
		if("__userMail".equals(key)) return user.getUserMail();
		
		if("__parent".equals(key)){
			//获取用户的组织
			String orgId = Db.findFirst(AppHelper.getSql("org.getOrgByUser"), userId).getStr("id");
			List<String> users = GroupService.getOrgMemberParent(orgId);
			if(users!=null&&users.size()>0){
				return AppHelper.listToString(users);
			}
		}
		//去组织中获取
		Record org = Db.findFirst("SELECT * FROM jfsnpm_org WHERE text = ?", key);
		if(org!=null&&!AppHelper.isEmpty(org.getStr("id"))){
			List<String> users = OrgService.getOrgMember(org.getStr("id"));
			if(users!=null&&users.size()>0){
				return AppHelper.listToString(users);
			}
		}
		//去角色中获取
		Record role = Db.findFirst("SELECT * FROM jfsnpm_role WHERE name = ?", key);
		if(role!=null&&!AppHelper.isEmpty(role.getStr("id"))){
			List<String> users = RoleService.getRoleMember(role.getStr("id"));
			if(users!=null&&users.size()>0){
				return AppHelper.listToString(users);
			}
		}
		//TODO:其他用户相关数据的写入
		return null;
	}

}
