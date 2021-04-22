package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.camelv.entity.CamelvServer;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvServerService {
	/**
	 * 获取封装好的路由信息
	 * 
	 * @param serverId
	 * @return
	 */
	 List<CamelvRoute> getRoutes(String serverId);

	 List<CamelvServer> getAll();

	 Page<CamelvServer> find(Page<CamelvServer> page, CamelvServer camelvServer);

	 ResponseData save(CamelvServer camelvServer);

	 ResponseData delete(String id);

	 CamelvServer get(String id);

	 ResponseData update(CamelvServer camelvServer);

	 String getData(String id);
}