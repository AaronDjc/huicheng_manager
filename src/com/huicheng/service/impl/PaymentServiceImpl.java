package com.huicheng.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.huicheng.dao.PaymentDao;
import com.huicheng.pojo.BillInfo;
import com.huicheng.pojo.OrderInfo;
import com.huicheng.pojo.PaymentInfo;
import com.huicheng.service.PaymentService;
import com.huicheng.util.IdCreateTools;

import net.sf.json.JSONObject;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	
	@Override
	public String getPaymentInfo(HttpServletRequest req) {
		String curPage = req.getParameter("page");
		String pageNo = req.getParameter("rows");
		
		String orderNo = req.getParameter("orderNo");
		
		Integer pageSize = Integer.parseInt(pageNo);
		Integer fromIndex = pageSize * (Integer.parseInt(curPage)-1);
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("fromIndex", fromIndex);
		params.put("pageSize", pageSize);
		params.put("orderNo", orderNo);
		List<PaymentInfo> rows = paymentDao.getPaymentInfo(params);
		
		Integer total = paymentDao.getCountPaymentInfo(params);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return new Gson().toJson(result);
	}

	@Override
	public String addPayMethodInfo(HttpServletRequest req){
		String orderNos = req.getParameter("orderNos");
		String prepaymentRate = req.getParameter("prepaymentRate");//预付率
		//String prepaidTime = req.getParameter("prepaidTime");//预付时间
		String payMentDay = req.getParameter("payMentDay");//票(货)到天数
		String arrivalAfterRate = req.getParameter("arrivalAfterRate");//票(货)到付款率
		String warrantyRate = req.getParameter("warrantyRate");//质保率
		String warrantyPeriod = req.getParameter("warrantyPeriod");//质保期限
		
		String[] orderNoArray = orderNos.split(",");
		List<String> orderNoList = Arrays.asList(orderNoArray);
		List<OrderInfo> orderInfos = paymentDao.getOrderInfos(orderNoList);
		List<PaymentInfo> payInfoList = new ArrayList<PaymentInfo>();
		/*for(OrderInfo order : orderInfos){
			PaymentInfo payInfo = new PaymentInfo();
			payInfo.setPaymentNo(IdCreateTools.getId("Payment"));//回款编号
			payInfo.setOrderNo(order.getOrderNo());//订单编号
			payInfo.setPrepaidMoney(order.getTotalPrice() * Integer.valueOf(prepaymentRate) / 100);//预付金额
			payInfo.setWarranty(order.getTotalPrice() * Integer.valueOf(warrantyRate) / 100);//质保金额
			//payInfo.setPaymentDate(prepaidTime);
			payInfo.setWarrantyPeriod(warrantyPeriod+"年");//质保期限
			payInfoList.add(payInfo);
		}*/
		
		int count = paymentDao.addPaymentMethodInfo(payInfoList);
		if(count > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}

	@Override
	public String addPaymentInfo(HttpServletRequest req) {
		JSONObject data = JSONObject.fromObject(req.getParameter("paymentInfo"));
		String paymentNo = IdCreateTools.getId("Payment");
		String orderNo = data.getString("orderNo");
		if(StringUtils.isBlank(orderNo)){
			orderNo = null;
		}
		String paymentDate = data.getString("paymentDate");
		String paymentMoney = data.getString("paymentMoney");
		String paymentRate = data.getString("paymentRate");
		
		PaymentInfo pay = new PaymentInfo();
		pay.setPaymentNo(paymentNo);
		pay.setOrderNo(orderNo);
		/*pay.setPaymentDate(paymentDate);
		pay.setPaymentMoney(Double.valueOf(paymentMoney));
		pay.setPaymentRate(Double.valueOf(paymentRate));*/
		int k = paymentDao.addPaymentInfo(pay);
		if(k > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}

}
