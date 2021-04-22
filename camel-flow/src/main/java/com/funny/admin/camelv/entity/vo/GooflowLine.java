package com.funny.admin.camelv.entity.vo;

import java.io.Serializable;

/**
 * 该类和gooflow中的line节点对应<br/>
 * 其id设计为线的起始端节点id与结束端节点的id共同组合而成<br/>
 * 这点很重要,它记录了原来节点之间的关系<br/>
 * 
 * @author liuchengbiao
 *
 */
public class GooflowLine implements Serializable {

	/** 序列ID */
	private static final long serialVersionUID = 10001L;

	private String id;// 线的id，由线的起始端节点id与结束端节点的id共同组合而成，这点很重要,它记录了原来节点之间的关系
	private String type;// 记录线的类型
	private String from;// 记录线的起始端节点id
	private String to;// 记录线的结束端节点id
	private String name;// 记录线上的名称，该值可作为camel执行的判断条件
	private Double M;// 记录线的折点

	public GooflowLine() {
		super();
	}

	public GooflowLine(String id, String type, String from, String to, String name) {
		super();
		this.id = id;
		this.type = type;
		this.from = from;
		this.to = to;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getM() {
		return M;
	}

	public void setM(Double m) {
		M = m;
	}

	@Override
	public String toString() {
		return "GooflowLine [id=" + id + ", type=" + type + ", from=" + from + ", to=" + to + ", name=" + name + ", M="
				+ M + "]";
	}

}
