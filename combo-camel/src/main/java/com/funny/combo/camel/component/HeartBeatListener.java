package com.funny.combo.camel.component;

import com.funny.combo.camel.register.RegisterCamelInstance;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 心跳
 *
 * @author xiaoka
 *
 */
public class HeartBeatListener {

	private static final Logger logger = LoggerFactory.getLogger(HeartBeatListener.class);

	@Resource
	private RegisterCamelInstance registerCamelInstance;

	/** 是否启动成功 */
	public static Boolean STARTED = false;

	public void heartbeat(Exchange exchange) {
		if (!STARTED) {
			logger.warn("项目未初始化完毕...");
			return;
		}
		registerCamelInstance.register();
	}
}
