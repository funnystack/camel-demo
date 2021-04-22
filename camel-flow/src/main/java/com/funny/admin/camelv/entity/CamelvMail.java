package com.funny.admin.camelv.entity;

import com.funny.combo.core.base.BaseEntity;

/**
 * 邮箱的资源<br/>
 * 
 * @author xiaoka
 *
 */
public class CamelvMail extends BaseEntity {
	private static final long serialVersionUID = -4178241291940091481L;
	// 发件人名称,默认和发件人邮箱一致
	private String name;
	// 邮箱服务器，发件人需要，收件人只需要邮箱账号即可
	private String mailHost;
	// 邮箱账号
	private String username;
	// 邮箱密码
	private String password;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
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
		return "CamelvMail [name=" + name + ", mailHost=" + mailHost + ", username=" + username + ", password="
				+ password + "]";
	}
}
