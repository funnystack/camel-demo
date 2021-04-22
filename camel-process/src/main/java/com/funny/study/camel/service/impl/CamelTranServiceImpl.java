package com.funny.study.camel.service.impl;

import com.funny.study.camel.service.CamelTranService;
import com.funny.study.camel.user.dao.DocInfoMapper;
import com.funny.study.camel.user.dao.UserWxInfoMapper;
import com.funny.study.camel.user.entity.DocInfoEntity;
import com.funny.study.camel.user.entity.UserWxInfoEntity;
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
