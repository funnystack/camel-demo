package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.dao.CamelvHttpDao;
import com.funny.admin.camelv.entity.CamelvHttp;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvHttpServiceImpl implements ICamelvHttpService {
	@Autowired
	private CamelvHttpDao camelvHttpDao;
	@Autowired
	private CamelvResourceHttpPushServiceImpl pushDataService;

	@Override
	public CamelvHttp get(String id) {
		try {
			return camelvHttpDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvHttp camelvHttp) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvHttpDao.insertSelective(camelvHttp);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvHttp(camelvHttp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData update(CamelvHttp camelvHttp) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvHttpDao.updateSelectiveById(camelvHttp);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvHttp(camelvHttp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
//			camelvHttpDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
			pushDataService.delete(ResourceType.RES_TYPE_HTTP, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvHttp> find(Page<CamelvHttp> page, CamelvHttp camelvHttp) {
		try {
//			return camelvHttpDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvHttp> getAll() {
		try {
			return camelvHttpDao.listByCondition(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvHttp>();
	}

}