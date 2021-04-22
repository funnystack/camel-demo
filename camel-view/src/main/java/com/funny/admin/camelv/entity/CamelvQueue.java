package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

/**
 * 队列实体<br/>
 * 包括AMQ与RabbitMQ<br/>
 *
 * @author xiaoka
 */
public class CamelvQueue extends BaseEntity {

    private static final long serialVersionUID = -333333300000L;
    // 类型,activeMQ,rabbitMQ
    private String type;
    // 队列名称
    private String name;
    // 队列ip
    private String ip;
    // 队列端口
    private Integer port;
    // 队列用户名
    private String userName;
    // 队列密码
    private String passWord;
    // 队列名称
    private String queueName;
    // rabbitmq的虚拟主机
    private String vHost;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getvHost() {
        return vHost;
    }

    public void setvHost(String vHost) {
        this.vHost = vHost;
    }

    @Override
    public String toString() {
        return "CamelvQueue [type=" + type + ", name=" + name + ", ip=" + ip + ", port=" + port + ", userName="
                + userName + ", passWord=" + passWord + ", queueName=" + queueName + ", vHost=" + vHost + "]";
    }
}
