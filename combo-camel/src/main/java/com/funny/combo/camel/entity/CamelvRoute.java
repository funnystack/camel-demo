package com.funny.combo.camel.entity;

import com.funny.combo.camel.consts.RouteType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CamelvRoute implements Serializable {
    /**
     * 流程id
     */
    private String flowId;
    /**
     * 记录该路由后续需要执行的多个路由id
     */
    private List<String> to;
    /**
     * 记录指向该路由的路由id
     */
    private List<String> from;
    /**
     * 记录分支路由指向下一个路由的条件，key:当前路由指向下一个路由的条件，value：满足条件执行的下一个路由id
     */
    private Map<String, String> condition;
    /**
     * 记录是否是聚合路由
     */
    private boolean aggregat;
    /**
     * 关联的异常路由id
     */
    private String exceptionId;
    /**
     * 参数
     */
    private List<ComponentOption> optionList;
    /**
     *
     */
    private String routeId;

    /**
     * 路由名称
     */
    private String routeName;
    /**
     * 对应camel的uri
     */
    private String uri;

    /**
     * 路由类型
     *
     * @see RouteType
     */
    private String routeType;
    /**
     * 路由其他参数json格式
     */
    private String param;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getFrom() {
        return from;
    }

    public void setFrom(List<String> from) {
        this.from = from;
    }

    public Map<String, String> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, String> condition) {
        this.condition = condition;
    }

    public boolean getAggregat() {
        return aggregat ;
    }

    public void setAggregat(boolean aggregat) {
        this.aggregat = aggregat;
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    public List<ComponentOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<ComponentOption> optionList) {
        this.optionList = optionList;
    }
}
