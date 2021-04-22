package com.example.demo.nettyhttp.processer;

import com.alibaba.fastjson.JSON;
import com.example.demo.nettyhttp.request.RequestParser;
import com.example.demo.nettyhttp.request.StudentParam;
import com.example.demo.nettyhttp.result.OperateResult;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.util.CharsetUtil;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.netty.http.NettyHttpMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class NettyMessageProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        System.out.println("received netty http message");
        final NettyHttpMessage message = exchange.getIn(NettyHttpMessage.class);
        final FullHttpRequest request = message.getHttpRequest();
        HttpHeaders httpHeaders = request.headers();
        System.out.println(request.getMethod());
        System.out.println(JSON.toJSONString(httpHeaders.entries()));

        ByteBuf byteBuf = request.content();
        String jsonStr = byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println(jsonStr);

        if(!StringUtils.isEmpty(jsonStr)){
            StudentParam studentParam  = null;
            try {
                studentParam = JSON.parseObject(jsonStr, StudentParam.class);
                System.out.println(studentParam.getName());
            } catch (Exception e) {
            }
        }


        Map<String, String> paramMap = new RequestParser(request).parse(); // 将GET, POST所有请求参数转换成Map对象
        System.out.println(JSON.toJSONString(paramMap));


        OperateResult operateResult = OperateResult.fail("ok","成功");
        final ByteBuf buf = Unpooled.copiedBuffer(JSON.toJSONString(operateResult), CharsetUtil.UTF_8);
//        message.setHeader("Content-type", "text/html;charset=UTF-8");
        message.setHeader("Content-type", "application/json;charset=UTF-8");

        //
        message.setHeader("Cache-Control","public,max-age=10");
        message.setBody(buf);

    }
}
