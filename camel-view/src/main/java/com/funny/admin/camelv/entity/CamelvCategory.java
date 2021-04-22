package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvCategory extends BaseEntity {

	private static final long serialVersionUID = 1000001L;
	private String name;// 类别名称
	private String pId;// 父类别关联id

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
}
