package com.funny.demo.camel.controller;

import com.alibaba.fastjson.JSON;
import com.funny.demo.camel.entity.UserWxInfoEntity;
import com.funny.demo.camel.service.CamelTranService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RestController
public class HealthController {

    @Autowired(required = false)
    private CamelContext camelContext;
    @Resource
    private CamelTranService camelTranService;

    @GetMapping("/health/status")
    public String health() {
        return "ok";
    }


    @GetMapping("/health/cancel")
    public String cancelOrderContext() {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:cancel", "20151020313");
        return "ok";
    }

    @GetMapping("/health/close")
    public String closeOrderContext() {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        UserWxInfoEntity userWxInfoEntity = new UserWxInfoEntity();
        userWxInfoEntity.setOpenId("20142912312312");
        producerTemplate.sendBody("direct:close", userWxInfoEntity);
        return "ok";
    }

    @GetMapping("/health/tran")
    public String tranTest() {
        camelTranService.addUser();
        return "ok";
    }

    @GetMapping("/health/bean")
    public String bean() {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:route-bean1", "123456");
        return "ok";
    }

    @GetMapping("/health/rpc")
    public String http() {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("docId", "123456");
        producerTemplate.sendBody("direct:route-http1", JSON.toJSONString(paraMap));
        return "ok";
    }
}
