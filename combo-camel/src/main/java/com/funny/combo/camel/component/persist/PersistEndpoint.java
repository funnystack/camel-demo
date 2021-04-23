package com.funny.combo.camel.component.persist;

import com.funny.combo.camel.consts.LogConstant;
import org.apache.camel.Component;
import org.apache.camel.Exchange;
import org.apache.camel.component.ResourceEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PersistEndpoint extends ResourceEndpoint {

    private Logger logger = LoggerFactory.getLogger(getClass());


    public PersistEndpoint(String uri, Component component, String remaining) {
        super(uri, component, remaining);
    }

    @Override
    protected void onExchange(Exchange exchange) {
        logger.info("持久化日志");
        String sql = packSql4MySql(exchange);
//		jdbcTemplate.execute(sql);
        // persistToFile(exchange);
    }

    /**
     * 拼接SQL
     *
     * @param exchange
     * @return
     */
    private String packSql4MySql(Exchange exchange) {
        String id = exchange.getProperty(LogConstant.LOG_MESSAGE_ID, String.class);
        String name = exchange.getProperty(LogConstant.LOG_SERVER_NAME, String.class);
        String status = exchange.getProperty(LogConstant.LOG_SERVER_STATUS, String.class);
        String startDate = exchange.getProperty(LogConstant.LOG_SERVER_START_TIME, String.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String requestBody = exchange.getProperty(LogConstant.LOG_SERVER_REQUEST_BODY, String.class);
        String requestHeader = exchange.getProperty(LogConstant.LOG_SERVER_REQUEST_HEADER, String.class);
        Map<String, Object> headers = exchange.getIn().getHeaders();
        StringBuffer responseHeader = new StringBuffer("");
        Set<Entry<String, Object>> entrySet = headers.entrySet();
        for (Entry<String, Object> entry : entrySet) {
            if (!StringUtils.isBlank(entry.getKey())) {
                responseHeader.append(entry.getKey());
                String val = entry.getValue() == null ? "" : entry.getValue().toString();
                responseHeader.append(":" + val + "\n");
            }
        }
        String responseBody = exchange.getIn().getBody(String.class);
        StringBuffer sb = new StringBuffer("");
        sb.append(
                "INSERT INTO camelv_server_log(id,name,status,start_date,end_date,request_header,request_body,response_header,response_body) ");
        sb.append("values(");
        sb.append("\'" + id + "\',");
        sb.append("\'" + name + "\',");
        sb.append(status + ",");
        sb.append("\'" + startDate + "\',");
        sb.append("\'" + sdf.format(new Date()) + "\',");
        sb.append("\'" + requestHeader.toString() + "\',");
        sb.append("\'" + requestBody + "\',");
        sb.append("\'" + responseHeader.toString() + "\',");
        sb.append("\'" + responseBody + "\')");

        return sb.toString();
    }

    /**
     * 持久化到文件系统
     *
     */
    // private void persistToFile(Exchange exchange) {
    // String id = exchange.getProperty(Constant.LOG_MESSAGE_ID, String.class);
    // String requestBody = exchange.getProperty(Constant.LOG_SERVER_REQUEST_BODY,
    // String.class);
    // String requestHeader =
    // exchange.getProperty(Constant.LOG_SERVER_REQUEST_HEADER, String.class);
    // Map<String, Object> headers = exchange.getIn().getHeaders();
    // StringBuffer responseHeader = new StringBuffer("");
    // Set<Entry<String, Object>> entrySet = headers.entrySet();
    // for (Entry<String, Object> entry : entrySet) {
    // if (!StringUtils.isBlank(entry.getKey())) {
    // responseHeader.append(entry.getKey());
    // String val = entry.getValue() == null ? "" : entry.getValue().toString();
    // responseHeader.append(":" + val + "\n");
    // }
    // }
    // String responseBody = exchange.getIn().getBody(String.class);
    // Map<String, String> map = new HashMap<String, String>();
    // map.put(Constant.LOG_MESSAGE_ID, id);
    // map.put(Constant.LOG_SERVER_REQUEST_BODY, requestBody);
    // map.put(Constant.LOG_SERVER_REQUEST_HEADER, requestHeader);
    // map.put(Constant.LOG_SERVER_REQUEST_HEADER, requestHeader);
    // map.put(Constant.LOG_SERVER_RESPONSE_BODY, responseBody);
    // map.put(Constant.LOG_SERVER_RESPONSE_HEADER, responseHeader.toString());
    // PersistFileComponent.persist(id, map.toString());
    // }

}
