package com.example.demo.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        byte[] buff = new byte[buf.readableBytes()];
        buf.readBytes(buff);
        //转换为字符串
        String message = new String(buff, "utf-8");

        //转换为json格式的字符串 实体类亦可这样转
        String json = JSON.toJSONString(message);
        //发送消息到队列(topic)
        out.add(json);

        //响应客户端请求的
        ctx.writeAndFlush("hello client");
    }

    @Override
    protected void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved0(ctx);
        //客户端断开连接时，触发此方法
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        //客户端连接成功时，触发此方法
    }
}
