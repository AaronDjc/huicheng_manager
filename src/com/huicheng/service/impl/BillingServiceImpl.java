package com.huicheng.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.huicheng.dao.BillingDao;
import com.huicheng.pojo.BillInfo;
import com.huicheng.pojo.OrderInfo;
import com.huicheng.service.BillingService;
import com.huicheng.util.IdCreateTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	private BillingDao billingDao;
	
	@Override
	public String getBillingInfo(HttpServletRequest req) {
		String curPage = req.getParameter("page");
		String pageNo = req.getParameter("rows");
		
		String orderNo = req.getParameter("orderNo");
		
		Integer pageSize = Integer.parseInt(pageNo);
		Integer fromIndex = pageSize * (Integer.parseInt(curPage)-1);
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("fromIndex", fromIndex);
		params.put("pageSize", pageSize);
		params.put("orderNo", orderNo);
		List<BillInfo> rows = billingDao.getBillingInfo(params);
		
		Integer total = billingDao.getCountBillingInfo(params);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return new Gson().toJson(result);
	}

	@Override
	public String addBillingInfo(HttpServletRequest req) {
		List<BillInfo> billList = new ArrayList<BillInfo>();
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		JSONObject data = JSONObject.fromObject(req.getParameter("billInfo"));
		String billNo = IdCreateTools.getId("Bill");
			String orderNo = data.getString("orderNo");
			if(StringUtils.isBlank(orderNo)){
				orderNo = null;
			}
			String billingNum = data.getString("amount");
			String billDate = data.getString("billDate");
			String billCompanyName = data.getString("billCompanyName");
			String invoiceType = data.getString("invoiceType");
			String invoiceNum = data.getString("invoiceNum");
			String billMoney = data.getString("billMoney");
			String deliveryMethod = data.getString("deliveryMethod");
			String address = data.getString("address");
			String contacts = data.getString("contacts");
			String telephone = data.getString("telephone");
			String expressNo = data.getString("expressNo");
			String mailDate = data.getString("mailDate");
			if(StringUtils.isBlank(mailDate)){
				mailDate = null;
			}
			
			BillInfo billInfo = new BillInfo();
			billInfo.setBillNo(billNo);
			billInfo.setOrderNo(orderNo);
			billInfo.setAmount(Integer.valueOf(billingNum));
			billInfo.setBillDate(billDate);
			billInfo.setBillCompanyName(billCompanyName);
			billInfo.setInvoiceType(invoiceType);
			billInfo.setInvoiceNum(invoiceNum);
			billInfo.setBillMoney(Double.valueOf(billMoney));
			billInfo.setDeliveryMethod(deliveryMethod);
			billInfo.setAddress(address);
			billInfo.setContacts(contacts);
			billInfo.setTelephone(telephone);
			billInfo.setExpressNo(expressNo);
			billInfo.setMailDate(mailDate);
		
		int k = billingDao.addBillInfos(billInfo);
		if(k > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}
}
