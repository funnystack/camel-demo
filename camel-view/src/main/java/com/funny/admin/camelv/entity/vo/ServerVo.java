package com.funny.admin.camelv.entity.vo;

import com.funny.combo.core.base.BaseEntity;

/**
 * 非持久化的其他属性
 * 
 * @author fangli
 *
 */
public class ServerVo extends BaseEntity {

	private static final long serialVersionUID = 8163921133669909702L;
	// 服务访问地址
	private String url;
	private String exceptionId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

}
