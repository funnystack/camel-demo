package com.funny.combo.camel;

import com.funny.combo.camel.consts.OperateResult;
import com.funny.combo.camel.context.CamelvContext;
import com.funny.combo.camel.entity.CamelvRoute;
import com.funny.combo.camel.manager.RouteManager;
import com.funny.combo.camel.register.RegisterCamelInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 使用数据库初始化
 *
 * @author xiaoka
 */
@Service
public class CamelvContextStarter implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(CamelvContextStarter.class);

    @Resource
    private RegisterCamelInstance registerCamelInstance;

    @Resource
    private RouteManager routeManager;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("初始化camelvContext...");
        CamelvContext.loadMetaData();
        logger.info("加载路由...");
        List<CamelvRoute> routeList = CamelvContext.getAllCamelRoutes();
        for (CamelvRoute camelvRoute : routeList) {
            OperateResult operateResult = routeManager.addRouteDefinition(camelvRoute);
            logger.info("{}路由加载结果:code={},result={}", camelvRoute.getName(),
                    operateResult.getReturncode(), operateResult.getMessage());
        }
        // 注册节点信息
        registerCamelInstance.register();
        // 初始化完毕
        logger.info("初始化完毕...");
    }
}
