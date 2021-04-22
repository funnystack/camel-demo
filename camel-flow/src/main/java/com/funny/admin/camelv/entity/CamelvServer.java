package com.funny.admin.camelv.entity;


import com.funny.admin.camelv.entity.vo.ServerVo;

public class CamelvServer extends ServerVo {
	private static final long serialVersionUID = -1818393041152707932L;
	private String categoryId;// 关联的类别ID信息
	private String name;// 服务名称
	private String type;// 服务类型http、activemq、rabbitmq
	private String authCode;// 服务调用授权码
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "CamelvServer [categoryId=" + categoryId + ", name=" + name + ", type=" + type + "]";
	}
}
