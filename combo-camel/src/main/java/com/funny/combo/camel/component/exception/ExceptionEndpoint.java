package com.funny.combo.camel.component.exception;

import com.alibaba.fastjson.JSON;
import com.funny.combo.camel.consts.LogConstant;
import org.apache.camel.Component;
import org.apache.camel.Exchange;
import org.apache.camel.component.ResourceEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理<br/>
 * URI Format格式<br/>
 * exception:void<br/>
 *
 * @author xiaoka
 *
 */
public class ExceptionEndpoint extends ResourceEndpoint {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionEndpoint.class);

	public ExceptionEndpoint(String uri, Component component, String remaining) {
		super(uri, component, remaining);
	}

	@Override
	protected void onExchange(Exchange exchange) throws Exception {
		logger.info("执行异常处理,exchangeId = " + exchange.getExchangeId());
		String serverId = exchange.getProperty("serverId", String.class);
		logger.info("serverId = " + serverId);

		// 获取错误类型
		RouteState routeState = exchange.getProperty("routeState", RouteState.class);
		if (routeState == null) {
			Throwable throwable = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
			String failureRouteID = exchange.getProperty(Exchange.FAILURE_ROUTE_ID, String.class);
			String failureEndpoint = exchange.getProperty(Exchange.FAILURE_ENDPOINT, String.class);
			logger.error("异常路由:" + failureRouteID);
			logger.error("异常路由endpoint:" + failureEndpoint);
			// 未知异常
			routeState = RouteState.UNKNOWN_EXCEPTION;
			if (throwable != null) {
				routeState.setMsg(throwable.toString());
			}
		}
		exchange.setProperty(LogConstant.LOG_SERVER_STATUS, "-1");
		// 拼接异常信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", routeState.getCode());
		map.put("msg", routeState.getMsg());
		map.put("desc", routeState.getDesc());
		String data = JSON.toJSONString(map);
		exchange.setOut(exchange.getIn());
		exchange.getOut().setBody(data, String.class);
	}
}
