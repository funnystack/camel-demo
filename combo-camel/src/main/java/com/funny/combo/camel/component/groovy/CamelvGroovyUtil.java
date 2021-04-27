package com.funny.combo.camel.component.groovy;

import com.funny.combo.camel.component.exception.RouteErrorCode;
import com.funny.combo.camel.consts.Constant;
import com.funny.combo.camel.context.CamelvContext;
import com.funny.combo.camel.entity.CamelvGroovy;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import org.apache.camel.Exchange;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 执行脚本
 *
 * @author fangli
 */
public class CamelvGroovyUtil {

    private static Logger logger = LoggerFactory.getLogger(CamelvGroovyUtil.class);

    /**
     * 执行指定路由脚本
     *
     * @param exchange
     * @param routeId
     * @return
     * @throws Exception
     */
    public static Object run(Exchange exchange, String routeId) throws Exception {
//		CamelvRoute route = CamelvContext.getCamelvRoute(routeId);
//		if (route == null) {
//			exchange.setProperty(Constant.ROUTE_STATE, RouteErrorCode.ROUTE_DELETED);
//			throw new Exception("该路由资源被删除了,该路由应该从camel中删除的,routeId=" + routeId);
//		}
//		String relatedResourceId = route.getRelatedResourceId();
//		// 是否未配置资源
//		if (StringUtils.isBlank(route.getRelatedResourceId())) {
//			exchange.setProperty(Constant.ROUTE_STATE, RouteErrorCode.RESOURCE_NOT_CONFIG);
//			throw new Exception("该路由未配置Groovy资源,请先配置,routeId=" + routeId);
//		}
        CamelvGroovy groovy = CamelvContext.getCamelvGroovy(routeId);
        if (groovy == null) {
            exchange.setProperty(Constant.ROUTE_STATE, RouteErrorCode.RESOURCE_NOT_FOUND);
            throw new Exception("该路由配置的groovy资源找不到,routeId=" + routeId);
        }
        if (StringUtils.isBlank(groovy.getScript())) {
            exchange.setProperty(Constant.ROUTE_STATE, RouteErrorCode.GROOVY_SCRIPT_NULL);
            throw new Exception("groovy脚本为空,groovy.id=" + groovy.getDataId());
        }
        // 执行脚本
        try {
            Class<?> groovyClass = null;
            if (CamelvContext.getGroovyClass(groovy.getDataId()) != null) {
                logger.info("***从缓存中获取 " + groovy.getDataId() + " 脚本的Class");
                groovyClass = CamelvContext.getGroovyClass(groovy.getDataId());
            } else {
                ClassLoader parent = null;
                if (parent == null) {
                    logger.error("***parent is null ***");
                    parent = Thread.currentThread().getContextClassLoader();
                }
                GroovyClassLoader loader = new GroovyClassLoader(parent);
                groovyClass = loader.parseClass(groovy.getScript());
                CamelvContext.addGroovyClass(groovy.getDataId(), groovyClass);
            }
            // 向groovy脚本中绑定变量值
            Binding binding = new Binding();
            String exchange_name = "camelv.groovy.exchange.name";
            binding.setVariable(exchange_name, exchange);
            Script script = InvokerHelper.createScript(groovyClass, binding);
            // 执行脚本
            Object result = script.run();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            exchange.setProperty(Constant.ROUTE_STATE, RouteErrorCode.GROOVY_SCRIPT_ERROR);
            throw new Exception("groovy脚本执行失败,groovy.id=" + groovy.getDataId());
        }
    }
}
