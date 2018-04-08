package com.huicheng.dao;

import java.util.List;
import java.util.Map;

import com.huicheng.pojo.OrderInfo;
import com.huicheng.pojo.PaymentInfo;

public interface PaymentDao {

	public List<PaymentInfo> getPaymentInfo(Map<String, Object> map);
	
	public int getCountPaymentInfo(Map<String, Object> map);
	
	public List<OrderInfo> getOrderInfos(List<String> orderNoList);
	
	public int addPaymentMethodInfo(List<PaymentInfo> payInfoList);
	
	public int addPaymentInfo(PaymentInfo payInfo);
}
