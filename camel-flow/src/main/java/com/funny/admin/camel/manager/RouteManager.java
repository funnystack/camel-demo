package com.funny.admin.camel.manager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.funny.admin.camel.context.CamelvContext;
import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.camelv.entity.vo.ResponseData;
import org.apache.camel.CamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.RoutesDefinition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.xiaoka.camel.context.CamelvContext;
import com.xiaoka.camelv.constant.Constant;
import com.xiaoka.camelv.entity.CamelvRoute;
import com.xiaoka.camelv.entity.vo.ResponseData;

/**
 * 管理路由发布、废弃的类
 * 
 * @author xiaoka
 *
 */
public class RouteManager {

	public static Logger logger = LoggerFactory.getLogger(RouteManager.class);

	@Autowired
	private CamelContext camelContext;

	/**
	 * 发布路由规则
	 * 
	 * @param routeRule
	 * @param serverId
	 * @return
	 */
	public ResponseData publish(String routeRule, String serverId) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		if (StringUtils.isBlank(routeRule)) {
			rd.setData("路由规则为空");
			return rd;
		}
		RoutesDefinition routesDefinition = null;
		// 解析路由规则
		try {
			InputStream is = new ByteArrayInputStream(routeRule.getBytes("UTF-8"));
			routesDefinition = camelContext.loadRoutesDefinition(is);
		} catch (Exception e) {
			e.printStackTrace();
			rd.setType(Constant.PARSE_XML_ERROR);
			return rd;
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
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			rd.setType(Constant.PUBLISH_ERROR);
			// 回滚
			abandonRoutes(routes);
		}
		return rd;
	}

	/**
	 * 添加单个路由
	 * 
	 * @param routeRule
	 * @return
	 */
	public ResponseData addRouteDefinition(CamelvRoute route) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		RoutesDefinition routesDefinition = null;

		RouteDefinition definition = camelContext.getRouteDefinition(Constant.PREFFIX_ROUTE_ID + route.getId());
		if (definition != null) {
			logger.info("该路由" + route.getId() + "已经加载");
			rd.setType(Constant.SUCCESS);
			return rd;
		}
		try {
			// 生成路由规则
			String routeRule = RouteTemplateManager.createRule(route);
			// logger.info("路由规则如下:\n"+routeRule);
			InputStream is = new ByteArrayInputStream(routeRule.getBytes("UTF-8"));
			routesDefinition = camelContext.loadRoutesDefinition(is);
		} catch (Exception e) {
			e.printStackTrace();
			rd.setType(Constant.PARSE_XML_ERROR);
			return rd;
		}
		// 发布
		List<String> routes = new ArrayList<String>();
		try {
			List<RouteDefinition> routeDefinitions = routesDefinition.getRoutes();
			for (RouteDefinition routeDefinition : routeDefinitions) {
				routes.add(routeDefinition.getId());
				camelContext.addRouteDefinition(routeDefinition);
				// 记录发布成功的id
				logger.debug(routeDefinition.getId() + "加载成功\n");
			}
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			rd.setType(Constant.PUBLISH_ERROR);
			abandonRoute(routes.get(0));
		}
		return rd;
	}

	/**
	 * 废弃路由
	 * 
	 * @param serverId
	 * @return
	 */
	public ResponseData abandon(String serverId) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			List<String> routes = CamelvContext.getPublishedRoutes(serverId);
			abandonRoutes(routes);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
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
			try {
				RouteDefinition routeDefinition = camelContext.getRouteDefinition(Constant.PREFFIX_ROUTE_ID + routeId);
				camelContext.removeRouteDefinition(routeDefinition);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("废弃路由：" + routeId + "失败");
			}
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
