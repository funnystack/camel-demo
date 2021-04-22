package com.funny.admin.camel.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.xiaoka.camel.service.ICamelvContextInitService;

/**
 * 使用数据库初始化
 * 
 * @author xiaoka
 *
 */
@Service
@Transactional(readOnly = false)
public class CamelvContextZookeeperInitServiceImpl implements ICamelvContextInitService {

	private static final Logger logger = LoggerFactory.getLogger(CamelvContextZookeeperInitServiceImpl.class);

	@Override
	@PostConstruct
	public void init() {
		String initType = Global.getConfig("camelv.init.type");
		if (!"zookeeper".equals(initType)) {
			return;
		}
		logger.info("使用Zookeeper初始化camelvContext...");

	}

}
