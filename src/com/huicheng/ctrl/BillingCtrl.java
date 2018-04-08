package com.huicheng.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huicheng.service.BillingService;

/**
 * 开票控制层
 * @author Administrator
 * 2017年10月21日
 */
@Controller
@RequestMapping("billing")
public class BillingCtrl {

	@Autowired
	private BillingService billingService;

	@RequestMapping(value="getBillingInfo",method=RequestMethod.POST)
	@ResponseBody
	public String getBillingInfo(HttpServletRequest req, HttpServletResponse res){
		String result = billingService.getBillingInfo(req);
		return result;
	}
	
	@RequestMapping(value="addBillingInfo",method=RequestMethod.POST)
	@ResponseBody
	public String addBillingInfo(HttpServletRequest req, HttpServletResponse res){
		String result = billingService.addBillingInfo(req);
		return result;
	}
	
}
