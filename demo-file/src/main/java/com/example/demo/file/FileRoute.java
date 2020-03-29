package com.example.demo.file;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("file:D://data1?noop=true").to("file:D://data2");

        from("file:/Users/fangli/input?noop=true").to("file:/Users/fangli/output");

    }
}
