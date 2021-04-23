package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvFtpEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158035298L;

    /**
    * 
    */
    private String dataId;

    /**
    * 
    */
    private String name;

    /**
    * 
    */
    private String ip;

    /**
    * 
    */
    private String port;

    /**
    * 
    */
    private String username;

    /**
    * 
    */
    private String password;

    /**
    * 
    */
    private String type;

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

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getName() {
        return name;
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

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getType() {
        return type;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }
}