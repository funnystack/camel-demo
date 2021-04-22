package com.funny.admin.camelv.service.impl;

import com.alibaba.fastjson.JSON;
import com.funny.admin.camelv.constant.Constant;
import com.funny.admin.camelv.constant.ResourceType;
import com.funny.admin.camelv.entity.*;
import com.funny.admin.camelv.entity.vo.ResponseData;
import com.funny.admin.camelv.service.ICamelvNodeService;
import com.funny.admin.camelv.service.ICamelvResourcePushService;
import com.funny.admin.camelv.service.ICamelvServerService;
import com.funny.admin.camelv.util.CamelvHttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用http模式推送
 * 
 * @author xiaoka
 *
 */
@Service
public class CamelvResourceHttpPushServiceImpl implements ICamelvResourcePushService {

	private static final Logger logger = LoggerFactory.getLogger(CamelvResourceHttpPushServiceImpl.class);

	@Autowired
	private ICamelvNodeService nodeService;
	@Autowired
	private ICamelvServerService serverService;

	@Override
	public ResponseData pushCamelvFtp(CamelvFtp camelvFtp) {
		String body = JSON.toJSONString(camelvFtp);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_FTP);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvGroovy(CamelvGroovy camelvGroovy) {
		String body = JSON.toJSONString(camelvGroovy);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_GROOVY);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvHost(CamelvHost camelvHost) {
		String body = JSON.toJSONString(camelvHost);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_HOST);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvHttp(CamelvHttp camelvHttp) {
		String body = JSON.toJSONString(camelvHttp);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_HTTP);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvJdbc(CamelvJdbc camelvJdbc) {
		String body = JSON.toJSONString(camelvJdbc);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_JDBC);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvMail(CamelvMail camelvMail) {
		String body = JSON.toJSONString(camelvMail);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_MAIL);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvQueue(CamelvQueue camelvQueue) {
		String body = JSON.toJSONString(camelvQueue);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_QUEUE);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvRoute(CamelvRoute camelvRoute) {
		String body = JSON.toJSONString(camelvRoute);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_ROUTE);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvServer(CamelvServer camelvServer) {
		String body = JSON.toJSONString(camelvServer);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", Constant.TYPE_SERVER);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvLine(CamelvLine camelvLine) {
		String body = JSON.toJSONString(camelvLine);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_LINE);
		header.put("option", "add");
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvLine(String serverId) {
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_LINE);
		List<CamelvRoute> routes = serverService.getRoutes(serverId);
		String body = JSON.toJSONString(routes);
		logger.info("改变路由关系:\n" + body);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvLine4Move(String oldFrom, String oldTo, CamelvLine line) {
		String body = JSON.toJSONString(line);
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_LINE);
		header.put("option", "move");
		header.put("oldFrom", oldFrom);
		header.put("oldTo", oldTo);
		return push(header, body);
	}

	@Override
	public ResponseData pushCamelvLine4Rename(String from, String to, String newName) {
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_LINE);
		header.put("option", "rename");
		header.put("from", from);
		header.put("to", to);
		header.put("newName", newName);
		return push(header, "");
	}

	@Override
	public ResponseData deleteCamelvLine(String from, String to) {
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", ResourceType.RES_TYPE_LINE);
		header.put("from", from);
		header.put("to", to);
		return delete(header);
	}

	@Override
	public ResponseData push(Map<String, String> header, String data) {
		List<CamelvNode> list = nodeService.getAll();
		for (CamelvNode node : list) {
			// 拼接url
			String url = "http://" + node.getId() + "/pushData";
			CamelvHttpUtils.sendAsyncHttpPost(url, Constant.CONTENT_TYPE, header, data);
		}
		return new ResponseData(Constant.SUCCESS);
	}

	@Override
	public ResponseData delete(Map<String, String> header) {
		List<CamelvNode> list = nodeService.getAll();
		for (CamelvNode node : list) {
			// 拼接url
			String url = "http://" + node.getId() + "/deleteData";
			CamelvHttpUtils.sendAsyncHttpPost(url, Constant.CONTENT_TYPE, header, "");
		}
		return new ResponseData(Constant.SUCCESS);
	}

	@Override
	public ResponseData delete(String type, String id) {
		Map<String, String> header = new HashMap<String, String>();
		header.put("type", type);
		header.put("id", id);
		return delete(header);
	}

}
