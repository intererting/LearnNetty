package com.yly.endecoder;

import com.yly.protocoltcp.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * * BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
 * * +------+--------+------+----------------+      +------+----------------+
 * * | HDR1 | Length | HDR2 | Actual Content |----->| HDR2 | Actual Content |
 * * | 0xCA | 0x000C | 0xFE | "HELLO, WORLD" |      | 0xFE | "HELLO, WORLD" |
 * * +------+--------+------+----------------+      +------+----------------+
 */
public class MyLengthMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) {
        out.writeByte(0xCA);
        out.writeShort(msg.getLen());
        out.writeByte(0xFE);
        out.writeBytes(msg.getContent());
    }
}
