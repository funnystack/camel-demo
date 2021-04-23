package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvAreaEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

/**
 * 操作工作区业务类
 *
 * @author xiaoka
 */
public interface ICamelvAreaService extends BaseService<CamelvAreaEntity> {

    /**
     * 获取服务关联的工作区集合
     *
     * @param id
     * @return
     */
    List<CamelvAreaEntity> getByServerId(String id);

}