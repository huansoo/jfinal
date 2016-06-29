package com.jfsnpm.jfsnpm.core.impl;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.dao.Menu;
import com.jfsnpm.jfsnpm.core.dao.Tree;
import com.jfsnpm.jfsnpm.core.global.IAuth;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public class AuthImpl implements IAuth {

	public List<Menu> getMenuByUserNo(String userNo) {
		if(AppHelper.isEmpty(userNo)) return null;
		List<Record> list = Db.find(AppHelper.getSql("menu.getMenuByUserNo"), userNo,"1",userNo,"1",userNo,"1");
		List<Menu> menuList = new ArrayList<Menu>();
		//循环处理
		for(Record r:list){
			Menu m = new Menu();
			m.setMenuId(r.getStr("id"));
			m.setpMenuId(r.getStr("pId"));
			m.setText(r.getStr("text"));
			m.setUrl(r.getStr("url"));
			m.setSortNo(r.getStr("sortNo"));
			menuList.add(m);
		}
		
		return menuList;
	}

	public List<Menu> getMenuAll() {
		List<Record> list = Db.find(AppHelper.getSql("menu.getMenuALL"));
		List<Menu> menuList = new ArrayList<Menu>();
		//循环处理
		for(Record r:list){
			Menu m = new Menu();
			m.setMenuId(r.getStr("id"));
			m.setpMenuId(r.getStr("pId"));
			m.setText(r.getStr("text"));
			m.setUrl(r.getStr("url"));
			m.setSortNo(r.getStr("sortNo"));
			m.setStatus(r.getStr("status"));
			menuList.add(m);
		}
		return menuList;
	}

	public boolean updateMenu(Tree tree) throws JfsnpmException {
		return tree.saveTree();
	}

	public boolean deleteMenu(String[] ids) throws JfsnpmException {
		for(String id:ids){
			if(!Db.deleteById("jfsnpm_menu", id)){
				throw new JfsnpmException("删除菜单失败");
			}
		}
		return true;
	}

}
