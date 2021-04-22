package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvJdbc;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvJdbcService {
    List<CamelvJdbc> getAll();

    CamelvJdbc get(String id);

    ResponseData save(CamelvJdbc camelvJdbc);

    ResponseData update(CamelvJdbc camelvJdbc);

    ResponseData delete(String id);

    Page<CamelvJdbc> find(Page<CamelvJdbc> page, CamelvJdbc camelvJdbc);

}