package com.funny.combo.camel.consts;

public enum RouteTypeEnum {
    ROUTE_TYPE_BEAN("bean"),
    ROUTE_TYPE_RPC("rpc"),
    ROUTE_TYPE_GROOVY("groovy"),
    ROUTE_TYPE_EXCEPTION("exception");

    private String type;

    RouteTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
