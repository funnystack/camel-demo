package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.entity.CamelvNodeEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvNodeService;
import com.funny.combo.camel.consts.Constant;
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
@RequestMapping(value = "/camelv/node")
public class CamelvNodeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ICamelvNodeService camelvNodeService;

    @RequestMapping(value = "delete")
    public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
        logger.info("删除  id = " + id);
        CamelvNodeEntity camelvNode = new CamelvNodeEntity();
        ResponseData rd = null;
        try {
            camelvNodeService.updateSelectiveById(camelvNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rd.getType().equals(Constant.SUCCESS)) {
            addMessage(redirectAttributes, "删除成功");
        } else {
            addMessage(redirectAttributes, "删除失败");
        }
        return "redirect:" + Global.getAdminPath() + "/camelv/node/list";
    }

    @RequestMapping(value = "list")
    public String find(Model model, CamelvNodeEntity camelvNode) {
        logger.info("查询  camelvNode " + camelvNode);
//		page.setPageSize(Integer.parseInt(Global.getConfig("page.pageSize")));
//		Page<CamelvNode> resultPage = camelvNodeService.find(page, camelvNode);
//		model.addAttribute("page", resultPage);
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
    public List<CamelvNodeEntity> getAll(Model model) {
        logger.info("查询  所有节点 ");
        List<CamelvNodeEntity> list = null;
        try {
            list = camelvNodeService.listByCondition(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
