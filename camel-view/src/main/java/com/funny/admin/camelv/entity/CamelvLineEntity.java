package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvLineEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158069543L;

    /**
    * 
    */
    private String dataId;

    /**
    * 关联服务
    */
    private String serverId;

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

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    public String getDataId() {
        return dataId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId == null ? null : serverId.trim();
    }

    public String getServerId() {
        return serverId;
    }

    public void setFromRouteId(String fromRouteId) {
        this.fromRouteId = fromRouteId == null ? null : fromRouteId.trim();
    }

    public String getFromRouteId() {
        return fromRouteId;
    }

    public void setToRouteId(String toRouteId) {
        this.toRouteId = toRouteId == null ? null : toRouteId.trim();
    }

    public String getToRouteId() {
        return toRouteId;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getType() {
        return type;
    }

    public void setM(Double m) {
        this.m = m;
    }

    public Double getM() {
        return m;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getName() {
        return name;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }
}