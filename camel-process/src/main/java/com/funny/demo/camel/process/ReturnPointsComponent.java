package com.funny.demo.camel.process;

import com.alibaba.fastjson.JSON;
import com.funny.demo.camel.entity.UserWxInfoEntity;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

@Service
public class ReturnPointsComponent extends AbstractProcessorComponent<UserWxInfoEntity> implements Processor {
//    @Resource
//    private DocInfoMapper docInfoMapper;
//
//    @Resource
//    private UserWxInfoMapper userWxInfoMapper;
//
//    @Resource
//    private CamelTranService camelTranService;


    @Override
    public void process(Exchange exchange) throws Exception {
        UserWxInfoEntity userWxInfoEntity = getRouteData(exchange);
        String routeId = exchange.getFromRouteId();
        System.out.println(routeId + ":" + JSON.toJSONString(userWxInfoEntity) + ":return points success");

//        UserWxInfoEntity userWxInfoEntity = new UserWxInfoEntity();
//        userWxInfoEntity.setOpenId("123");
//        userWxInfoEntity.setUserId(123L);
//        userWxInfoMapper.insertSelective(userWxInfoEntity);
//
//        DocInfoEntity docInfoEntity = new DocInfoEntity();
//        docInfoEntity.setDocUserId(123L);
//        docInfoMapper.insertSelective(docInfoEntity);


//        camelTranService.addUser();


    }
}
