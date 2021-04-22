package com.funny.admin.camel.context;

import com.funny.admin.camelv.constant.RouteType;
import com.funny.admin.camelv.entity.*;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.xiaoka.camelv.constant.RouteType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 存储camel运行需要的上下文等信息
 * 
 * @author xiaoka
 *
 */
public class CamelvContext {

	private static final Logger logger = LoggerFactory.getLogger(CamelvContext.class);

	/** 记录发布成功的路由,可用于回滚,key:服务id,此id非持久化id,value:一个服务关联的多个路由id */
	private static ConcurrentMap<String, List<String>> publishedRouteMap = new ConcurrentHashMap<String, List<String>>();
	/** 记录groovy脚本编译的class,key:grovy脚本的hashcode值，value:groovy脚本编译后的class */
	private static ConcurrentMap<String, Class<?>> groovyClassMap = new ConcurrentHashMap<String, Class<?>>();
	/** 记录groovy脚本，key:脚本id,value：实体对象 */
	private static ConcurrentMap<String, CamelvGroovy> camelvGroovyMap = new ConcurrentHashMap<String, CamelvGroovy>();
	/** 记录ftp信息，key:id,value：实体对象 */
	private static ConcurrentMap<String, CamelvFtp> camelvFtpMap = new ConcurrentHashMap<String, CamelvFtp>();
	/** 记录主机信息，key:id,value：实体对象 */
	private static ConcurrentMap<String, CamelvHost> camelvHostMap = new ConcurrentHashMap<String, CamelvHost>();
	/** 记录http信息，key:id,value：实体对象 */
	private static ConcurrentMap<String, CamelvHttp> camelvHttpMap = new ConcurrentHashMap<String, CamelvHttp>();
	/** 记录jdbc信息，key:id,value：实体对象 */
	private static ConcurrentMap<String, CamelvJdbc> camelvJdbcMap = new ConcurrentHashMap<String, CamelvJdbc>();
	/** 记录Mail信息，key:id,value：实体对象 */
	private static ConcurrentMap<String, CamelvMail> camelvMailMap = new ConcurrentHashMap<String, CamelvMail>();
	/** 记录队列信息，key:id,value：实体对象 */
	private static ConcurrentMap<String, CamelvQueue> camelvQueueMap = new ConcurrentHashMap<String, CamelvQueue>();
	/** 记录路由信息，key:id,value：实体对象 */
	private static ConcurrentMap<String, CamelvRoute> camelvRouteMap = new ConcurrentHashMap<String, CamelvRoute>();
	/** 记录添加的服务，key:服务id,value:服务实体 */
	private static ConcurrentMap<String, CamelvServer> camelvServerMap = new ConcurrentHashMap<String, CamelvServer>();

	/**
	 * 从内存中删除对象
	 * 
	 * @param id
	 */
	public static void deleteCamelvGroovy(String id) {
		camelvGroovyMap.remove(id);
	}

	/**
	 * 从内存中删除对象
	 * 
	 * @param id
	 */
	public static void deleteCamelvFtp(String id) {
		camelvFtpMap.remove(id);
	}

	/**
	 * 从内存中删除对象
	 * 
	 * @param id
	 */
	public static void deleteCamelvHost(String id) {
		camelvHostMap.remove(id);
	}

	/**
	 * 从内存中删除对象
	 * 
	 * @param id
	 */
	public static void deleteCamelvHttp(String id) {
		camelvHttpMap.remove(id);
	}

	/**
	 * 从内存中删除对象
	 * 
	 * @param id
	 */
	public static void deleteCamelvJdbc(String id) {
		camelvJdbcMap.remove(id);
	}

	/**
	 * 从内存中删除对象
	 * 
	 * @param id
	 */
	public static void deleteCamelvMail(String id) {
		camelvMailMap.remove(id);
	}

	/**
	 * 从内存中删除对象
	 * 
	 * @param id
	 */
	public static void deleteCamelvQueue(String id) {
		camelvQueueMap.remove(id);
	}

	/**
	 * 从内存中删除对象
	 * 
	 * @param id
	 */
	public static void deleteCamelvRoute(String id) {
		camelvRouteMap.remove(id);
	}

