package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

/**
 * 对应gooflow中的area
 *
 * @author xiaoka
 */
public class CamelvArea extends BaseEntity {
    /**
     * 序列ID
     */
    private static final long serialVersionUID = 10000L;
    private String name;// 节点显示的名称
    private Integer left;// 节点距离左侧的距离,单位：像素
    private Integer top;// 节点距离顶部的距离，单位：像素
    private Integer width;// 节点的宽度，单位：像素
    private Integer height;// 节点的高度，单位：像素
    private String color;// 颜色
    private String serverId;// 关联的服务id

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return "CamelvArea [name=" + name + ", left=" + left + ", top=" + top + ", width=" + width + ", height="
                + height + ", color=" + color + ", serverId=" + serverId + "]";
    }
}
