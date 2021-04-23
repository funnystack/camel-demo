package com.funny.admin.camelv.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/camelv/api")
public class CamelvApiController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "index")
	public String index(Model model) {
		logger.info("帮助API");
		return "modules/camelv/api/index";
	}

	@RequestMapping(value = "addComponent")
	public String addComponent(Model model) {
		logger.info("添加组件");
		return "modules/camelv/api/addComponent";
	}

}
