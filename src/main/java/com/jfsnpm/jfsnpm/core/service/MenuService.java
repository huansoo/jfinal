package com.jfsnpm.jfsnpm.core.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.AppConfig;
import com.jfsnpm.jfsnpm.core.dao.Menu;
import com.jfsnpm.jfsnpm.core.dao.Tree;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public class MenuService {
	/**
	 * 获取有权限的用户菜单
	 * @param c
	 * @return
	 */
	public static List<Menu> getMenu(Controller c){
		return AppConfig.authimpl.getMenuByUserNo(UserService.getUserInfor(c).getUserNo());
	}
	/**
	 * 获取用户topten菜单
	 * @param userId
	 * @return
	 */
	public static List<Record> getMenuTopTen(String userId){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.MONTH, -3);//月份减一
		String datestr = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
		List<Record> list = Db.find(AppHelper.getSql("menu.getMenuTopTen"), userId,userId,userId,datestr);
		if(list.size()<1) return null;
		return list.size()>10?list.subList(1, 10):list.subList(1, list.size());
	}
	/**
	 * 获取所有菜单
	 * @return
	 */
	public static List<Menu> getMenuAll(){
		return AppConfig.authimpl.getMenuAll();
	}

	public static boolean checkMenuByUserNo(String userNo,String MenuUrl){
		//TODO:checkMenuByUserId
		return false;
	}
	/**
	 * 保存菜单
	 * @param menus JSON树
	 * @param deleteIds 需要删除的ids
	 * @return
	 * @throws JfsnpmException
	 * @throws Exception
	 */
	public static boolean updateMenu(String menus,String deleteIds) throws JfsnpmException{
		Tree tree = AppHelper.json2Tree(menus);
		String[] deleteidlist = AppHelper.json2Object(deleteIds, String[].class);
		return AppConfig.authimpl.updateMenu(tree)&&AppConfig.authimpl.deleteMenu(deleteidlist);
	}
}
