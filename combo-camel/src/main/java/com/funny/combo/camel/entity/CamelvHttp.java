package com.funny.combo.camel.entity;

import java.io.Serializable;

/**
 * http资源
 *
 * @author fangli
 *
 */
public class CamelvHttp implements Serializable {

    /**
     *
     */
    private String dataId;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String requestUrl;
    /**
     * http类型
     */
    private String requestType;
    /**
     * 请求数据类型
     * CONTENT_TYPE = "text/plain"
     * CONTENT_TYPE = "application/json;charset=UTF-8"
     */
    private String contentType;
    /**
     * 请求参数
     */
    private String params;
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

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
