package com.funny.study.camel.controller;

import com.funny.study.camel.service.CamelTranService;
import com.funny.study.camel.user.entity.UserWxInfoEntity;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 */
@RestController
public class HealthController {

    @Resource
    private CamelContext cancelOrderContext;
    @Resource
    private CamelContext closeOrderContext;
    @Resource
    private CamelTranService camelTranService;

    @GetMapping("/health/status")
    public String health() {
        return "ok";
    }


    @GetMapping("/health/cancel")
    public String cancelOrderContext() {
        ProducerTemplate producerTemplate = cancelOrderContext.createProducerTemplate();
        producerTemplate.sendBody("direct:cancel", "20151020313");
        return "ok";
    }

    @GetMapping("/health/close")
    public String closeOrderContext() {
        ProducerTemplate producerTemplate = closeOrderContext.createProducerTemplate();
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
}
