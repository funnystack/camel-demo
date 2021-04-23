package com.funny.admin.camelv.web;

import com.funny.admin.camelv.entity.CamelvLineEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 操作路由之间的关系
 *
 * @author liuchengbiao
 */
@Controller
@RequestMapping(value = "/camelv/line")
public class CamelvLineController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
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
    public ResponseData save(Model model, CamelvLineEntity line) {
        logger.info("保存line:" + line);
        ResponseData rd = null;
        try {
            lineService.insertSelective(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        CamelvLineEntity camelvLineEntity = new CamelvLineEntity();
        ResponseData rd = null;
        try {
            lineService.updateSelectiveById(camelvLineEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        CamelvLineEntity camelvLineEntity = new CamelvLineEntity();
        ResponseData rd = null;
        try {
            lineService.updateSelectiveById(camelvLineEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        CamelvLineEntity camelvLineEntity = new CamelvLineEntity();
        ResponseData rd = null;
        try {
            lineService.updateSelectiveById(camelvLineEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        CamelvLineEntity camelvLineEntity = new CamelvLineEntity();
        ResponseData rd = null;
        try {
            lineService.updateSelectiveById(camelvLineEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        CamelvLineEntity camelvLineEntity = new CamelvLineEntity();
        ResponseData rd = null;
        try {
            lineService.updateSelectiveById(camelvLineEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

}
