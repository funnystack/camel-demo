package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvCategoryMapper;
import com.funny.admin.camelv.entity.CamelvAreaEntity;
import com.funny.admin.camelv.entity.CamelvCategoryEntity;
import com.funny.admin.camelv.service.ICamelvCategoryService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CamelvCategoryServiceImpl extends BaseServiceImpl<CamelvCategoryEntity> implements ICamelvCategoryService {
	@Resource
	private CamelvCategoryMapper camelvCategoryMapper;

	@Override
	protected BaseMapper<CamelvCategoryEntity> baseMapper() {
		return camelvCategoryMapper;
	}
}
