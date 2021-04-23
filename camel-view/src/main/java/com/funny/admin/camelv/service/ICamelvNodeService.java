package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvNode;
import com.funny.admin.camelv.entity.CamelvNodeEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvNodeService extends BaseService<CamelvNodeEntity> {
    List<CamelvNode> getAll();


    CamelvNodeEntity findByURL(String url);
}