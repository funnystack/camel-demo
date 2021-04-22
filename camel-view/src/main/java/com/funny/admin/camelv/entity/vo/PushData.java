package com.funny.admin.camelv.entity.vo;

/**
 * 数据修改时推送的数据
 * 
 * @author xiaoka
 *
 */
public class PushData {

	private String type;// 数据类型，如：groovy、http等
	private String data;// 数据

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
