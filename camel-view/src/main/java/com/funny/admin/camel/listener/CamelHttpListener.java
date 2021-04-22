package com.funny.admin.camel.listener;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funny.admin.camel.context.CamelvContext;
import com.funny.admin.camel.manager.RouteManager;
import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.constant.RouteType;
import com.funny.admin.camelv.entity.*;
import com.funny.admin.camelv.entity.vo.ComponentOption;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.util.CamelvUtil;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class CamelHttpListener {

	private static final Logger logger = LoggerFactory.getLogger(CamelHttpListener.class);

	@Resource
	private RouteManager routeManager;

	/**
	 * 远程路由规则发布
	 *
	 * @param exchange
	 */
	public void publish(Exchange exchange) {
		logger.info("远程发布路由...");
		String serverId = exchange.getIn().getHeader("serverId", String.class);
		String rule = exchange.getIn().getBody(String.class);
		ResponseData rd = routeManager.publish(rule, serverId);
		exchange.setOut(exchange.getIn());
		exchange.getOut().setBody(rd.getType(), String.class);
		logger.info("发布结果:" + rd.getType());
	}

	/**
	 * 远程废弃路由
	 *
	 * @param exchange
	 */
	public void abandon(Exchange exchange) {
		logger.info("远程废弃路由...");
		String serverId = exchange.getIn().getHeader("serverId", String.class);
		ResponseData rd = routeManager.abandon(serverId);
		exchange.setOut(exchange.getIn());
		exchange.getOut().setBody(rd.getType(), String.class);
		logger.info("废弃结果:" + rd.getType());
	}

	/**
	 * 远程同步缓存数据<br/>
	 * 日后可替换为zk<br/>
	 * 可使用异步同步<br/>
	 *
	 * @param exchange
	 */
	public void putData(Exchange exchange) {
		logger.info("远程数据推送...");
		String type = exchange.getIn().getHeader("type", String.class);
		logger.info("推送数据类型：" + type);
		String data = exchange.getIn().getBody(String.class);
		logger.info("推送数据:===\n" + data + "\n===");
		// 资源推送刷新缓存
		if (ResourceType.RES_TYPE_GROOVY.equals(type)) {
			CamelvGroovy camelvGroovy = JSON.parseObject(data, CamelvGroovy.class);
			CamelvContext.addCamelvGroovy(camelvGroovy);
			CamelvContext.deleteGroovyClass(camelvGroovy.getId()+"");
			logger.info("camelvGroovy:" + camelvGroovy);
		} else if (ResourceType.RES_TYPE_FTP.equals(type)) {
			CamelvFtp camelvFtp = JSON.parseObject(data, CamelvFtp.class);
			CamelvContext.addCamelvFtp(camelvFtp);
			logger.info("camelvFtp:" + camelvFtp);
		} else if (ResourceType.RES_TYPE_HOST.equals(type)) {
			CamelvHost camelvHost = JSON.parseObject(data, CamelvHost.class);
			CamelvContext.addCamelvHost(camelvHost);
			logger.info("camelvHost:" + camelvHost);
		} else if (ResourceType.RES_TYPE_HTTP.equals(type)) {
			CamelvHttp camelvHttp = JSON.parseObject(data, CamelvHttp.class);
			CamelvContext.addCamelvHttp(camelvHttp);
			logger.info("camelvHttp:" + camelvHttp);
		} else if (ResourceType.RES_TYPE_JDBC.equals(type)) {
			CamelvJdbc camelvJdbc = JSON.parseObject(data, CamelvJdbc.class);
			CamelvContext.addCamelvJdbc(camelvJdbc);
			logger.info("camelvJdbc:" + camelvJdbc);
		} else if (ResourceType.RES_TYPE_MAIL.equals(type)) {
			CamelvMail camelvMail = JSON.parseObject(data, CamelvMail.class);
			CamelvContext.addCamelvMail(camelvMail);
			logger.info("camelvMail:" + camelvMail);
		} else if (ResourceType.RES_TYPE_QUEUE.equals(type)) {
			CamelvQueue camelvQueue = JSON.parseObject(data, CamelvQueue.class);
			CamelvContext.addCamelvQueue(camelvQueue);
			logger.info("camelvQueue:" + camelvQueue);
		} else if (Constant.TYPE_SERVER.equals(type)) {
			CamelvServer camelvServer = JSON.parseObject(data, CamelvServer.class);
			CamelvContext.addCamelvServer(camelvServer);
			logger.info("camelvServer:" + camelvServer);
		} else if (ResourceType.RES_TYPE_ROUTE.equals(type)) {
			CamelvRoute camelvRoute = JSON.parseObject(data, CamelvRoute.class);
			// 是否是异常路由
			if (RouteType.ROUTE_TYPE_EXCEPTION.equals(camelvRoute.getType())) {
				CamelvContext.addCamelvRoute4Exception(camelvRoute);
			} else {
				// 非异常路由
				CamelvContext.addCamelvRoute4Route(camelvRoute);
				// 发布路由，如果是jetty且监听地址不为空那么就添加
				if (RouteType.ROUTE_TYPE_JETTY.equals(camelvRoute.getType())
						&& StringUtils.isBlank(camelvRoute.getUri())) {
					logger.info("该服务为添加入口地址，未加载进camel中.");
				} else {
					routeManager.addRouteDefinition(camelvRoute);
				}
			}
			/** 添加参数 */
			List<ComponentOption> optionList = CamelvUtil.parseOption(camelvRoute.getOption());
			CamelvContext.getCamelvRoute(camelvRoute.getId()+"").setOptionList(optionList);
			logger.info("camelvRoute:" + camelvRoute);
		} else if (ResourceType.RES_TYPE_LINE.equals(type)) {
			ObjectMapper objectMapper = new ObjectMapper();
			Object object;
			try {
				object = objectMapper.readValue(data, new TypeReference<List<CamelvRoute>>() {
				});
				@SuppressWarnings("unchecked")
				List<CamelvRoute> routeList = (List<CamelvRoute>) object;
				for (CamelvRoute camelvRoute : routeList) {
					CamelvContext.addCamelvRoute4Line(camelvRoute);
					/** 添加参数 */
					List<ComponentOption> optionList = CamelvUtil.parseOption(camelvRoute.getOption());
					CamelvContext.getCamelvRoute(camelvRoute.getId()+"").setOptionList(optionList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		exchange.getOut().setBody(Constant.SUCCESS, String.class);
	}

	/**
	 * 远程同步数据删除<br/>
	 * 日后可替换为zk监听目录删除<br/>
	 *
	 * @param exchange
	 */
	public void deleteData(Exchange exchange) {
		logger.info("远程数据删除...");
		String type = exchange.getIn().getHeader("type", String.class);
		logger.info("删除数据类型：" + type);
		String id = exchange.getIn().getHeader("id", String.class);
		logger.info("删除数据id=" + id);
		// 从缓存中删除数据
		if (ResourceType.RES_TYPE_GROOVY.equals(type)) {
			CamelvContext.deleteCamelvGroovy(id);
		} else if (ResourceType.RES_TYPE_FTP.equals(type)) {
			CamelvContext.deleteCamelvFtp(id);
		} else if (ResourceType.RES_TYPE_HOST.equals(type)) {
			CamelvContext.deleteCamelvHost(id);
		} else if (ResourceType.RES_TYPE_HTTP.equals(type)) {
			CamelvContext.deleteCamelvHttp(id);
		} else if (ResourceType.RES_TYPE_JDBC.equals(type)) {
			CamelvContext.deleteCamelvJdbc(id);
		} else if (ResourceType.RES_TYPE_MAIL.equals(type)) {
			CamelvContext.deleteCamelvMail(id);
		} else if (ResourceType.RES_TYPE_QUEUE.equals(type)) {
			CamelvContext.deleteCamelvQueue(id);
		} else if (Constant.TYPE_SERVER.equals(type)) {
			CamelvContext.deleteCamelvServer(id);
			List<CamelvRoute> routeList = CamelvContext.getCamelvRouteByServerId(id);
			for (CamelvRoute route : routeList) {
				routeManager.abandonRoute(route.getId()+"");
				CamelvContext.deleteCamelvRoute(route.getId()+"");
			}
			logger.warn("服务 id = " + id + "，已经删除.");
		} else if (ResourceType.RES_TYPE_ROUTE.equals(type)) {
			// 是否删除的是异常
			CamelvRoute route = CamelvContext.getCamelvRoute(id);
			if (route != null && RouteType.ROUTE_TYPE_EXCEPTION.equals(route.getType())) {
				CamelvServer server = CamelvContext.getCamelvServer(route.getServerId());
				if (server != null) {
					server.setExceptionId(null);
				}
			}
			CamelvContext.deleteCamelvRoute(id);
			// 废弃该路由
			routeManager.abandonRoute(id);
			logger.warn("删除了一个路由 id = " + id + "，可能会出现调不通情况.");
		}
		exchange.getOut().setBody(Constant.SUCCESS, String.class);
	}

}
