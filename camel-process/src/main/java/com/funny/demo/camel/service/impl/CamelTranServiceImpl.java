package com.funny.demo.camel.service.impl;

import com.funny.demo.camel.service.CamelTranService;
import com.funny.demo.camel.dao.DocInfoMapper;
import com.funny.demo.camel.entity.DocInfoEntity;
import com.funny.demo.camel.dao.UserWxInfoMapper;
import com.funny.demo.camel.entity.UserWxInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CamelTranServiceImpl implements CamelTranService {
    @Resource
    private DocInfoMapper docInfoMapper;

    @Resource
    private UserWxInfoMapper userWxInfoMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser() {
        UserWxInfoEntity userWxInfoEntity = new UserWxInfoEntity();
        userWxInfoEntity.setOpenId("123");
        userWxInfoEntity.setUserId(123L);
        userWxInfoMapper.insertSelective(userWxInfoEntity);

        DocInfoEntity docInfoEntity = new DocInfoEntity();
        docInfoEntity.setDocUserId(12312L);
        docInfoMapper.insertSelective(docInfoEntity);
        return 2;
    }
}
