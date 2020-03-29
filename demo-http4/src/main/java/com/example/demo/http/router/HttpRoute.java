package com.example.demo.http.router;

import com.example.demo.http.processer.GetMethodProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpRoute extends RouteBuilder {
    @Autowired
    private GetMethodProcessor getMethodProcessor;

    @Override
    public void configure() throws Exception {
        from("http4:http://0.0.0.0:80").process(getMethodProcessor);

    }
}
