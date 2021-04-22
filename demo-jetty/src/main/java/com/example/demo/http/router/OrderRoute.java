package com.example.demo.http.router;

import com.example.demo.http.processer.OrderCreateProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderRoute extends RouteBuilder {

    @Resource
    private OrderCreateProcessor postMethodProcessor;

    @Override
    public void configure() throws Exception {
        from("jetty:http://localhost:8080/order/create").process(postMethodProcessor);

    }
}
