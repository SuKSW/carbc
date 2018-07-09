package network.Client.Handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class CommonClientHandler extends ChannelInboundHandlerAdapter {
    private final Logger log = LoggerFactory.getLogger(CommonClientHandler.class);
    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(50); // capacity is at least 50 bytes
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release();
        buf = null;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String msg = "send me something";
        try {
            byte[] b = msg.getBytes("UTF-8");
            ChannelFuture f = ctx.writeAndFlush(b);
            System.out.println(f.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("channel read");
        ByteBuf byteBuff = (ByteBuf) msg;
        buf.writeBytes(byteBuff);
        byteBuff.release();

        //if (buf.readableBytes() >= 16) { // check whether at least 16 bytes have arrived
            while (byteBuff.isReadable()) {
                System.out.print(byteBuff.readByte());
            }
            System.out.println();
        //}
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
