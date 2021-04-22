package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvLine;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

/**
 * 操作路由之间的关系业务<br/>
 *
 * @author liuchengbiao
 */
public interface ICamelvLineService {

    List<CamelvLine> getAll();

    /**
     * 根据服务id获取关联的路由关系<br/>
     *
     * @param serverId
     * @return
     */
    List<CamelvLine> getByServerId(String serverId);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    CamelvLine get(String id);

    /**
     * 保存<br/>
     *
     * @param line
     * @return
     */
    ResponseData save(CamelvLine line);

    /**
     * 移动
     *
     * @return
     */
    ResponseData move(String id, String newFrom, String newTo);

    /**
     * 设置线的类型
     *
     * @param id
     * @param type
     * @return
     */
    ResponseData setType(String id, String type);

    /**
     * 修改线的折点
     *
     * @param id
     * @param M
     * @return
     */
    ResponseData setM(String id, Double M);

    /**
     * 删除<br/>
     *
     * @param id
     * @return
     */
    ResponseData delete(String id);

    /**
     * 路由条件修改
     *
     * @param id
     * @param name
     * @return
     */
    ResponseData rename(String id, String name);

}