package com.funny.admin.camelv.entity;

import com.funny.admin.camelv.entity.vo.RouteVo;

public class CamelvRoute extends RouteVo {

    private static final long serialVersionUID = -5278302121217657829L;
    private String name;// 定义路由名称
    private String type;// 节点类型，如：jetty，http，ftp...
    private String serverId;// 关联的服务id，一个服务对应多个路由
    private String relatedResourceId;// 关联资源id
    private String option;// 路由的其他配置参数
    private String uri;// 对应camel的uri
    // 坐标位置
    private Integer left;// 距离左侧位置，单位：像素
    private Integer top;// 距离顶侧位置，单位：像素
    private Integer width;// 距离左侧位置，单位：像素
    private Integer height;// 距离左侧位置，单位：像素

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

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getRelatedResourceId() {
        return relatedResourceId;
    }

    public void setRelatedResourceId(String relatedResourceId) {
        this.relatedResourceId = relatedResourceId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


}
