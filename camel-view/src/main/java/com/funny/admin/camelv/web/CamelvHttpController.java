package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvHttpEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvHttpService;
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
@RequestMapping(value = "/camelv/http")
public class CamelvHttpController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvHttpService camelvHttpService;

	@RequestMapping(value = "form")
	public String form(Model model, String id) {
		logger.info("编辑 id = " + id);
		CamelvHttpEntity camelvHttp = new CamelvHttpEntity();
		if (!StringUtils.isBlank(id)) {
			camelvHttp = camelvHttpService.findByDataId(id);
		}
		model.addAttribute("camelvHttp", camelvHttp);
		return "modules/camelv/res/http/form";
	}

	@RequestMapping(value = "form/error")
	public String formError(Model model, CamelvHttpEntity camelvHttp) {
		model.addAttribute("camelvHttp", camelvHttp);
		return "modules/camelv/res/http/form";
	}

	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes, CamelvHttpEntity camelvHttp) {
		logger.info("保存 " + camelvHttp);
		ResponseData rd = null;
		try {
			camelvHttpService.insertSelective(camelvHttp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + Global.getAdminPath() + "/camelv/http/list";
		}
		addMessage(redirectAttributes, "保存失败");
		return formError(model, camelvHttp);
	}

	@RequestMapping(value = "delete")
	public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
		logger.info("删除  id = " + id);
		CamelvHttpEntity camelvHttpEntity  = new CamelvHttpEntity();
		ResponseData rd = null;
		try {
			camelvHttpService.updateSelectiveById(camelvHttpEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/http/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model, CamelvHttpEntity camelvHttp) {
		logger.info("查询  camelvHttp " + camelvHttp);
//		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
//		Page<CamelvHttp> resultPage = camelvHttpService.find(page, camelvHttp);
//		model.addAttribute("page", resultPage);
		model.addAttribute("camelvHttp", camelvHttp);
		return "modules/camelv/res/http/list";
	}
}
