package com.funny.admin.camel.component.https;

import com.funny.admin.camelv.constant.Constant;
import org.apache.camel.Component;
import org.apache.camel.Exchange;
import org.apache.camel.component.ResourceEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpsEndpoint extends ResourceEndpoint {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@UriParam
	private String relatedResourceId;// 关联的资源id

	@UriParam
	private String requestType;// 请求类型:get、post

	public HttpsEndpoint(String uri, Component component, String remaining) {
		super(uri, component, remaining);
	}

	@Override
	protected void onExchange(Exchange exchange) {
		logger.info("execute https ");
		String url = "";
		String oldBody = exchange.getIn().getBody(String.class);
		// 设置header
		Map<String, Object> headers = exchange.getIn().getHeaders();
		Map<String, String> headerMap = new HashMap<String, String>();
		Set<Entry<String, Object>> entrySet = headers.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			if (!StringUtils.isBlank(entry.getKey())) {
				Object v = entry.getValue();
				headerMap.put(entry.getKey(), v == null ? null : v.toString());
			}
		}
		String resultBody = "";
		// 判断请求类型
		if (Constant.HTTP_GET.equals(requestType)) {
//			resultBody = RequestHelper.doGet(url, headerMap);
		} else if (Constant.HTTP_POST.equals(requestType)) {
			String contentType = exchange.getIn().getHeader("Content-Type", String.class);
			contentType = contentType == null ? "text/plain;charset=utf-8" : contentType;
//			resultBody = RequestHelper.doPost(url, contentType, headerMap, oldBody);
		}
		// 设置结果到body中
		exchange.setOut(exchange.getIn());
		exchange.getOut().setBody(resultBody, String.class);
	}

	public String getRelatedResourceId() {
		return relatedResourceId;
	}

	public void setRelatedResourceId(String relatedResourceId) {
		this.relatedResourceId = relatedResourceId;
	}
}
