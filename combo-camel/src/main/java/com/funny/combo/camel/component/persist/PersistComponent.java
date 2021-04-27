package com.funny.combo.camel.component.persist;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 持久化组件
 *
 * @author fangli
 */
@Component
public class PersistComponent extends DefaultComponent {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {

        PersistEndpoint persistEndpoint = new PersistEndpoint(uri, this, remaining);
//		persistEndpoint.setJdbcTemplate(jdbcTemplate);
        return persistEndpoint;
    }
}
