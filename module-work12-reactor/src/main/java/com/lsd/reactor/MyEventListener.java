package com.lsd.reactor;

import java.util.List;

/**
 * @author nhsoft.lsd
 */
interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);
    void processComplete();
}