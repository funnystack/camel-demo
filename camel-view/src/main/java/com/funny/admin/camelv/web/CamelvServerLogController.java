package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvServerLog;
import com.funny.admin.camelv.service.CamelvServerLogService;
import com.funny.admin.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "${adminPath}/camelv/log")
public class CamelvServerLogController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private CamelvServerLogService camelvServerLogService;

	/**
	 * 日志查询
	 *
	 * @param model
	 * @param page
	 * @param camelvServerLog
	 * @return
	 */
	@RequestMapping(value = "list")
	public String find(Model model, Page<CamelvServerLog> page, CamelvServerLog camelvServerLog) {
		logger.info("查询日志 " + camelvServerLog);
		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
		Page<CamelvServerLog> resultPage = camelvServerLogService.find(page, camelvServerLog);
		model.addAttribute("page", resultPage);
		model.addAttribute("camelvLog", camelvServerLog);
		return "modules/camelv/log/list";
	}

	/**
	 * 日志详情
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String find(Model model, String id) {
		logger.info("查询日志详情 " + id);
		CamelvServerLog log = camelvServerLogService.get(id);
		model.addAttribute("camelvLog", log);
		return "modules/camelv/log/detail";
	}
}
