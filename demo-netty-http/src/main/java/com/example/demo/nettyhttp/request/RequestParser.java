package com.example.demo.nettyhttp.request;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {
    private FullHttpRequest fullReq;

    /**
     * 构造一个解析器
     *
     * @param req
     */
    public RequestParser(FullHttpRequest req) {
        this.fullReq = req;
    }

    /**
     * 解析请求参数
     *
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     * @throws Exception
     */
    public Map<String, String> parse() throws Exception {
        HttpMethod method = fullReq.method();

        Map<String, String> parmMap = new HashMap<>();

        QueryStringDecoder decoder = new QueryStringDecoder(fullReq.uri());
        decoder.parameters().entrySet().forEach(entry -> {
            // entry.getValue()是一个List, 只取第一个元素
            parmMap.put(entry.getKey(), entry.getValue().get(0));
        });
        if (HttpMethod.GET == method) {
            return parmMap;
        } else if (HttpMethod.POST == method) {
            // 是POST请求
            HttpPostRequestDecoder postRequestDecoder = new HttpPostRequestDecoder(fullReq);
            postRequestDecoder.offer(fullReq);

            List<InterfaceHttpData> parmList = postRequestDecoder.getBodyHttpDatas();

            for (InterfaceHttpData parm : parmList) {

                Attribute data = (Attribute) parm;
                parmMap.put(data.getName(), data.getValue());
            }

        }
        return parmMap;
    }
}