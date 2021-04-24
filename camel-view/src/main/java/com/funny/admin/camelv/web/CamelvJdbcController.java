package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvJdbcEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvJdbcService;
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
@RequestMapping(value = "/camelv/jdbc")
public class CamelvJdbcController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ICamelvJdbcService camelvJdbcService;

    @RequestMapping(value = "form")
    public String form(Model model, String id) {
        logger.info("编辑 id = " + id);
        CamelvJdbcEntity camelvJdbc = new CamelvJdbcEntity();
        if (!StringUtils.isBlank(id)) {
            camelvJdbc = camelvJdbcService.findByDataId(id);
        }
        model.addAttribute("camelvJdbc", camelvJdbc);
        return "modules/camelv/res/jdbc/form";
    }

    @RequestMapping(value = "form/error")
    public String formError(Model model, CamelvJdbcEntity camelvJdbc) {
        model.addAttribute("camelvJdbc", camelvJdbc);
        return "modules/camelv/res/jdbc/form";
    }

    @RequestMapping(value = "save")
    public String save(Model model, RedirectAttributes redirectAttributes, CamelvJdbcEntity camelvJdbc) {
        logger.info("保存 " + camelvJdbc);
        ResponseData rd = null;
        try {
            camelvJdbcService.insertSelective(camelvJdbc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rd.getType().equals(Constant.SUCCESS)) {
            addMessage(redirectAttributes, "保存成功");
            return "redirect:" + Global.getAdminPath() + "/camelv/jdbc/list";
        }
        addMessage(redirectAttributes, "保存失败");
        return formError(model, camelvJdbc);
    }

    @RequestMapping(value = "delete")
    public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
        logger.info("删除  id = " + id);
        CamelvJdbcEntity camelvJdbcEntity = new CamelvJdbcEntity();
        ResponseData rd = null;
        try {
            camelvJdbcService.updateSelectiveById(camelvJdbcEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rd.getType().equals(Constant.SUCCESS)) {
            addMessage(redirectAttributes, "删除成功");
        } else {
            addMessage(redirectAttributes, "删除失败");
        }
        return "redirect:" + Global.getAdminPath() + "/camelv/jdbc/list";
    }

    @RequestMapping(value = "list")
    public String find(Model model,CamelvJdbcEntity camelvJdbc) {
        logger.info("查询  camelvJdbc " + camelvJdbc);
//		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
//		Page<CamelvJdbc> resultPage = camelvJdbcService.find(page, camelvJdbc);
//		model.addAttribute("page", resultPage);
        model.addAttribute("camelvJdbc", camelvJdbc);
        return "modules/camelv/res/jdbc/list";
    }
}
