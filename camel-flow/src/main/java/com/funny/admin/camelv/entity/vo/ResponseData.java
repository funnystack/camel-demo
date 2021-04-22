package com.funny.admin.camelv.entity.vo;

public class ResponseData {

	private String type;// 类型
	private Object data;// 数据

	public ResponseData(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseData [type=" + type + ", data=" + data + "]";
	}
}
