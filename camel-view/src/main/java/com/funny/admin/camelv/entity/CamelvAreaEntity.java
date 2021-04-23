package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvAreaEntity extends BaseEntity {
    private static final long serialVersionUID = 1619157992563L;

    /**
    * 
    */
    private String dataId;

    /**
    * 工作区名称
    */
    private String name;

    /**
    * 工作区颜色
    */
    private String color;

    /**
    * 关联服务
    */
    private String serverId;

    /**
    * 左侧位置
    */
    private Integer leftPx;

    /**
    * 距离顶部位置
    */
    private Integer topPx;

    /**
    * 宽度
    */
    private Integer width;

    /**
    * 高度
    */
    private Integer height;

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

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getColor() {
        return color;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId == null ? null : serverId.trim();
    }

    public String getServerId() {
        return serverId;
    }

    public void setLeftPx(Integer leftPx) {
        this.leftPx = leftPx;
    }

    public Integer getLeftPx() {
        return leftPx;
    }

    public void setTopPx(Integer topPx) {
        this.topPx = topPx;
    }

    public Integer getTopPx() {
        return topPx;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getWidth() {
        return width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }
}