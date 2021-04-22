package com.funny.admin.camel.service.impl;

import com.funny.admin.camel.context.CamelvContext;
import com.funny.admin.camel.listener.HeartBeatListener;
import com.funny.admin.camel.manager.RouteManager;
import com.funny.admin.camel.service.ICamelvContextInitService;
import com.funny.admin.camelv.constant.Global;
import com.funny.admin.camelv.constant.RouteType;
import com.funny.admin.camelv.entity.*;
import com.funny.admin.camelv.entity.vo.ComponentOption;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.*;
import com.funny.admin.camelv.util.CamelvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

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

	@Resource
	private ICamelvNodeService nodeService;
	@Resource
	private ICamelvFtpService ftpService;
	@Resource
	private ICamelvGroovyService groovyService;
	@Resource
	private ICamelvHostService hostService;
	@Resource
	private ICamelvHttpService httpService;
	@Resource
	private ICamelvJdbcService jdbcService;
	@Resource
	private ICamelvMailService mailService;
	@Resource
	private ICamelvQueueService queueService;
	@Resource
	private ICamelvRouteService routeService;
	@Resource
	private ICamelvServerService serverService;
	@Resource
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
			List<CamelvRoute> routes = serverService.getRoutes(camelvServer.getId()+"");
			for (CamelvRoute camelvRoute : routes) {
				if (camelvRoute.getType().equals(RouteType.ROUTE_TYPE_EXCEPTION)) {
					CamelvContext.addCamelvRoute4Exception(camelvRoute);
				} else {
					CamelvContext.addCamelvRoute4Line(camelvRoute);
				}
				/** 添加参数 */
				List<ComponentOption> optionList = CamelvUtil.parseOption(camelvRoute.getOption());
				CamelvContext.getCamelvRoute(camelvRoute.getId()+"").setOptionList(optionList);
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
//			node.setId(url);
			nodeService.save(node);
		} else {
			nodeService.update(node);
		}
		// 初始化完毕
		HeartBeatListener.STARTED = true;
		logger.info("初始化完毕...");
	}

}
