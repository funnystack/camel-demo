package com.funny.admin.camelv.service;

import java.util.List;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvGroovy;
import com.funny.admin.camelv.entity.vo.ResponseData;

public interface ICamelvGroovyService {

    List<CamelvGroovy> getAll();

    CamelvGroovy get(String id);

    ResponseData save(CamelvGroovy camelvGroovy);

    ResponseData update(CamelvGroovy camelvGroovy);

    ResponseData delete(String id);

    Page<CamelvGroovy> find(Page<CamelvGroovy> page, CamelvGroovy camelvGroovy);

}