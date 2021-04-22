package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.vo.ResponseData;

/**
 * 将路由节点与路由之间的关系组成camel可识别的规则<br/>
 * 
 * @author xiaoka
 *
 */
public interface ICamelvCreateRouteRuleService {
	/**
	 * 生成指定服务的路由规则
	 * 
	 * @param serverId
	 * @return
	 */
	ResponseData create(String serverId);

}