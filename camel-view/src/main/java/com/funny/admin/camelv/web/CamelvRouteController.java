package com.funny.admin.camelv.web;

import com.funny.admin.camelv.constant.RouteType;
import com.funny.admin.camelv.entity.CamelvGroovyEntity;
import com.funny.admin.camelv.entity.CamelvHttpEntity;
import com.funny.admin.camelv.entity.CamelvRouteEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvGroovyService;
import com.funny.admin.camelv.service.ICamelvHttpService;
import com.funny.admin.camelv.service.ICamelvRouteService;
import com.funny.admin.camelv.util.CamelvUtil;
import com.funny.combo.camel.entity.ComponentOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/camelv/route")
public class CamelvRouteController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ICamelvRouteService routeService;

    @Resource
    private ICamelvGroovyService groovyService;

    @Resource
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
    public ResponseData save(Model model, CamelvRouteEntity route) {
        logger.info("保存:" + route);
        ResponseData rd = null;
        try {
            routeService.insertSelective(route);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public String form(Model model, CamelvRouteEntity route, String id) {
        logger.info("编辑路由:" + route);
        route = routeService.findByDataId(id);
        String routeType = route.getType();
        // 组件参数显示
        List<ComponentOption> optionList = CamelvUtil.parseOption(route.getComponentOption());
        model.addAttribute("optionList", optionList);
        // groovy资源
        List<CamelvGroovyEntity> groovyList = new ArrayList<CamelvGroovyEntity>();
        if (RouteType.ROUTE_TYPE_GROOVY.equals(routeType) || RouteType.ROUTE_TYPE_EXCEPTION.equals(routeType)) {
            try {
                groovyList = groovyService.listByCondition(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // http资源
        List<CamelvHttpEntity> httpList = new ArrayList<CamelvHttpEntity>();
        if (RouteType.ROUTE_TYPE_HTTP.equals(routeType)) {
            try {
                httpList = httpService.listByCondition(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    public ResponseData update(Model model, CamelvRouteEntity route) {
        logger.info("修改:" + route);
        ResponseData rd = null;
        try {
            routeService.updateSelectiveById(route);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public ResponseData move(Model model, CamelvRouteEntity route) {
        logger.info("移动路由:" + route);
        ResponseData rd = null;

        try {
            routeService.updateSelectiveById(route);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        ResponseData rd = null;

        try {
            CamelvRouteEntity route = new CamelvRouteEntity();
            routeService.updateSelectiveById(route);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
