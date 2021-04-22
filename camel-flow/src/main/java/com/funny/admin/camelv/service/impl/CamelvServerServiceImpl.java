package com.funny.admin.camelv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.xiaoka.camelv.constant.Constant;
import com.xiaoka.camelv.constant.RouteType;
import com.xiaoka.camelv.dao.CamelvServerDao;
import com.xiaoka.camelv.entity.CamelvArea;
import com.xiaoka.camelv.entity.CamelvLine;
import com.xiaoka.camelv.entity.CamelvRoute;
import com.xiaoka.camelv.entity.CamelvServer;
import com.xiaoka.camelv.entity.vo.GooflowArea;
import com.xiaoka.camelv.entity.vo.GooflowLine;
import com.xiaoka.camelv.entity.vo.GooflowNode;
import com.xiaoka.camelv.entity.vo.ResponseData;
import com.xiaoka.camelv.service.ICamelvAreaService;
import com.xiaoka.camelv.service.ICamelvLineService;
import com.xiaoka.camelv.service.ICamelvResourcePushService;
import com.xiaoka.camelv.service.ICamelvRouteService;
import com.xiaoka.camelv.service.ICamelvServerService;

@Service
public class CamelvServerServiceImpl implements ICamelvServerService {

	private static final Logger logger = LoggerFactory.getLogger(CamelvServerServiceImpl.class);

	@Autowired
	private CamelvServerDao serverDao;
	@Autowired
	private ICamelvRouteService routeService;
	@Autowired
	private ICamelvLineService lineService;
	@Autowired
	private ICamelvAreaService areaService;

	@Autowired
	private ICamelvResourcePushService pushService;

