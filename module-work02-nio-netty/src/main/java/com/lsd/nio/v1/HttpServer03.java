package com.lsd.nio.v1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author nhsoft.lsd
 */
public class HttpServer03 {

   public static void main(String[] args) throws IOException {
       ExecutorService executorService = Executors.newFixedThreadPool(100);
       ServerSocket serverSocket = new ServerSocket(8090);
       while (true) {
           Socket socket = serverSocket.accept();
           executorService.execute(() -> service(socket));
       }
   }

   private static void service(Socket socket) {
       try {
           byte[] message = "hello, lusudong".getBytes(StandardCharsets.UTF_8);
           PrintStream writer = new PrintStream(socket.getOutputStream());
           writer.println("HTTP/1.1 200 OK");// 返回应答消息,并结束应答
           writer.println("Content-Type:text/html");
           writer.println("Content-Length:" + message.length);// 返回内容字节数
           writer.println();// 根据 HTTP 协议, 空行将结束头信息
           writer.write(message);
           writer.close();
           socket.close();
       } catch (Exception e) {
           e.printStackTrace();
       }

   }
}
