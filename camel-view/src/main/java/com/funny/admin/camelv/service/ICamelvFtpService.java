package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvFtpEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvFtpService extends BaseService<CamelvFtpEntity> {

    CamelvFtpEntity findByDataId(String id);

    List<CamelvFtpEntity> getAll();

}
