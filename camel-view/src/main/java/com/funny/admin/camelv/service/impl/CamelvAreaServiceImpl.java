package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvAreaMapper;
import com.funny.admin.camelv.entity.CamelvAreaEntity;
import com.funny.admin.camelv.service.ICamelvAreaService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvAreaServiceImpl extends BaseServiceImpl<CamelvAreaEntity> implements ICamelvAreaService {

    @Resource
    private CamelvAreaMapper camelvAreaMapper;

    @Override
    protected BaseMapper<CamelvAreaEntity> baseMapper() {
        return camelvAreaMapper;
    }


    @Override
    public List<CamelvAreaEntity> getByServerId(String id) {
        return null;
    }
}
