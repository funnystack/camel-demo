package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

/**
 * camel JDBC资源<br/>
 * 1.在添加的同时注册到各个节点<br/>
 * 2.在各个节点启动时注册到各个节点<br/>
 *
 * @author xiaoka
 */
public class CamelvJdbc extends BaseEntity {
    private static final long serialVersionUID = 100000001L;
    // 资源名称
    private String name;
    // 驱动名称
    private String driver;
    // 数据库连接地址
    private String url;
    // 连接用户名
    private String username;
    // 连接密码
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CamelvJdbc [name=" + name + ", driver=" + driver + ", url=" + url + ", username=" + username
                + ", password=" + password + "]";
    }
}
