package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.RouteType;
import com.funny.admin.camelv.entity.CamelvGroovy;
import com.funny.admin.camelv.entity.CamelvHttp;
import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.camelv.entity.vo.ComponentOption;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvGroovyService;
import com.funny.admin.camelv.service.ICamelvHttpService;
import com.funny.admin.camelv.service.ICamelvRouteService;
import com.funny.admin.camelv.util.CamelvUtil;
import com.funny.admin.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/camelv/route")
public class CamelvRouteController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICamelvRouteService routeService;

	@Autowired
	private ICamelvGroovyService groovyService;

	@Autowired
	private ICamelvHttpService httpService;

	/**
	 * 保存路由<br/>
	 * 
	 * @param model
	 * @param route
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseData save(Model model, CamelvRoute route) {
		logger.info("保存:" + route);
		ResponseData rd = routeService.save(route);
		return rd;
	}

	/**
	 * 修改路由<br/>
	 * 
	 * @param model
	 * @param route
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(Model model, CamelvRoute route) {
		logger.info("编辑路由:" + route);
		route = routeService.get(route.getId()+"");
		String routeType = route.getType();
		// 组件参数显示
		List<ComponentOption> optionList = CamelvUtil.parseOption(route.getOption());
		model.addAttribute("optionList", optionList);
		// groovy资源
		List<CamelvGroovy> groovyList = new ArrayList<CamelvGroovy>();
		if (RouteType.ROUTE_TYPE_GROOVY.equals(routeType) || RouteType.ROUTE_TYPE_EXCEPTION.equals(routeType)) {
			groovyList = groovyService.getAll();
		}
		// http资源
		List<CamelvHttp> httpList = new ArrayList<CamelvHttp>();
		if (RouteType.ROUTE_TYPE_HTTP.equals(routeType)) {
			httpList = httpService.getAll();
		}
		model.addAttribute("groovyList", groovyList);
		model.addAttribute("httpList", httpList);
		model.addAttribute("route", route);
		return "modules/camelv/route/" + route.getType() + "Component";
	}

	/**
	 * 更新路由信息
	 * 
	 * @param model
	 * @param route
	 * @return
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public ResponseData update(Model model, CamelvRoute route) {
		logger.info("修改:" + route);
		ResponseData rd = routeService.update(route);
		return rd;
	}

	/**
	 * 移动路由
	 * 
	 * @param model
	 * @param route
	 * @return
	 */
	@RequestMapping(value = "move")
	@ResponseBody
	public ResponseData move(Model model, CamelvRoute route) {
		logger.info("移动路由:" + route);
		ResponseData rd = routeService.move(route);
		return rd;
	}

	/**
	 * 删除路由
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseData delete(Model model, String id) {
		logger.info("删除路由:" + id);
		ResponseData rd = routeService.delete(id);
		return rd;
	}

	/**
	 * 重命名<br/>
	 * 
	 * @param model
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "rename")
	@ResponseBody
	public ResponseData rename(Model model, String id, String name) {
		logger.info("路由重命名:" + id);
		ResponseData rd = routeService.rename(id, name);
		return rd;
	}

	/**
	 * 重新设置路由图形大小
	 * 
	 * @param model
	 * @param id
	 * @param width
	 * @param height
	 * @return
	 */
	@RequestMapping(value = "resize")
	@ResponseBody
	public ResponseData resize(Model model, String id, Integer width, Integer height) {
		logger.info("路由设置图形大小:" + id);
		ResponseData rd = routeService.resize(id, width, height);
		return rd;
	}

}
