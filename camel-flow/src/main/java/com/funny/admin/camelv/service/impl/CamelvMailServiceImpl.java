package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.dao.CamelvMailDao;
import com.funny.admin.camelv.entity.CamelvMail;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvMailServiceImpl implements ICamelvMailService {
	@Autowired
	private CamelvMailDao camelvMailDao;
	@Autowired
	private CamelvResourceHttpPushServiceImpl pushDataService;

	@Override
	public CamelvMail get(String id) {
		try {
			return camelvMailDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvMail camelvMail) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvMailDao.insertSelective(camelvMail);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvMail(camelvMail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData update(CamelvMail camelvMail) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvMailDao.updateSelectiveById(camelvMail);
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvMail(camelvMail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
//			camelvMailDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
			pushDataService.delete(ResourceType.RES_TYPE_MAIL, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvMail> find(Page<CamelvMail> page, CamelvMail camelvMail) {
		try {
//			return camelvMailDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvMail> getAll() {
		try {
			return camelvMailDao.listByCondition(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvMail>();
	}

}