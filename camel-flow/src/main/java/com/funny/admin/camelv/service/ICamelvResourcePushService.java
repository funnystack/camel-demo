package com.funny.admin.camelv.service;

import com.funny.admin.camelv.entity.*;
import com.funny.admin.camelv.entity.vo.ResponseData;

import java.util.Map;

/**
 * 资源推送接口<br/>
 * 
 * @author xiaoka
 *
 */
public interface ICamelvResourcePushService {

	 ResponseData pushCamelvFtp(CamelvFtp camelvFtp);

	 ResponseData pushCamelvGroovy(CamelvGroovy camelvGroovy);

	 ResponseData pushCamelvHost(CamelvHost camelvHost);

	 ResponseData pushCamelvHttp(CamelvHttp camelvHttp);

	 ResponseData pushCamelvJdbc(CamelvJdbc camelvJdbc);

	 ResponseData pushCamelvMail(CamelvMail camelvMail);

	 ResponseData pushCamelvQueue(CamelvQueue camelvQueue);

	 ResponseData pushCamelvRoute(CamelvRoute camelvRoute);

	 ResponseData pushCamelvServer(CamelvServer camelvServer);
	
	 ResponseData pushCamelvLine(CamelvLine camelvLine);
	
	 ResponseData pushCamelvLine(String serverId);

	/**
	 * 移动路由关系触发
	 * 
	 * @param oldFrom
	 * @param oldTo
	 * @param camelvLine
	 * @return
	 */
	 ResponseData pushCamelvLine4Move(String oldFrom, String oldTo, CamelvLine line);

	 ResponseData pushCamelvLine4Rename(String oldFrom, String oldTo, String newName);

	/**
	 * 删除路由关系
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	 ResponseData deleteCamelvLine(String from, String to);

	/**
	 * 将指定类型的资源推送到camel中
	 * 
	 * @param header
	 * @param data
	 * @return
	 */
	 ResponseData push(Map<String, String> header, String data);

	/**
	 * 删除指定类型的资源
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	 ResponseData delete(Map<String, String> header);

	 ResponseData delete(String type, String id);

}