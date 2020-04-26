package com.stt;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public class Client {
    /** 服务器IP */
    private static final String HOST = "127.0.0.1";
    /** 服务器端口号 */
    private static final int PORT = 8888;


    public static void main(String[] args) {
        // 创建NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建NIO客户端
        Bootstrap client = new Bootstrap();
        // 将线程组配置到启动类中，并创建SocketChannel，设置处理类
        client.group(group).channel(NioSocketChannel.class)
                .handler(new HelloClientInitializer());

        try {
            // 发起异步连接操作
            ChannelFuture future = client.connect(HOST, PORT).sync();
            // 关闭连接后，主函数退出。
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放线程组资源
            group.shutdownGracefully();
        }
    }
}
