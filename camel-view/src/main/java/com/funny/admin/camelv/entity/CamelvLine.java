package com.funny.admin.camelv.entity;

import java.io.Serializable;

public class CamelvLine implements Serializable {
    /**
     *
     */
    private String dataId;

    /**
     * 关联服务
     */
    private String serverId;

    /**
     * 起始端
     */
    private String fromRouteId;

    /**
     * 指向端
     */
    private String toRouteId;

    /**
     * 类型
     */
    private String type;

    /**
     * 线的折点
     */
    private Double m;

    /**
     * 条件
     */
    private String name;

    /**
     * 备注信息
     */
    private String remarks;
}
