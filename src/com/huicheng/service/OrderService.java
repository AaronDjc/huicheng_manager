package com.huicheng.service;


import javax.servlet.http.HttpServletRequest;


public interface OrderService {

	public String getAllOrders(HttpServletRequest req);
	
	public String addDeliveryInfo(HttpServletRequest req);
	
	public String addBillInfo(HttpServletRequest req);
	
	public String queryPayMethod(HttpServletRequest req);
	
	public String queryDeliveryOrBillInfo(HttpServletRequest req);
	
	public String addPayMethodInfo(HttpServletRequest req);
	
	public String editOrderNoData(HttpServletRequest req);
		
}
