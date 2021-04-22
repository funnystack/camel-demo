package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvFtp;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvFtpService;
import com.funny.admin.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "${adminPath}/camelv/ftp")
public class CamelvFtpController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICamelvFtpService camelvFtpService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvFtp camelvFtp = new CamelvFtp();
		if (!StringUtils.isBlank(id)) {
			camelvFtp = camelvFtpService.get(id);
		}
		model.addAttribute("camelvFtp", camelvFtp);
		return "modules/camelv/res/ftp/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvFtp camelvFtp) {
		model.addAttribute("camelvFtp", camelvFtp);
		return "modules/camelv/res/ftp/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvFtp camelvFtp) {
		logger.info("保存 " + camelvFtp);
		ResponseData rd = camelvFtpService.save(camelvFtp);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + Global.getAdminPath() + "/camelv/ftp/list";
		}
		addMessage(redirectAttributes, "保存失败");
		return formError(model, camelvFtp);
	}

	@RequestMapping(value = "delete")
	public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
		logger.info("删除  id = " + id);
		ResponseData rd = camelvFtpService.delete(id);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/ftp/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model, Page<CamelvFtp> page, CamelvFtp camelvFtp) {
		logger.info("查询  camelvFtp " + camelvFtp);
		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
		Page<CamelvFtp> resultPage = camelvFtpService.find(page, camelvFtp);
		model.addAttribute("page", resultPage);
		model.addAttribute("camelvFtp", camelvFtp);
		return "modules/camelv/res/ftp/list";
	}

}
