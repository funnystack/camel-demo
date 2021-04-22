package com.funny.admin.camelv.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.xiaoka.camelv.constant.Constant;
import com.xiaoka.camelv.dao.CamelvServerLogDao;
import com.xiaoka.camelv.entity.CamelvServerLog;
import com.xiaoka.camelv.entity.vo.ResponseData;
import com.xiaoka.camelv.service.CamelvServerLogService;

@Service
public class CamelvServerLogServiceImpl implements CamelvServerLogService {
	@Autowired
	private CamelvServerLogDao camelvServerLogDao;

	@Override
	public CamelvServerLog get(String id) {
		try {
			return camelvServerLogDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvServerLog camelvServerLog) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			camelvServerLogDao.save(camelvServerLog);
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
			camelvServerLogDao.save(camelvServerLog);
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
			camelvServerLogDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvServerLog> find(Page<CamelvServerLog> page, CamelvServerLog log) {
		try {
			DetachedCriteria dc = camelvServerLogDao.createDetachedCriteria();
			dc.add(Restrictions.eq(CamelvServerLog.FIELD_DEL_FLAG, CamelvServerLog.DEL_FLAG_NORMAL));
			dc.addOrder(Order.desc("startDate"));
			// 模糊查询
			if (!StringUtils.isBlank(log.getName())) {
				dc.add(Restrictions.like("name", log.getName(), MatchMode.ANYWHERE).ignoreCase().ignoreCase());
			}
			if (log.getStartDate() != null && log.getEndDate() != null) {
				// 时间段
				dc.add(Restrictions.ge("startDate", log.getStartDate()));
				dc.add(Restrictions.lt("endDate", log.getEndDate()));
			} else if (log.getStartDate() != null && log.getEndDate() == null) {
				// 以开始时间为准
				dc.add(Restrictions.ge("startDate", log.getStartDate()));
			} else if (log.getStartDate() == null && log.getEndDate() != null) {
				// 以结束时间为准
				dc.add(Restrictions.lt("endDate", log.getEndDate()));
			}
			// 报文模糊查询
			if (!StringUtils.isBlank(log.getRequestBody())) {
				dc.add(Restrictions.or(Restrictions.like("requestBody", log.getRequestBody(),MatchMode.ANYWHERE).ignoreCase(),   
	                    Restrictions.like("responseBody", log.getRequestBody(),MatchMode.ANYWHERE).ignoreCase()));
			}
			return camelvServerLogDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}