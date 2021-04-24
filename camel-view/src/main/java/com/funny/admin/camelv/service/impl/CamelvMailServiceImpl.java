package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvMailMapper;
import com.funny.admin.camelv.entity.CamelvMailEntity;
import com.funny.admin.camelv.service.ICamelvMailService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvMailServiceImpl extends BaseServiceImpl<CamelvMailEntity> implements ICamelvMailService {
    @Resource
    private CamelvMailMapper camelvMailMapper;

    // 			pushDataService.pushCamelvMail(camelvMail);

    @Override
    protected BaseMapper<CamelvMailEntity> baseMapper() {
        return camelvMailMapper;
    }


    @Override
    public CamelvMailEntity findByDataId(String id) {
        return null;
    }

    @Override
    public List<CamelvMailEntity> getAll() {
        return null;
    }
}
