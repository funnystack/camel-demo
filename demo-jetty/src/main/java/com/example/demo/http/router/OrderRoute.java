package com.example.demo.http.router;

import com.example.demo.http.processer.OrderCreateProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRoute extends RouteBuilder {

    @Autowired
    private OrderCreateProcessor postMethodProcessor;

    @Override
    public void configure() throws Exception {
        from("jetty:http://localhost:8080/order/create").process(postMethodProcessor);

    }
}
