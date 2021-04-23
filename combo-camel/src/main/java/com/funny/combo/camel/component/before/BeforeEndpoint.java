package com.funny.combo.camel.component.before;

import com.funny.combo.camel.component.exception.RouteState;
import com.funny.combo.camel.consts.Constant;
import com.funny.combo.camel.consts.LogConstant;
import com.funny.combo.camel.consts.RouteType;
import com.funny.combo.camel.context.CamelvContext;
import com.funny.combo.camel.entity.CamelvRoute;
import com.funny.combo.camel.entity.ComponentOption;
import org.apache.camel.Component;
import org.apache.camel.Exchange;
import org.apache.camel.component.ResourceEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 执行路由的前置脚本，设置当前路由需要执行的uri<br/>
 * 处理每个路由之前需要做的工作<br/>
 * URI Format格式<br/>
 * before:routeType?routeId=XXX<br/>
 *
 * @author xiaoka
 */
public class BeforeEndpoint extends ResourceEndpoint {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @UriParam
    private String routeId;

    public BeforeEndpoint(String uri, Component component, String remaining) {
        super(uri, component, remaining);
    }

    @Override
    protected void onExchange(Exchange exchange) throws Exception {
        logger.info("执行 before, type = " + getResourceUri() + " , exchangeId = " + exchange.getExchangeId());
        String type = getResourceUri();
        CamelvRoute route = CamelvContext.getCamelvRoute(routeId);
        /** 路由被删除了 */
        if (route == null) {
            exchange.setProperty(Constant.ROUTE_STATE, RouteState.ROUTE_DELETED);
            throw new Exception("该路由资源被删除了,该路由应该从camel中删除的,routeId=" + routeId);
        }
        /** 权限验证 */
        permissionValidation(exchange, route);
        /**
         * 思路:<br/>
         * 根据不同路由类型，动态拼接该路由需要执行的uri
         */
        if (RouteType.ROUTE_TYPE_HTTP.equals(type)) {
            /**
             * 执行http路由，动态设置需要执行的地址<br/>
             */
            beforeRunHttp(exchange, route);
        } else if (RouteType.ROUTE_TYPE_GROOVY.equals(type)) {
            beforeRunGroovy(exchange, route);
        }
        /** 设置预处理参数 */
        setBeforeOption(exchange, route);

        exchange.setOut(exchange.getIn());
    }

    /**
     * 权限验证
     *
     * @param exchange
     * @return
     * @throws Exception
     */
    private void permissionValidation(Exchange exchange, CamelvRoute route) throws Exception {
        try {
            String permissionValidationSwitch = "camelv.permission.validation.switch";
            if (!"true".equals(permissionValidationSwitch)) {
                /** 不做权限验证 */
                return;
            }
            String permissionHeader = "camelv.permission.header.name";
            String authCodeHeader = exchange.getIn().getHeader(permissionHeader, String.class);
            if (authCodeHeader == null) {
                exchange.setProperty(Constant.ROUTE_STATE, RouteState.PERMISSION_ERROR);
                throw new Exception("未携带授权码!");
            }
//            CamelvServer server = CamelvContext.getCamelvServer(route.getServerId());
//            String cacheAuthCode = server.getAuthCode();
//            if (!authCodeHeader.equals(cacheAuthCode)) {
//                exchange.setProperty(Constant.ROUTE_STATE, RouteState.PERMISSION_ERROR);
//                throw new Exception("授权码不匹配!");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.setProperty(Constant.ROUTE_STATE, RouteState.PERMISSION_ERROR);
            throw new Exception("权限验证失败!");
        }
    }

    /**
     * 设置参数
     *
     * @param exchange
     * @param route
     */
    private void setBeforeOption(Exchange exchange, CamelvRoute route) {
        /** 根据route.id获取关联的参数，并设置到exchange中 */
        Map<String, Object> headers = exchange.getIn().getHeaders();
        List<ComponentOption> optionList = route.getOptionList();
        if (optionList != null) {
            for (ComponentOption option : optionList) {
                Object old = headers.put(option.getName(), option.getValue());
                if (old != null) {
                    logger.warn("警告:" + option.getName() + "原来的值" + old + "被替换为" + option.getValue());
                }
            }
        }
    }

    /**
     * 开始执行jetty路由
     *
     * @param exchange
     * @param route
     * @throws Exception
     */
    private void startJetty(Exchange exchange, CamelvRoute route) throws Exception {
        // 是否开启持久化
        String persistSwitch = "camelv.persist.switch";
        String oldBody = exchange.getIn().getBody(String.class);
        if ("true".equals(persistSwitch)) {
            /** 开启持久化 */
            exchange.setProperty("serverId", "serverId");
            logger.info("入口报文:\n" + oldBody);
            // 记录调用信息
            SimpleDateFormat sdf = new SimpleDateFormat(LogConstant.LOG_TIME_PATTERN);
            String name = "serverName";
            String messageId = UUID.randomUUID().toString().replaceAll("-", "");
            exchange.setProperty(LogConstant.LOG_MESSAGE_ID, messageId);
            exchange.setProperty(LogConstant.LOG_SERVER_START_TIME, sdf.format(new Date()));
            exchange.setProperty(LogConstant.LOG_SERVER_STATUS, "0");
            exchange.setProperty(LogConstant.LOG_SERVER_NAME, name);
            Map<String, Object> headers = exchange.getIn().getHeaders();
            StringBuffer hd = new StringBuffer("");
            Set<Entry<String, Object>> entrySet = headers.entrySet();
            for (Entry<String, Object> entry : entrySet) {
                if (!StringUtils.isBlank(entry.getKey())) {
                    hd.append(entry.getKey());
                    String val = entry.getValue() == null ? "" : entry.getValue().toString();
                    hd.append(":" + val + "\n");
                }
            }
            exchange.setProperty(LogConstant.LOG_SERVER_REQUEST_BODY, oldBody);
            exchange.setProperty(LogConstant.LOG_SERVER_REQUEST_HEADER, hd.toString());
            exchange.setProperty(Constant.PERSIST_SWITCH, persistSwitch);
        }
        exchange.getIn().setBody(oldBody, String.class);
    }

    /**
     * 动态配置http信息
     *
     * @param exchange
     * @param route
     * @throws Exception
     */
    private void beforeRunHttp(Exchange exchange, CamelvRoute route) throws Exception {
        // 去除不需要的头
        exchange.getIn().removeHeader(Exchange.HTTP_URI);
//        if (StringUtils.isBlank(route.getRelatedResourceId())) {
//            exchange.setProperty(Constant.ROUTE_STATE, RouteState.RESOURCE_NOT_CONFIG);
//            throw new Exception("未配置http资源");
//        }
//        // 获取关联的http信息
//        CamelvHttp camelvHttp = CamelvContext.getCamelvHttp(route.getRelatedResourceId());
//        if (camelvHttp == null) {
//            exchange.setProperty(Constant.ROUTE_STATE, RouteState.RESOURCE_NOT_FOUND);
//            throw new Exception("路由关联的http资源找不到了");
//        }
        // 资源找到了，那么就需要执行
        exchange.setProperty(Constant.NEXT_URI, "url");
        logger.info("下一个路由地址:" + "url");
    }

    /**
     * 执行groovy基本的前置
     *
     * @param exchange
     * @param route
     * @throws Exception
     */
    private void beforeRunGroovy(Exchange exchange, CamelvRoute route) throws Exception {
        // TODO 这里填写之前groovy基本的前置处理,暂时不需要,可自行添加实现

    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
