package com.funny.demo.camel.bean;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReduceService {

    public void reduceStore(Exchange exchange){
        System.out.println("reduceStore:" + exchange.getIn().getBody()+"fromRouteId="+exchange.getFromRouteId());
    }


    public void reducePoints(Exchange exchange){
        System.out.println("reducePoints:" + exchange.getIn().getBody()+"fromRouteId="+exchange.getFromRouteId());
    }
}
