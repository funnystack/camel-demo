package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvGroovy;
import com.funny.admin.camelv.entity.CamelvGroovyEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvGroovyService extends BaseService<CamelvGroovyEntity> {
    CamelvGroovyEntity findByDataId(String id);

    List<CamelvGroovy> getAll();

}