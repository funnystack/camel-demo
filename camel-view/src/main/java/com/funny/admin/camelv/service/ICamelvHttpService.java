package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvHttpEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvHttpService extends BaseService<CamelvHttpEntity> {
    CamelvHttpEntity findByDataId(String id);

    List<CamelvHttpEntity> getAll();

}
