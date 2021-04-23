package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvHostMapper;
import com.funny.admin.camelv.entity.CamelvHost;
import com.funny.admin.camelv.entity.CamelvHostEntity;
import com.funny.admin.camelv.service.ICamelvHostService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvHostServiceImpl extends BaseServiceImpl<CamelvHostEntity> implements ICamelvHostService {
    @Resource
    private CamelvHostMapper camelvHostMapper;

    @Override
    protected BaseMapper<CamelvHostEntity> baseMapper() {
        return camelvHostMapper;
    }

    @Override
    public CamelvHostEntity findByDataId(String id) {
        return null;
    }

    @Override
    public List<CamelvHost> getAll() {
        return null;
    }

    //pushDataService.pushCamelvHost(camelvHost);


}
