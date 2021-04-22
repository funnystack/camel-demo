package com.funny.admin.camel.bean;

import java.util.Random;

import com.funny.admin.camelv.constant.Constant;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 测试
 * 
 * @author xiaoka
 *
 */
public class TestBean {

	private static Logger logger = LoggerFactory.getLogger(TestBean.class);

	/**
	 * 测试,返回随机码
	 * 
	 * @param exchange
	 * @return
	 */
	public void getCode(Exchange exchange, String msg) {
		logger.info("接收到的消息：\n" + msg);
		Random random = new Random();
		int val = random.nextInt(1000);
		logger.info("随机码：" + val);
		exchange.setOut(exchange.getIn());
		exchange.getOut().setBody(val, String.class);
	}

	public String testA(Exchange exchange) {
		String msg = "testA执行...";
		logger.info(msg);
		exchange.setProperty(Constant.NEXT_URI, "direct:testB,direct:testC");
		return msg;
	}

	public String testB(Exchange exchange) {
		String msg = "testB执行...";
		logger.info(msg);
		exchange.setProperty(Constant.NEXT_URI, "direct:testD");
		return msg;
	}

	public String testC(Exchange exchange) throws InterruptedException {
		String msg = "testC执行...抛出异常";
		exchange.setProperty(Constant.NEXT_URI, "direct:testD");
		logger.info(msg);
		Thread.sleep(1000);
		int a = 0 / 0;
		return msg;
	}

	public String testD(Exchange exchange) {
		String msg = "testD执行...";
		logger.info(msg);
		return msg;
	}

	public String testE(Exchange exchange) {
		String msg = "testE执行异常处理...";
		logger.info(msg);
		return msg;
	}

}
