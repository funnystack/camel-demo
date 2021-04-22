package com.funny.study.camel.router;

import com.funny.study.camel.process.ReturnPointsComponent;
import com.funny.study.camel.process.ReturnStockComponent;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:hello")
                .routeId("close_order")
                .bean(ReturnPointsComponent.class)
                .bean(ReturnStockComponent.class)
                .to("log:bar");
    }
}
