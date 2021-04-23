package com.funny.admin.camelv.service.impl;

import com.alibaba.fastjson.JSON;
import com.funny.admin.camelv.constant.RouteType;
import com.funny.admin.camelv.dao.CamelvServerMapper;
import com.funny.admin.camelv.entity.*;
import com.funny.admin.camelv.entity.vo.GooflowArea;
import com.funny.admin.camelv.entity.vo.GooflowLine;
import com.funny.admin.camelv.entity.vo.GooflowNode;
import com.funny.admin.camelv.service.ICamelvAreaService;
import com.funny.admin.camelv.service.ICamelvLineService;
import com.funny.admin.camelv.service.ICamelvRouteService;
import com.funny.admin.camelv.service.ICamelvServerService;
import com.funny.combo.core.base.BaseMapper;
import com.funny.combo.core.base.BaseServiceImpl;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CamelvServerServiceImpl extends BaseServiceImpl<CamelvServerEntity> implements ICamelvServerService {

    private static final Logger logger = LoggerFactory.getLogger(CamelvServerServiceImpl.class);


    @Resource
    private CamelvServerMapper camelvServerMapper;
    @Resource
    private ICamelvRouteService routeService;
    @Resource
    private ICamelvLineService lineService;
    @Resource
    private ICamelvAreaService areaService;

    @Override
    protected BaseMapper<CamelvServerEntity> baseMapper() {
        return camelvServerMapper;
    }

    @Override
    public String getData(String id) {
        String data = "";
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取路由
            List<CamelvRouteEntity> routeList = routeService.getByServerId(id);
            // 获取路由之间的关系
            List<CamelvLineEntity> lineList = lineService.getByServerId(id);
            map.put("initNum", routeList.size());
            // 存储页面节点
            Map<String, GooflowNode> nodeMap = new HashMap<String, GooflowNode>();
            for (CamelvRouteEntity route : routeList) {
                GooflowNode node = new GooflowNode();
                node.setId(route.getDataId() + "");
                node.setName(route.getName());
                node.setType(route.getType());
                node.setLeft(route.getLeftPx());
                node.setTop(route.getTopPx());
                node.setWidth(route.getWidth());
                node.setHeight(route.getHeight());
                node.setAlt(false);
                nodeMap.put(node.getId(), node);
            }
            map.put("nodes", nodeMap);
            // 存储页面线
            Map<String, GooflowLine> lineMap = new HashMap<String, GooflowLine>();
            for (CamelvLineEntity line : lineList) {
                GooflowLine gline = new GooflowLine();
                gline.setId(line.getDataId());
                gline.setFrom(line.getFromRouteId());
                gline.setTo(line.getToRouteId());
                gline.setName(line.getName());
                gline.setType(line.getType());
                gline.setM(line.getM());
                lineMap.put(gline.getId(), gline);
            }
            map.put("lines", lineMap);
            // 存储工作区
            Map<String, GooflowArea> areaMap = new HashMap<String, GooflowArea>();
            List<CamelvAreaEntity> areaList = areaService.getByServerId(id);
            for (CamelvAreaEntity area : areaList) {
                GooflowArea ga = new GooflowArea();
                ga.setAlt(true);
                ga.setColor(area.getColor());
                ga.setName(area.getName());
                ga.setHeight(area.getHeight());
                ga.setWidth(area.getWidth());
                ga.setLeft(area.getLeftPx());
                ga.setTop(area.getTopPx());
                areaMap.put(area.getId() + "", ga);
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
        data = JSON.toJSONString(map);
        return data;
    }

    @Override
    public List<CamelvServer> getAll() {
        return null;
    }


    //pushService.pushCamelvServer(camelvServer);

    @Override
    public List<CamelvRoute> getRoutes(String serverId) {
        try {
            List<CamelvRouteEntity> routeEntityList = routeService.getByServerId(serverId);
            List<CamelvRoute> routeList = Lists.newArrayList();
            BeanUtils.copyProperties(routeEntityList, routeList);

            // 获取服务相关的关系
            List<CamelvLineEntity> lineList = lineService.getByServerId(serverId);
            Map<String, CamelvRouteEntity> routeMap = new HashMap<String, CamelvRouteEntity>();
            for (CamelvRouteEntity route : routeList) {
                routeMap.put(route.getDataId(), route);
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
                for (CamelvLineEntity line : lineList) {
                    if (line.getFromRouteId().equals(route.getId())) {
                        CamelvRouteEntity camelvRouteEntity = routeMap.get(line.getToRouteId());
                        CamelvRoute toRoute = new CamelvRoute();
                        BeanUtils.copyProperties(camelvRouteEntity, toRoute);
                        // 排除异常
                        if (toRoute != null && !RouteType.ROUTE_TYPE_EXCEPTION.equals(toRoute.getType())) {
                            // 添加to
                            if (!toList.contains(line.getToRouteId())) {
                                toList.add(line.getToRouteId());
                            }
                        }
                        if (toRoute != null && RouteType.ROUTE_TYPE_EXCEPTION.equals(toRoute.getType())) {
                            // 设置关联异常
                            route.setExceptionId(line.getToRouteId());
                        }
                        // 添加条件
                        if (!StringUtils.isBlank(line.getName())) {
                            condition.put(line.getName(), line.getToRouteId());
                            if (!branchList.contains(line.getToRouteId())) {
                                branchList.add(line.getToRouteId());
                            }
                        }
                    }
                    // 是否是聚合节点,排除异常节点
                    if (line.getToRouteId().equals(route.getId()) && !RouteType.ROUTE_TYPE_EXCEPTION.equals(route.getType())) {
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
                    for (CamelvLineEntity line : lineList) {
                        if (line.getToRouteId().equals(route.getId())) {

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

    @Override
    public CamelvServerEntity findByDataId(String id) {
        return null;
    }

}
