package com.funny.admin.camel.listener;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.config.Global;
import com.xiaoka.camelv.entity.CamelvNode;
import com.xiaoka.camelv.service.ICamelvNodeService;

/**
 * 心跳
 * 
 * @author xiaoka
 *
 */
public class HeartBeatListener {

	private static final Logger logger = LoggerFactory.getLogger(HeartBeatListener.class);

	@Autowired
	private ICamelvNodeService nodeService;

	/** 是否启动成功 */
	public static Boolean STARTED = false;

	public void heartbeat(Exchange exchange) {
		if (!STARTED) {
			logger.warn("项目未初始化完毕...");
			return;
		}
		String initType = Global.getConfig("camelv.init.type");
		if ("zookeeper".equals(initType)) {
			logger.error("zookeeper心跳失败,未开发...");
			
			return;
		}
		// 写入节点信息
		String url = Global.getConfig("jetty.url");
		CamelvNode node = nodeService.get(url);
		nodeService.update(node);
	}
}
