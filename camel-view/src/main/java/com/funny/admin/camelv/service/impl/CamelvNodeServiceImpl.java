package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.dao.CamelvNodeDao;
import com.funny.admin.camelv.entity.CamelvNode;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvNodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvNodeServiceImpl implements ICamelvNodeService {
	@Resource
	private CamelvNodeDao camelvNodeDao;

	@Override
	@Transactional(readOnly = false)
	public CamelvNode get(String id) {
		try {
			return camelvNodeDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public ResponseData save(CamelvNode camelvNode) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvNodeDao.insertSelective(camelvNode);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseData update(CamelvNode camelvNode) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvNodeDao.updateSelectiveById(camelvNode);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
//			camelvNodeDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvNode> find(Page<CamelvNode> page, CamelvNode camelvNode) {
		try {
//			return camelvNodeDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CamelvNode> getAll() {
		try {
			return camelvNodeDao.listByCondition(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvNode>();
	}

}
