package io.github.kimmking.gateway.homeworkWeek03;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/21 16:24
 * File: HttpClientHandler
 * Project: nio02
 */

/**
 * 重写客户端的handler
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("/");
        String msg = "hello";
        FullHttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1,
                HttpMethod.GET,
                uri.toASCIIString(),
                Unpooled.wrappedBuffer(msg.getBytes(StandardCharsets.UTF_8))
        );
        request.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8")
                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());

        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        FullHttpResponse response = (FullHttpResponse) msg;
        try {
            ByteBuf content = response.content();
            HttpHeaders headers = response.headers();
            System.out.println("content:" + content.toString(CharsetUtil.UTF_8));
            System.out.println("headers:" + headers.get("content-type"));
        } finally {
            response.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
