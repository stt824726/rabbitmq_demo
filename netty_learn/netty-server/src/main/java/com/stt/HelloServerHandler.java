package com.stt;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 获取客户端发送的消息
     *
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        // 收到消息直接打出
        System.out.println(channelHandlerContext.channel().remoteAddress()+"说:" + s);

        // 向客户端发送消息
        channelHandlerContext.writeAndFlush("服务器已收到消息，内容是：" + s + "\n");
    }

    /**
     * 建立连接后调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"连接！");

        // 向客户端发送消息
        ctx.writeAndFlush("欢迎登录"+ InetAddress.getLocalHost().getHostName()+"\n");
    }
}
