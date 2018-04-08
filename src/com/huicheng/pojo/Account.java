package com.huicheng.pojo;

/**
 * 
 * @author Administrator
 * 2018年1月21日
 */
public class Account {
	/**
	 * 账号（员工编号）
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 员工姓名
	 */
	private String name;
	/**
	 * 部门id
	 */
	private String deptId;
	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * createTime
	 * @return
	 */
	private String createTime;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
