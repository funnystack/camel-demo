package com.example.demo.http.processer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class PostMethodProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("received order cancel message");
        String userId = (String) exchange.getIn().getHeader("userId");
        System.out.println("userId="+userId);
        exchange.getOut().setBody("<html><body>router is good</body></html>");
    }
}
