package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

public class CamelvQueueEntity extends BaseEntity {
    private static final long serialVersionUID = 1619158091469L;

    /**
    * 
    */
    private String dataId;

    /**
    * 
    */
    private String type;

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
    private Integer port;

    /**
    * 
    */
    private String userName;

    /**
    * 
    */
    private String passWord;

    /**
    * 
    */
    private String queueName;

    /**
    * 
    */
    private String vHost;

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

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getType() {
        return type;
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

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName == null ? null : queueName.trim();
    }

    public String getQueueName() {
        return queueName;
    }

    public void setvHost(String vHost) {
        this.vHost = vHost == null ? null : vHost.trim();
    }

    public String getvHost() {
        return vHost;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRemarks() {
        return remarks;
    }
}