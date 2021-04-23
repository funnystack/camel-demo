package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvGroovy;
import com.funny.admin.camelv.entity.CamelvGroovyEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvGroovyService;
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
@RequestMapping(value = "/camelv/groovy")
public class CamelvGroovyController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvGroovyService camelvGroovyService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvGroovyEntity camelvGroovy = new CamelvGroovy();
		if (!StringUtils.isBlank(id)) {
			camelvGroovy = camelvGroovyService.findByDataId(id);
		}
		model.addAttribute("camelvGroovy", camelvGroovy);
		return "modules/camelv/res/groovy/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvGroovyEntity camelvGroovy) {
		model.addAttribute("camelvGroovy", camelvGroovy);
		return "modules/camelv/res/groovy/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvGroovyEntity camelvGroovy) {
		logger.info("保存 " + camelvGroovy);
		// 转义
		if (!StringUtils.isBlank(camelvGroovy.getScript())) {
//			camelvGroovy.setScript(StringEscapeUtils.unescapeHtml(camelvGroovy.getScript()));
		}
		ResponseData rd = null;
		try {
			camelvGroovyService.insertSelective(camelvGroovy);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		ResponseData rd = null;
		CamelvGroovyEntity camelvGroovy = new CamelvGroovy();
		try {
			camelvGroovyService.updateSelectiveById(camelvGroovy);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
//		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
//		Page<CamelvGroovy> resultPage = camelvGroovyService.find(page, camelvGroovy);
//		model.addAttribute("page", resultPage);
		model.addAttribute("camelvGroovy", camelvGroovy);
		return "modules/camelv/res/groovy/list";
	}

	public static void main(String[] args) {
		String msg = "def msg = &quot;test&quot;;";
//		System.out.println(StringEscapeUtils.unescapeHtml(msg));
	}

}