	/**
	 * 根据服务id获取关联的发布成功的路由id集合<br/>
	 * 
	 * @param serverId
	 * @return
	 */
	public static List<String> getPublishedRoutes(String serverId) {
		return publishedRouteMap.get(serverId);
	}

	/**
	 * 添加指定的服务和与之关联的路由id集合
	 * 
	 * @param serverId
	 * @param routes
	 */
	public static void addPublishedRoutes(String serverId, List<String> routes) {
		publishedRouteMap.put(serverId, routes);
	}

	/**
	 * 添加groovy编译后的脚本
	 * 
	 * @param groovyHashCode
	 * @param clazz
	 */
	public static void addGroovyClass(String groovyHashCode, Class<?> clazz) {
		groovyClassMap.put(groovyHashCode, clazz);
	}

	/**
	 * 根据groovy脚本的hashcode值获取编译后的class
	 * 
	 * @param groovyHashCode
	 * @return
	 */
	public static Class<?> getGroovyClass(String groovyHashCode) {
		return groovyClassMap.get(groovyHashCode);
	}

	/**
	 * 移除脚本class
	 * 
	 * @param id
	 */
	public static void deleteGroovyClass(String id) {
		groovyClassMap.remove(id);
	}

	/**
	 * 根据id获取内存中缓存的groovy对象
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvGroovy getCamelvGroovy(String id) {
		return camelvGroovyMap.get(id);
	}

	/**
	 * 添加groovy到内存中
	 * 
	 * @param camelvGroovy
	 */
	public static void addCamelvGroovy(CamelvGroovy camelvGroovy) {
		camelvGroovyMap.put(camelvGroovy.getId()+"", camelvGroovy);
	}

	/**
	 * 根据id获取内存中缓存的对象
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvFtp getCamelvFtp(String id) {
		return camelvFtpMap.get(id);
	}

	/**
	 * 添加对象到内存中
	 * 
	 * @param camelvFtp
	 */
	public static void addCamelvFtp(CamelvFtp camelvFtp) {
		camelvFtpMap.put(camelvFtp.getId()+"", camelvFtp);
	}

	/**
	 * 根据id获取内存中缓存的对象
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvHost getCamelvHost(String id) {
		return camelvHostMap.get(id);
	}

	/**
	 * 添加对象到内存中
	 * 
	 * @param camelvHost
	 */
	public static void addCamelvHost(CamelvHost camelvHost) {
		camelvHostMap.put(camelvHost.getId()+"", camelvHost);
	}

	/**
	 * 根据id获取内存中缓存的对象
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvHttp getCamelvHttp(String id) {
		return camelvHttpMap.get(id);
	}

	/**
	 * 添加对象到内存中
	 * 
	 * @param camelvHttp
	 */
	public static void addCamelvHttp(CamelvHttp camelvHttp) {
		camelvHttpMap.put(camelvHttp.getId()+"", camelvHttp);
	}

	/**
	 * 根据id获取内存中缓存的对象
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvJdbc getCamelvJdbc(String id) {
		return camelvJdbcMap.get(id);
	}

	/**
	 * 添加对象到内存中
	 * 
	 * @param camelvJdbc
	 */
	public static void addCamelvJdbc(CamelvJdbc camelvJdbc) {
		camelvJdbcMap.put(camelvJdbc.getId(), camelvJdbc);
	}

	/**
	 * 根据id获取内存中缓存的对象
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvMail getCamelvMail(String id) {
		return camelvMailMap.get(id);
	}

	/**
	 * 添加对象到内存中
	 * 
	 * @param camelvMail
	 */
	public static void addCamelvMail(CamelvMail camelvMail) {
		camelvMailMap.put(camelvMail.getId(), camelvMail);
	}

	/**
	 * 根据id获取内存中缓存的对象
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvQueue getCamelvQueue(String id) {
		return camelvQueueMap.get(id);
	}

	/**
	 * 添加对象到内存中
	 * 
	 * @param camelvQueue
	 */
	public static void addCamelvQueue(CamelvQueue camelvQueue) {
		camelvQueueMap.put(camelvQueue.getId(), camelvQueue);
	}

