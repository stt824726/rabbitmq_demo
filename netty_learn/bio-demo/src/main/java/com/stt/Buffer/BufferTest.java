package com.stt.Buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("D:\\learnFile\\Java多线程编程深入详解.pdf","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            int bytesRead = fileChannel.read(byteBuffer);
            while(bytesRead != -1){
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    System.out.print((Byte)byteBuffer.get());
                }
                byteBuffer.clear();
                bytesRead = fileChannel.read(byteBuffer);
            }
            Thread.sleep(60000);
            fileChannel.close();
            while(true){

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("can not find file");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("io exception");
        }finally {
            randomAccessFile.close();

        }


    }
}
