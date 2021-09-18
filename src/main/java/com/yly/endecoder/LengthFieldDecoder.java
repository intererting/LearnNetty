package com.yly.endecoder;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2021/9/18 下午1:43
 * @since 1.0
 * <p>
 * TAG-----------------LENGTH-----------------VALUE
 * （VALUE的长度）
 * <p>
 * lengthFieldOffset        LENGTH部分偏移量  e.g 0
 * lengthFieldLength        LENGTH部分长度  e.g 2
 * initialBytesToStrip      内容部分需要跳过的字节数 eg 2
 * lengthAdjustment         指Length后Value的长度，当LENGTH部分包括lengthFieldLength时，为了获取VALUE的长度需要加上lengthAdjustment，所以这个为负数
 * <p>
 * * lengthFieldOffset   = 1 (= the length of HDR1)
 * * lengthFieldLength   = 2
 * * <b>lengthAdjustment</b>    = <b>1</b> (= the length of HDR2)
 * * <b>initialBytesToStrip</b> = <b>3</b> (= the length of HDR1 + LEN)
 * *
 * * BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
 * * +------+--------+------+----------------+      +------+----------------+
 * * | HDR1 | Length | HDR2 | Actual Content |----->| HDR2 | Actual Content |
 * * | 0xCA | 0x000C | 0xFE | "HELLO, WORLD" |      | 0xFE | "HELLO, WORLD" |
 * * +------+--------+------+----------------+      +------+----------------+
 */
public class LengthFieldDecoder extends LengthFieldBasedFrameDecoder {
    public LengthFieldDecoder() {
        super(1460, 1, 2,
                1, 3, true);
    }
}
