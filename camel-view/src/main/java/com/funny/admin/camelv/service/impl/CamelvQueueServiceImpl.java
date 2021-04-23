package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvQueueMapper;
import com.funny.admin.camelv.entity.CamelvQueue;
import com.funny.admin.camelv.entity.CamelvQueueEntity;
import com.funny.admin.camelv.service.ICamelvQueueService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvQueueServiceImpl extends BaseServiceImpl<CamelvQueueEntity> implements ICamelvQueueService {
    @Resource
    private CamelvQueueMapper camelvQueueMapper;

    @Override
    protected BaseMapper<CamelvQueueEntity> baseMapper() {
        return camelvQueueMapper;
    }

    @Override
    public CamelvQueueEntity findByDataId(String id) {
        return null;
    }

    @Override
    public List<CamelvQueue> getAll() {
        return null;
    }
    //			pushDataService.pushCamelvQueue(camelvQueue);


}
