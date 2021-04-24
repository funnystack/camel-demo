package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.entity.CamelvHostEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvHostService;
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
@RequestMapping(value = "/camelv/host")
public class CamelvHostController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvHostService camelvHostService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvHostEntity camelvHost = new CamelvHostEntity();
		if (!StringUtils.isBlank(id)) {
			camelvHost = camelvHostService.findByDataId(id);
		}
		model.addAttribute("camelvHost", camelvHost);
		return "modules/camelv/res/host/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvHostEntity camelvHost) {
		model.addAttribute("camelvHost", camelvHost);
		return "modules/camelv/res/host/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvHostEntity camelvHost) {
		logger.info("保存 " + camelvHost);
		ResponseData rd = null;
		try {
			camelvHostService.insertSelective(camelvHost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + Global.getAdminPath() + "/camelv/host/list";
		}
		addMessage(redirectAttributes, "保存失败");
		return formError(model, camelvHost);
	}

	@RequestMapping(value = "delete")
	public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
		logger.info("删除  id = " + id);
		CamelvHostEntity camelvHostEntity = new CamelvHostEntity();
		ResponseData rd = null;
		try {
			camelvHostService.updateSelectiveById(camelvHostEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/host/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model,CamelvHostEntity camelvHost) {
		logger.info("查询  camelvHost " + camelvHost);
//		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
//		Page<CamelvHost> resultPage = camelvHostService.find(page, camelvHost);
//		model.addAttribute("page", resultPage);
		model.addAttribute("camelvHost", camelvHost);
		return "modules/camelv/res/host/list";
	}
}
