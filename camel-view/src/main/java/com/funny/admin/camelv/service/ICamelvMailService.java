package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvMail;
import com.funny.admin.camelv.entity.CamelvMailEntity;
import com.funny.combo.core.base.BaseService;

import java.util.List;

public interface ICamelvMailService extends BaseService<CamelvMailEntity> {

    CamelvMailEntity findByDataId(String id);

    List<CamelvMail> getAll();

}