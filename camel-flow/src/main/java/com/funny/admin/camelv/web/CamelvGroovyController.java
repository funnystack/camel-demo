package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvGroovy;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvGroovyService;
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
@RequestMapping(value = "${adminPath}/camelv/groovy")
public class CamelvGroovyController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICamelvGroovyService camelvGroovyService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvGroovy camelvGroovy = new CamelvGroovy();
		if (!StringUtils.isBlank(id)) {
			camelvGroovy = camelvGroovyService.get(id);
		}
		model.addAttribute("camelvGroovy", camelvGroovy);
		return "modules/camelv/res/groovy/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvGroovy camelvGroovy) {
		model.addAttribute("camelvGroovy", camelvGroovy);
		return "modules/camelv/res/groovy/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvGroovy camelvGroovy) {
		logger.info("保存 " + camelvGroovy);
		// 转义
		if (!StringUtils.isBlank(camelvGroovy.getScript())) {
//			camelvGroovy.setScript(StringEscapeUtils.unescapeHtml(camelvGroovy.getScript()));
		}
		ResponseData rd = camelvGroovyService.save(camelvGroovy);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + Global.getAdminPath() + "/camelv/groovy/list";
		}
		addMessage(redirectAttributes, "保存失败");
		return formError(model, camelvGroovy);
	}

	@RequestMapping(value = "delete")
	public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
		logger.info("删除  id = " + id);
		ResponseData rd = camelvGroovyService.delete(id);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/groovy/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model, Page<CamelvGroovy> page, CamelvGroovy camelvGroovy) {
		logger.info("查询  camelvGroovy " + camelvGroovy);
		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
		Page<CamelvGroovy> resultPage = camelvGroovyService.find(page, camelvGroovy);
		model.addAttribute("page", resultPage);
		model.addAttribute("camelvGroovy", camelvGroovy);
		return "modules/camelv/res/groovy/list";
	}

	public static void main(String[] args) {
		String msg = "def msg = &quot;test&quot;;";
//		System.out.println(StringEscapeUtils.unescapeHtml(msg));
	}

}
