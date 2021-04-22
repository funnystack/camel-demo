package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.dao.CamelvServerLogDao;
import com.funny.admin.camelv.entity.CamelvServerLog;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.CamelvServerLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CamelvServerLogServiceImpl implements CamelvServerLogService {
	@Resource
	private CamelvServerLogDao camelvServerLogDao;

	@Override
	public CamelvServerLog get(String id) {
		try {
			return camelvServerLogDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvServerLog camelvServerLog) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvServerLogDao.insertSelective(camelvServerLog);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData update(CamelvServerLog camelvServerLog) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvServerLogDao.updateSelectiveById(camelvServerLog);
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
//			camelvServerLogDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvServerLog> find(Page<CamelvServerLog> page, CamelvServerLog log) {
		try {
//			dc.addOrder(Order.desc("startDate"));
			// 模糊查询
			if (!StringUtils.isBlank(log.getName())) {
//				dc.add(Restrictions.like("name", log.getName(), MatchMode.ANYWHERE).ignoreCase().ignoreCase());
			}
			if (log.getStartDate() != null && log.getEndDate() != null) {
				// 时间段
//				dc.add(Restrictions.ge("startDate", log.getStartDate()));
//				dc.add(Restrictions.lt("endDate", log.getEndDate()));
			} else if (log.getStartDate() != null && log.getEndDate() == null) {
				// 以开始时间为准
//				dc.add(Restrictions.ge("startDate", log.getStartDate()));
			} else if (log.getStartDate() == null && log.getEndDate() != null) {
				// 以结束时间为准
//				dc.add(Restrictions.lt("endDate", log.getEndDate()));
			}
			// 报文模糊查询
			if (!StringUtils.isBlank(log.getRequestBody())) {
//				dc.add(Restrictions.or(Restrictions.like("requestBody", log.getRequestBody(),MatchMode.ANYWHERE).ignoreCase(),
//	                    Restrictions.like("responseBody", log.getRequestBody(),MatchMode.ANYWHERE).ignoreCase()));
			}
//			return camelvServerLogDao.listByCondition(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
