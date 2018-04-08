package com.huicheng.dao;

import java.util.List;
import java.util.Map;

import com.huicheng.pojo.DeliveryInfo;
import com.huicheng.pojo.PaymentInfo;

public interface DeliveryDao {

	public List<DeliveryInfo> getDeliveryInfo(Map<String, Object> map);
	
	public int getCountDeliveryInfo(Map<String, Object> map);
	
	public int addBillingInfo(Map<String, Object> map);
	
	public int addPaymentInfo(List<PaymentInfo> list);
	
	public int addDeliveryInfo(DeliveryInfo info);
}
