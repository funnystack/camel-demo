package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

import java.util.Date;

/**
 * 服务日志
 * 
 * @author xiaoka
 *
 */
public class CamelvServerLog extends BaseEntity {

	private static final long serialVersionUID = -456877738554710794L;
	// id:为messageid,每次不同
	// 服务名称
	private String name;
	// 调用状态-1:失败,0:成功
	private Integer status;
	// 开始时间
	private Date startDate;
	// 结束时间
	private Date endDate;
	// 请求头
	private String requestHeader;
	// 请求体
	private String requestBody;
	// 响应头
	private String responseHeader;
	// 响应体
	private String responseBody;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(String requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(String responseHeader) {
		this.responseHeader = responseHeader;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	@Override
	public String toString() {
		return "CamelvServerLog [name=" + name + ", status=" + status + ", startDate=" + startDate + ", endDate="
				+ endDate + ", requestBody=" + requestBody + "]";
	}
	
}
