package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.dao.CamelvHostDao;
import com.funny.admin.camelv.entity.CamelvHost;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvHostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvHostServiceImpl implements ICamelvHostService {
	@Resource
	private CamelvHostDao camelvHostDao;
	@Resource
	private CamelvResourceHttpPushServiceImpl pushDataService;

	@Override
	public CamelvHost get(String id) {
		try {
			return camelvHostDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvHost camelvHost) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvHostDao.insertSelective(camelvHost);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvHost(camelvHost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData update(CamelvHost camelvHost) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvHostDao.updateSelectiveById(camelvHost);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvHost(camelvHost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
//			camelvHostDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
			pushDataService.delete(ResourceType.RES_TYPE_HOST, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvHost> find(Page<CamelvHost> page, CamelvHost camelvHost) {
		try {
//			return camelvHostDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CamelvHost> getAll() {
		try {
			return camelvHostDao.listByCondition(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvHost>();
	}

}
