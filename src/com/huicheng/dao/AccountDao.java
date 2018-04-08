package com.huicheng.dao;

import java.util.List;
import java.util.Map;

import com.huicheng.pojo.Account;

/**
 * 账号dao层
 * @author Administrator
 * 2018年1月21日
 */
public interface AccountDao {
	
	/**
	 * 跟新帐号基本信息
	 * @param acc
	 */
	public void edit(Account acc);
	/**
	 * 查询帐号以及帐号关联的角色信息
	 * @param account
	 */
	public Account findAccountInfo(String account);
	/**
	 * 批量删除帐号角色关联关系
	 * @param accounts
	 */
	public void delBatchRoleByAccs(String[] accounts);
	/**
	 * 删除帐号角色中间表数据
	 * @param account
	 */
	public void delRoleByAcc(String account);
	/**
	 * 批量删除
	 * @param accounts
	 */
	public void delBatch(String[] accounts);
	/**
	 * 删除帐号基本数据
	 * @param account
	 */
	public void del(String account);
	/**
	 * 新增帐号角色中间表数据
	 * @param params
	 */
	public void addAccRole(Map<String, Object> params);
	/**
	 * 新增帐号基本信息
	 * @param acc
	 */
	public void add(Map<String, Object> params);

	/**
	 * 登录验证
	 * @param acc
	 * @return
	 */
	public Account loginValidate(Map<String, Object> params);
	/**
	 * 修改密码
	 * @param account
	 */
	public void editPassword(Account account);
}
