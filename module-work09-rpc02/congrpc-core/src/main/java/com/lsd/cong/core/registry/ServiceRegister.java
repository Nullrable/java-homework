package com.lsd.cong.core.registry;

import com.lsd.cong.core.consumer.URL;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface ServiceRegister {

    void register(List<URL> urls);
}