	/**
	 * 添加服务
	 * 
	 * @param server
	 */
	public static void addCamelvServer(CamelvServer server) {
		camelvServerMap.put(server.getId(), server);
	}

	/**
	 * 删除服务
	 * 
	 * @param id
	 */
	public static void deleteCamelvServer(String id) {
		camelvServerMap.remove(id);
	}

	/**
	 * 获取服务信息
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvServer getCamelvServer(String id) {
		return camelvServerMap.get(id);
	}

	/**
	 * 根据id获取内存中缓存的对象
	 * 
	 * @param id
	 * @return
	 */
	public static CamelvRoute getCamelvRoute(String id) {
		return camelvRouteMap.get(id);
	}

	/**
	 * 根据服务id获取关联的路由信息
	 * 
	 * @param serverId
	 * @return
	 */
	public static List<CamelvRoute> getCamelvRouteByServerId(String serverId) {
		List<CamelvRoute> list = new ArrayList<CamelvRoute>();
		Set<Entry<String, CamelvRoute>> entrySet = camelvRouteMap.entrySet();
		for (Entry<String, CamelvRoute> entry : entrySet) {
			if (entry.getValue().getServerId().equals(serverId)) {
				list.add(entry.getValue());
			}
		}
		return list;
	}

	/**
	 * 添加对象到内存中,改变路由关系时调用
	 * 
	 * @param camelvRoute
	 */
	public static void addCamelvRoute4Line(CamelvRoute camelvRoute) {
		CamelvRoute route = camelvRouteMap.get(camelvRoute.getId());
		if (route != null) {
			// 不创建新对象
			route.setOption(camelvRoute.getOption());
			route.setName(camelvRoute.getName());
			route.setUri(camelvRoute.getUri());
			route.setRelatedResourceId(camelvRoute.getRelatedResourceId());

			route.setAggregat(camelvRoute.getAggregat());
			route.setTo(camelvRoute.getTo());
			route.setCondition(camelvRoute.getCondition());
			route.setExceptionId(camelvRoute.getExceptionId());

		} else {
			camelvRouteMap.put(camelvRoute.getId()+"", camelvRoute);
		}
	}

	/**
	 * 添加对象到内存中，改变路由属性时调用
	 * 
	 * @param camelvRoute
	 */
	public static void addCamelvRoute4Route(CamelvRoute camelvRoute) {
		CamelvRoute route = camelvRouteMap.get(camelvRoute.getId());
		if (route != null) {
			// 不创建新对象
			route.setOption(camelvRoute.getOption());
			route.setName(camelvRoute.getName());
			route.setUri(camelvRoute.getUri());
			route.setRelatedResourceId(camelvRoute.getRelatedResourceId());
		} else {
			camelvRouteMap.put(camelvRoute.getId()+"", camelvRoute);
		}
	}

	/**
	 * 添加对应到内存中,路由为异常时触发
	 * 
	 * @param camelvRoute
	 */
	public static void addCamelvRoute4Exception(CamelvRoute camelvRoute) {
		addCamelvRoute4Route(camelvRoute);
		// 修改服务关联的异常
		CamelvServer server = camelvServerMap.get(camelvRoute.getServerId());
		if (server != null) {
			server.setExceptionId(camelvRoute.getId()+"");
		}
	}

	/**
	 * 添加路由关系
	 * 
	 * @param line
	 */
	public static void addCamelvRouteLine(CamelvLine line) {
		/**
		 * 思路:<br/>
		 * 1.每一条关系都唯一确认一个路由来源 from<br/>
		 * 2.根据来源from来修改，from指定的路由的下一个需要指定的地址<br/>
		 * 3.修改from指定的路由到达下一个路由的条件
		 */
		CamelvRoute route = camelvRouteMap.get(line.getFrom());
		if (route == null) {
			logger.error("更新路由 id = " + line.getFrom() + "关系失败,已经被删除.");
			return;
		}
		List<String> toList = route.getTo();
		if (toList == null) {
			toList = new ArrayList<String>();
			route.setTo(toList);
		}
		if (!toList.contains(line.getTo())) {
			// 记录本路由下一个需要执行的路由信息
			toList.add(line.getTo());
			// 添加执行条件
			Map<String, String> condition = route.getCondition();
			if (condition == null) {
				condition = new HashMap<String, String>();
				route.setCondition(condition);
			}
			if (!StringUtils.isBlank(line.getName())) {
				// 记录条件
				condition.put(line.getName(), line.getTo());
			}
		}
		/**
		 * 思路:<br/>
		 * 1.每一条关系都唯一确认一个路由去向 to<br/>
		 * 2.根据去向 to 来修改，to 指定的路由的下一个需要执行的地址<br/>
		 * 3.添加下一个路由的前一个路由索引
		 */
		CamelvRoute toRoute = camelvRouteMap.get(line.getTo());
		List<String> fromList = toRoute.getFrom();
		if (fromList == null) {
			fromList = new ArrayList<String>();
			toRoute.setFrom(fromList);
		}
		if (!fromList.contains(line.getFrom())) {
			fromList.add(line.getFrom());
		}
	}

