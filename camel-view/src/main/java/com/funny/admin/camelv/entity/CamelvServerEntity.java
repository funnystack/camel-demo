package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvServerEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158106172L;

    /**
    * 
    */
    private String dataId;

    /**
    * 关联类别id
    */
    private String categoryId;

    /**
    * 服务名称
    */
    private String name;

    /**
    * 发布节点地址
    */
    private String publishCamelUrl;

    /**
    * 备注信息
    */
    private String remarks;

    /**
    * 
    */
    private String type;

    /**
    * 
    */
    private String authCode;

    /**
    * 路由规则
    */
    private String routeRule;

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    public String getDataId() {
        return dataId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getName() {
        return name;
    }

    public void setPublishCamelUrl(String publishCamelUrl) {
        this.publishCamelUrl = publishCamelUrl == null ? null : publishCamelUrl.trim();
    }

    public String getPublishCamelUrl() {
        return publishCamelUrl;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getType() {
        return type;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setRouteRule(String routeRule) {
        this.routeRule = routeRule == null ? null : routeRule.trim();
    }

    public String getRouteRule() {
        return routeRule;
    }
}