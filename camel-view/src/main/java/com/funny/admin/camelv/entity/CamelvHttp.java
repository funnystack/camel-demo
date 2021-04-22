package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

/**
 * http资源
 * 
 * @author xiaoka
 *
 */
public class CamelvHttp extends BaseEntity {
	private static final long serialVersionUID = 2000000000L;
	// http服务名称
	private String name;
	// http服务地址
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "CamelvHttp [name=" + name + ", url=" + url + "]";
	}
}
