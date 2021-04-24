package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvHostEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvHostService extends BaseService<CamelvHostEntity> {
    CamelvHostEntity findByDataId(String id);
    List<CamelvHostEntity> getAll();

}
