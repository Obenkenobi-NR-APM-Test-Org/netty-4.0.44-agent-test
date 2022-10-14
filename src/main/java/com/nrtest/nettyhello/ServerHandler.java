package com.nrtest.nettyhello;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {
        boolean succeed = true;
//        try {
//            CassandraConnector.executeCassandra(session -> {
//                ResultSet rs = session.execute("select release_version from system.local");    // (3)
//                Row row = rs.one();
//                System.out.println(row.getString("release_version"));
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            succeed = false;
//        }
        String responseText = succeed ? "Hello World!": "failed";
        ByteBuf content = Unpooled.copiedBuffer(responseText, CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
        response.headers().set("Content-Type", "text/html");
        response.headers().set("Content-Length", content.readableBytes());
        ctx.write(response);
        ctx.flush();
    }

}
