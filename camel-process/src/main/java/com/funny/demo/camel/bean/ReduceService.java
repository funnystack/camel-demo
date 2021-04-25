package com.funny.demo.camel.bean;

import com.alibaba.fastjson.JSON;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReduceService {

    public void reduceStore(Exchange exchange){
        Map<String, Object> paramMap =  exchange.getIn().getHeaders();
        System.out.println("reduceStore:" + exchange.getIn().getBody()+",fromRouteId="+exchange.getFromRouteId()+",params="+ JSON.toJSONString(paramMap));
    }


    public void reducePoints(Exchange exchange){
        Map<String, Object> paramMap =  exchange.getIn().getHeaders();
        System.out.println("reducePoints:" + exchange.getIn().getBody()+",fromRouteId="+exchange.getFromRouteId()+",params="+ JSON.toJSONString(paramMap));
    }
}
