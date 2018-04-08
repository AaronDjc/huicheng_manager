package com.huicheng.service;

import javax.servlet.http.HttpServletRequest;

public interface BillingService {

	public String getBillingInfo(HttpServletRequest req);
	
	public String addBillingInfo(HttpServletRequest req);
}
