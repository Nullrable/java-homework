package com.lsd.cong.core.consumer;

import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface Directory<T> {

    Class<T> getInterface();

    List<Invoker<T>> getAllInvokers();
}
