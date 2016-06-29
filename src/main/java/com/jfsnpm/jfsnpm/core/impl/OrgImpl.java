package com.jfsnpm.jfsnpm.core.impl;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.dao.Org;
import com.jfsnpm.jfsnpm.core.dao.TreeOrg;
import com.jfsnpm.jfsnpm.core.global.IOrg;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public class OrgImpl implements IOrg {
	@Override
	public List<Org> getOrgAll() {
		List<Record> list = Db.find(AppHelper.getSql("org.getOrgALL"));
		List<Org> menuList = new ArrayList<Org>();
		//循环处理
		for(Record r:list){
			Org m = new Org();
			m.setOrgId(r.getStr("id"));
			m.setpOrgId(r.getStr("pId"));
			m.setText(r.getStr("text"));
			m.setSortNo(r.getStr("sortNo"));
			m.setStatus(r.getStr("status"));
			menuList.add(m);
		}
		return menuList;
	}

	@Override
	public boolean updateOrg(TreeOrg tree) throws JfsnpmException {
		return tree.saveTree();
	}

	@Override
	public boolean deleteOrg(String[] ids) throws JfsnpmException {
		for(String id:ids){
			if(!Db.deleteById("jfsnpm_org", id)){
				throw new JfsnpmException("删除组织失败");
			}
		}
		return true;
	}

	@Override
	public List<Org> getOrgChildren(String orgId) {
		Record thisOrg = Db.findFirst(AppHelper.getSql("org.getOrg"),orgId);
		List<Record> list = Db.find(AppHelper.getSql("org.getOrgChildren"),thisOrg.getStr("SortNo")+"%");
		List<Org> orgList = new ArrayList<Org>();
		//循环处理
		for(Record r:list){
			Org m = new Org();
			m.setOrgId(r.getStr("id"));
			m.setpOrgId(r.getStr("pId"));
			m.setText(r.getStr("text"));
			m.setSortNo(r.getStr("sortNo"));
			m.setStatus(r.getStr("status"));
			orgList.add(m);
		}
		return orgList;
	}

	@Override
	public List<Org> getOrgParent(String orgId) {
		Record thisOrg = Db.findFirst(AppHelper.getSql("org.getOrg"),orgId);
		String orgSort = thisOrg.getStr("sortNo");
		if(!AppHelper.isEmpty(orgSort)&&orgSort.length() > 4){
			orgSort = orgSort.substring(0, orgSort.length()-3);
		}
		List<Record> list = Db.find(AppHelper.getSql("org.getOrgChildren"),orgSort);
		List<Org> orgList = new ArrayList<Org>();
		//循环处理
		for(Record r:list){
			Org m = new Org();
			m.setOrgId(r.getStr("id"));
			m.setpOrgId(r.getStr("pId"));
			m.setText(r.getStr("text"));
			m.setSortNo(r.getStr("sortNo"));
			m.setStatus(r.getStr("status"));
			orgList.add(m);
		}
		return orgList;
	}

}
