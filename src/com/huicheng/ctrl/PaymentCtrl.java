package com.huicheng.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huicheng.service.PaymentService;

/**
 * 开票控制层
 * @author Administrator
 * 2017年10月21日
 */
@Controller
@RequestMapping("payment")
public class PaymentCtrl {

	@Autowired
	private PaymentService paymentService;

	@RequestMapping(value="getPaymentInfo",method=RequestMethod.POST)
	@ResponseBody
	public String getPaymentInfo(HttpServletRequest req, HttpServletResponse res){
		String result = paymentService.getPaymentInfo(req);
		return result;
	}
	
	@RequestMapping(value="addPayMethodInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addPayMethodInfo(HttpServletRequest req, HttpServletResponse res){
		String result = paymentService.addPayMethodInfo(req);
		return result;
	}
	
	@RequestMapping(value="addPaymentInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addPaymentInfo(HttpServletRequest req, HttpServletResponse res){
		String result = paymentService.addPaymentInfo(req);
		return result;
	}
}
