package com.lsd.cache.lru.test;

/**
 * @author nhsoft.lsd
 */
public class Test {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String args[]) {
        System.out.println(tableSizeFor(2)); // output:2
        System.out.println(tableSizeFor(3)); // output:4
        System.out.println(tableSizeFor(4)); // output:4
        System.out.println(tableSizeFor(8)); // output:8
        System.out.println(tableSizeFor(17));// output:32
    }


    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
