package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvCategory;
import com.funny.admin.camelv.service.ICamelvCategoryService;
import com.funny.admin.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "${adminPath}/camelv/category")
public class CamelvCategoryController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvCategoryService camelvCategoryService;

	@RequestMapping(value = "list")
	public String find(Model model, Page<CamelvCategory> page, CamelvCategory camelvFtp) {
		logger.info("查询 类别 " + camelvFtp);
		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
		camelvCategoryService.getClass();
		return "modules/camelv/res/ftp/list";
	}
}
