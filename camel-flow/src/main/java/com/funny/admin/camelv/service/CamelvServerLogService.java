package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvServerLog;
import com.funny.admin.camelv.entity.vo.ResponseData;

public interface CamelvServerLogService {
	 CamelvServerLog get(String id);

	 ResponseData save(CamelvServerLog camelvServerLog);

	 ResponseData update(CamelvServerLog camelvServerLog);

	 ResponseData delete(String id);

	 Page<CamelvServerLog> find(Page<CamelvServerLog> page, CamelvServerLog camelvServerLog);

}