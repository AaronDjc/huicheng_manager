package com.huicheng.service;

import javax.servlet.http.HttpServletRequest;

public interface PaymentService {

	public String getPaymentInfo(HttpServletRequest req);
	
	public String addPayMethodInfo(HttpServletRequest req);
	
	public String addPaymentInfo(HttpServletRequest req);
}
