package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;
import java.util.Date;

public class CamelvServerLogEntity extends BaseEntity {
    private static final long serialVersionUID = 1619160648817L;

    /**
    * 
    */
    private String dataId;

    /**
    * 服务名称
    */
    private String name;

    /**
    * 服务状态
    */
    private Integer status;

    /**
    * 开始时间
    */
    private Date startDate;

    /**
    * 结束时间
    */
    private Date endDate;

    /**
    * 备注信息
    */
    private String remarks;

    /**
    * 请求头
    */
    private String requestHeader;

    /**
    * 请求体
    */
    private String requestBody;

    /**
    * 响应头
    */
    private String responseHeader;

    /**
    * 响应体
    */
    private String responseBody;

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

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader == null ? null : requestHeader.trim();
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody == null ? null : requestBody.trim();
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader == null ? null : responseHeader.trim();
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody == null ? null : responseBody.trim();
    }

    public String getResponseBody() {
        return responseBody;
    }
}