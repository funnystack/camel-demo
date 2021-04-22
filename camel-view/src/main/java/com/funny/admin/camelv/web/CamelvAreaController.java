package com.funny.admin.camelv.web;

import com.funny.admin.camelv.entity.CamelvArea;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvAreaService;
import com.funny.admin.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "${adminPath}/camelv/area")
public class CamelvAreaController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private ICamelvAreaService areaService;

	/**
	 * 保存工作区<br/>
	 *
	 * @param model
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseData save(Model model, CamelvArea area) {
		logger.info("保存工作区:" + area);
		ResponseData rd = areaService.save(area);
		return rd;
	}

	/**
	 * 移动工作区
	 *
	 * @param model
	 * @param id
	 * @param left
	 * @param top
	 * @return
	 */
	@RequestMapping(value = "move")
	@ResponseBody
	public ResponseData move(Model model, String id, Integer left, Integer top) {
		logger.info("移动工作区: id = " + id + " , left =  " + left + " , top = " + top);
		ResponseData rd = areaService.move(id, left, top);
		return rd;
	}

	/**
	 * 删除工作区
	 *
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseData delete(Model model, String id) {
		logger.info("删除路由:" + id);
		ResponseData rd = areaService.delete(id);
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
		ResponseData rd = areaService.rename(id, name);
		return rd;
	}

	/**
	 * 重新设置工作区图形大小
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
		ResponseData rd = areaService.resize(id, width, height);
		return rd;
	}

}
