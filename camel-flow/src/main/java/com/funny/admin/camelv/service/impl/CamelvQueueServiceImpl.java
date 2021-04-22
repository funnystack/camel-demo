package com.funny.admin.camelv.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.funny.admin.camelv.entity.vo.ResponseData;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.xiaoka.camelv.constant.Constant;
import com.xiaoka.camelv.constant.ResourceType;
import com.xiaoka.camelv.dao.CamelvQueueDao;
import com.xiaoka.camelv.entity.CamelvQueue;
import com.xiaoka.camelv.entity.vo.ResponseData;
import com.xiaoka.camelv.service.ICamelvQueueService;

@Service
public class CamelvQueueServiceImpl implements ICamelvQueueService {
	@Autowired
	private CamelvQueueDao camelvQueueDao;
	@Autowired
	private CamelvResourceHttpPushServiceImpl pushDataService;

	@Override
	public CamelvQueue get(String id) {
		try {
			return camelvQueueDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvQueue camelvQueue) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvQueueDao.save(camelvQueue);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvQueue(camelvQueue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData update(CamelvQueue camelvQueue) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvQueueDao.save(camelvQueue);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvQueue(camelvQueue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvQueueDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
			pushDataService.delete(ResourceType.RES_TYPE_QUEUE, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvQueue> find(Page<CamelvQueue> page, CamelvQueue camelvQueue) {
		try {
			DetachedCriteria dc = camelvQueueDao.createDetachedCriteria();
			dc.add(Restrictions.eq(CamelvQueue.FIELD_DEL_FLAG, CamelvQueue.DEL_FLAG_NORMAL));
			return camelvQueueDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvQueue> getAll() {
		try {
			DetachedCriteria dc = camelvQueueDao.createDetachedCriteria();
			dc.add(Restrictions.eq(CamelvQueue.FIELD_DEL_FLAG, CamelvQueue.DEL_FLAG_NORMAL));
			return camelvQueueDao.find(dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvQueue>();
	}

}