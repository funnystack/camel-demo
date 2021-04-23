package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvMailEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158076361L;

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
    private String mailHost;

    /**
    * 
    */
    private String username;

    /**
    * 
    */
    private String password;

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

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost == null ? null : mailHost.trim();
    }

    public String getMailHost() {
        return mailHost;
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

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }
}