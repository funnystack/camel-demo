package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvFtpMapper;
import com.funny.admin.camelv.entity.CamelvFtp;
import com.funny.admin.camelv.entity.CamelvFtpEntity;
import com.funny.admin.camelv.service.ICamelvFtpService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvFtpServiceImpl extends BaseServiceImpl<CamelvFtpEntity> implements ICamelvFtpService {
    @Resource
    private CamelvFtpMapper camelvFtpMapper;

    @Override
    protected BaseMapper<CamelvFtpEntity> baseMapper() {
        return camelvFtpMapper;
    }

    @Override
    public CamelvFtpEntity findByDataId(String id) {
        return null;
    }

    @Override
    public List<CamelvFtp> getAll() {
        List<CamelvFtpEntity> camelvFtpEntityList = camelvFtpMapper.listByCondition(null);
        List<CamelvFtp> list = new ArrayList<>();
        BeanUtils.copyProperties(camelvFtpEntityList, list);
        return list;
    }


    // pushDataService.pushCamelvFtp(camelvFtp);


}
