package com.funny.study.camel.process;

import com.alibaba.fastjson.JSON;
import com.funny.study.camel.user.entity.UserWxInfoEntity;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

@Service
public class ReturnStockComponent extends AbstractProcessorComponent<UserWxInfoEntity> implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        UserWxInfoEntity userWxInfoEntity = getRouteData(exchange);
        String routeId = exchange.getFromRouteId();
        System.out.println(routeId + ":" + JSON.toJSONString(userWxInfoEntity) + ":return stock success");
    }
}
