package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.dao.CamelvLineDao;
import com.funny.admin.camelv.entity.CamelvLine;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvLineServiceImpl implements ICamelvLineService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CamelvLineDao lineDao;
	@Autowired
	private CamelvResourceHttpPushServiceImpl pushDataService;

	@Override
	public List<CamelvLine> getByServerId(String serverId) {
		try {
//			dc.add(Restrictions.eq("serverId", serverId));
			List<CamelvLine> list = lineDao.listByCondition(null);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvLine>();
	}

	@Override
	public CamelvLine get(String id) {
		try {
			return lineDao.findEntityById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvLine line) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			if (line.getFrom().equals(line.getTo())) {
				logger.error("箭头不能够指向自己...");
				return rd;
			}
			line.setId(null);
			lineDao.insertSelective(line);
			rd.setData(line.getId());
			rd.setType(Constant.SUCCESS);
			pushDataService.pushCamelvLine(line.getServerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData move(String id, String newFrom, String newTo) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			if (newFrom.equals(newTo)) {
				logger.error("箭头不能够指向自己...");
				return rd;
			}
			CamelvLine dbLine = lineDao.findEntityById(Long.valueOf(id));

			String oldFrom = dbLine.getFrom();
			String oldTo = dbLine.getTo();

			dbLine.setFrom(newFrom);
			dbLine.setTo(newTo);
			lineDao.updateSelectiveById(dbLine);
			rd.setType(Constant.SUCCESS);

			// pushDataService.pushCamelvLine4Move(oldFrom, oldTo, dbLine);
			pushDataService.pushCamelvLine(dbLine.getServerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			CamelvLine line = get(id);
//			lineDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
			// 删除旧的
			// pushDataService.deleteCamelvLine(line.getFrom(), line.getTo());
			pushDataService.pushCamelvLine(line.getServerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData setType(String id, String type) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			CamelvLine dbLine = lineDao.findEntityById(Long.valueOf(id));
			dbLine.setType(type);
			// 设置默认值
			dbLine.setM(0.0);
			lineDao.updateSelectiveById(dbLine);
			rd.setType(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData setM(String id, Double M) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			CamelvLine dbLine = lineDao.findEntityById(Long.valueOf(id));
			dbLine.setM(M);
			lineDao.updateSelectiveById(dbLine);
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
			CamelvLine dbLine = lineDao.get(id);
			dbLine.setName(name);
			lineDao.updateSelectiveById(dbLine);
			rd.setType(Constant.SUCCESS);
//			pushDataService.pushCamelvLine4Rename(dbLine.getFrom(), dbLine.getTo(), name);
			pushDataService.pushCamelvLine(dbLine.getServerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvLine> getAll() {
		try {
			List<CamelvLine> list = lineDao.listByCondition(null);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvLine>();
	}
}