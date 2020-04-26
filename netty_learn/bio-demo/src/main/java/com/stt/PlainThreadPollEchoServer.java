package com.stt;

import sun.nio.ByteBuffered;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlainThreadPollEchoServer {
    public static void main(String[] args) throws Exception{
        new PlainThreadPollEchoServer().serve(8088);
    }

    public void serve(int port) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final ServerSocket socket = new ServerSocket(port);
        try {
            while (true) {
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                executorService.submit(new ConnectNioHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class ConnectNioHandler extends Thread{
    private Socket socket;
    public ConnectNioHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                InputStream inputStream = socket.getInputStream();
                BufferedReader by = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    System.out.println(by.readLine());
                    System.out.println(Thread.currentThread().getId());
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ex) {
// ignore on close
                }

            }
        }
    }
}