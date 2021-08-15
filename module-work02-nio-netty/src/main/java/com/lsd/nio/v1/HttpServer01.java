package com.lsd.nio.v1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author nhsoft.lsd
 */
public class HttpServer01 {

   public static void main(String[] args) {

       try {
           ServerSocket serverSocket = new ServerSocket(8088);

           while (true) {
               Socket socket = serverSocket.accept();
               service(socket);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   private static void service(Socket socket) throws IOException {
       PrintStream writer = null;
       try {
           byte[] message = "hello, lusudong".getBytes(StandardCharsets.UTF_8);
           writer = new PrintStream(socket.getOutputStream());
           writer.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
           writer.println("Content-Type:text/html");
           writer.println("Content-Length:" + message.length);// 返回内容字节数
           writer.println();// 根据 HTTP 协议, 空行将结束头信息
           writer.write(message);
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           writer.close();
           socket.close();
       }

   }
}
