package com.huicheng.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huicheng.service.OrderService;

/**
 * 订单控制层
 * @author Administrator
 * 2017年10月21日
 */
@Controller
@RequestMapping("order")
public class OrderCtrl {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value="getAllOrders",method=RequestMethod.POST)
	@ResponseBody
	public String getAllOrders(HttpServletRequest req, HttpServletResponse res){
		String result = orderService.getAllOrders(req);
		return result;
	}
	
	@RequestMapping(value="addDeliveryInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addDeliveryInfo(HttpServletRequest req, HttpServletResponse res){
		String result = orderService.addDeliveryInfo(req);
		return result;
	}
	
	@RequestMapping(value="addBillInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addBillInfo(HttpServletRequest req, HttpServletResponse res){
		String result = orderService.addBillInfo(req);
		return result;
	}
	/*@RequestMapping(value="queryPayMethod",method=RequestMethod.POST)
	@ResponseBody
	public String queryPayMethod(HttpServletRequest req, HttpServletResponse res){
		String result = orderService.queryPayMethod(req);
		return result;
	}*/
	
	//查询是否有发货信息或开票信息
	@RequestMapping(value="queryDeliveryOrBillInfo",method=RequestMethod.POST)
	@ResponseBody
	public String queryDeliveryOrBillInfo(HttpServletRequest req, HttpServletResponse res){
		String result = orderService.queryDeliveryOrBillInfo(req);
		return result;
	}
	
	@RequestMapping(value="addPayMethodInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addPayMethodInfo(HttpServletRequest req, HttpServletResponse res){
		String result = orderService.addPayMethodInfo(req);
		return result;
	}
	
	@RequestMapping(value="editOrderNoData",method=RequestMethod.POST)
	@ResponseBody
	public String editOrderNoData(HttpServletRequest req, HttpServletResponse res){
		String result = orderService.editOrderNoData(req);
		return result;
	}
}
