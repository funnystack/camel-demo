package com.funny.admin.camelv.entity.vo;

/**
 * 发布时将数据填充
 * 
 * @author xiaoka
 *
 */
public class PublishData {

	private String serverId;// 标识发布的服务id
	private String routeRule;// 发布的路由模板

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getRouteRule() {
		return routeRule;
	}

	public void setRouteRule(String routeRule) {
		this.routeRule = routeRule;
	}
}
