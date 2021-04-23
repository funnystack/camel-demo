package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvRouteEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158099290L;

    /**
    * 
    */
    private String dataId;

    /**
    * 路由名称
    */
    private String name;

    /**
    * 路由类型
    */
    private String type;

    /**
    * 关联服务
    */
    private String serverId;

    /**
    * 关联的资源id
    */
    private String relatedResourceId;

    /**
    * 路由其他参数json格式
    */
    private String param;

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

    /**
    * 
    */
    private String componentOption;

    /**
    * 
    */
    private String uri;

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

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getType() {
        return type;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId == null ? null : serverId.trim();
    }

    public String getServerId() {
        return serverId;
    }

    public void setRelatedResourceId(String relatedResourceId) {
        this.relatedResourceId = relatedResourceId == null ? null : relatedResourceId.trim();
    }

    public String getRelatedResourceId() {
        return relatedResourceId;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getParam() {
        return param;
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

    public void setComponentOption(String componentOption) {
        this.componentOption = componentOption == null ? null : componentOption.trim();
    }

    public String getComponentOption() {
        return componentOption;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public String getUri() {
        return uri;
    }
}