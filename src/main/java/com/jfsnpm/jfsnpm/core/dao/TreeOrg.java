package com.jfsnpm.jfsnpm.core.dao;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public class TreeOrg {
	public String menuid;
	public String name;

	public String menustatus;
	
	public boolean isParent;
	public boolean checked;
	public List<TreeOrg> children;
	
	public String menupid;
	public String menusortno;
	
	//原始数据
	public String nameo;
	public String menustatuso;
	public boolean checkedo;
	
	
	public String menupido;
	public String menusortnoo;
	/**
	 * 保存树	
	 * 			1.自动判断是否为新增节点,若为新增节点，生成UUID。
	 * 			2.自动更新父序号			menupid		String
	 * 			3.自动更新排序号			menusortno	String
	 * 			4.更新到数据库表 jfsnpm_org[id,pId,text,sortNo,status]	
	 * @return this
	 * @throws Exception 
	 */
	public boolean saveTree() throws JfsnpmException{
		if(menupid==null&&!"_root".equals(menuid)) return true;
		Record tree = null;
		boolean isNew = false;
		boolean isEdit = true;
		if(AppHelper.isEmpty(menuid)){
			isNew = true;
			menuid = AppHelper.getUUID();
			tree = new Record().set("id",menuid);
		}
		if(AppHelper.isEmpty(menusortno)) menusortno = "_";
		if(children != null&&children.size()>0){
			for(int  i = 0   ; i < children.size(); i++) {
				children.get(i).menupid = menuid;
				children.get(i).menusortno = menusortno + String.format("%03d", i);
				if(!children.get(i).saveTree()) return false;
			}
		}
		if(menupid!=null&&AppHelper.isSame(menupid, menupido)&&AppHelper.isSame(menusortno, menusortnoo)&&
				AppHelper.isSame(menustatus, menustatuso)&&
				AppHelper.isSame(name, nameo)){
			isEdit = false;
		}
		if(isNew){
			tree.set("pId", menupid).set("text", name).set("sortNo", menusortno).set("status", menustatus);
			if(!Db.save("jfsnpm_org", tree)) throw new JfsnpmException("添加组织失败");
		}else{
			if(isEdit){
				if(!(Db.update("UPDATE jfsnpm_org SET pId=?,text=?,status=?,sortNo=?"
						+ " WHERE id=?",menupid,name,menustatus,menusortno,menuid)>0)) throw new JfsnpmException("更新组织失败");
			}else{
				return true;
			}
		}
		return true;
		
		
	}
}
