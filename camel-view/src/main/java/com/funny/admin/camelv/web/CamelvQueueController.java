package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.entity.CamelvQueueEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvQueueService;
import com.funny.combo.camel.consts.Constant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/camelv/queue")
public class CamelvQueueController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvQueueService camelvQueueService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvQueueEntity camelvQueue = new CamelvQueueEntity();
		if (!StringUtils.isBlank(id)) {
			camelvQueue = camelvQueueService.findByDataId(id);
		}
		model.addAttribute("camelvQueue", camelvQueue);
		return "modules/camelv/res/queue/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvQueueEntity camelvQueue) {
		model.addAttribute("camelvQueue", camelvQueue);
		return "modules/camelv/res/queue/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvQueueEntity camelvQueue) {
		logger.info("保存 " + camelvQueue);
		ResponseData rd = null;
		try {
			camelvQueueService.insertSelective(camelvQueue);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		CamelvQueueEntity camelvQueueEntity = new CamelvQueueEntity();
		ResponseData rd = null;
		try {
			camelvQueueService.updateSelectiveById(camelvQueueEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/queue/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model,  CamelvQueueEntity camelvQueue) {
		logger.info("查询  camelvQueue " + camelvQueue);
		model.addAttribute("camelvQueue", camelvQueue);
		return "modules/camelv/res/queue/list";
	}
}
