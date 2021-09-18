package com.yly.endecoder;

import com.yly.protocoltcp.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) {
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
