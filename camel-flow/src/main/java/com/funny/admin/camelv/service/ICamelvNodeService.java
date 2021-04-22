package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvNode;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvNodeService {

    List<CamelvNode> getAll();

    CamelvNode get(String id);

    ResponseData save(CamelvNode camelvNode);

    ResponseData update(CamelvNode camelvNode);

    ResponseData delete(String id);

    Page<CamelvNode> find(Page<CamelvNode> page, CamelvNode camelvNode);

}