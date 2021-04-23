package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvGroovyMapper;
import com.funny.admin.camelv.entity.CamelvGroovy;
import com.funny.admin.camelv.entity.CamelvGroovyEntity;
import com.funny.admin.camelv.service.ICamelvGroovyService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvGroovyServiceImpl extends BaseServiceImpl<CamelvGroovyEntity> implements ICamelvGroovyService {
    @Resource
    private CamelvGroovyMapper camelvGroovyMapper;

    @Override
    protected BaseMapper<CamelvGroovyEntity> baseMapper() {
        return camelvGroovyMapper;
    }

    @Override
    public CamelvGroovyEntity findByDataId(String id) {
        return null;
    }

    @Override
    public List<CamelvGroovy> getAll() {
        return null;
    }
    //			pushDataService.pushCamelvGroovy(camelvGroovy);

}
