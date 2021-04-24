package com.funny.demo.camel.bean;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class RollBackService {

    public void rollBackStore(Exchange exchange){
        System.out.println("rollBackStore:"+ exchange.getIn().getBody()+"fromRouteId="+exchange.getExchangeId());
    }


    public void rollBackPoints(Exchange exchange){
        System.out.println("rollBackPoints:"+ exchange.getIn().getBody() +"fromRouteId="+exchange.getExchangeId());
    }
}
