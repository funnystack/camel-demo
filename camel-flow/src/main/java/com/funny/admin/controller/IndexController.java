package com.funny.admin.controller;

import com.funny.admin.model.bo.MenuTree;
import com.funny.admin.model.entity.AdminMenuEntity;
import com.funny.admin.model.entity.AdminUserEntity;
import com.funny.admin.service.AdminMenuService;
import com.funny.admin.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private AdminMenuService menuService;
	
	@RequestMapping("/")
	public String index(Model model){
		AdminUserEntity account = null;
		if(SecurityContextHolder.getContext().getAuthentication() != null &&
				 SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
			account = (AdminUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}

		if(account == null){
			return "login";
		}

		List<AdminMenuEntity> menuEntityList = null;
		try {
			menuEntityList = menuService.findMenuByAccountId(account.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<MenuTree>  treeList= MenuUtil.buildTree(menuEntityList);
		model.addAttribute("trees", treeList);
		model.addAttribute("account", account);
		return "index";
	}

	@RequestMapping("/dashboard")
	public String dashboard(Model model){

		return "dashboard";
	}
}
