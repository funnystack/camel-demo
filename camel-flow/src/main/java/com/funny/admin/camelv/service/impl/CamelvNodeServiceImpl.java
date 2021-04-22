package com.funny.admin.camelv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.xiaoka.camelv.constant.Constant;
import com.xiaoka.camelv.dao.CamelvNodeDao;
import com.xiaoka.camelv.entity.CamelvNode;
import com.xiaoka.camelv.entity.vo.ResponseData;
import com.xiaoka.camelv.service.ICamelvNodeService;

@Service
public class CamelvNodeServiceImpl implements ICamelvNodeService {
	@Autowired
	private CamelvNodeDao camelvNodeDao;

	@Override
	@Transactional(readOnly = false)
	public CamelvNode get(String id) {
		try {
			return camelvNodeDao.get(id);
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
			camelvNodeDao.save(camelvNode);
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
			camelvNodeDao.save(camelvNode);
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
			camelvNodeDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvNode> find(Page<CamelvNode> page, CamelvNode camelvNode) {
		try {
			DetachedCriteria dc = camelvNodeDao.createDetachedCriteria();
			dc.add(Restrictions.eq(CamelvNode.FIELD_DEL_FLAG, CamelvNode.DEL_FLAG_NORMAL));
			return camelvNodeDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CamelvNode> getAll() {
		try {
			DetachedCriteria dc = camelvNodeDao.createDetachedCriteria();
			dc.add(Restrictions.eq(CamelvNode.FIELD_DEL_FLAG, CamelvNode.DEL_FLAG_NORMAL));
			return camelvNodeDao.find(dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvNode>();
	}

}