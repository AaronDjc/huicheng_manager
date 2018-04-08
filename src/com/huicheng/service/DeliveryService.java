package com.huicheng.service;

import javax.servlet.http.HttpServletRequest;

public interface DeliveryService {

	public String getDeliveryInfo(HttpServletRequest req);
	
	public String addBillingInfo(HttpServletRequest req);
	
	public String addPaymentInfo(HttpServletRequest req);
	
	public String addDeliveryInfo(HttpServletRequest req);
}
