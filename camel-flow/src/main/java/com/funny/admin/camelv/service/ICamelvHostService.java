package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvHost;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvHostService {
    List<CamelvHost> getAll();

    CamelvHost get(String id);

    ResponseData save(CamelvHost camelvHost);

    ResponseData update(CamelvHost camelvHost);

    ResponseData delete(String id);

    Page<CamelvHost> find(Page<CamelvHost> page, CamelvHost camelvHost);

}