package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvJdbcEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvJdbcService extends BaseService<CamelvJdbcEntity> {
    CamelvJdbcEntity findByDataId(String id);
    List<CamelvJdbcEntity> getAll();

}
