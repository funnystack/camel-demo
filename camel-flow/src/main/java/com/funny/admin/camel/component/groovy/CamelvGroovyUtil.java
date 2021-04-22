package com.funny.admin.camel.component.groovy;

import org.apache.camel.Exchange;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.xiaoka.camel.component.exception.RouteState;
import com.xiaoka.camel.context.CamelvContext;
import com.xiaoka.camelv.constant.Constant;
import com.xiaoka.camelv.entity.CamelvGroovy;
import com.xiaoka.camelv.entity.CamelvRoute;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;

/**
 * 执行脚本
 * 
 * @author xiaoka
 *
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
		CamelvRoute route = CamelvContext.getCamelvRoute(routeId);
		if (route == null) {
			exchange.setProperty(Constant.ROUTE_STATE, RouteState.ROUTE_DELETED);
			throw new Exception("该路由资源被删除了,该路由应该从camel中删除的,routeId=" + routeId);
		}
		String relatedResourceId = route.getRelatedResourceId();
		// 是否未配置资源
		if (StringUtils.isBlank(route.getRelatedResourceId())) {
			exchange.setProperty(Constant.ROUTE_STATE, RouteState.RESOURCE_NOT_CONFIG);
			throw new Exception("该路由未配置Groovy资源,请先配置,routeId=" + routeId);
		}
		CamelvGroovy groovy = CamelvContext.getCamelvGroovy(route.getRelatedResourceId());
		if (groovy == null) {
			exchange.setProperty(Constant.ROUTE_STATE, RouteState.RESOURCE_NOT_FOUND);
			throw new Exception("该路由配置的groovy资源找不到,routeId=" + routeId);
		}
		if (StringUtils.isBlank(groovy.getScript())) {
			exchange.setProperty(Constant.ROUTE_STATE, RouteState.GROOVY_SCRIPT_NULL);
			throw new Exception("groovy脚本为空,groovy.id=" + groovy.getId());
		}
		// 执行脚本
		try {
			Class<?> groovyClass = null;
			if (CamelvContext.getGroovyClass(relatedResourceId) != null) {
				logger.info("***从缓存中获取 " + relatedResourceId + " 脚本的Class");
				groovyClass = CamelvContext.getGroovyClass(relatedResourceId);
			} else {
				ClassLoader parent = null;
				if (parent == null) {
					logger.error("***parent is null ***");
					parent = Thread.currentThread().getContextClassLoader();
					// CamelvGroovyUtil.class.getClassLoader();
				}
				@SuppressWarnings("resource")
				GroovyClassLoader loader = new GroovyClassLoader(parent);
				groovyClass = loader.parseClass(groovy.getScript());
				CamelvContext.addGroovyClass(relatedResourceId, groovyClass);
			}
			// 向groovy脚本中绑定变量值
			Binding binding = new Binding();
			String exchange_name = Global.getConfig("camelv.groovy.exchange.name");
			binding.setVariable(exchange_name, exchange);
			Script script = InvokerHelper.createScript(groovyClass, binding);
			// 执行脚本
			Object result = script.run();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			exchange.setProperty(Constant.ROUTE_STATE, RouteState.GROOVY_SCRIPT_ERROR);
			throw new Exception("groovy脚本执行失败,groovy.id=" + groovy.getId());
		}
	}
}
