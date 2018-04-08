package com.huicheng.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.huicheng.pojo.Account;
import com.huicheng.service.AccountService;


@Controller
@SessionAttributes("loginAccount")
public class LoginCtrl{
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 退出登录
	 * @author 
	 * 
	 */
	@RequestMapping("loginOut.do")
	@ResponseBody
	public Map<String, Object> loginOut( ModelMap modelMap) {
		Map<String, Object> result = new HashMap<>();
		modelMap.put("loginAccount", "");
		result.put("code", 0);
		result.put("msg", "安全退出");
		return result;
}
	
	@RequestMapping("loginOn.do")
	@ResponseBody
	public Map<String, Object> loginOn(ModelMap modelMap,String token,HttpSession sess,HttpServletRequest req) {
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		Account acc = accountService.LoginValidate(account,password);
		
		Map<String, Object> result = new HashMap<>();
		if(acc != null) {
			modelMap.put("loginAccount", acc);
			result.put("code", 1);
			result.put("href", "index.jsp");
		}else{
			result.put("code", 0);
			result.put("msg", "账户名或密码错误");
		}
		return result;
	}
}
