package com.yly;

public class Main {

    public static void main(String[] args) {
//        int a = 32;
//        System.out.println(isPowOf2(a));

        byte a = (byte) 0xEE;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a & 0xFF));
        System.out.println(Integer.toHexString(a & 0xFF));
    }

    /**
     * 是否是2的指数
     */
    public static boolean isPowOf2(int a) {
        return (a & -a) == a;
    }
}