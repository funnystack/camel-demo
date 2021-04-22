package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.dao.CamelvGroovyDao;
import com.funny.admin.camelv.entity.CamelvGroovy;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvGroovyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvGroovyServiceImpl implements ICamelvGroovyService {
	@Resource
	private CamelvGroovyDao camelvGroovyDao;
	@Resource
	private CamelvResourceHttpPushServiceImpl pushDataService;

	@Override
	public CamelvGroovy get(String id) {
		try {
			return camelvGroovyDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvGroovy camelvGroovy) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvGroovyDao.updateSelectiveById(camelvGroovy);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvGroovy(camelvGroovy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData update(CamelvGroovy camelvGroovy) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvGroovyDao.updateSelectiveById(camelvGroovy);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvGroovy(camelvGroovy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
//			camelvGroovyDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
			pushDataService.delete(ResourceType.RES_TYPE_GROOVY, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvGroovy> find(Page<CamelvGroovy> page, CamelvGroovy camelvGroovy) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvGroovy> getAll() {
		try {
			return camelvGroovyDao.listByCondition(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvGroovy>();
	}

}
