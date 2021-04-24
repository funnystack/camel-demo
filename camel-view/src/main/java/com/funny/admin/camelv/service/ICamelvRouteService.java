package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvRouteEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvRouteService extends BaseService<CamelvRouteEntity> {
    CamelvRouteEntity findByDataId(String id);

    String getServerUrl(String serverId);

    ResponseData rename(String id, String newName);

    ResponseData resize(String id, Integer width, Integer height);

    List<CamelvRouteEntity> getByServerId(String serverId);

    List<CamelvRouteEntity> getAll();
}
