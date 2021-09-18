package com.yly.endecoder;

import com.yly.protocoltcp.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.UUID;


//处理业务的handler
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        //接收到数据，并处理
        System.out.println("服务端收到信息");
        System.out.println(msg);
        //回复消息
        String responseContent = UUID.randomUUID().toString();
        System.out.println("服务端发送消息 " + responseContent);
        int responseLen = responseContent.getBytes(StandardCharsets.UTF_8).length;
        byte[] responseContentBytes = responseContent.getBytes(StandardCharsets.UTF_8);
        //构建一个协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(responseLen);
        messageProtocol.setContent(responseContentBytes);

        ctx.writeAndFlush(messageProtocol);


    }
}
