package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvQueue;
import com.funny.admin.camelv.entity.CamelvQueueEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvQueueService extends BaseService<CamelvQueueEntity> {
    CamelvQueueEntity findByDataId(String id);
    List<CamelvQueue> getAll();

}