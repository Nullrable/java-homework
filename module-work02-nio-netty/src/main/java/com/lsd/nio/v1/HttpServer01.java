package com.lsd.nio.v1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author nhsoft.lsd
 */
public class HttpServer01 {

   public static void main(String[] args) throws Exception {
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

    private static void isAllEmpty(String name, String serialNumber) {
        if (serialNumber == null) {
            serialNumber = "";
        }
        if (name == null) {
            name = "";
        }
        System.out.println("2:" + serialNumber.hashCode());

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

    private static boolean isReachable(int nping, int wping, String ipping) throws Exception {

        int nReceived = 0;
        int nLost = 0;

        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ping -n " + nping + " -w " + wping + " " + ipping);
        Scanner scanner = new Scanner(process.getInputStream());
        process.waitFor();
        ArrayList<String> strings = new ArrayList<>();
        String data = "";
        //
//        InetAddress.getByName("www.zalou.cn").isReachable();
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            data = data + string + "\n";
            strings.add(string);
        }

        if (data.contains("IP address must be specified.")
                || (data.contains("Ping request could not find host " + ipping + ".")
                || data.contains("Please check the name and try again."))) {
            throw new Exception(data);
        } else if (nping > strings.size()) {
            throw new Exception(data);
        }

        int index = 2;

        for (int i = index; i < nping + index; i++) {
            String string = strings.get(i);
            if (string.contains("Destination host unreachable.")) {
                nLost++;
            } else if (string.contains("Request timed out.")) {
                nLost++;
            } else if (string.contains("bytes") && string.contains("time") && string.contains("TTL")) {
                nReceived++;
            } else {
            }
        }

        return nReceived > 0;
    }
}
