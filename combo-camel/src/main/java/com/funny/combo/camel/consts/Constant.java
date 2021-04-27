package com.funny.combo.camel.consts;

/**
 * 工程使用的常量<br/>
 *
 * @author fangli
 *
 */
public interface Constant {
	/** 成功 */
	public static final String SUCCESS = "success";
	/** 失败 */
	public static final String ERROR = "error";
	/** 创建路由规则失败 */
	public static final String CREATE_RULE_ERROR = "createRuleError";

	/** 路由类型 */
	public static final String TYPE_NODE = "node";
	/** 路由关系类型 */
	public static final String TYPE_LINE = "line";
	// 以下是camel发布错误提示
	/**  */
	public static final String PARSE_XML_ERROR = "路由规则解析失败";
	/**  */
	public static final String PUBLISH_ERROR = "发布失败";

	public static final String ROUTE_RULE_NULL = "路由规则为空";
	// 以下是http类型
	/** post请求 */
	public static final String HTTP_POST = "post";
	/** get请求 */
	public static final String HTTP_GET = "get";
	/** 默认contentType类型 */
	public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

	/** 自定义路由id前缀 */
	public static final String PREFFIX_ROUTE_ID = "route-";
	/** 路由direct */
	public static final String DIRECT = "direct:";
	/** Recipient List delimiter 多个路由地址分隔符 */
	public static final String RECIPIENT_LIST_DELIMITER = ",";
	/** 分支路由时条件匹配，条件名称，in.header */
	public static final String BRANCH_HEADER_NAME = "condition";
	/** 下一个路由地址 */
	public static final String NEXT_URI = "nextUri";
	/** 异常路由地址 */
	public static final String EXCEPTION_NEXT_URI = "exceptionUrl";
	// 错误码
	/** 异常时存入的理由状态异常信息 */
	public static final String ROUTE_STATE = "routeState";
	/** 持久化开关 */
	public static final String PERSIST_SWITCH = "persistSwitch";

	/** 入口消息exchangeId */
	public static final String ORIGINAL_EXCHANGE_ID = "originalExchangeId";

}
