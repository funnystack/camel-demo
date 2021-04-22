package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.dao.CamelvJdbcDao;
import com.funny.admin.camelv.entity.CamelvJdbc;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvJdbcServiceImpl implements ICamelvJdbcService {
	@Autowired
	private CamelvJdbcDao camelvJdbcDao;
	@Autowired
	private CamelvResourceHttpPushServiceImpl pushDataService;

	@Override
	public CamelvJdbc get(String id) {
		try {
			return camelvJdbcDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvJdbc camelvJdbc) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvJdbcDao.insertSelective(camelvJdbc);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvJdbc(camelvJdbc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData update(CamelvJdbc camelvJdbc) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvJdbcDao.updateSelectiveById(camelvJdbc);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvJdbc(camelvJdbc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
//			camelvJdbcDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
			pushDataService.delete(ResourceType.RES_TYPE_JDBC, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvJdbc> find(Page<CamelvJdbc> page, CamelvJdbc camelvJdbc) {
		try {
//			return camelvJdbcDao(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvJdbc> getAll() {
		try {
			return camelvJdbcDao.listByCondition(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvJdbc>();
	}

}