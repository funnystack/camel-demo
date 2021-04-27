package com.funny.admin.camelv.entity.vo;

import java.io.Serializable;

/**
 * 对应gooflow中的area
 * 
 * @author fangli
 *
 */
public class GooflowArea implements Serializable {

	/** 序列ID */
	private static final long serialVersionUID = 10000L;
	private String name;// 节点显示的名称
	private Integer left;// 节点距离左侧的距离,单位：像素
	private Integer top;// 节点距离顶部的距离，单位：像素
	private Integer width;// 节点的宽度，单位：像素
	private Integer height;// 节点的高度，单位：像素
	private Boolean alt;// 标识是否改变
	private String color;// 颜色

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "GooflowArea [name=" + name + ", left=" + left + ", top=" + top + ", width=" + width + ", height="
				+ height + ", alt=" + alt + ", color=" + color + "]";
	}
}
