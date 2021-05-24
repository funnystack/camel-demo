package com.example.demo.netty;

import io.netty.channel.ChannelHandler;
import org.apache.camel.component.netty4.DefaultChannelHandlerFactory;


public class DecoderFactory extends DefaultChannelHandlerFactory {
    @Override
    public ChannelHandler newChannelHandler() {
        return new Decoder();
    }
}
