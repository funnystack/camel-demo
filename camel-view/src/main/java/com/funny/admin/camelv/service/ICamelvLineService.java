package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvLine;
import com.funny.admin.camelv.entity.CamelvLineEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

/**
 * 操作路由之间的关系业务<br/>
 *
 * @author liuchengbiao
 */
public interface ICamelvLineService extends BaseService<CamelvLineEntity> {
    List<CamelvLineEntity> getByServerId(String serverId);
    List<CamelvLine> getAll();
}