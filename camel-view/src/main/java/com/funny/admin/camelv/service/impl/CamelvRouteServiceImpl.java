package com.funny.admin.camelv.service.impl;

import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.dao.CamelvLineDao;
import com.funny.admin.camelv.dao.CamelvRouteDao;
import com.funny.admin.camelv.entity.CamelvLine;
import com.funny.admin.camelv.entity.CamelvRoute;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvRouteService;
import com.funny.admin.camelv.service.ICamelvServerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamelvRouteServiceImpl implements ICamelvRouteService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CamelvRouteDao routeDao;
    @Resource
    private CamelvLineDao lineDao;
    @Resource
    private CamelvResourceHttpPushServiceImpl pushDataService;
    @Resource
    private ICamelvServerService serverService;

    @Override
    public ResponseData save(CamelvRoute route) {
        ResponseData rd = new ResponseData(Constant.ERROR);
        try {
            CamelvRoute dbRoute = get(route.getId()+"");
            if (dbRoute == null) {
                // 新加
                route.setId(null);
                route.setWidth(100);
                route.setHeight(35);
                routeDao.updateSelectiveById(route);
                rd.setType(Constant.SUCCESS);
                rd.setData(route.getId());
                pushDataService.pushCamelvRoute(route);
                return rd;
            }
        } catch (Exception e) {
            logger.error("保存路由失败");
            e.printStackTrace();
        }
        return rd;
    }

    @Override
    public List<CamelvRoute> getByServerId(String serverId) {
        try {
//			dc.add(Restrictions.eq("serverId", serverId));
            List<CamelvRoute> list = routeDao.listByCondition(null);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CamelvRoute>();
    }

    @Override
    public CamelvRoute get(String id) {
        try {
            return routeDao.findEntityById(Long.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseData update(CamelvRoute route) {
        ResponseData rd = new ResponseData(Constant.ERROR);
        try {
            String option = "";
            if (!StringUtils.isBlank(route.getOption())) {
                // 转义
//				option = StringEscapeUtils.unescapeHtml(route.getOption());
            }
            // 这里需要根据不同类型做判断
            route.setOption(option);
            routeDao.updateSelectiveById(route);
            rd.setType(Constant.SUCCESS);
            pushDataService.pushCamelvRoute(route);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

    @Override
    public ResponseData move(CamelvRoute route) {
        ResponseData rd = new ResponseData(Constant.ERROR);
        try {
            CamelvRoute dbRoute = routeDao.findEntityById(Long.valueOf(route.getId()));
            // 更新坐标
            dbRoute.setLeft(route.getLeft());
            dbRoute.setTop(route.getTop());
            routeDao.updateSelectiveById(dbRoute);
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
            // 删除与该节点的所有关系线
//			lineDc.add(Restrictions.or(Restrictions.eq("from", id), Restrictions.eq("to", id)));
            List<CamelvLine> lineList = lineDao.listByCondition(null);
            for (CamelvLine line : lineList) {
//				lineDao.deleteById(line.getId());
            }
            // 删除路由本身
//			routeDao.deleteById(id);
            rd.setType(Constant.SUCCESS);
            pushDataService.delete(ResourceType.RES_TYPE_ROUTE, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

    @Override
    public ResponseData rename(String id, String newName) {
        ResponseData rd = new ResponseData(Constant.ERROR);
        try {
            CamelvRoute dbRoute = routeDao.findEntityById(Long.valueOf(id));
            dbRoute.setName(newName);
            routeDao.updateSelectiveById(dbRoute);
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
            CamelvRoute dbRoute = routeDao.findEntityById(Long.valueOf(id));
            dbRoute.setWidth(width);
            dbRoute.setHeight(height);
            routeDao.updateSelectiveById(dbRoute);
            rd.setType(Constant.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rd;
    }

    public static void main(String[] args) {
        String option = "option=name-&gt;tom,ddd-&gt;111,";
//		option = StringEscapeUtils.unescapeHtml(option);
        System.out.println(option);

    }

    @Override
    @Transactional(readOnly = false)
    public List<CamelvRoute> getAll() {
        try {
            return routeDao.listByCondition(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CamelvRoute>();
    }

    @Override
    public String getServerUrl(String serverId) {
        try {
//			dc.add(Restrictions.eq("type", RouteType.ROUTE_TYPE_JETTY));
//			dc.add(Restrictions.eq("serverId", serverId));
            List<CamelvRoute> list = routeDao.listByCondition(null);
            return list == null || list.size() == 0 ? "" : list.get(0).getUri();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
