package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.CamelvArea;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.List;

/**
 * 操作工作区业务类
 *
 * @author xiaoka
 */
public interface ICamelvAreaService {

    /**
     * 获取服务关联的工作区集合
     *
     * @param id
     * @return
     */
    List<CamelvArea> getByServerId(String id);

    /**
     * 获取
     *
     * @param id
     * @return
     */
    CamelvArea get(String id);

    /**
     * 保存<br/>
     *
     * @return
     */
    ResponseData save(CamelvArea area);

    /**
     * 删除<br/>
     *
     * @param id
     * @return
     */
    ResponseData delete(String id);

    /**
     * 工作区名称修改
     *
     * @param id
     * @param name
     * @return
     */
    ResponseData rename(String id, String name);

    /**
     * 移动工作区
     *
     * @param id
     * @param left
     * @param top
     * @return
     */
    ResponseData move(String id, Integer left, Integer top);

    /**
     * 重新设置工作区大小
     *
     * @param id
     * @param width
     * @param height
     * @return
     */
    ResponseData resize(String id, Integer width, Integer height);

}