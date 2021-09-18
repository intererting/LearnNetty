package com.yly.endecoder;

import com.yly.protocoltcp.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class NettyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    //当通道就绪就会触发该方法
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
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        System.out.println("客户端收到信息");
        System.out.println(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
