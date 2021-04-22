package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvNode;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvNodeService;
import com.funny.admin.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/camelv/node")
public class CamelvNodeController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvNodeService camelvNodeService;

	@RequestMapping(value = "delete")
	public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
		logger.info("删除  id = " + id);
		ResponseData rd = camelvNodeService.delete(id);
		if (rd.getType().equals(Constant.SUCCESS)) {
			addMessage(redirectAttributes, "删除成功");
		} else {
			addMessage(redirectAttributes, "删除失败");
		}
		return "redirect:" + Global.getAdminPath() + "/camelv/node/list";
	}

	@RequestMapping(value = "list")
	public String find(Model model, Page<CamelvNode> page, CamelvNode camelvNode) {
		logger.info("查询  camelvNode " + camelvNode);
		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
		Page<CamelvNode> resultPage = camelvNodeService.find(page, camelvNode);
		model.addAttribute("page", resultPage);
		model.addAttribute("camelvNode", camelvNode);
		return "modules/camelv/node/list";
	}

	/**
	 * 获取节点
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getAll")
	@ResponseBody
	public List<CamelvNode> getAll(Model model) {
		logger.info("查询  所有节点 ");
		List<CamelvNode> list = camelvNodeService.getAll();
		return list;
	}
}
