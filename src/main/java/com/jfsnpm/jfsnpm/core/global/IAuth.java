package com.jfsnpm.jfsnpm.core.global;

import java.util.List;

import com.jfsnpm.jfsnpm.core.dao.Menu;
import com.jfsnpm.jfsnpm.core.dao.Tree;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public interface IAuth {
	/**
	 * 获取用户菜单
	 * @param userId
	 * @return
	 */
	public List<Menu> getMenuByUserNo(String userNo);
	/**
	 * 获取所有菜单
	 * @return
	 */
	public List<Menu> getMenuAll();
	/**
	 * 更新菜单树
	 * @param tree
	 * @return
	 * @throws JfsnpmException 
	 */
	public boolean updateMenu(Tree tree) throws JfsnpmException;
	/**
	 * 删除菜单
	 * @param ids
	 * @return
	 * @throws JfsnpmException
	 */
	public boolean deleteMenu(String[] ids) throws JfsnpmException;
}
