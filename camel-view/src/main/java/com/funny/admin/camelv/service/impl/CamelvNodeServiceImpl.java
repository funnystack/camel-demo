package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvNodeMapper;
import com.funny.admin.camelv.entity.CamelvNodeEntity;
import com.funny.admin.camelv.service.ICamelvNodeService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvNodeServiceImpl extends BaseServiceImpl<CamelvNodeEntity> implements ICamelvNodeService {
	@Resource
	private CamelvNodeMapper camelvNodeMapper;
	@Override
	protected BaseMapper<CamelvNodeEntity> baseMapper() {
		return camelvNodeMapper;
	}


	@Override
	public List<CamelvNodeEntity> getAll() {
		return null;
	}

	@Override
	public CamelvNodeEntity findByURL(String url) {
		return null;
	}
}
