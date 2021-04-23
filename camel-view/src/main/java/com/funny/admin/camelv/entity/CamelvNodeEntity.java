package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvNodeEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158085053L;

    /**
    * 
    */
    private String dataId;

    /**
    * 
    */
    private String ip;

    /**
    * 
    */
    private String port;

    /**
    * 备注信息
    */
    private String remarks;

    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    public String getDataId() {
        return dataId;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getPort() {
        return port;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }
}