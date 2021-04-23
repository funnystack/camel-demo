package com.funny.combo.camel.component.exception;

/**
 * 路由执行状态<br/>
 * 
 * @author xiaoka
 *
 */
public enum RouteState {

	UNKNOWN_EXCEPTION("700", "未知异常", "该异常不在处理范围内!"), 
	ROUTE_DELETED("701", "路由已经被删除","请确定该路由是否被删除!"), 
	RESOURCE_NOT_CONFIG("702", "资源未配置", "请先配置该路由的关联资源!"),
	RESOURCE_NOT_FOUND("703", "资源未找到", "请确定资源是否被删除!"),
	GROOVY_SCRIPT_NULL("704", "groovy脚本为空", "请先配置groovy脚本!"),
	JETTY_NOT_CONFIG_NEXT("705", "入口未配置to路由", "请先配置服务的to路由!"),
	LINE_NOT_CONFIG("706", "路由之间关系未配置", "请先配置路由关系"),
	CONDITION_IS_NULL("707", "消息头未携带分支条件", "请确定消息头中是否携带分支条件消息头"),
	CONDITION_NOT_MATCH("708", "条件匹配失败", "请确定条件是否正确"),
	GROOVY_SCRIPT_ERROR("709", "groovy脚本执行失败", "请检查groovy脚本是否正确"),
	SERVER_DELETED("710", "服务已经被删除", "请检查是否存在该服务"),
	PERMISSION_ERROR("711", "权限验证失败", "请检查是否具有权限调用"),
	;

	/** 错误码 */
	private String code;
	/** 错误信息 */
	private String msg;
	/** 错误描述 */
	private String desc;

	private RouteState(String code, String msg, String desc) {
		this.code = code;
		this.msg = msg;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
