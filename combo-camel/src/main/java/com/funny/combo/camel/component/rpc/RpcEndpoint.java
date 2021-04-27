package com.funny.combo.camel.component.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.funny.combo.camel.component.exception.RouteErrorCode;
import com.funny.combo.camel.consts.Constant;
import com.funny.combo.camel.context.CamelvContext;
import com.funny.combo.camel.entity.CamelvHttp;
import com.funny.combo.camel.entity.CamelvRoute;
import com.funny.combo.camel.utils.RequestHelper;
import org.apache.camel.Component;
import org.apache.camel.Exchange;
import org.apache.camel.component.ResourceEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 动态设置路由下一个需要执行的地址
 * URI Format格式
 * rpc:routeType?routeId=XXX
 *
 * @author fangli
 */
public class RpcEndpoint extends ResourceEndpoint {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @UriParam
    private String routeId;

    public RpcEndpoint(String uri, Component component, String remaining) {
        super(uri, component, remaining);
    }

    @Override
    protected void onExchange(Exchange exchange) throws Exception {
        logger.info("execute rpc start ");
        // 设置header
        Map<String, Object> headers = exchange.getIn().getHeaders();
        Map<String, String> headerMap = new HashMap<String, String>();
        Set<Entry<String, Object>> entrySet = headers.entrySet();
        for (Entry<String, Object> entry : entrySet) {
            if (!StringUtils.isBlank(entry.getKey())) {
                Object v = entry.getValue();
                headerMap.put(entry.getKey(), v == null ? null : v.toString());
            }
        }
        CamelvRoute route = CamelvContext.getCamelvRoute(routeId);
        if (route == null) {
            exchange.setProperty(Constant.ROUTE_STATE, RouteErrorCode.ROUTE_DELETED);
            throw new Exception("该路由资源被删除了,该路由应该从camel中删除的,routeId=" + routeId);
        }
        CamelvHttp camelvHttp = CamelvContext.getCamelvHttp(routeId);
        Map<String, String> params = getParameters(camelvHttp, exchange);
        String resultBody = "";
        // 判断请求类型
        if (Constant.HTTP_GET.equalsIgnoreCase(camelvHttp.getRequestType())) {
            resultBody = RequestHelper.get(camelvHttp.getRequestUrl(), params, headerMap);
        } else if (Constant.HTTP_POST.equalsIgnoreCase(camelvHttp.getRequestType())) {
            if (Constant.CONTENT_TYPE_JSON.equalsIgnoreCase(camelvHttp.getContentType())) {
                String paramJson = getParameJson(camelvHttp,exchange);
                resultBody = RequestHelper.postJsonWithHeader(camelvHttp.getRequestUrl(), paramJson, headerMap);
            }else{
                resultBody = RequestHelper.post(camelvHttp.getRequestUrl(), params, headerMap);
            }
        }
        logger.info("execute rpc end ");
        // 设置结果到body中
        exchange.setOut(exchange.getIn());
        exchange.getOut().setBody(resultBody, String.class);
    }

    private Map<String, String> getParameters(CamelvHttp camelvHttp, Exchange exchange) {
        if (StringUtils.isBlank(camelvHttp.getParams())) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        String oldBody = exchange.getIn().getBody(String.class);
        JSONObject jsonObject = JSON.parseObject(oldBody);

        JSONObject params = JSON.parseObject(camelvHttp.getParams());
        for (String key : params.keySet()) {
            String valueExpression = params.getString(key);
            if (valueExpression.contains("$")) {
                valueExpression = valueExpression.replace("${", "").replace("}", "");
                if(valueExpression.contains(".")){
                    String[] array = valueExpression.split(".");
                    for (int i = 0; i < array.length; i++) {
                        jsonObject = jsonObject.getJSONObject(array[i]);
                    }
                    map.put(key, jsonObject.toString());
                }else{
                    map.put(key, jsonObject.getString(valueExpression));
                }
            } else {
                map.put(key, valueExpression);
            }
        }
        return map;
    }


    private String getParameJson(CamelvHttp camelvHttp, Exchange exchange) {
        if (StringUtils.isBlank(camelvHttp.getParams())) {
            return null;
        }
        String result = "";
        Map<String, String> map = new HashMap<>();
        String oldBody = exchange.getIn().getBody(String.class);
        JSONObject jsonObject = JSON.parseObject(oldBody);
        JSONObject params = JSON.parseObject(camelvHttp.getParams());
        for (String key : params.keySet()) {
            String valueExpression = params.getString(key);
            if (valueExpression.contains("$") && key.equalsIgnoreCase("@@")) {
                valueExpression = valueExpression.replace("${", "").replace("}", "");
                if(valueExpression.contains(".")){
                    String[] array = valueExpression.split(".");
                    for (int i = 0; i < array.length; i++) {
                        jsonObject = jsonObject.getJSONObject(array[i]);
                    }
                    result = jsonObject.toString();
                }else{
                    result = jsonObject.getString(valueExpression);
                }
            }
        }
        return result;
    }


    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
