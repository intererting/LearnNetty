package com.yly.endecoder;

import com.yly.protocoltcp.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

enum MyDecoderState {
    //
    READ_LENGTH,
    //
    READ_CONTENT;
}

public class MyMessageDecoder extends ReplayingDecoder<MyDecoderState> {

    private int length;

    public MyMessageDecoder() {
        super(MyDecoderState.READ_LENGTH);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {

        switch (state()) {
            case READ_LENGTH:
                length = in.readInt();
                checkpoint(MyDecoderState.READ_CONTENT);
                break;
            case READ_CONTENT:
                byte[] buffer = new byte[length];
                in.readBytes(buffer);
                out.add(new MessageProtocol(length, buffer));
                checkpoint(MyDecoderState.READ_LENGTH);
            default:
                break;
        }
    }
}
