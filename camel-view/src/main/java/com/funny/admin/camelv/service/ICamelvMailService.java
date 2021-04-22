package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvMail;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvMailService {
    List<CamelvMail> getAll();

    CamelvMail get(String id);

    ResponseData save(CamelvMail camelvMail);

    ResponseData update(CamelvMail camelvMail);

    ResponseData delete(String id);

    Page<CamelvMail> find(Page<CamelvMail> page, CamelvMail camelvMail);

}