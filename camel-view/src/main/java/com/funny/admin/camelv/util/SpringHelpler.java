package com.funny.admin.camelv.util;

import com.funny.admin.config.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;


@Component
public class SpringHelpler {

	private static final Logger logger = LoggerFactory.getLogger(SpringHelpler.class);

	public ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();

	/**
	 * 根据对象id销毁spring容器中的对象
	 *
	 * @param beanId
	 * @return
	 */
	public boolean destroy(String beanId) {

		try {
			DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
					.getAutowireCapableBeanFactory();
			beanFactory.removeBeanDefinition(beanId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean exist(String beanId) {
		try {
			Object object = applicationContext.getBean(beanId);
			return object == null ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean regBean(String param, String beanId) {
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
				.getAutowireCapableBeanFactory();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<beans xmlns=\"http://www.springframework.org/schema/beans\""
				+ "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
				+ "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
				+ "       \">";
		if (param != null && !"".equals(param)) {
			xml += param;
		}
		xml += "</beans>";
		boolean flag = false;
		XmlBeanFactory factory = new XmlBeanFactory(new ByteArrayResource(xml.getBytes()));
		try {
			beanFactory.registerBeanDefinition(beanId, factory.getMergedBeanDefinition(beanId));
			Object obj = SpringContextHolder.getBean(beanId);
			logger.info("注册bean成功:" + param + ",class:" + obj.getClass().getName());
		} catch (Exception e) {
			logger.error("注册bean失败：" + param);
			flag = false;
			try {
				beanFactory.removeBeanDefinition(beanId);
			} catch (Exception e1) {
				System.out.println("销毁bean失败:" + param);
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		flag = true;
		return flag;
	}
}
