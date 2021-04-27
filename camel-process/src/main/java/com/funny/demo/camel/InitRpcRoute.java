package com.funny.demo.camel;

import com.alibaba.fastjson.JSON;
import com.funny.combo.camel.context.CamelvContext;
import com.funny.combo.camel.entity.CamelvHttp;
import com.funny.combo.camel.entity.CamelvLine;

import java.util.HashMap;
import java.util.Map;

public class InitRpcRoute {


    public static void init() {
        CamelvHttp camelvHttp1 = new CamelvHttp();
        camelvHttp1.setDataId("http1");
        camelvHttp1.setName("queryDocInfo");
        camelvHttp1.setRequestUrl("http://localhost:8080/queryDocInfoById");
        camelvHttp1.setRequestType("get");

        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("id", "${docId}");
        camelvHttp1.setParams(JSON.toJSONString(paraMap));
        CamelvContext.addCamelvHttp(camelvHttp1);

        CamelvHttp camelvHttp2 = new CamelvHttp();
        camelvHttp2.setDataId("http2");
        camelvHttp2.setName("updateDocInfo");
        camelvHttp2.setRequestUrl("http://localhost:8080/updateDocInfo");
        camelvHttp2.setRequestType("post");
        camelvHttp2.setContentType("application/json;charset=UTF-8");

        Map<String, String> para2Map = new HashMap<>();
        para2Map.put("@@", "${data}");
        camelvHttp2.setParams(JSON.toJSONString(para2Map));

        CamelvContext.addCamelvHttp(camelvHttp2);

        CamelvLine camelvLine = new CamelvLine();
        camelvLine.setLineId("line1");
        camelvLine.setFlowId("testflow");
        camelvLine.setName("line Name");
        camelvLine.setFromRouteId("http1");
        camelvLine.setToRouteId("http2");

        CamelvContext.addCamelvRouteLine(camelvLine);
    }
}
