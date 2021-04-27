package com.funny.combo.camel.manager;

import com.funny.combo.camel.consts.Constant;
import com.funny.combo.camel.consts.RouteTypeEnum;
import com.funny.combo.camel.entity.CamelvRoute;
import com.funny.combo.camel.utils.FreeMarkers;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RouteTemplateManager {

    private static Logger logger = LoggerFactory.getLogger(RouteTemplateManager.class);
    private static Configuration cfg = new Configuration();

    static {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("routetemplate").getPath();
            cfg.setDefaultEncoding("UTF-8");
            cfg.setDirectoryForTemplateLoading(new File(path));
        } catch (Exception e) {
            logger.error("获取路由模板失败!!!");
        }
    }

    /**
     * 根据模板生成路由规则
     *
     * @param route
     * @return
     * @throws IOException
     */
    public static String createRule(CamelvRoute route) {
        Map<String, String> model = new HashMap<>();
        model.put("routePreffix", Constant.PREFFIX_ROUTE_ID);
        model.put("routeId", route.getRouteId());
        model.put("routeUri", route.getUri());
        model.put("routeFromUri", Constant.DIRECT + Constant.PREFFIX_ROUTE_ID + route.getRouteId());
        model.put("routeType", route.getRouteType());
        model.put("propertyType", "${property.type}");
        model.put("propertyNextUri", "${property." + Constant.NEXT_URI + "}");
        model.put("propertyPersistSwitch", "false");
        String routeRule = "";
        try {
            Template template = cfg.getTemplate(RouteTypeEnum.ROUTE_TYPE_BEAN.getType() + ".ftl");
            routeRule = FreeMarkers.renderTemplate(template, model);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据路由模板创建路由失败!!!");
        }

        return routeRule;
    }


}
