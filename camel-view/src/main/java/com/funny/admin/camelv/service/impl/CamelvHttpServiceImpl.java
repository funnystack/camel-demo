package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvHttpMapper;
import com.funny.admin.camelv.entity.CamelvHttpEntity;
import com.funny.admin.camelv.service.ICamelvHttpService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvHttpServiceImpl extends BaseServiceImpl<CamelvHttpEntity> implements ICamelvHttpService {
    @Resource
    private CamelvHttpMapper camelvHttpMapper;

    @Override
    protected BaseMapper<CamelvHttpEntity> baseMapper() {
        return camelvHttpMapper;
    }

    @Override
    public CamelvHttpEntity findByDataId(String id) {
        return null;
    }

    @Override
    public List<CamelvHttpEntity> getAll() {
        return null;
    }

    // 			pushDataService.pushCamelvHttp(camelvHttp);


}
