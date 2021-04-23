package com.funny.admin.camelv.entity.vo;

import java.io.Serializable;

/**
 * 对应camel的Component Options选项<br/>
 * 
 * @author xiaoka
 *
 */
public class ComponentOption implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11111111L;
	private String name;// 参数名称
	private String value;// 参数值
	private String desc;// 参数描述

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "CamelvOption [name=" + name + ", value=" + value + ", desc=" + desc + "]";
	}
}
