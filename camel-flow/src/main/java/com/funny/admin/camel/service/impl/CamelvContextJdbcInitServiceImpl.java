package com.funny.admin.camel.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.xiaoka.camel.context.CamelvContext;
import com.xiaoka.camel.listener.HeartBeatListener;
import com.xiaoka.camel.manager.RouteManager;
import com.xiaoka.camel.service.ICamelvContextInitService;
import com.xiaoka.camelv.constant.RouteType;
import com.xiaoka.camelv.entity.CamelvFtp;
import com.xiaoka.camelv.entity.CamelvGroovy;
import com.xiaoka.camelv.entity.CamelvHost;
import com.xiaoka.camelv.entity.CamelvHttp;
import com.xiaoka.camelv.entity.CamelvJdbc;
import com.xiaoka.camelv.entity.CamelvMail;
import com.xiaoka.camelv.entity.CamelvNode;
import com.xiaoka.camelv.entity.CamelvQueue;
import com.xiaoka.camelv.entity.CamelvRoute;
import com.xiaoka.camelv.entity.CamelvServer;
import com.xiaoka.camelv.entity.vo.ComponentOption;
import com.xiaoka.camelv.entity.vo.ResponseData;
import com.xiaoka.camelv.service.ICamelvFtpService;
import com.xiaoka.camelv.service.ICamelvGroovyService;
import com.xiaoka.camelv.service.ICamelvHostService;
import com.xiaoka.camelv.service.ICamelvHttpService;
import com.xiaoka.camelv.service.ICamelvJdbcService;
import com.xiaoka.camelv.service.ICamelvMailService;
import com.xiaoka.camelv.service.ICamelvNodeService;
import com.xiaoka.camelv.service.ICamelvQueueService;
import com.xiaoka.camelv.service.ICamelvRouteService;
import com.xiaoka.camelv.service.ICamelvServerService;
import com.xiaoka.camelv.util.CamelvUtil;

/**
 * 使用数据库初始化
 * 
 * @author xiaoka
 *
 */
@Service
@Transactional(readOnly = false)
public class CamelvContextJdbcInitServiceImpl implements ICamelvContextInitService {

	private static final Logger logger = LoggerFactory.getLogger(CamelvContextJdbcInitServiceImpl.class);

	@Autowired
	private ICamelvNodeService nodeService;
	@Autowired
	private ICamelvFtpService ftpService;
	@Autowired
	private ICamelvGroovyService groovyService;
	@Autowired
	private ICamelvHostService hostService;
	@Autowired
	private ICamelvHttpService httpService;
	@Autowired
	private ICamelvJdbcService jdbcService;
	@Autowired
	private ICamelvMailService mailService;
	@Autowired
	private ICamelvQueueService queueService;
	@Autowired
	private ICamelvRouteService routeService;
	@Autowired
	private ICamelvServerService serverService;
	@Autowired
	private RouteManager routeManager;

	@Override
	@PostConstruct
	public void init() {

		String initType = Global.getConfig("camelv.init.type");
		if (!"jdbc".equals(initType)) {
			return;
		}

		logger.info("使用JDBC初始化camelvContext...");

		logger.info("初始化ftp...");
		List<CamelvFtp> ftpList = ftpService.getAll();
		for (CamelvFtp camelvFtp : ftpList) {
			CamelvContext.addCamelvFtp(camelvFtp);
		}
		logger.info("初始化groovy...");
		List<CamelvGroovy> groovyList = groovyService.getAll();
		for (CamelvGroovy camelvGroovy : groovyList) {
			CamelvContext.addCamelvGroovy(camelvGroovy);
		}
		logger.info("初始化主机Host...");
		List<CamelvHost> hostList = hostService.getAll();
		for (CamelvHost camelvHost : hostList) {
			CamelvContext.addCamelvHost(camelvHost);
		}
		logger.info("初始化主机http...");
		List<CamelvHttp> httpList = httpService.getAll();
		for (CamelvHttp camelvHttp : httpList) {
			CamelvContext.addCamelvHttp(camelvHttp);
		}
		logger.info("初始化主机jdbc...");
		List<CamelvJdbc> jdbcList = jdbcService.getAll();
		for (CamelvJdbc camelvJdbc : jdbcList) {
			CamelvContext.addCamelvJdbc(camelvJdbc);
		}
		logger.info("初始化主机mail...");
		List<CamelvMail> mailList = mailService.getAll();
		for (CamelvMail camelvMail : mailList) {
			CamelvContext.addCamelvMail(camelvMail);
		}
		logger.info("初始化主机mail...");
		List<CamelvQueue> queueList = queueService.getAll();
		for (CamelvQueue camelvQueue : queueList) {
			CamelvContext.addCamelvQueue(camelvQueue);
		}
		logger.info("初始化服务信息...");
		List<CamelvServer> serverList = serverService.getAll();
		for (CamelvServer camelvServer : serverList) {
			CamelvContext.addCamelvServer(camelvServer);
		}

		logger.info("初始化路由信息...");
		serverList = serverService.getAll();
		for (CamelvServer camelvServer : serverList) {
			List<CamelvRoute> routes = serverService.getRoutes(camelvServer.getId());
			for (CamelvRoute camelvRoute : routes) {
				if (camelvRoute.getType().equals(RouteType.ROUTE_TYPE_EXCEPTION)) {
					CamelvContext.addCamelvRoute4Exception(camelvRoute);
				} else {
					CamelvContext.addCamelvRoute4Line(camelvRoute);
				}
				/** 添加参数 */
				List<ComponentOption> optionList = CamelvUtil.parseOption(camelvRoute.getOption());
				CamelvContext.getCamelvRoute(camelvRoute.getId()).setOptionList(optionList);
			}
		}
		logger.info("加载路由...");
		List<CamelvRoute> routeList = routeService.getAll();
		for (CamelvRoute camelvRoute : routeList) {
			ResponseData rd = routeManager.addRouteDefinition(camelvRoute);
			logger.info(camelvRoute.getName() + "路由加载结果:" + rd);
		}
		logger.info("初始化节点信息...");
		// 获取节点发布地址
		String url = Global.getConfig("jetty.url");
		// 写入节点信息
		CamelvNode node = nodeService.get(url);
		if (node == null) {
			node = new CamelvNode();
			node.setId(url);
			nodeService.save(node);
		} else {
			nodeService.update(node);
		}
		// 初始化完毕
		HeartBeatListener.STARTED = true;
		logger.info("初始化完毕...");
	}

}
