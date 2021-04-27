package com.funny.combo.camel;

import com.funny.combo.camel.component.HeartBeatListener;
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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author fangli
 */
@Component
public class CamelvContextStarter implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(CamelvContextStarter.class);

    @Resource
    private RegisterCamelInstance registerCamelInstance;

    @Resource
    private RouteManager routeManager;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("camelvContext初始化开始...");
        CamelvContext.loadMetaData();
        logger.info("加载路由...");
        List<CamelvRoute> routeList = CamelvContext.getAllCamelRoutes();
        for (CamelvRoute camelvRoute : routeList) {
            OperateResult operateResult = routeManager.addRouteDefinition(camelvRoute);
            logger.info("{}路由加载结果:code={},result={}", camelvRoute.getRouteName(),
                    operateResult.getReturncode(), operateResult.getMessage());
        }
        // 注册节点信息
        registerCamelInstance.register();
        HeartBeatListener.STARTED = true;
        // 初始化完毕
        logger.info("camelvContext初始化完毕...");
    }
}
