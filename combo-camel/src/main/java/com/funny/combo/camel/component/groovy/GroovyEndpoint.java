package com.funny.combo.camel.component.groovy;

import org.apache.camel.Component;
import org.apache.camel.Exchange;
import org.apache.camel.component.ResourceEndpoint;
import org.apache.camel.spi.UriParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行groovy脚本<br/>
 * URI Format格式<br/>
 * groovy:groovy?routeId=XXX<br/>
 *
 * @author fangli
 *
 */
public class GroovyEndpoint extends ResourceEndpoint {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@UriParam
	private String routeId;

	public GroovyEndpoint(String uri, Component component, String remaining) {
		super(uri, component, remaining);
	}

	@Override
	protected void onExchange(Exchange exchange) throws Exception {
		logger.info("执行groovy,exchangeId = " + exchange.getExchangeId());
		CamelvGroovyUtil.run(exchange, routeId);
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
}
