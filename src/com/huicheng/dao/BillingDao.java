package com.huicheng.dao;

import java.util.List;
import java.util.Map;

import com.huicheng.pojo.BillInfo;
import com.huicheng.pojo.DeliveryInfo;

public interface BillingDao {

	public List<BillInfo> getBillingInfo(Map<String, Object> map);
	
	public int getCountBillingInfo(Map<String, Object> map);
	
	public int addBillInfos(BillInfo bill);
}
