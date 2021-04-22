package com.funny.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.funny.admin.service.AccountService;
import com.funny.admin.common.utils.DESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author fangli
 * @date 创建时间：2017年8月7日 下午2:23:21
 */

@Controller
public class AuthController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AuthController.class);


	@Autowired
	private AccountService accountService;
	

	@RequestMapping("auto/login")
	public String autoLogin(@RequestParam("token")String token,Model model) {
		

		try {
			logger.info("自动登录密文参数：{}", token);
			
			String userInfo = DESUtil.decrypt(token);
			
			logger.info("自动登录明文参数：{}", userInfo);
			
			String[] userInfos = userInfo.split("|");
			
//			Result<Account> result = accountService.getAccountByCode(userInfos[0]);
//			JsonResult<AdminUserEntity> result = new Result<>();
//			if(result.isSuccess()&&result.getData()!=null){
//				model.addAttribute("username", result.getData().getUsername());
//				model.addAttribute("password", result.getData().getPassword());
//				return "auto_login";
//			}
		} catch (Exception e) {
			logger.error("系统异常：", e.getMessage());
			return "no_account";
		}
		
		
		return "no_account";
	}
	
	@RequestMapping(value = "login")
	public String login(String login_failure, String login_out, String overdue, ModelMap map,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		logger.info("登录");


		if (null != login_failure) {
			
			if (Integer.valueOf(login_failure) == -1) {
				
				model.addAttribute("msg", "账户不存在！");
			}
			
			if (Integer.valueOf(login_failure) == -2) {
				
				model.addAttribute("msg", "账户已被锁定！");
			} 
			
			if (Integer.valueOf(login_failure) == -3) {
				
				model.addAttribute("msg", "账户已被禁用！");
			}
			
			if (Integer.valueOf(login_failure) == -4) {
				
				model.addAttribute("msg", "证书过期！");
			}
			
			if (Integer.valueOf(login_failure) == -5) {
				
				model.addAttribute("msg", "未知原因！");
			}
			
			if (Integer.valueOf(login_failure) <= 10) {
				
				int c = 123;
				
				if (c != 0) {
					
					model.addAttribute("msg",
							"用户名或密码不正确!您还可以登陆" + c + "次，当天" + 123 + "次登陆错误账户将被锁定！");
				} else {
					
					model.addAttribute("msg", "账户已被锁定，请明天登陆或联系管理员！");
				}
			}
		}
		
		if (null != login_out) {
		
			model.addAttribute("msg", "您已成功注销退出系统.");
		}
		
		if (null != overdue) {
			
			model.addAttribute("msg", "登录过期,请重新登录.");
		}
		
		if (null != request.getHeader("Referer")) {
			
			setCookie("referer", request.getHeader("Referer"), response);
		}
		
		return "login";
	}
}
