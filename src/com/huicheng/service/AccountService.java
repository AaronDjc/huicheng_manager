package com.huicheng.service;

import com.huicheng.pojo.Account;

/**
 * 账号服务接口
 * @author Administrator
 * 2018年1月21日
 */
public interface AccountService {
	/**
	 * 导出Excel
	 * @param condition
	 * @return
	 *//*
	public HSSFWorkbook export(AccountCondition condition);*/
	/**
	 * 编辑帐号信息
	 * @param acc
	 * @param roleIds
	 *//*
	public void edit(Account acc, String[] roleIds);*/
	/**
	 * 查询帐号以及帐号关联的角色信息
	 * @param account
	 */
	public Account findAccountInfo(String account);
	/**
	 * 批量删除
	 * @param accounts
	 */
	public void delBatch(String[] accounts);
	/**
	 * 删除
	 * @param account
	 */
	public void del(String account);
	/**
	 * 新增帐号
	 * @param acc
	 * @param roleIds
	 */
	public void add(Account acc, String[] roleIds);
	
	/**
	 * 登录验证
	 * @param acc
	 * @return
	 */
	public Account LoginValidate(String account, String password);
	/**
	 * 修改密码
	 * @param account
	 */
	public void editPassword(Account account);
}
