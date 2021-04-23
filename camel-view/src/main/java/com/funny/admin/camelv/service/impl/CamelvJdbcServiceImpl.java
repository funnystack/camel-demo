package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.dao.CamelvJdbcMapper;
import com.funny.admin.camelv.entity.CamelvJdbc;
import com.funny.admin.camelv.entity.CamelvJdbcEntity;
import com.funny.admin.camelv.service.ICamelvJdbcService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CamelvJdbcServiceImpl extends BaseServiceImpl<CamelvJdbcEntity>  implements ICamelvJdbcService {
	@Resource
	private CamelvJdbcMapper camelvJdbcMapper;
	@Override
	protected BaseMapper<CamelvJdbcEntity> baseMapper() {
		return camelvJdbcMapper;
	}

	@Override
	public CamelvJdbcEntity findByDataId(String id) {
		return null;
	}

	@Override
	public List<CamelvJdbc> getAll() {
		return null;
	}

	//			pushDataService.pushCamelvJdbc(camelvJdbc);



}
