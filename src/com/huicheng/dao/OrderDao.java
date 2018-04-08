package com.huicheng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huicheng.pojo.BillInfo;
import com.huicheng.pojo.DeliveryInfo;
import com.huicheng.pojo.OrderInfo;
import com.huicheng.pojo.PaymentInfo;

public interface OrderDao {

	public List<OrderInfo> getAllOrders(Map<String, Object> map);
	
	public List<DeliveryInfo> getDeliveryData();
	
	public List<PaymentInfo> getPaymentData(List<OrderInfo> infos);
	
	public int getAllCount(Map<String, Object> map);
	
	public int addDeliveryInfo(Map<String, Object> map);
	
	public OrderInfo getRemainAmount(String orderNo);
	
	public void changeInfo(Map<String, Object> map);
	
	public String queryPayMethod(Map<String, Object> map);
	
	public String queryDeliveryInfo(Map<String, Object> map);
	
	public String queryBillingInfo(Map<String, Object> map);
	
	public String queryPayFlag(String[] array);
	
	public List<OrderInfo> getOrderInfos(List<String> list);
	
	public int addPaymentMethodInfo(List<PaymentInfo> payList);
	
	public int updatePayInfo(List<OrderInfo> payList);
	
	public int updateOrderStatus(List<OrderInfo> orderList);
	
	public int addDeliveryInfos(List<DeliveryInfo> deliveryList);
	
	public int editOrderNoData(Map<String, Object> map);
	
	public OrderInfo queryUnitPriceByOrderNo(Map<String, Object> map);
	
	public OrderInfo queryAmountByOrderNo(Map<String, Object> map);
	
	public int updateOrderInfoByBill(List<OrderInfo> orderList);
	
	public int addBillInfos(List<BillInfo> billInfoList);
}
