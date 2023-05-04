package com.lsd.nio.v3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author nhsoft.lsd
 */
public class Reactor {

    public Reactor() throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket ssocket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(8092);
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
                if (key.equals(SelectionKey.OP_ACCEPT)) {

                } else if(key.equals(SelectionKey.OP_READ)){

                }
//                try {
//                    if (key.isValid() && key.isAcceptable()){
//                        SocketChannel sc = serverChannel.accept();
//                        service(sc);
//                    }
//                } catch (IOException ex) {
//                    key.cancel();
//                    try {
//                        key.channel().close();
//
//                    } catch (IOException cex) {
//                    }
//                }
            }
        }
    }
}
