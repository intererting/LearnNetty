package com.yly.endecoder;

import com.yly.protocoltcp.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2021/9/18 下午2:45
 * @since 1.0
 */
public class MyLengthClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String mes = "今天天气冷，吃火锅";
        byte[] content = mes.getBytes(StandardCharsets.UTF_8);
        int length = mes.getBytes(StandardCharsets.UTF_8).length;
        //创建协议包对象
        MessageProtocol messageProtocol = new MessageProtocol(length, content);
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client收到信息");
        //这个地方只剩下LEHGTH后面的内容
        System.out.println(Integer.toHexString(msg.readByte() & 0xFF));
        System.out.println(msg.readBytes(msg.readableBytes()).toString(Charset.defaultCharset()));
    }
}
