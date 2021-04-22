package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvFtp;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvFtpService {

    List<CamelvFtp> getAll();

    CamelvFtp get(String id);

    ResponseData save(CamelvFtp camelvFtp);

    ResponseData update(CamelvFtp camelvFtp);

    ResponseData delete(String id);

    Page<CamelvFtp> find(Page<CamelvFtp> page, CamelvFtp camelvFtp);

}