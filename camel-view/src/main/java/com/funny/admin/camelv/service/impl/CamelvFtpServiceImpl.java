package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.Page;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.dao.CamelvFtpDao;
import com.funny.admin.camelv.entity.CamelvFtp;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvFtpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvFtpServiceImpl implements ICamelvFtpService {
    @Resource
    private CamelvFtpDao camelvFtpDao;
    @Resource
    private CamelvResourceHttpPushServiceImpl pushDataService;

    @Override
    public CamelvFtp get(String id) {
        try {
            return camelvFtpDao.findEntityById(Long.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseData save(CamelvFtp camelvFtp) {
        ResponseData rd = new ResponseData(Constant.ERROR);
        try {
            camelvFtpDao.updateSelectiveById(camelvFtp);
            rd.setType(Constant.SUCCESS);
            pushDataService.pushCamelvFtp(camelvFtp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

    @Override
    public ResponseData update(CamelvFtp camelvFtp) {
        ResponseData rd = new ResponseData(Constant.ERROR);
        try {
            camelvFtpDao.updateSelectiveById(camelvFtp);
            rd.setType(Constant.SUCCESS);
            pushDataService.pushCamelvFtp(camelvFtp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

    @Override
    public ResponseData delete(String id) {
        ResponseData rd = new ResponseData(Constant.ERROR);
        try {
//			camelvFtpDao.deleteById(id);
            rd.setType(Constant.SUCCESS);

            pushDataService.delete(ResourceType.RES_TYPE_FTP, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

    @Override
    public Page<CamelvFtp> find(Page<CamelvFtp> page, CamelvFtp camelvFtp) {
        try {
//			DetachedCriteria dc = camelvFtpDao.createDetachedCriteria();
//			dc.add(Restrictions.eq(CamelvFtp.FIELD_DEL_FLAG, CamelvFtp.DEL_FLAG_NORMAL));
            camelvFtpDao.listByCondition(camelvFtp);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CamelvFtp> getAll() {
        try {
            return camelvFtpDao.listByCondition(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CamelvFtp>();
    }

}
