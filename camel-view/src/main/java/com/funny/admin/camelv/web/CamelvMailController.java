package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.entity.CamelvMailEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvMailService;
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
@RequestMapping(value = "/camelv/mail")
public class CamelvMailController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvMailService camelvMailService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvMailEntity camelvMail = new CamelvMailEntity();
		if (!StringUtils.isBlank(id)) {
			camelvMail = camelvMailService.findByDataId(id);
		}
		model.addAttribute("camelvMail", camelvMail);
		return "modules/camelv/res/mail/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvMailEntity camelvMail) {
		model.addAttribute("camelvMail", camelvMail);
		return "modules/camelv/res/jdbc/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvMailEntity camelvMail) {
		logger.info("保存 " + camelvMail);
		ResponseData rd = null;
		try {
			camelvMailService.insertSelective(camelvMail);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		CamelvMailEntity camelvMailEntity = new CamelvMailEntity();
		ResponseData rd = null;
		try {
			camelvMailService.updateSelectiveById(camelvMailEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/mail/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model, CamelvMailEntity camelvMail) {
		logger.info("查询  camelvMail " + camelvMail);
//		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
//		Page<CamelvMail> resultPage = camelvMailService.find(page, camelvMail);
//		model.addAttribute("page", resultPage);
		model.addAttribute("camelvMail", camelvMail);
		return "modules/camelv/res/mail/list";
	}
}
