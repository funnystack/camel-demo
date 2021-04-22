package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvRouteService {

    String getServerUrl(String serverId);

    List<CamelvRoute> getAll();

    CamelvRoute get(String id);

    ResponseData save(CamelvRoute route);

    ResponseData move(CamelvRoute route);

    ResponseData delete(String id);

    ResponseData rename(String id, String newName);

    ResponseData resize(String id, Integer width, Integer height);

    ResponseData update(CamelvRoute route);

    List<CamelvRoute> getByServerId(String serverId);
}