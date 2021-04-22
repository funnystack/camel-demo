package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

/**
 * ftp资源<br/>
 * 分为ftp、sfpt两种类型<br/>
 * 
 * @author xiaoka
 *
 */
public class CamelvFtp extends BaseEntity {
	private static final long serialVersionUID = -56598749382483L;
	// 实体表示名称
	private String name;
	// ftp连接ip
	private String ip;
	// ftp连接断开
	private String port;
	// ftp连接用户名
	private String username;
	// ftp连接密码
	private String password;
	// ftp类型，标识：ftp、sftp
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FtpInfo [name=" + name + ", ip=" + ip + ", port=" + port + ", username=" + username + ", password="
				+ password + ", type=" + type + "]";
	}
}
