package com.example.demohttp.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:D://data1?noop=true").to("file:D://data2");
    }
}
