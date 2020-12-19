package com.lsd.rpcfx.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/19/20 10:37 AM
 * @Modified By：
 */
class NettyClient {


    /**
     * 客户端业务处理Handler
     */
    private ClientChannelHandler clientChannelHandler = new ClientChannelHandler();


    /**
     * 事件池
     */
    private EventLoopGroup group = new NioEventLoopGroup();

    /**
     * 客户端通道
     */
    private Channel clientChannel;


    public NettyClient(String host, int port) {



        Bootstrap bootstrap = new Bootstrap().group(group).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000) // 超时时间。
                .channel(NioSocketChannel.class).handler(new ChannelInitializer() {

                    @Override
                    protected void initChannel(Channel channel) {

                        channel.pipeline().addLast(new HttpClientCodec());
                        channel.pipeline().addLast(new HttpObjectAggregator(65536));
                        channel.pipeline().addLast(new HttpContentDecompressor());

                        channel.pipeline().addLast(clientChannelHandler);
                    }

                });

        //发起同步连接操作
        ChannelFuture channelFuture = bootstrap.connect(host, port);

        //注册连接事件
        channelFuture.addListener((ChannelFutureListener)future -> {
            //如果连接成功
            if (future.isSuccess()) {
                System.out.println("客户端[" + channelFuture.channel().localAddress().toString() + "]已连接...");
                clientChannel = channelFuture.channel();
            }
            //如果连接失败，尝试重新连接
            else{
                System.out.println("客户端[" + channelFuture.channel().localAddress().toString() + "]连接失败，重新连接中...");
                future.channel().close();
                bootstrap.connect(host, port);
            }
        });

        //注册关闭事件
        channelFuture.channel().closeFuture().addListener(cfl -> {
            close();
            System.out.println("客户端[" + channelFuture.channel().localAddress().toString() + "]已断开...");
        });
    }



    public Object doExecuteRequest(String request, String mediaType) throws Exception {

        ChannelPromise channelPromise = clientChannelHandler.sendMessage(request, mediaType);
        channelPromise.await(); //等待
        return clientChannelHandler.getResponse();
    }

    /**
     * 客户端关闭
     */
    private void close() {
        //关闭客户端套接字
        if(clientChannel != null){
            clientChannel.close();
        }
        //关闭客户端线程组
        if (group != null) {
            group.shutdownGracefully();
        }
    }


    private class ClientChannelHandler extends ChannelInboundHandlerAdapter {

        private ChannelHandlerContext ctx;
        private ChannelPromise promise;
        private Object response;


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            this.ctx = ctx;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {

            System.out.println("msg -> "+msg);

            if(msg instanceof FullHttpResponse){
                FullHttpResponse httpResponse = (FullHttpResponse)msg;
                ByteBuf buf = httpResponse.content();
                String result = buf.toString(CharsetUtil.UTF_8);
                response = result;
            }
            promise.setSuccess();
            System.out.println("response: " + response);


        }


        public synchronized ChannelPromise sendMessage(String request, String mediaType) {
            while (ctx == null) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                    //logger.error("等待ChannelHandlerContext实例化");
                } catch (InterruptedException e) {
                    System.out.println("等待ChannelHandlerContext实例化过程中出错");
                }
            }
            promise = ctx.newPromise();
            send(request, mediaType, ctx);
            return promise;
        }

        public Object getResponse(){
            return response;
        }
    }

    private void send(String request, String mediaType, ChannelHandlerContext ctx) {

        ByteBuf outByteBuf = Unpooled.copiedBuffer(request, Charset.forName("UTF-8"));

        FullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, "/", outByteBuf);
        httpRequest.headers().add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        httpRequest.headers().set(HttpHeaderNames.CONTENT_TYPE, mediaType);
        httpRequest.headers().add(HttpHeaderNames.CONTENT_LENGTH,httpRequest.content().readableBytes());
        ctx.writeAndFlush(httpRequest);
    }


}

