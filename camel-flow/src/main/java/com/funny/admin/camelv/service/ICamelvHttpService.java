package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvHttp;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvHttpService {
	 List<CamelvHttp> getAll();

	 CamelvHttp get(String id);

	 ResponseData save(CamelvHttp camelvHttp);

	 ResponseData update(CamelvHttp camelvHttp);

	 ResponseData delete(String id);

	 Page<CamelvHttp> find(Page<CamelvHttp> page, CamelvHttp camelvHttp);

}