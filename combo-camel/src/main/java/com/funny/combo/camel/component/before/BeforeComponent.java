package com.funny.combo.camel.component.before;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BeforeComponent extends DefaultComponent {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {

		BeforeEndpoint beforeEndpoint = new BeforeEndpoint(uri, this, remaining);

		return beforeEndpoint;
	}

}
