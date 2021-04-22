package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvLine extends BaseEntity {
	// 线的id，由线的起始端节点id与结束端节点的id共同组合而成，这点很重要,它记录了原来节点之间的关系
	private static final long serialVersionUID = 1L;
	private String from;// 记录线的起始端节点id
	private String to;// 记录线的结束端节点id
	private String type;// 记录线的类型
	private String name;// 记录线上的名称，该值可作为camel执行的判断条件
	private Double m;// 记录折线的中点
	private String serverId;// 关联的服务id

	public CamelvLine() {

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	
	public Double getM() {
		return m;
	}

	public void setM(Double m) {
		this.m = m;
	}

	@Override
	public String toString() {
		return "CamelvLine [from=" + from + ", to=" + to + ", type=" + type + ", name=" + name + ", serverId="
				+ serverId + "]";
	}
}
