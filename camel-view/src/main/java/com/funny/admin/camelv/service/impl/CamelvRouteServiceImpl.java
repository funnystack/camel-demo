package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvLineMapper;
import com.funny.admin.camelv.dao.CamelvRouteMapper;
import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.camelv.entity.CamelvRouteEntity;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvRouteService;
import com.funny.admin.camelv.service.ICamelvServerService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvRouteServiceImpl extends BaseServiceImpl<CamelvRouteEntity> implements ICamelvRouteService {

    @Resource
    private CamelvRouteMapper camelvRouteMapper;
    @Resource
    private CamelvLineMapper camelvLineMapper;
    @Resource
    private ICamelvServerService serverService;

    @Override
    protected BaseMapper<CamelvRouteEntity> baseMapper() {
        return camelvRouteMapper;
    }

    @Override
    public CamelvRouteEntity findByDataId(String id) {
        return null;
    }

    @Override
    public String getServerUrl(String serverId) {
        return null;
    }

    @Override
    public ResponseData rename(String id, String newName) {
        return null;
    }

    @Override
    public ResponseData resize(String id, Integer width, Integer height) {
        return null;
    }

    @Override
    public List<CamelvRouteEntity> getByServerId(String serverId) {
        return null;
    }

    @Override
    public List<CamelvRoute> getAll() {
        return null;
    }


    //                 pushDataService.pushCamelvRoute(route);


}
