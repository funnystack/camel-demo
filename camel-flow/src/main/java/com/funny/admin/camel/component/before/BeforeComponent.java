package com.funny.admin.camel.component.before;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeforeComponent extends DefaultComponent {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {

		BeforeEndpoint beforeEndpoint = new BeforeEndpoint(uri, this, remaining);
		
		return beforeEndpoint;
	}

}
