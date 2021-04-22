package com.funny.admin.camel.component.persist;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 持久化组件
 * 
 * @author xiaoka
 *
 */
@Component
public class PersistComponent extends DefaultComponent {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {

		PersistEndpoint persistEndpoint = new PersistEndpoint(uri, this, remaining);
		persistEndpoint.setJdbcTemplate(jdbcTemplate);
		return persistEndpoint;
	}
}
