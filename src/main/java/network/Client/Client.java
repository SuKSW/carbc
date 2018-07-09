package network.Client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import network.Client.Handlers.CommonClientHandler;
import network.Listener.Handlers.CommonListenerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client extends Thread{
    private final Logger log = LoggerFactory.getLogger(Client.class);
    private String neighbourIP;
    private int port;

    public void init(String neighbourIP, int port) {
        this.neighbourIP = neighbourIP;
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new CommonClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(neighbourIP, port).sync();
            log.info("Connecting to neighbour: {}:{}", neighbourIP, port);


            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
