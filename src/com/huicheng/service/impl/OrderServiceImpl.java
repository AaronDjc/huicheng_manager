package com.huicheng.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.huicheng.dao.OrderDao;
import com.huicheng.pojo.BillInfo;
import com.huicheng.pojo.DeliveryInfo;
import com.huicheng.pojo.OrderInfo;
import com.huicheng.pojo.PaymentInfo;
import com.huicheng.service.OrderService;
import com.huicheng.util.ExcelUtil;
import com.huicheng.util.IdCreateTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class OrderServiceImpl implements OrderService{
	
	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	public static String[] orderInfoArr = {"orderMonth","documentName","orderDate","orderNo","customerNum","region","salesman",
			"customerNo","orderAttribute","customerFullName","projectName","productNo","productCategory",
			"productName","model","amount","unit","unitPrice","totalPrice","deliveryAddress1","deliveryAddress2",
			"specialRemark","plugNum","originalOrder","inquiryNo","referProgramNo","referOrderNo","predeliveryDate"};
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public String getAllOrders(HttpServletRequest req) {
		String curPage = req.getParameter("page");
		String pageNo = req.getParameter("rows");
		
		String orderNo = req.getParameter("orderNo");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String documentName = req.getParameter("documentName");
		String customerFullName = req.getParameter("customerFullName");
		String projectName = req.getParameter("projectName");
		String billCompanyName = req.getParameter("billCompanyName");
		String contractNo = req.getParameter("contractNo");
		String specialPriceApplication = req.getParameter("specialPriceApplication");
		String productCategory = req.getParameter("productCategory");
		String inquiryNo = req.getParameter("inquiryNo");
		
		String salesman = req.getParameter("salesman");
		String region = req.getParameter("region");
		String customerNum = req.getParameter("customerNum");
		String customerNo = req.getParameter("customerNo");
		String orderAttribute = req.getParameter("orderAttribute");
		String productName = req.getParameter("productName");
		String model = req.getParameter("model");
		String deliveryAddress1 = req.getParameter("deliveryAddress1");
		String deliveryAddress2 = req.getParameter("deliveryAddress2");
		String originalOrder = req.getParameter("originalOrder");
		String referProgramNo = req.getParameter("referProgramNo");
		String referOrderNo = req.getParameter("referOrderNo");
		String amount = req.getParameter("amount");
		String unit = req.getParameter("unit");
		String totalPrice = req.getParameter("totalPrice");
		
		Integer pageSize = Integer.parseInt(pageNo);
		Integer fromIndex = pageSize * (Integer.parseInt(curPage)-1);
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("fromIndex", fromIndex);
		params.put("pageSize", pageSize);
		
		params.put("orderNo", orderNo);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("documentName", documentName);
		params.put("customerFullName", customerFullName);
		params.put("projectName", projectName);
		params.put("billCompanyName", billCompanyName);
		params.put("contractNo", contractNo);
		params.put("specialPriceApplication", specialPriceApplication);
		params.put("productCategory", productCategory);
		params.put("inquiryNo", inquiryNo);
		
		params.put("salesman", salesman);
		params.put("region", region);
		params.put("customerNum", customerNum);
		params.put("customerNo", customerNo);
		params.put("orderAttribute", orderAttribute);
		params.put("productName", productName);
		params.put("model", model);
		params.put("deliveryAddress1", deliveryAddress1);
		params.put("deliveryAddress2", deliveryAddress2);
		params.put("originalOrder", originalOrder);
		params.put("referProgramNo", referProgramNo);
		params.put("referOrderNo", referOrderNo);
		params.put("unit", unit);
		params.put("amount", amount);
		params.put("totalPrice", totalPrice);
		List<OrderInfo> rows = orderDao.getAllOrders(params);
		
	//	List<DeliveryInfo> list = orderDao.getDeliveryData();
		if(CollectionUtils.isNotEmpty(rows)){
			List<PaymentInfo> list = orderDao.getPaymentData(rows);
			int totalAmount = 0;
			int totalRemainAmount = 0;
			double totalAllMoney = 0;
			double deliAllMoney = 0;
			double billAllMoney = 0;
			double payAllMoney=0;
			double totalAllBalance=0;
			int remainAllInvoicesNum = 0;
			Integer deliveryNum = 0;
			Integer billingNum = 0;
			Double paymentMoney = 0.00;
			Double advanceTotalPayment = 0.00;
			Double arrivalTotalPayment = 0.00;
			Double warGoldTotalPayment = 0.00;
			for(OrderInfo order : rows){
				deliveryNum = order.getAmount() - order.getRemainAmount();
				order.setDeliveryTotalPrice(deliveryNum*order.getUnitPrice());
				billingNum = order.getAmount() - order.getRemainInvoicesNum();
				order.setBillingTotalPrice(billingNum*order.getUnitPrice());
				for(PaymentInfo payment : list){
					if(order.getOrderNo().equals(payment.getOrderNo())){
						if(payment.getAdvancePayment()==null){
							payment.setAdvancePayment(0.00);
						}
						if(payment.getArrivalPayment()==null){
							payment.setArrivalPayment(0.00);
						}
						if(payment.getWarGoldPayment()==null){
							payment.setWarGoldPayment(0.00);
						}
						advanceTotalPayment += payment.getAdvancePayment();
						arrivalTotalPayment += payment.getArrivalPayment();
						warGoldTotalPayment += payment.getWarGoldPayment();
						paymentMoney += (payment.getAdvancePayment()+payment.getArrivalPayment()+payment.getWarGoldPayment());
					}
				}
				order.setAdvanceTotalPayment(advanceTotalPayment);
				order.setArrivalTotalPayment(arrivalTotalPayment);
				order.setWarGoldTotalPayment(warGoldTotalPayment);
				order.setPaymentTotalPrice(paymentMoney);
				order.setTotalBalance(order.getTotalPrice()-paymentMoney);
				totalAmount = totalAmount+Integer.valueOf(order.getAmount());//订单货物数量总计
				totalRemainAmount = totalRemainAmount+Integer.valueOf(order.getRemainAmount());//剩余货物数量总计
				totalAllMoney = totalAllMoney+order.getTotalPrice();//订单金额总计
				deliAllMoney = deliAllMoney+order.getDeliveryTotalPrice();//发货额总计
				remainAllInvoicesNum +=order.getRemainInvoicesNum();//剩余开票货物数量总计
				billAllMoney = billAllMoney+order.getBillingTotalPrice();//开票额总计
				payAllMoney = payAllMoney+order.getPaymentTotalPrice();//回款总计
				totalAllBalance = totalAllBalance+order.getTotalBalance();//余额总计
			}
			
			OrderInfo orderSum = new OrderInfo();
			orderSum.setAmount(totalAmount);
			orderSum.setRemainAmount(totalRemainAmount);
			orderSum.setTotalPrice(totalAllMoney);
			orderSum.setDeliveryTotalPrice(deliAllMoney);
			orderSum.setRemainInvoicesNum(remainAllInvoicesNum);
			orderSum.setBillingTotalPrice(billAllMoney);
			orderSum.setPaymentTotalPrice(payAllMoney);
			orderSum.setTotalBalance(totalAllBalance);
			orderSum.setOrderNo("总计");
			rows.add(orderSum);
		}
	
		
		Integer total = orderDao.getAllCount(params);
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return new Gson().toJson(result);
	}

	@Override
	public String addDeliveryInfo(HttpServletRequest req) {
		//JSONObject orderArray = JSONObject.fromObject(req.getParameter("orderArray"));
//      int rows = obj.getInt("rows");
     // JSONArray data = JSONArray.fromObject(req.getParameter("orderArray"));
		List<DeliveryInfo> deliveryList = new ArrayList<DeliveryInfo>();
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		String sss = req.getParameter("orderArray");
		JSONArray array = JSONArray.fromObject(req.getParameter("orderArray"));
		String deliveryNo = IdCreateTools.getId("Delivery");
		for(int i=0;i < array.size();i++){
			JSONObject data = array.getJSONObject(i);
			String orderNo = data.getString("orderNo");
			String deliveryDate = data.getString("deliveryDate");
			String deliveryNum = data.getString("deliveryNum");
			String arrivalDate = data.getString("arrivalDate");
			String deliveryMoney = data.getString("deliveryMoney");
			String remainAmount = data.getString("remainAmount");
			
			OrderInfo order = new OrderInfo();
			order.setOrderNo(orderNo);
			order.setRemainAmount(Integer.valueOf(remainAmount));
			orderList.add(order);
			
			DeliveryInfo delivery = new DeliveryInfo();
			delivery.setDeliveryNo(deliveryNo);
			delivery.setOrderNo(orderNo);
			delivery.setDeliveryMoney(Double.valueOf(deliveryMoney));
			delivery.setDeliveryDate(deliveryDate);
			delivery.setDeliveryNum(Integer.valueOf(deliveryNum));
			delivery.setArrivalDate(arrivalDate);
			deliveryList.add(delivery);
		}
		
		for(OrderInfo ord : orderList){
			int result = Integer.parseInt(ord.getRemainAmount().toString());
			ord.setRemainAmount(result);
		}
		
		int i = orderDao.updateOrderStatus(orderList);
		
		int k = orderDao.addDeliveryInfos(deliveryList);
		if(i > 0 && k > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
		/*String orderNo = req.getParameter("orderNo");
		String deliveryDate = req.getParameter("delivDate");
		String deliveryNum = req.getParameter("deliveryNum");
		String arrivalDate = req.getParameter("arrivalDate");
		
		OrderInfo orderInfo = orderDao.getRemainAmount(orderNo);
		int remainAmount = orderInfo.getRemainAmount();
		int resultNum = remainAmount - Integer.parseInt(deliveryNum);
		Map<String, Object> map = new HashMap<String, Object>();
		if(resultNum == 0){
			map.put("isDelivery",0);
		}else{
			map.put("isDelivery",1);
		}
		map.put("orderNo", orderNo);
		map.put("resultNum", resultNum);
		map.put("payFlag", '否');
		orderDao.changeInfo(map);
		
		String deliveryNo = IdCreateTools.getId("Delivery");
		Map<String, Object> goodsMap = new HashMap<String, Object>();
		goodsMap.put("deliveryDate", deliveryDate);
		goodsMap.put("deliveryNum", deliveryNum);
		goodsMap.put("arrivalDate", arrivalDate);
		goodsMap.put("deliveryNo", deliveryNo);
		goodsMap.put("orderNo", orderNo);
		
		int result = orderDao.addDeliveryInfo(goodsMap);
		if(result == 1){
			return "SUCCESS";
		}else{
			return "FAIL";
		}*/
	}

	@Override
	public String queryPayMethod(HttpServletRequest req) {
		String orderNo = req.getParameter("orderNo");
		
		Map<String, Object> payMethodMap = new HashMap<String, Object>();
		payMethodMap.put("orderNo", orderNo);
		String payMethod = orderDao.queryPayMethod(payMethodMap);
		if(StringUtils.isEmpty(payMethod)){
			return "NOTEXSIT";
		}else{
			return "EXSIT";
		}
	}

	@Override
	public String queryDeliveryOrBillInfo(HttpServletRequest req) {
		String orderNos = req.getParameter("orderNos");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNos", orderNos);
		String deliveryDate = orderDao.queryDeliveryInfo(map);
		String billingDate = orderDao.queryBillingInfo(map);
		if(StringUtils.isNotEmpty(deliveryDate) || StringUtils.isNotEmpty(billingDate)){
			return "NOTEDITOR";
		}else{
			return "EDITOR";
		}
	}

	@Override
	public String addPayMethodInfo(HttpServletRequest req) {
		String orderNos = req.getParameter("orderNos");
		String prepaymentRate = req.getParameter("prepaymentRate");//预付率
		//String prepaidTime = req.getParameter("prepaidTime");//预付时间
		String payMentDay = req.getParameter("payMentDay");//票(货)到天数
		String arrivalAfterRate = req.getParameter("arrivalAfterRate");//票(货)到付款率
		String warrantyRate = req.getParameter("warrantyRate");//质保率
		String warrantyPeriod = req.getParameter("warrantyPeriod");//质保期限
		String payMethod = req.getParameter("payMethod");//质保期限
		
		prepaymentRate = prepaymentRate.equals("")?"0":prepaymentRate;
		arrivalAfterRate = arrivalAfterRate.equals("")?"0":arrivalAfterRate;
		warrantyRate = warrantyRate.equals("")?"0":warrantyRate;
		warrantyPeriod = warrantyPeriod.equals("")?"0":warrantyPeriod;
		String[] orderNoArray = orderNos.split(",");
		List<String> orderNoList = Arrays.asList(orderNoArray);
		List<OrderInfo> orderInfos = orderDao.getOrderInfos(orderNoList);
		/*List<PaymentInfo> payInfoList = new ArrayList<PaymentInfo>();
		for(OrderInfo order : orderInfos){
			PaymentInfo payInfo = new PaymentInfo();
			payInfo.setPaymentNo(IdCreateTools.getId("Payment"));//回款编号
			payInfo.setOrderNo(order.getOrderNo());//订单编号
			payInfo.setPrepaidMoney(order.getTotalPrice() * Integer.valueOf(prepaymentRate) / 100);//预付金额
			payInfo.setWarranty(order.getTotalPrice() * Integer.valueOf(warrantyRate) / 100);//质保金额
			//payInfo.setPaymentDate(prepaidTime);
			payInfo.setWarrantyPeriod(warrantyPeriod);//质保期限
			payInfoList.add(payInfo);
		}*/
		
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		for(OrderInfo order : orderInfos){
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrderNo(order.getOrderNo());//订单编号
			orderInfo.setPrepaidMoney(order.getTotalPrice() * Integer.valueOf(prepaymentRate) / 100);//预付金额
			orderInfo.setArrivalMoney(order.getTotalPrice() * Integer.valueOf(arrivalAfterRate) / 100);
			orderInfo.setWarranty(order.getTotalPrice() * Integer.valueOf(warrantyRate) / 100);//质保金额
			//payInfo.setPaymentDate(prepaidTime);
			orderInfo.setWarrantyPeriod(warrantyPeriod);//质保期限
			orderInfo.setPayMethod(payMethod);
			orderInfo.setPayFlag("是");
			orderInfoList.add(orderInfo);
		}
		
		int count = orderDao.updatePayInfo(orderInfoList);
		if(count > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}

	@Override
	public String editOrderNoData(HttpServletRequest req) {
		String orderNo = req.getParameter("orderNo");
		String salesman = req.getParameter("salesman");
		String projectName = req.getParameter("projectName");
		String productNo = req.getParameter("productNo");
		String unitPrice = req.getParameter("unitPrice");
		String amount = req.getParameter("amount");
		String newRemainAmount = req.getParameter("newRemainAmount");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderNo",orderNo);
		map.put("unitPrice",unitPrice);
		map.put("amount",amount);
		
		OrderInfo orderInfoByUnitPrice = orderDao.queryUnitPriceByOrderNo(map);
		if(orderInfoByUnitPrice == null){
			Double newTotalPrice = Double.parseDouble(unitPrice) * Integer.parseInt(amount);
			map.put("totalPrice", newTotalPrice);
		}else{
			map.put("totalPrice", orderInfoByUnitPrice.getTotalPrice());
		}
		
		OrderInfo orderInfoByAmount = orderDao.queryAmountByOrderNo(map);
		if(orderInfoByAmount == null){
			Double newTotalPrice = Double.parseDouble(unitPrice) * Integer.parseInt(amount);
			map.put("totalPrice", newTotalPrice);
		}else{
			map.put("totalPrice", orderInfoByAmount.getTotalPrice());
		}
		map.put("salesman",salesman);
		map.put("projectName",projectName);
		map.put("productNo",productNo);
		map.put("newRemainAmount", newRemainAmount);
		
		int i = orderDao.editOrderNoData(map);
		if(i > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}

	@Override
	public String addBillInfo(HttpServletRequest req) {
		List<BillInfo> billList = new ArrayList<BillInfo>();
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		JSONArray array = JSONArray.fromObject(req.getParameter("billArray"));
		String billNo = IdCreateTools.getId("Bill");
		for(int i=0;i < array.size();i++){
			JSONObject data = array.getJSONObject(i);
			String orderNo = data.getString("orderNo");
			String billingNum = data.getString("billingNum");
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
			String remainInvoicesNum = data.getString("remainInvoicesNum");
			
			OrderInfo order = new OrderInfo();
			order.setOrderNo(orderNo);
			order.setRemainInvoicesNum(Integer.valueOf(remainInvoicesNum));
			orderList.add(order);
			
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
			
			billList.add(billInfo);
		}
		
		int i = orderDao.updateOrderInfoByBill(orderList);
		
		int k = orderDao.addBillInfos(billList);
		if(i > 0 && k > 0){
			return "SUCCESS";
		}else{
			return "FAIL";
		}
	}

	@Override  
    public String uploadExcelFile(MultipartFile file) {  
		String fileName = file.getOriginalFilename();//获取文件名  
        try {  
            if (!ExcelUtil.validateExcel(fileName)) {// 验证文件名是否合格  
                return null;  
            }  
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本  
            if (ExcelUtil.isExcel2007(fileName)) {  
                isExcel2003 = false;  
            }  
            List<OrderInfo> orderList = createExcel(file.getInputStream(), isExcel2003); 
            if(CollectionUtils.isNotEmpty(orderList)){
            	int k = orderDao.addExcelData(orderList);
            	if(k > 0 ){  
                    return "SUCCESS";  
    	        }else{  
    	        	return "FAIL";   
    	        }
            }
        } catch (Exception e) {  
        	LOG.info("导入Excel失败："+e.toString());
        }  
        return "FAIL";  
    }  
	
	public List<OrderInfo> createExcel(InputStream is, boolean isExcel2003) {  
        try{  
            Workbook wb = null;  
            if (isExcel2003) {// 当excel是2003时,创建excel2003  
                wb = new HSSFWorkbook(is);  
            } else {// 当excel是2007时,创建excel2007  
            	wb = new XSSFWorkbook(is);
            }  
            @SuppressWarnings("unused")
			List<OrderInfo> orderList = readExcelValue(wb);// 读取Excel里面客户的信息  
            return orderList;
        } catch (IOException e) {  
        	LOG.info("导入Excel异常");
        }  
        return new ArrayList<OrderInfo>();  
    } 
	
	 private List<OrderInfo> readExcelValue(Workbook wb) {  
        // 得到第一个shell  
        Sheet sheet = wb.getSheetAt(0);  
        // 得到Excel的行数  
        int totalRows = sheet.getPhysicalNumberOfRows();  
        // 得到Excel的列数(前提是有行数)  
        int totalCells = 0;
        if (totalRows > 1 && sheet.getRow(0) != null) {  
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
        }  
        List<OrderInfo> orderList = new ArrayList<OrderInfo>();  
        
        // 循环Excel行数  
        for (int r = 1; r < totalRows; r++) {  
            Row row = sheet.getRow(r);  
            if (row == null || row.getFirstCellNum() == -1){  
                continue;  
            }  
            OrderInfo order = new OrderInfo();  
            // 添加到list  
            try {
            	orderList.add(dataObj(order,row));
			} catch (Exception e) {
				LOG.info("数据导入异常：异常在"+r+"行"+"；"+e.toString());
			}  
        }  
        return orderList;  
    }
	 
	 private  static  OrderInfo  dataObj(OrderInfo obj, Row row) throws Exception {    
	 	Class clazz = obj.getClass();       
        Field[] fields = clazz.getDeclaredFields();    
        Field remainAmount = clazz.getDeclaredField("remainAmount");
        Field remainInvoicesNum = clazz.getDeclaredField("remainInvoicesNum");
        remainAmount.setAccessible(true);
        remainInvoicesNum.setAccessible(true);
        if (fields != null && fields.length > 0) {    
            //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）   
            for(int i =0;i<orderInfoArr.length;i++){
            	for (int j = 0; j < fields.length; j++) {  
            		fields[j].setAccessible(true);
                	if(orderInfoArr[i].equalsIgnoreCase(fields[j].getName())){
                		String a = getVal(row.getCell(i));
                		if(StringUtils.isNotBlank(a)){
                			if(fields[j].getType().getName().equals("java.lang.Double")){
                				fields[j].set(obj,Double.valueOf(a));
                    		}else if(fields[j].getType().getName().equals("java.lang.Integer")){
                    			int k = a.indexOf('.');
                    			String b = k>-1?a.substring(0, k):a;
                    			if(fields[j].getName().equals("customerNo") ||fields[j].getName().equals("productNo")){
                    				fields[j].set(obj,b);
                    			}else{
                    				fields[j].set(obj,Integer.valueOf(b));
                    				if("amount".equals(orderInfoArr[i])){
                    					remainAmount.set(obj,Integer.valueOf(b));
                    					remainInvoicesNum.set(obj,Integer.valueOf(b));
                    				}
                    			}
                    		}else{
                				fields[j].set(obj,a);
                    		}
                    		 
                    		break;
                		}
                	}
                }
        	} 
        } 
        return obj;
    }  
	 
	 public static String firstLetterToUpper(String str) {
		if (str == null || str.trim().equals("")) {
			return "";
		}
		return new StringBuilder(str.substring(0,1).toUpperCase())
			.append(str.substring(1)).toString();
	}
	 
	 public static String getVal(Cell hssfCell) {    
		 if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {    
			 return hssfCell.getStringCellValue();    
		 } else if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ||hssfCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){    
			 return String.valueOf(hssfCell.getNumericCellValue());    
		 } else {
			 return "";
		 }
	 }  
}
