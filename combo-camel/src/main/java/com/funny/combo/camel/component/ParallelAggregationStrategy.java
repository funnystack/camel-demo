package com.funny.combo.camel.component;

import com.funny.combo.camel.consts.Constant;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 并行聚合策略
 *
 * @author xiaoka
 *
 */
public class ParallelAggregationStrategy implements AggregationStrategy {

	private static Logger logger = LoggerFactory.getLogger(ParallelAggregationStrategy.class);

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		logger.info("并行聚合...");
		if (oldExchange == null) {
			logger.info("oldExchange为空返回newExchange," + Constant.NEXT_URI + ":" + newExchange.getProperty(Constant.NEXT_URI)+" , exchangeId = "+newExchange.getExchangeId());
			return newExchange;
		}
		if (newExchange == null) {
			logger.error(
					"newExchange the newest exchange (can be <tt>null</tt> if there was no data possible to acquire 请检查.");
			return null;
		}
		logger.info("newExchange," + Constant.NEXT_URI + ":" + newExchange.getProperty(Constant.NEXT_URI)+" , exchangeId = "+newExchange.getExchangeId());
		String nextUri = newExchange.getProperty(Constant.NEXT_URI,String.class);
		oldExchange.setProperty(Constant.NEXT_URI,nextUri==null?"":nextUri);
		return oldExchange;
	}
}
