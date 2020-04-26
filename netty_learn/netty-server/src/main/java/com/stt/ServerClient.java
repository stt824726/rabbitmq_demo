package com.stt;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerClient {

    /**
     * 监听端口
     */
    private static final int PORT = 8888;

    public static void main(String[] args) {
        // 用于接受客户端连接的线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 用于进行SocketChannel读写的线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // netty启动类
        ServerBootstrap server = new ServerBootstrap();
// 将线程组配置到启动类中
        server.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)  // 创建ServerSocketChannel
                .childHandler(new HelloServerInitializer());    // 绑定IO事件处理类
        try {
            System.out.println("服务端已启动，等待客户端连接...");
            // 服务器绑定端口
            ChannelFuture future = server.bind(PORT).sync();
            // 等待服务端监听端口关闭后，main函数才退出
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放线程组资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
