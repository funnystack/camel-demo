package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.entity.CamelvServerEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvRouteService;
import com.funny.admin.camelv.service.ICamelvServerService;
import com.funny.combo.camel.consts.Constant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.net.URLDecoder;

@Controller
@RequestMapping(value = "/camelv/server")
public class CamelvServerController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ICamelvServerService camelvServerService;
    @Resource
    private ICamelvRouteService routeService;

    /**
     * 路由编辑
     *
     * @param model
     * @param id
     * @param message
     * @return
     */
    @RequestMapping(value = "edit")
    public String save(Model model, String id, String message) {
        logger.info("流程编辑 id = " + id);
        String data = camelvServerService.getData(id);
        CamelvServerEntity server = camelvServerService.findByDataId(id);
        model.addAttribute("data", data);
        model.addAttribute("server", server);
        if (!StringUtils.isBlank(message)) {
            try {
                message = URLDecoder.decode(message, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            addMessage(model, message);
        }
        System.out.println(data);
        return "modules/camelv/camelvServer/index";
    }

    @RequestMapping(value = "form")
    public String form(Model model, String id) {
        logger.info("编辑 id = " + id);
        CamelvServerEntity camelvServer = new CamelvServerEntity();
        if (!StringUtils.isBlank(id)) {
            camelvServer = camelvServerService.findByDataId(id);
        }
        model.addAttribute("camelvServer", camelvServer);
        return "modules/camelv/server/form";
    }

    @RequestMapping(value = "form/error")
    public String formError(Model model, CamelvServerEntity camelvServer) {
        model.addAttribute("camelvServer", camelvServer);
        return "modules/camelv/server/form";
    }

    @RequestMapping(value = "save")
    public String save(Model model, RedirectAttributes redirectAttributes, CamelvServerEntity camelvServer) {
        logger.info("保存 " + camelvServer);
        ResponseData rd = null;
        try {
            camelvServerService.insertSelective(camelvServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rd.getType().equals(Constant.SUCCESS)) {
            addMessage(redirectAttributes, "保存成功");
            return "redirect:" + Global.getAdminPath() + "/camelv/server/list";
        }
        addMessage(redirectAttributes, "保存失败");
        return formError(model, camelvServer);
    }

    @RequestMapping(value = "delete")
    public String delete(Model model, RedirectAttributes redirectAttributes, String id) {
        logger.info("删除  id = " + id);
        CamelvServerEntity camelvServer = new CamelvServerEntity();
        ResponseData rd = null;
        try {
            camelvServerService.updateSelectiveById(camelvServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rd.getType().equals(Constant.SUCCESS)) {
            addMessage(redirectAttributes, "删除成功");
        } else {
            addMessage(redirectAttributes, "删除失败");
        }
        return "redirect:" + Global.getAdminPath() + "/camelv/server/list";
    }

    @RequestMapping(value = "list")
    public String find(Model model, CamelvServerEntity camelvServer) {
        logger.info("查询  camelvServer " + camelvServer);
        // 汉化地址
//		List<CamelvServer> list = resultPage.getList();
//		for (CamelvServer server : list) {
//			if (server.getType().equals(ServerType.HTTP)) {
//				String uri = routeService.getServerUrl(server.getId()+"");
//				server.setUrl(uri);
//			}
//		}
//		model.addAttribute("page", resultPage);
        model.addAttribute("camelvServer", camelvServer);
        return "modules/camelv/server/list";
    }
}
