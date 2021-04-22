package com.funny.admin.camelv.service;

import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.entity.CamelvQueue;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

public interface ICamelvQueueService {
	 List<CamelvQueue> getAll();

	 CamelvQueue get(String id);

	 ResponseData save(CamelvQueue camelvQueue);

	 ResponseData update(CamelvQueue camelvQueue);

	 ResponseData delete(String id);

	Page<CamelvQueue> find(Page<CamelvQueue> page, CamelvQueue camelvQueue);

}