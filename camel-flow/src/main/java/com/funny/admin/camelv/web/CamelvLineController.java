package com.funny.admin.camelv.web;

import com.funny.admin.camelv.entity.CamelvLine;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvLineService;
import com.funny.admin.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 操作路由之间的关系
 * 
 * @author liuchengbiao
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/camelv/line")
public class CamelvLineController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICamelvLineService lineService;

	/**
	 * 添加路由之间的关系
	 * 
	 * @param model
	 * @param line
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public ResponseData save(Model model, CamelvLine line) {
		logger.info("保存line:" + line);
		ResponseData rd = lineService.save(line);
		return rd;
	}

	/**
	 * 删除2个路由之间的关系
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseData delete(Model model, String id) {
		logger.info("删除line:" + id);
		ResponseData rd = lineService.delete(id);
		return rd;
	}

	/**
	 * 改变路由之间的关系
	 * 
	 * @param model
	 * @param id
	 * @param newFrom
	 * @return
	 */
	@RequestMapping(value = "move")
	@ResponseBody
	public ResponseData move(Model model, String id, String newFrom, String newTo) {
		logger.info("移动line:" + id + "," + newFrom + " > " + newTo);
		ResponseData rd = lineService.move(id, newFrom, newTo);
		return rd;
	}

	/**
	 * 修改线的类型
	 * 
	 * @param model
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "setType")
	@ResponseBody
	public ResponseData setType(Model model, String id, String type) {
		logger.info("修改线的类型 id = " + id + " , type = " + type);
		ResponseData rd = lineService.setType(id, type);
		return rd;
	}

	/**
	 * 设置连线的折点
	 * 
	 * @param model
	 * @param id
	 * @param M
	 * @return
	 */
	@RequestMapping(value = "setM")
	@ResponseBody
	public ResponseData setM(Model model, String id, Double M) {
		logger.info("修改线的类型M id = " + id + " , M = " + M);
		ResponseData rd = lineService.setM(id, M);
		return rd;
	}

	/**
	 * 路由执行调价修改
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
		ResponseData rd = lineService.rename(id, name);
		return rd;
	}

}