	@Override
	public String getData(String id) {
		String data = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取路由
			List<CamelvRoute> routeList = routeService.getByServerId(id);
			// 获取路由之间的关系
			List<CamelvLine> lineList = lineService.getByServerId(id);
			map.put("initNum", routeList.size());
			// 存储页面节点
			Map<String, GooflowNode> nodeMap = new HashMap<String, GooflowNode>();
			for (CamelvRoute route : routeList) {
				GooflowNode node = new GooflowNode();
				node.setId(route.getId());
				node.setName(route.getName());
				node.setType(route.getType());
				node.setLeft(route.getLeft());
				node.setTop(route.getTop());
				node.setWidth(route.getWidth());
				node.setHeight(route.getHeight());
				node.setAlt(false);
				nodeMap.put(node.getId(), node);
			}
			map.put("nodes", nodeMap);
			// 存储页面线
			Map<String, GooflowLine> lineMap = new HashMap<String, GooflowLine>();
			for (CamelvLine line : lineList) {
				GooflowLine gline = new GooflowLine();
				gline.setId(line.getId());
				gline.setFrom(line.getFrom());
				gline.setTo(line.getTo());
				gline.setName(line.getName());
				gline.setType(line.getType());
				gline.setM(line.getM());
				lineMap.put(gline.getId(), gline);
			}
			map.put("lines", lineMap);
			// 存储工作区
			Map<String, GooflowArea> areaMap = new HashMap<String, GooflowArea>();
			List<CamelvArea> areaList = areaService.getByServerId(id);
			for (CamelvArea area : areaList) {
				GooflowArea ga = new GooflowArea();
				ga.setAlt(true);
				ga.setColor(area.getColor());
				ga.setName(area.getName());
				ga.setHeight(area.getHeight());
				ga.setWidth(area.getWidth());
				ga.setLeft(area.getLeft());
				ga.setTop(area.getTop());
				areaMap.put(area.getId(), ga);
			}
			map.put("areas", areaMap);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, GooflowNode> nodeMap = new HashMap<String, GooflowNode>();
			Map<String, GooflowLine> lineMap = new HashMap<String, GooflowLine>();
			Map<String, GooflowArea> areaMap = new HashMap<String, GooflowArea>();
			map.put("nodes", nodeMap);
			map.put("lines", lineMap);
			map.put("areas", areaMap);
		}
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		data = gson.toJson(map);
		return data;
	}

	@Override
	public CamelvServer get(String id) {
		try {
			return serverDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData update(CamelvServer camelvServer) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			serverDao.save(camelvServer);
			rd.setType(Constant.SUCCESS);
			pushService.pushCamelvServer(camelvServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public Page<CamelvServer> find(Page<CamelvServer> page, CamelvServer camelvServer) {
		try {
			DetachedCriteria dc = serverDao.createDetachedCriteria();
			dc.add(Restrictions.eq(CamelvServer.FIELD_DEL_FLAG, CamelvServer.DEL_FLAG_NORMAL));
			// 模糊查询
			if (!StringUtils.isBlank(camelvServer.getName())) {
				dc.add(Restrictions.like("name", camelvServer.getName(), MatchMode.ANYWHERE).ignoreCase().ignoreCase());
			}
			// 类型查询
			if (camelvServer.getType() != null && !"*".equals(camelvServer.getType())) {
				dc.add(Restrictions.eq("type", camelvServer.getType()));
			}
			dc.addOrder(Order.desc("updateDate"));
			return serverDao.find(page, dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseData save(CamelvServer camelvServer) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			serverDao.save(camelvServer);
			rd.setType(Constant.SUCCESS);
			pushService.pushCamelvServer(camelvServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	public ResponseData delete(String id) {
		ResponseData rd = new ResponseData(Constant.ERROR);
		try {
			serverDao.deleteById(id);
			rd.setType(Constant.SUCCESS);
			pushService.delete(Constant.TYPE_SERVER, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rd;
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvServer> getAll() {
		try {
			DetachedCriteria dc = serverDao.createDetachedCriteria();
			dc.add(Restrictions.eq(CamelvServer.FIELD_DEL_FLAG, CamelvServer.DEL_FLAG_NORMAL));
			return serverDao.find(dc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CamelvServer>();
	}

	@Override
	@Transactional(readOnly = false)
	public List<CamelvRoute> getRoutes(String serverId) {
		try {
			List<CamelvRoute> routeList = routeService.getByServerId(serverId);
			// 获取服务相关的关系
			List<CamelvLine> lineList = lineService.getByServerId(serverId);
			Map<String, CamelvRoute> routeMap = new HashMap<String, CamelvRoute>();
			for (CamelvRoute route : routeList) {
				routeMap.put(route.getId(), route);
			}
			// 记录line.to指向的路由id，且该线是条件关系
			List<String> branchList = new ArrayList<String>();
			for (CamelvRoute route : routeList) {
				// 记录后续路由指定的id
				List<String> toList = new ArrayList<String>();
				// 记录条件
				Map<String, String> condition = new HashMap<String, String>();
				// 记录聚合
				int aggregatCount = 0;
				for (CamelvLine line : lineList) {
					if (line.getFrom().equals(route.getId())) {
						CamelvRoute toRoute = routeMap.get(line.getTo());
						// 排除异常
						if (toRoute != null && !RouteType.ROUTE_TYPE_EXCEPTION.equals(toRoute.getType())) {
							// 添加to
							if (!toList.contains(line.getTo())) {
								toList.add(line.getTo());
							}
						}
						if (toRoute != null && RouteType.ROUTE_TYPE_EXCEPTION.equals(toRoute.getType())) {
							// 设置关联异常
							route.setExceptionId(line.getTo());
						}
						// 添加条件
						if (!StringUtils.isBlank(line.getName())) {
							condition.put(line.getName(), line.getTo());
							if (!branchList.contains(line.getTo())) {
								branchList.add(line.getTo());
							}
						}
					}
					// 是否是聚合节点,排除异常节点
					if (line.getTo().equals(route.getId()) && !RouteType.ROUTE_TYPE_EXCEPTION.equals(route.getType())) {
						aggregatCount++;
					}
				}
				route.setTo(toList);
				route.setCondition(condition);
				route.setAggregat(aggregatCount >= 2);
			}

			// 排除分支路由聚合聚合不执行问题
			for (CamelvRoute route : routeList) {
				if (route.getAggregat()) {// 判断是否分支聚合
					// 获取line.from
					for (CamelvLine line : lineList) {
						if (line.getTo().equals(route.getId())) {

						}
					}
				}
			}
			return routeList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取路由封装好的调用信息失败!!!");
		}
		return new ArrayList<CamelvRoute>();
	}

}