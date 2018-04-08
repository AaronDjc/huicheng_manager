package com.huicheng.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huicheng.dao.AccountDao;
import com.huicheng.pojo.Account;
import com.huicheng.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	
	@Override
	public Account LoginValidate(String account, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("password", password);
		return accountDao.loginValidate(map);
	}

	@Override
	public void add(Account acc, String[] roleIds) {
		// 新增帐号基本信息数据
		//accountDao.add(acc);
		// 新增帐号角色中间表数据
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", acc.getAccount());
		params.put("roleIds", roleIds);
		accountDao.addAccRole(params);
	}

	@Override
	public void del(String account) {
		// 删除帐号基本信息
		accountDao.del(account);
		// 删除帐号角色中间表信息
		accountDao.delRoleByAcc(account);
	}

	@Override
	public Account findAccountInfo(String account) {
		return accountDao.findAccountInfo(account);
	}

	@Override
	public void delBatch(String[] accounts) {
		// 删除帐号基本信息
		accountDao.delBatch(accounts);
		// 删除帐号角色关联关系
		accountDao.delBatchRoleByAccs(accounts);
	}

	@Override
	public void editPassword(Account account) {
		// TODO Auto-generated method stub
		
	}

}
