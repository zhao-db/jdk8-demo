package com.example.jdk8.jdk8demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/8/25
 * @since 3.0.1
 */
public class TestMain {

//    public static void main(String[] args) {
//        int weightFloorLeft1 = 3;
//        String weightLocation1 = "1" + weightFloorLeft1 + "01";
//        System.out.println("weightLocation1 = " + weightLocation1);
//        byte[] bytes = ByteUtil.convertHexStringToBytes(weightLocation1);
//        System.out.println("bytes = " + bytes);
//        System.out.println("ByteUtil.toHexString(bytes) = " + ByteUtil.byte2Short(bytes, 0));
//
//    }

    public static void main(String[] args) {
        String srcMsg = "31eb2c000101e903232002009c4deb080000000005210d479a19e3fa7eb7ffff48fd5cc1c06e47410000";
        byte[] bytes = ByteBufUtil.decodeHexDump(srcMsg);
        ByteBuf buf = Unpooled.copiedBuffer(bytes);
        byte[] ss = new byte[32];
        buf.readBytes(ss);
        long x = buf.readUnsignedIntLE();
        int z = buf.readIntLE();
        int sum = buf.readShortLE();
        System.out.println("x+z");
    }

}
