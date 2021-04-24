package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvLineMapper;
import com.funny.admin.camelv.entity.CamelvLineEntity;
import com.funny.admin.camelv.service.ICamelvLineService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvLineServiceImpl extends BaseServiceImpl<CamelvLineEntity> implements ICamelvLineService {


    @Resource
    private CamelvLineMapper camelvLineMapper;

    @Override
    protected BaseMapper<CamelvLineEntity> baseMapper() {
        return camelvLineMapper;
    }

    @Override
    public List<CamelvLineEntity> getByServerId(String serverId) {
        return null;
    }

    @Override
    public List<CamelvLineEntity> getAll() {
        return null;
    }

    //			pushDataService.pushCamelvLine(line.getServerId());


}
