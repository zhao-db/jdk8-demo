package com.example.jdk8.jdk8demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字节工具类
 *
 * @author nick
 * @since 1.0.0
 */
public final class ByteUtil {
    private ByteUtil() {
    }

    private static final char[] HEX_CHAR_ARR = "0123456789ABCDEF".toCharArray();

    /**
     * 将字节数组转成16进制的字符串
     *
     * @param bytes
     * @return
     * @author nick 2016年6月7日
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : bytes) {
            sb.append(HEX_CHAR_ARR[(b & 0xF0) >> 4]);
            sb.append(HEX_CHAR_ARR[(b & 0x0F)]).append(" ");
        }
        return sb.toString();
    }

    public static String toHexString(char c) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        sb.append(HEX_CHAR_ARR[(c & 0xF000) >> 12]);
        sb.append(HEX_CHAR_ARR[(c & 0x0F00) >> 8]);
        sb.append(HEX_CHAR_ARR[(c & 0x00F0) >> 4]);
        sb.append(HEX_CHAR_ARR[(c & 0x000F)]);
        return sb.toString();
    }

    /**
     * 将字节数组转成16进制的字符串
     *
     * @param bytes
     * @return
     * @author nick 2016年6月7日
     */
    public static String safeCharToHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (byte b : bytes) {
            sb.append(HEX_CHAR_ARR[(b & 0xF0) >> 4]);
            sb.append(HEX_CHAR_ARR[(b & 0x0F)]).append(" ");
        }
        return sb.toString();
    }

    /**
     * 功能： 十六进制字符串转字节数组
     *
     * @param hexString 十六进制字符串
     * @return 字节数组
     */
    public static byte[] convertHexStringToBytes(String hexString) {
        //判空
        if (hexString == null || hexString.length() == 0) {
            return null;
        }

        //合法性校验
        if (!hexString.matches("[a-fA-F0-9]*") || hexString.length() % 2 != 0) {
            return null;
        }

        //计算
        int mid = hexString.length() / 2;
        byte[] bytes = new byte[mid];
        for (int i = 0; i < mid; i++) {
            bytes[i] = Integer.valueOf(hexString.substring(i * 2, i * 2 + 2), 16).byteValue();
        }

        return bytes;
    }

    /**
     * 功能：将整数转化为合法的十六进制字符串
     *
     * @param source 整数
     * @param len    至少占多少个字节
     * @return 十六进制数
     * <p>
     * 说明：为什么要有len？
     * 某些场景需要指定大小的二进制字符串（这里的大小指的是转化为字节数组后所占大小）
     * 例如，现在需要把1这个整数转化为十六进制数，
     * 如果len为1，结果为 01
     * 如果len为2，结果为 0001
     * <p>
     * 但是len不限制大小，比如len设置为1，source为125120，那么结果仍然为1e8c0，
     * 不会截断结果
     */
    public static String convertIntToHexString(int source, int len) {
        //一个字节占两位，所以要乘以2
        len <<= 1;
        StringBuilder res = new StringBuilder(Integer.toHexString(source));
        int comp = len - res.length();

        //位数不足，需要补0
        if (comp > 0) {
            for (int i = 0; i < comp; i++) {
                res.insert(0, "0");
            }
        }

        return res.toString();
    }

    public static String convertShortToHexString(short source) {
        //一个字节占两位，所以要乘以2
        StringBuilder res = new StringBuilder();
        try {
            res = new StringBuilder(Integer.toHexString(source));
            if (res.length() > 4) {
                res.replace(0, 4, "");
            }
        } catch (Exception e) {
        }
        return res.toString();
    }

    /**
     * 将字节数组转成16进制的字符串,并每5位标记一个逗号
     *
     * @param bytes
     * @return
     * @author nick 2016年6月7日
     */
    public static String safeCharToHexStringAddSplit(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        int count = 1;
        for (byte b : bytes) {
            sb.append(HEX_CHAR_ARR[(b & 0xF0) >> 4]);
            if (count % 5 == 0) {
                sb.append(HEX_CHAR_ARR[(b & 0x0F)]).append(",");
            } else {
                sb.append(HEX_CHAR_ARR[(b & 0x0F)]).append(" ");
            }
            count++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int source = 50529538;
        byte[] bytes = int2ByteArray(source);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("bytes = " + bytes[i]);
        }
        FaultLevel byCode = FaultLevel.getByCode(bytes[1]);
        System.out.println(byCode.name());

        int res = 0;
        for (int i = 0; i < bytes.length; i++) {
            res += (bytes[i] & 0xff) << ((3 - i) * 8);
        }
        System.out.println("res = " + res);

    }


    public static short byte2Short(byte[] bytes, int offset) {
        short value;
        value = (short) ((bytes[offset] & 0xFF) << 8 | (bytes[offset + 1] & 0xFF));
        return value;
    }

    public static byte[] int2ByteArray(int nums) {
        byte[] res = new byte[4];
        res[0] = (byte) ((nums >> 24) & 0xFF);
        res[1] = (byte) ((nums >> 16) & 0xFF);
        res[2] = (byte) ((nums >> 8) & 0xFF);
        res[3] = (byte) ((nums) & 0xFF);
        return res;
    }

    /**
     * 故障等级
     */
    @Getter
    @AllArgsConstructor
    public enum FaultLevel {
        WARN((byte) 0x01),
        ERROR((byte) 0x02),
        FATAL((byte) 0x03);

        private byte code;

        public static FaultLevel getByCode(short code) {
            for (FaultLevel it : values()) {
                if (it.code == code) {
                    return it;
                }
            }
            return null;
        }
    }
}
