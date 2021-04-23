package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvServerLog;
import com.funny.admin.camelv.entity.CamelvServerLogEntity;
import com.funny.admin.camelv.service.CamelvServerLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/camelv/log")
public class CamelvServerLogController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CamelvServerLogService camelvServerLogService;

    /**
     * 日志查询
     *
     * @param model
     * @param page
     * @param camelvServerLog
     * @return
     */
    @RequestMapping(value = "list")
    public String find(Model model, Page<CamelvServerLog> page, CamelvServerLog camelvServerLog) {
        logger.info("查询日志 " + camelvServerLog);
        model.addAttribute("camelvLog", camelvServerLog);
        return "modules/camelv/log/list";
    }

    /**
     * 日志详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "detail")
    public String find(Model model, Long id) {
        logger.info("查询日志详情 " + id);
        CamelvServerLogEntity log = null;
        try {
            log = camelvServerLogService.findEntityById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("camelvLog", log);
        return "modules/camelv/log/detail";
    }
}
