package com.lsd.net.bytebuddy.interceptor;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public class MemoryDatabase {

    public List<String> load(String info) {
        List list = Arrays.asList(info + ": foo", info + ": bar");
        list.forEach(System.out :: println);
        return list;
    }
}
