package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.dao.CamelvAreaDao;
import com.funny.admin.camelv.entity.CamelvArea;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvAreaServiceImpl implements ICamelvAreaService {

	@Autowired
	private CamelvAreaDao areaDao;

	@Override
	public CamelvArea get(String id) {
		try {
			return areaDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvArea area) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			area.setId(null);
			areaDao.insertSelective(area);
			rd.setType(Constant.SUCCESS);
			rd.setData(area.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
//			areaDao.count();
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData rename(String id, String name) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			CamelvArea dbArea = areaDao.findEntityById(Long.valueOf(id));
			dbArea.setName(name);
			areaDao.updateSelectiveById(dbArea);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData move(String id, Integer left, Integer top) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			CamelvArea dbArea = areaDao.findEntityById(Long.valueOf(id));
			dbArea.setLeft(left);
			dbArea.setTop(top);
			areaDao.updateSelectiveById(dbArea);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData resize(String id, Integer width, Integer height) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			CamelvArea dbArea = areaDao.findEntityById(Long.valueOf(id));
			dbArea.setWidth(width);
			dbArea.setHeight(height);
			areaDao.updateSelectiveById(dbArea);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public List<CamelvArea> getByServerId(String id) {
		try {
			CamelvArea camelvArea = new CamelvArea();
			camelvArea.setServerId(id);
			List<CamelvArea> list = areaDao.listByCondition(camelvArea);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvArea>();
	}

}
