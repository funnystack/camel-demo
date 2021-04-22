package com.funny.admin.camel.manager;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.RouteType;
import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.servlet.FreeMarkers;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class RouteTemplateManager {

	private static Logger logger = LoggerFactory.getLogger(RouteTemplateManager.class);
	private static Configuration cfg = new Configuration();
	static {
		try {
			String path = RouteTemplateManager.class.getResource("").getFile() + File.separator + "template";
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

		Map<String, String> model = Maps.newHashMap();
		model.put("routePreffix", Constant.PREFFIX_ROUTE_ID);
		model.put("routeId", route.getId()+"");
		model.put("routeUri", route.getUri());
		model.put("routeFromUri", Constant.DIRECT + Constant.PREFFIX_ROUTE_FROM_URI + route.getId());
		model.put("routeType", route.getType());
		model.put("propertyType", "${property.type}");
		model.put("propertyNextUri", "${property." + Constant.NEXT_URI + "}");
		model.put("propertyPersistSwitch", "${property.persistSwitch}");

		String routeRule = "";
		String type = route.getType();
		try {
			if (RouteType.ROUTE_TYPE_JETTY.equals(type)) {
				/** jetty路由模板设置 */
				Template template = cfg.getTemplate("jetty.ftl");
				routeRule = FreeMarkers.renderTemplate(template, model);
			} else if (RouteType.ROUTE_TYPE_GROOVY.equals(type)) {
				/** groovy路由模板设置 */
				Template template = cfg.getTemplate("groovy.ftl");
				routeRule = FreeMarkers.renderTemplate(template, model);
			} else if (RouteType.ROUTE_TYPE_HTTP.equals(type)) {
				/** http路由模板设置 */
				Template template = cfg.getTemplate("http.ftl");
				routeRule = FreeMarkers.renderTemplate(template, model);
			} else if (RouteType.ROUTE_TYPE_EXCEPTION.equals(type)) {
				/** exception路由模板设置 */
				Template template = cfg.getTemplate("exception.ftl");
				routeRule = FreeMarkers.renderTemplate(template, model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据路由模板创建路由失败!!!");
		}

		return routeRule;
	}

	public static void main(String[] args) {
		CamelvRoute route = new CamelvRoute();
		route.setId(123456L);
		// route.setType(Constant.ROUTE_TYPE_HTTP);
		// route.setType(Constant.ROUTE_TYPE_JETTY);
		route.setType(RouteType.ROUTE_TYPE_GROOVY);
		route.setUri("test");
		String rule2 = createRule(route);
		System.out.println(rule2);
	}
}
