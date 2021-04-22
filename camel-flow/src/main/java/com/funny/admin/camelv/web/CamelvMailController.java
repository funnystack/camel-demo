package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvMail;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvMailService;
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
@RequestMapping(value = "${adminPath}/camelv/mail")
public class CamelvMailController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICamelvMailService camelvMailService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvMail camelvMail = new CamelvMail();
		if (!StringUtils.isBlank(id)) {
			camelvMail = camelvMailService.get(id);
		}
		model.addAttribute("camelvMail", camelvMail);
		return "modules/camelv/res/mail/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvMail camelvMail) {
		model.addAttribute("camelvMail", camelvMail);
		return "modules/camelv/res/jdbc/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvMail camelvMail) {
		logger.info("保存 " + camelvMail);
		ResponseData rd = camelvMailService.save(camelvMail);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + Global.getAdminPath() + "/camelv/mail/list";
		}
		addMessage(redirectAttributes, "保存失败");
		return formError(model, camelvMail);
	}

	@RequestMapping(value = "delete")
	public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
		logger.info("删除  id = " + id);
		ResponseData rd = camelvMailService.delete(id);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/mail/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model, Page<CamelvMail> page, CamelvMail camelvMail) {
		logger.info("查询  camelvMail " + camelvMail);
		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
		Page<CamelvMail> resultPage = camelvMailService.find(page, camelvMail);
		model.addAttribute("page", resultPage);
		model.addAttribute("camelvMail", camelvMail);
		return "modules/camelv/res/mail/list";
	}
}