	/**
	 * 删除路由关系
	 * 
	 * @param from
	 * @param to
	 */
	public static void deleteCamelvRouteLine(String from, String to) {

		CamelvRoute route = camelvRouteMap.get(from);
		if (route != null) {
			List<String> toList = route.getTo();
			if (toList != null && toList.contains(to)) {
				toList.remove(to);
			}
			// 删除条件
			Map<String, String> condition = route.getCondition();
			if (condition != null) {
				String key = null;
				Set<Entry<String, String>> entrySet = condition.entrySet();
				for (Entry<String, String> entry : entrySet) {
					if (entry.getValue().equals(to)) {
						key = entry.getKey();
						break;
					}
				}
				if (key != null) {
					condition.remove(key);
				}
			}
		}
		CamelvRoute toRoute = camelvRouteMap.get(to);
		if (toRoute != null) {
			List<String> fromList = toRoute.getFrom();
			if (fromList != null && fromList.contains(from)) {
				fromList.remove(from);
			}
		}
	}

	/**
	 * 修改指定路由执行的条件
	 * 
	 * @param from
	 * @param to
	 * @param newName
	 */
	public static void updateCamelvRouteLineName(String from, String to, String newName) {
		CamelvRoute route = camelvRouteMap.get(from);
		if (route == null) {
			logger.error("修改路由执行条件失败,路由id=" + from + "已删除.");
			return;
		}
		Map<String, String> condition = route.getCondition();
		boolean exist = false;
		String key = null;
		if (condition != null) {
			Set<Entry<String, String>> entrySet = condition.entrySet();
			for (Entry<String, String> entry : entrySet) {
				if (entry.getValue().equals(to)) {
					exist = true;
					key = entry.getKey();
					break;
				}
			}
			if (exist) {
				// 删除
				condition.remove(key);
				if (!StringUtils.isBlank(newName)) {
					condition.put(newName, to);
				}
			} else {
				condition.put(newName, to);
			}
		} else {
			condition = new HashMap<String, String>();
			condition.put(newName, to);
		}
	}

	/**
	 * 根据uri地址获取关联的路由
	 * 
	 * @param uri
	 * @return
	 */
	public static CamelvRoute getRouteByJettyServletUri(String uri) {
		if (StringUtils.isBlank(uri)) {
			return null;
		}
		Set<Entry<String, CamelvRoute>> entrySet = camelvRouteMap.entrySet();
		for (Entry<String, CamelvRoute> entry : entrySet) {
			CamelvRoute route = entry.getValue();
			if (RouteType.ROUTE_TYPE_JETTY.equals(route.getType()) && route.getUri().equals(uri)) {
				return route;
			}
		}
		return null;
	}

	/**
	 * 解析xml注册到spring中
	 * 
	 * @param jdbc
	 * @return
	 */
	public static String getJdbcBeanXml(CamelvJdbc jdbc) {
		StringBuffer sb = new StringBuffer();

		return sb.toString();
	}

	/**
	 * 解析xml注册到spring中
	 * 
	 * @param queue
	 * @return
	 */
	public static String getQueueBeanXml(CamelvQueue queue) {
		StringBuffer sb = new StringBuffer();

		return sb.toString();
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("AAA");
		String name = new String("AAA");
		System.out.println(list.contains(name));
	}
}
