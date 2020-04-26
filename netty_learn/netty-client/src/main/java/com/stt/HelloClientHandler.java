package com.stt;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

public class HelloClientHandler extends SimpleChannelInboundHandler<String> {
    private Thread thread = null;

    /**
     * 获取服务器发送的消息
     *
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("服务器说："+s);
    }

    /**
     * 建立连接后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (thread == null) {
            thread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    String msg = scanner.next();
                    ctx.writeAndFlush(msg + "\n");
                }
            });

            thread.start();
        }
    }

    /**
     * 与服务器断开连接后调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端关闭");
        super.channelInactive(ctx);
    }
}
