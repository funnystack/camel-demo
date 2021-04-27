package com.funny.combo.camel.manager;

import com.funny.combo.camel.consts.Constant;
import com.funny.combo.camel.consts.OperateResult;
import com.funny.combo.camel.context.CamelvContext;
import com.funny.combo.camel.entity.CamelvRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.RoutesDefinition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理路由发布、废弃的类
 *
 * @author fangli
 */
@Component
public class RouteManager {

    public static Logger logger = LoggerFactory.getLogger(RouteManager.class);

    @Autowired(required = false)
    private CamelContext camelContext;


    /**
     * 发布路由规则
     *
     * @param routeRule
     * @param serverId
     * @return
     */
    public OperateResult publish(String routeRule, String serverId) {
        if (StringUtils.isBlank(routeRule)) {
            return OperateResult.fail(Constant.ROUTE_RULE_NULL);
        }
        RoutesDefinition routesDefinition = null;
        // 解析路由规则
        try {
            InputStream is = new ByteArrayInputStream(routeRule.getBytes("UTF-8"));
            routesDefinition = camelContext.loadRoutesDefinition(is);
        } catch (Exception e) {
            e.printStackTrace();
            return OperateResult.fail(Constant.PARSE_XML_ERROR);
        }
        // 发布
        List<String> routes = new ArrayList<String>();
        CamelvContext.addPublishedRoutes(serverId, routes);
        try {
            List<RouteDefinition> routeDefinitions = routesDefinition.getRoutes();
            for (RouteDefinition routeDefinition : routeDefinitions) {
                routes.add(routeDefinition.getId());
                camelContext.addRouteDefinition(routeDefinition);
                // 记录发布成功的id
                logger.debug(routeDefinition.getId() + "发布成功\n");
            }
            return OperateResult.succ();
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚
            abandonRoutes(routes);
            return  OperateResult.fail(Constant.PUBLISH_ERROR);
        }
    }

    /**
     * 添加单个路由
     *
     * @return
     */
    public OperateResult addRouteDefinition(CamelvRoute route) {
        RoutesDefinition routesDefinition = null;
        RouteDefinition definition = camelContext.getRouteDefinition(Constant.PREFFIX_ROUTE_ID + route.getRouteId());
        if (definition != null) {
            logger.info("该路由" + route.getRouteId() + "已经加载");
            return OperateResult.succ();
        }
        try {
            // 生成路由规则
            String routeRule = RouteTemplateManager.createRule(route);
            logger.info("路由规则如下:\n" + routeRule);
            InputStream is = new ByteArrayInputStream(routeRule.getBytes("UTF-8"));
            routesDefinition = camelContext.loadRoutesDefinition(is);
        } catch (Exception e) {
            e.printStackTrace();
            return OperateResult.fail(Constant.PARSE_XML_ERROR);
        }
        // 发布
        List<String> routes = new ArrayList<String>();
        try {
            List<RouteDefinition> routeDefinitions = routesDefinition.getRoutes();
            for (RouteDefinition routeDefinition : routeDefinitions) {
                routes.add(routeDefinition.getId());
                camelContext.addRouteDefinition(routeDefinition);
                // 记录发布成功的id
                logger.info(routeDefinition.getId() + "加载成功\n");
            }
            return OperateResult.succ();
        } catch (Exception e) {
            e.printStackTrace();
            return OperateResult.fail(Constant.PUBLISH_ERROR);
        }
    }


    /**
     * 废弃路由
     *
     * @param routes
     */
    public void abandonRoutes(List<String> routes) {
        if (routes == null) {
            logger.error("无路由需要废弃");
            return;
        }
        for (String routeId : routes) {
            abandonRoute(routeId);
        }
    }

    /**
     * 废弃指定路由
     *
     * @param routeId
     */
    public void abandonRoute(String routeId) {
        try {
            RouteDefinition routeDefinition = camelContext.getRouteDefinition(Constant.PREFFIX_ROUTE_ID + routeId);
            camelContext.removeRouteDefinition(routeDefinition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("废弃路由：" + routeId + "失败");
        }
    }
}
