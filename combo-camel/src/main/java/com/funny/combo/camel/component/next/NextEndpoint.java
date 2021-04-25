package com.funny.combo.camel.component.next;

import com.funny.combo.camel.component.exception.RouteState;
import com.funny.combo.camel.consts.Constant;
import com.funny.combo.camel.context.CamelvContext;
import com.funny.combo.camel.entity.CamelvRoute;
import org.apache.camel.Component;
import org.apache.camel.Exchange;
import org.apache.camel.component.ResourceEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 动态设置路由下一个需要执行的地址<br/>
 * URI Format格式<br/>
 * next:routeType?routeId=XXX<br/>
 *
 * @author xiaoka
 *
 */
public class NextEndpoint extends ResourceEndpoint {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@UriParam
	private String routeId;

	public NextEndpoint(String uri, Component component, String remaining) {
		super(uri, component, remaining);
	}

	@Override
	protected void onExchange(Exchange exchange) throws Exception {
		logger.info("设置下一个路由地址,type = " + getResourceUri() + ",exchangeId = " + exchange.getExchangeId());
		/** 获取路由类型 */
		String routeType = getResourceUri();
		// 获取下一个路由地址
		String nextUri = "", type = "";
		CamelvRoute route = CamelvContext.getCamelvRoute(routeId);
		if (route == null) {
			exchange.setProperty(Constant.ROUTE_STATE, RouteState.ROUTE_DELETED);
			throw new Exception("路由已经被删除,id = " + routeId);
		}
		/** 获取该路由的后续路由id信息，根据该id集合个数来判断执行策略 */
		List<String> toList = route.getTo();
		int toSize = (toList == null || toList.size() == 0) ? 0 : toList.size();
		if (toSize == 0) {
			type = "doNothing"; // 没有配置后续路由或执行结束
		}
		if (toSize == 1) {
			String nextRouteId = toList.get(0);
			CamelvRoute toRoute = CamelvContext.getCamelvRoute(nextRouteId);
			// 鉴别是否是分支路由
			String exchangeId = exchange.getExchangeId();
			String original_exchange_id = exchange.getProperty(Constant.ORIGINAL_EXCHANGE_ID, String.class);
			// 后续路由是一个聚合路由,并且exchangeId不相等，那么认为是并行聚合,否则认为是串行或分支聚合
			if (toRoute.getAggregat() && StringUtils.isNoneBlank(original_exchange_id) &&!exchangeId.equals(original_exchange_id)) {
				type = "parallelAggregate";// 并行聚合
			} else {
				type = "serial";//分支聚合或串行
			}
			nextUri = Constant.DIRECT + Constant.PREFFIX_ROUTE_ID + nextRouteId;
		}
//		/**
//		 * 后续路由只有一个<br/>
//		 * 1.可能是串行<br/>
//		 * 2.可能是并行聚合<br/>
//		 * 3.可能是分支聚合<br/>
//		 */
//		if(route.getCondition() != null && route.getCondition().size() > 0) {
//			/** 分支路由 */
//			type = "branch";
//			/**
//			 * 条件匹配:<br/>
//			 * 由header中的condition参数来匹配line.name参数<br/>
//			 * 未匹配成功的抛出异常<br/>
//			 */
//			/** 参数名字，可配置. TODO 这里 */
//			String conditonKey = "condition";
//			String condition = exchange.getIn().getHeader(conditonKey, String.class);
//			/** 保证分支携带了条件 */
//			if (StringUtils.isBlank(condition)) {
//				exchange.setProperty(Constant.ROUTE_STATE, RouteState.CONDITION_IS_NULL);
//				throw new Exception("分支路由执行时条件为空");
//			}
//			/** 进行条件匹配 */
//			Map<String, String> conditionMap = route.getCondition();
//			if (conditionMap.get(condition) == null) {
//				exchange.setProperty(Constant.ROUTE_STATE, RouteState.CONDITION_NOT_MATCH);
//				throw new Exception("分支路由条件匹配失败");
//			}
//			/** 这里用于在聚合时判断使用 */
//			exchange.setProperty(Constant.ORIGINAL_EXCHANGE_ID, exchange.getExchangeId());
//			// 设置地址
//			nextUri = Constant.DIRECT + Constant.PREFFIX_ROUTE_FROM_URI + conditionMap.get(condition);
//		} else {
//			/** 并行路由 */
//			type = "parallel";
//			int size = toList.size();
//			for (int i = 0; i < size; i++) {
//				nextUri += Constant.DIRECT + Constant.PREFFIX_ROUTE_FROM_URI + toList.get(i);
//				if (i != size - 1) {
//					// 添加分隔符
//					nextUri += Constant.RECIPIENT_LIST_DELIMITER;
//				}
//			}
//			/** 这里用于在聚合时判断使用 */
//			exchange.setProperty(Constant.ORIGINAL_EXCHANGE_ID, exchange.getExchangeId());
//		}
		// 设置执行类型
		exchange.setProperty("type", type);
		// 设置下一个地址
		exchange.setProperty(Constant.NEXT_URI, nextUri);
		logger.info("type:" + type + " , nextUri = " + nextUri);
		exchange.setOut(exchange.getIn());
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
}
