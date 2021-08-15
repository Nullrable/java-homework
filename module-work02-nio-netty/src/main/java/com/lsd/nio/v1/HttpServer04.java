package com.lsd.nio.v1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author nhsoft.lsd
 */
public class HttpServer04 {



   public static void main(String[] args) throws IOException {
       ServerSocketChannel serverChannel = ServerSocketChannel.open();
       serverChannel.configureBlocking(false);
       ServerSocket ssocket = serverChannel.socket();
       InetSocketAddress address = new InetSocketAddress(8091);
       ssocket.bind(address);
       Selector selector = Selector.open();
       serverChannel.register(selector, SelectionKey.OP_ACCEPT);

       for (;;) {
           try {
               selector.select();
           } catch (IOException ex) {
               ex.printStackTrace();
               break;
           }

           Set<SelectionKey> readyKeys = selector.selectedKeys();
           Iterator<SelectionKey> iterator = readyKeys.iterator();

           while (iterator.hasNext()) {
               SelectionKey key = iterator.next();
               iterator.remove();
               try {
                   if (key.isValid() && key.isAcceptable()){
                       SocketChannel sc = serverChannel.accept();
                       service(sc);
                   }
               } catch (IOException ex) {
                   key.cancel();
                   try {
                       key.channel().close();

                   } catch (IOException cex) {
                   }
               }
           }
       }
   }

   private static void service(SocketChannel channel) {
       try {
           byte[] message = "hello, lusudong".getBytes(StandardCharsets.UTF_8);

           ByteBuffer buf = ByteBuffer.allocate(512);
           buf.put("HTTP/1.1 200\r\n".getBytes());
           buf.put("Content-Type:text/html\r\n".getBytes());
           buf.put(("Content-Length:" + message.length + "\r\n").getBytes());
           buf.put("\r\n".getBytes());
           buf.put(message);
           buf.flip();
           channel.write(buf);
           channel.shutdownOutput();
           channel.close();
       } catch (Exception e) {
           e.printStackTrace();
       }

   }
}
