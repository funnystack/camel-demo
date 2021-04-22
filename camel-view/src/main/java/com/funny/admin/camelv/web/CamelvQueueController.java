package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvQueue;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvQueueService;
import com.funny.admin.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "${adminPath}/camelv/queue")
public class CamelvQueueController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvQueueService camelvQueueService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvQueue camelvQueue = new CamelvQueue();
		if (!StringUtils.isBlank(id)) {
			camelvQueue = camelvQueueService.get(id);
		}
		model.addAttribute("camelvQueue", camelvQueue);
		return "modules/camelv/res/queue/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvQueue camelvQueue) {
		model.addAttribute("camelvQueue", camelvQueue);
		return "modules/camelv/res/queue/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvQueue camelvQueue) {
		logger.info("保存 " + camelvQueue);
		ResponseData rd = camelvQueueService.save(camelvQueue);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + Global.getAdminPath() + "/camelv/queue/list";
		}
		addMessage(redirectAttributes, "保存失败");
		return formError(model, camelvQueue);
	}

	@RequestMapping(value = "delete")
	public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
		logger.info("删除  id = " + id);
		ResponseData rd = camelvQueueService.delete(id);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/queue/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model, Page<CamelvQueue> page, CamelvQueue camelvQueue) {
		logger.info("查询  camelvQueue " + camelvQueue);
		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
		Page<CamelvQueue> resultPage = camelvQueueService.find(page, camelvQueue);
		model.addAttribute("page", resultPage);
		model.addAttribute("camelvQueue", camelvQueue);
		return "modules/camelv/res/queue/list";
	}
}
