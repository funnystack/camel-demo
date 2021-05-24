package com.example.demo.http.router;

import com.example.demo.http.processer.GetMethodProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HttpRoute extends RouteBuilder {
    @Resource
    private GetMethodProcessor getMethodProcessor;

    @Override
    public void configure() throws Exception {
        from("http://0.0.0.0:80").process(getMethodProcessor);

    }
}
