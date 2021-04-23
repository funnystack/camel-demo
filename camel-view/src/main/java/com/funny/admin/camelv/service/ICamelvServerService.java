package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.camelv.entity.CamelvServer;
import com.funny.admin.camelv.entity.CamelvServerEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvServerService extends BaseService<CamelvServerEntity> {
    /**
     * 获取封装好的路由信息
     *
     * @param serverId
     * @return
     */
    List<CamelvRoute> getRoutes(String serverId);

    CamelvServerEntity findByDataId(String id);

    String getData(String id);
    List<CamelvServer> getAll();

}