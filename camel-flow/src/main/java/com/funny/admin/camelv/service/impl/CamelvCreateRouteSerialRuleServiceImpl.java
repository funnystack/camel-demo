package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.RouteType;
import com.funny.admin.camelv.entity.CamelvLine;
import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvCreateRouteRuleService;
import com.funny.admin.camelv.service.ICamelvLineService;
import com.funny.admin.camelv.service.ICamelvRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CamelvCreateRouteSerialRuleServiceImpl implements ICamelvCreateRouteRuleService {

	@Autowired
	private ICamelvRouteService routeService;
	@Autowired
	private ICamelvLineService lineService;

	@Override
	public ResponseData create(String serverId) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		// 获取所有的路由
		List<CamelvRoute> routes = routeService.getByServerId(serverId);
		// 获取所有的路由关系
		List<CamelvLine> lines = lineService.getByServerId(serverId);
		// 将路由集合转为map集合
		Map<String, CamelvRoute> routeMap = new HashMap<String, CamelvRoute>();
		for (CamelvRoute r : routes) {
			routeMap.put(r.getId()+"", r);
		}
		// 将所有的路由关系转为map,key:from,value:to的集合
		Map<String, String> lineMap = new HashMap<String, String>();

		for (CamelvLine line : lines) {
			lineMap.put(line.getFrom(), line.getTo());
		}
		StringBuffer rule = new StringBuffer("");
		rule.append("\n<routes xmlns=" + "\"http://camel.apache.org/schema/spring\"" + ">");
		// 遍历路由，开始生成规则
		for (CamelvRoute route : routes) {
			String type = route.getType();
			if (RouteType.ROUTE_TYPE_JETTY.equals(type)) {
				rule.append(createJettyRoute(route, lineMap));
			} else if (RouteType.ROUTE_TYPE_GROOVY.equals(type)) {
				rule.append(createGroovyRoute(route, lineMap));
			} else if (RouteType.ROUTE_TYPE_HTTP.equals(type)) {
				rule.append(createHttpRoute(route, lineMap));
			}
		}
		rule.append("\n</routes>");
		rd.setData(rule.toString());
		return rd;
	}

	public String createJettyRoute(CamelvRoute route, Map<String, String> lineMap) {
		StringBuffer sb = new StringBuffer("");
		sb.append("\n\t<route id=\"route-" + route.getId() + "\">");
		sb.append("\n\t\t<description>This is an  jetty  route.</description>");
		String uri = "jetty://http://{{jetty.url}}/" + route.getUri();
		sb.append("\n\t\t<from uri=\"" + uri + "\"/>");
		sb.append("\n\t\t<removeHeaders pattern=\"CamelHttpUri\"/>");
		sb.append(createOnException());

		String nextUri = "direct:camelv" + lineMap.get(route.getId());
		sb.append("\n\t\t<to uri=\"" + nextUri + "\"/>");
		sb.append("\n\t</route>");
		return sb.toString();
	}

	public String createHttpRoute(CamelvRoute route, Map<String, String> lineMap) {
		StringBuffer sb = new StringBuffer("");
		sb.append("\n\t<route id=\"route-" + route.getId() + "\">");
		sb.append("\n\t\t<description>This is an  http  route.</description>");
		String uri = "direct:camelv" + route.getId();
		sb.append("\n\t\t<from uri=\"" + uri + "\"/>");
		sb.append(createOnException());
		sb.append("\n\t\t<to uri=\"before:http?routeId=" + route.getId() + "\" />");
		// 动态指定地址
		sb.append("\n\t\t<routingSlip ignoreInvalidEndpoints=\"true\">");
		sb.append("\n\t\t\t<simple>${property.nextUrl}</simple>");
		sb.append("\n\t\t</routingSlip>");
		if (lineMap.get(route.getId()) != null) {
			String nextUri = "direct:camelv" + lineMap.get(route.getId());
			sb.append("\n\t\t<to uri=\"" + nextUri + "\"/>");
		}
		sb.append("\n\t</route>");
		return sb.toString();
	}

	public String createGroovyRoute(CamelvRoute route, Map<String, String> lineMap) {
		StringBuffer sb = new StringBuffer("");
		sb.append("\n\t<route id=\"route-" + route.getId() + "\">");
		sb.append("\n\t\t<description>This is an  groovy  route.</description>");
		String uri = "direct:camelv" + route.getId();
		sb.append("\n\t\t<from uri=\"" + uri + "\"/>");
		sb.append(createOnException());
		// 指定groovy
		uri = "groovy:vm?routeId=" + route.getId();
		sb.append("\n\t\t<to uri=\"" + uri + "\"/>");
		if (lineMap.get(route.getId()) != null) {
			String nextUri = "direct:camelv" + lineMap.get(route.getId());
			sb.append("\n\t\t<to uri=\"" + nextUri + "\"/>");
		}
		sb.append("\n\t</route>");
		return sb.toString();
	}

	public String createNextUri(CamelvRoute route, Map<String, List<String>> lineMap) {
		StringBuffer sb = new StringBuffer("");
		// 路由后面是否具有其他路由
		List<String> list = lineMap.get(route.getId());
		if (list == null || list.size() == 0) {
			return sb.toString();
		}
		// 串行执行
		if (list.size() == 1) {
			String nextUri = "direct:camelv" + list.get(0);
			sb.append("\n\t\t<to uri=\"" + nextUri + "\"/>");
			return sb.toString();
		}
		// 并行执行

		return sb.toString();
	}

	/**
	 * 设置异常
	 * 
	 * @return
	 */
	public String createOnException() {
		StringBuffer sb = new StringBuffer("");
		sb.append("\n\t\t<onException>");
		sb.append("\n\t\t\t<exception>java.lang.Throwable</exception>");
		sb.append("\n\t\t\t<handled><constant>true</constant></handled>");
		sb.append("\n\t\t\t<doTry>");
		sb.append("\n\t\t\t\t<bean ref=\"exceptionHandle\" method=\"handle\" />");
		sb.append("\n\t\t\t</doTry>");
		sb.append("\n\t\t</onException>");
		return sb.toString();
	}

}
