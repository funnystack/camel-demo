package com.example.demo.http.processer;

import com.alibaba.fastjson.JSON;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GetMethodProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("received get message");
        HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);

        System.out.println(JSON.toJSONString(request.getCookies()));

        System.out.println(JSON.toJSONString(request.getHeaderNames()));

        System.out.println(JSON.toJSONString(request.getParameterMap()));

        HttpServletResponse response = exchange.getIn().getBody(HttpServletResponse.class);

    }
}
