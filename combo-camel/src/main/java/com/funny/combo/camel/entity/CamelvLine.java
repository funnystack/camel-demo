package com.funny.combo.camel.entity;

import java.io.Serializable;

public class CamelvLine implements Serializable {
    /**
     * 流程id
     */
    private String flowId;
    /**
     *
     */
    private String lineId;
    /**
     * 起始端
     */
    private String fromRouteId;

    /**
     * 指向端
     */
    private String toRouteId;

    /**
     * 类型
     */
    private String type;

    /**
     * 线的折点
     */
    private Double m;

    /**
     * 条件
     */
    private String name;

    /**
     * 备注信息
     */
    private String remarks;



    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getFromRouteId() {
        return fromRouteId;
    }

    public void setFromRouteId(String fromRouteId) {
        this.fromRouteId = fromRouteId;
    }

    public String getToRouteId() {
        return toRouteId;
    }

    public void setToRouteId(String toRouteId) {
        this.toRouteId = toRouteId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getM() {
        return m;
    }

    public void setM(Double m) {
        this.m = m;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
