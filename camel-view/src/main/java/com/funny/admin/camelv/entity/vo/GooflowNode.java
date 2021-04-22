package com.funny.admin.camelv.entity.vo;

import java.io.Serializable;

/**
 * 该节点与gooflow中的node节点对应<br/>
 * 通过id以及type字段来和具体的业务结合<br/>
 * 
 * @author liuchengbiao
 *
 */
public class GooflowNode implements Serializable {

	/** 序列ID */
	private static final long serialVersionUID = 10000L;
	private String id;// 节点id
	private String name;// 节点显示的名称
	private Integer left;// 节点距离左侧的距离,单位：像素
	private Integer top;// 节点距离顶部的距离，单位：像素
	private String type;// 节点的类型，不同类型显示的图标样式、所具有的功能不同
	private Integer width;// 节点的宽度，单位：像素
	private Integer height;// 节点的高度，单位：像素
	private Boolean alt;// 标识是否改变

	public GooflowNode() {
		super();
	}

	public GooflowNode(String id, String name, Integer left, Integer top, String type, Integer width, Integer height,
			Boolean alt) {
		super();
		this.id = id;
		this.name = name;
		this.left = left;
		this.top = top;
		this.type = type;
		this.width = width;
		this.height = height;
		this.alt = alt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Boolean getAlt() {
		return alt;
	}

	public void setAlt(Boolean alt) {
		this.alt = alt;
	}

	@Override
	public String toString() {
		return "GooflowNode [id=" + id + ", name=" + name + ", left=" + left + ", top=" + top + ", type=" + type
				+ ", width=" + width + ", height=" + height + ", alt=" + alt + "]";
	}
}
