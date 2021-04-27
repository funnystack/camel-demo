package com.funny.combo.camel.manager;


import com.funny.combo.camel.consts.OperateResult;

/**
 * 将路由节点与路由之间的关系组成camel可识别的规则<br/>
 *
 * @author fangli
 *
 */
@Deprecated
public interface ICamelvCreateRouteRuleService  {

	/**
	 * 生成指定服务的路由规则
	 *
	 * @param flowId
	 * @return
	 */
	OperateResult create(String flowId);

}
