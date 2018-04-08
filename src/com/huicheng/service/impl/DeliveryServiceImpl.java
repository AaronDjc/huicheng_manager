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
import com.huicheng.dao.DeliveryDao;
import com.huicheng.pojo.DeliveryInfo;
import com.huicheng.pojo.PaymentInfo;
import com.huicheng.service.DeliveryService;
import com.huicheng.util.IdCreateTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryDao deliveryDao;
	
	@Override
	public String getDeliveryInfo(HttpServletRequest req) {
		String curPage = req.getParameter("page");
		String pageNo = req.getParameter("rows");
		
		String orderNo = req.getParameter("orderNo");
		
		Integer pageSize = Integer.parseInt(pageNo);
		Integer fromIndex = pageSize * (Integer.parseInt(curPage)-1);
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("fromIndex", fromIndex);
		params.put("pageSize", pageSize);
		params.put("orderNo", orderNo);
		List<DeliveryInfo> rows = deliveryDao.getDeliveryInfo(params);
		
		Integer total = deliveryDao.getCountDeliveryInfo(params);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return new Gson().toJson(result);
	}

	@Override
	public String addBillingInfo(HttpServletRequest req) {
		
		String orderNo = req.getParameter("orderNo");
		String number = req.getParameter("number");
		String billMoney = req.getParameter("billMoney");
		String billDate = req.getParameter("billDate");
		String invoiceType = req.getParameter("invoiceType");
		String invoiceNum = req.getParameter("invoiceNum");
		String deliveryMethod = req.getParameter("deliveryMethod");
		String mailAddress = req.getParameter("mailAddress");
		String contacts = req.getParameter("contacts");
		String telephone = req.getParameter("telephone");
		String expressNo = req.getParameter("expressNo");
		String mailDate = req.getParameter("mailDate");
		String billCompanyName = req.getParameter("billCompanyName");
		
		number = StringUtils.isEmpty(number)?null:number;
		billMoney = StringUtils.isEmpty(billMoney)?null:billMoney;
		billDate = StringUtils.isEmpty(billDate)?null:billDate;
		mailDate = StringUtils.isEmpty(mailDate)?null:mailDate;
		Map<String, Object> map = new HashMap<String, Object>();
		String billNo = IdCreateTools.getId("Bill");
		map.put("billNo", billNo);
		map.put("orderNo", orderNo);
		map.put("amount", number);
		map.put("billMoney", billMoney);
		map.put("billDate", billDate);
		map.put("invoiceType", invoiceType);
		map.put("invoiceNum", invoiceNum);
		map.put("deliveryMethod", deliveryMethod);
		map.put("address", mailAddress);
		map.put("contacts", contacts);
		map.put("telephone", telephone);
		map.put("expressNo", expressNo);
		map.put("mailDate", mailDate);
		map.put("billCompanyName", billCompanyName);
		int count = deliveryDao.addBillingInfo(map);
		if(count > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}

	@Override
	public String addPaymentInfo(HttpServletRequest req){
		JSONArray array = JSONArray.fromObject(req.getParameter("paymentArray"));
		List<PaymentInfo> payList = new ArrayList<PaymentInfo>();
		String paymentNo = IdCreateTools.getId("Payment");
		for(int i = 0;i<array.size();i++){
			JSONObject data = array.getJSONObject(i);
			PaymentInfo payment = new PaymentInfo();
			payment.setPaymentNo(paymentNo);
			payment.setOrderNo(data.getString("orderNo"));
			payment.setAdvancePaymentDate(data.getString("advancePaymentDate"));
			payment.setAdvancePayment(Double.valueOf(data.getString("advancePayment")));
			payment.setAdvancePaymentRate(Double.valueOf(data.getString("advancePaymentRate")));
			payList.add(payment);
		}
		int i = deliveryDao.addPaymentInfo(payList);
		if(i > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}

	@Override
	public String addDeliveryInfo(HttpServletRequest req) {
		JSONObject json = JSONObject.fromObject(req.getParameter("deliveryInfo"));
		String orderNo = json.getString("orderNo");
		String deliveryDate = json.getString("deliveryDate");
		String deliveryNum = json.getString("deliveryNum");
		String deliveryMoney = json.getString("deliveryMoney");
		String arrivalDate = json.getString("arrivalDate");
		String receiptDate = json.getString("receiptDate");
		String returnInfo = json.getString("returnInfo");
		
		DeliveryInfo info = new DeliveryInfo();
		info.setDeliveryNo(IdCreateTools.getId("Delivery"));
		info.setOrderNo(orderNo);
		info.setDeliveryDate(deliveryDate);
		info.setDeliveryNum(Integer.valueOf(deliveryNum));
		info.setDeliveryMoney(Double.valueOf(deliveryMoney));
		info.setArrivalDate(arrivalDate);
		info.setReceiptDate("".equals(receiptDate)?null:receiptDate);
		info.setReturnInfo(returnInfo);
		
		int count = deliveryDao.addDeliveryInfo(info);
		if(count > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}
}
