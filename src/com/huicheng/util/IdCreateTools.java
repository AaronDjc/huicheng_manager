package com.huicheng.util;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;


/**
 * 订单工具�?
 * @author eason
 *
 * 2016�?6�?6日下�?5:31:06
 */
public class IdCreateTools {

	/**
	 * 生成订单流水�?
	 * @return
	 */
	public static String getId(String type) {
		Calendar calendar = Calendar.getInstance();
		if(StringUtils.equals("Delivery", type)){
			return "D-" + (calendar.getTime().getTime());
		}else if(StringUtils.equals("Bill", type)){
			return "B-" + (calendar.getTime().getTime());
		}else if(StringUtils.equals("Payment", type)){
			return "P-" + (calendar.getTime().getTime());
		}else{
			return "FAIL";
		}
		
	}
}
