package com.example.demo.http.processer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        System.out.println("received order create message");

    }
}
