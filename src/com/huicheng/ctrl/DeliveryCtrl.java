package com.huicheng.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huicheng.service.DeliveryService;

/**
 * 发货控制层
 * @author Administrator
 * 2017年10月21日
 */
@Controller
@RequestMapping("delivery")
public class DeliveryCtrl {

	@Autowired
	private DeliveryService deliveryService;

	@RequestMapping(value="getDeliveryInfo",method=RequestMethod.POST)
	@ResponseBody
	public String getDeliveryInfo(HttpServletRequest req, HttpServletResponse res){
		String result = deliveryService.getDeliveryInfo(req);
		return result;
	}
	
	@RequestMapping(value="addBillingInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addBillingInfo(HttpServletRequest req, HttpServletResponse res){
		String result = deliveryService.addBillingInfo(req);
		return result;
	}
	
	@RequestMapping(value="addPaymentInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addPaymentInfo(HttpServletRequest req, HttpServletResponse res){
		String result = deliveryService.addPaymentInfo(req);
		return result;
	}
	
	@RequestMapping(value="addDeliveryInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addDeliveryInfo(HttpServletRequest req, HttpServletResponse res){
		String result = deliveryService.addDeliveryInfo(req);
		return result;
	}
	
}
