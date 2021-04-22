package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

/**
 * 一些和主机相关的资源<br>
 * 如：ssh,zookeeper,hdfs，Hbase,Redis<br/>
 * 这些资源一般都是通过ip、port形式连接<br/>
 * 将他们组合在一张表中，通过type字段来区分<br/>
 * 
 * @author xiaoka
 *
 */
public class CamelvHost extends BaseEntity {
	private static final long serialVersionUID = -4178241291940091481L;
	// 用于区分不同类型ssh,zookeeper,hdfs，Hbase,Redis
	private String type;
	// 资源名称
	private String name;
	// 连接ip
	private String ip;
	// 连接port
	private String port;
	// 登录用户名和密码，如：ssh登录需要，zookeeper可能验证需要等
	private String username;
	private String password;

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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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
		return "CamelvHost [type=" + type + ", name=" + name + ", ip=" + ip + ", port=" + port + ", username="
				+ username + ", password=" + password + "]";
	}
}
