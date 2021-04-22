package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.dao.CamelvQueueDao;
import com.funny.admin.camelv.entity.CamelvQueue;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvQueueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvQueueServiceImpl implements ICamelvQueueService {
	@Resource
	private CamelvQueueDao camelvQueueDao;
	@Resource
	private CamelvResourceHttpPushServiceImpl pushDataService;

	@Override
	public CamelvQueue get(String id) {
		try {
			return camelvQueueDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvQueue camelvQueue) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvQueueDao.insertSelective(camelvQueue);
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
			camelvQueueDao.updateSelectiveById(camelvQueue);
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
//			camelvQueueDao.deleteById(id);
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
//			return camelvQueueDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvQueue> getAll() {
		try {
			return camelvQueueDao.listByCondition(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvQueue>();
	}

}
