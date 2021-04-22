package com.funny.admin.camelv.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.xiaoka.camelv.entity.CamelvServerLog;
import com.xiaoka.camelv.service.CamelvServerLogService;

@Controller
@RequestMapping(value = "${adminPath}/camelv/log")
public class CamelvServerLogController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
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
