package com.yd.scala.aviator.util;



public class ByteUtils {

    public static byte[] subByte(byte[] old,int start, int len) {
        if (old == null) return null;
        byte[] bs = new byte[len];
        System.arraycopy(old, start, bs, 0, len);
        return bs;
    }

    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < byteNum.length; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    public static int bytes2Int(byte[] byteNum) {
        int num = 0;
        for (int ix = 0; ix < byteNum.length; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

}
