package com.example.demo.nettyhttp.router;

import com.example.demo.nettyhttp.processer.NettyMessageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HttpRoute extends RouteBuilder {
    @Resource
    private NettyMessageProcessor nettyMessageProcessor;

    /**
     * 在Netty4上方的路由中，只有uri完全匹配时，HTTP才会匹配，因此如果您输入以下内容，它将匹配
     *
     *  `http：//0.0.0.0：8123 / foo`，但不匹配
     * `http：//0.0.0.0：8123 / foo / bar`。
     * 因此，如果要启用通配符匹配，请执行以下操作：
     *
     * from("netty4-http:http://0.0.0.0:8123/foo?matchOnUriPrefix=true").to("mock:foo");
     * 因此，现在Netty匹配任何以开头的端点foo。
     *
     * 要匹配任何端点，您可以执行以下操作：
     *
     * from("netty4-http:http://0.0.0.0:8123?matchOnUriPrefix=true").to("mock:foo");
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        from("netty4-http:http://0.0.0.0:8080/v3?matchOnUriPrefix=true").process(nettyMessageProcessor);

    }
}
