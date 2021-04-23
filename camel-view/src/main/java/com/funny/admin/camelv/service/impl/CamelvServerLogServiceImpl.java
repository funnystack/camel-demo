package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvServerLogMapper;
import com.funny.admin.camelv.entity.CamelvServerLogEntity;
import com.funny.admin.camelv.service.CamelvServerLogService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CamelvServerLogServiceImpl extends BaseServiceImpl<CamelvServerLogEntity> implements CamelvServerLogService {
	@Resource
	private CamelvServerLogMapper camelvServerLogMapper;


	@Override
	protected BaseMapper<CamelvServerLogEntity> baseMapper() {
		return camelvServerLogMapper;
	}
}
