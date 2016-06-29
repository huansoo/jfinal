package com.jfsnpm.jfsnpm.core.global;

import java.util.List;

import com.jfsnpm.jfsnpm.core.dao.Org;
import com.jfsnpm.jfsnpm.core.dao.TreeOrg;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;

public interface IOrg {
	/**
	 * 获取组织树
	 * @return
	 */
	public List<Org> getOrgAll();
	/**
	 * 更新组织树
	 * @param tree
	 * @return
	 * @throws JfsnpmException 
	 */
	public boolean updateOrg(TreeOrg tree) throws JfsnpmException;
	/**
	 * 删除组织
	 * @param ids
	 * @return
	 * @throws JfsnpmException
	 */
	public boolean deleteOrg(String[] ids) throws JfsnpmException;
	/**
	 * 获取指定组织的成员组织
	 * @param orgId
	 * @return
	 */
	public List<Org> getOrgChildren(String orgId);
	public List<Org> getOrgParent(String orgId);
}
