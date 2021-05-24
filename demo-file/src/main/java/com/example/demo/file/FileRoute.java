package com.example.demo.file;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("file:D://camel//in?noop=true")
                .to("file:D://camel//output");

    }
}
