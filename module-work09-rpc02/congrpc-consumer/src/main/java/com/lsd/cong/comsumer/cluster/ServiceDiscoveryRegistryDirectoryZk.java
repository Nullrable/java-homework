package com.lsd.cong.comsumer.cluster;

import com.alibaba.fastjson.JSON;
import com.lsd.cong.core.config.SPIType;
import com.lsd.cong.core.config.ZookeeperConstants;
import com.lsd.cong.core.consumer.URL;
import com.lsd.cong.comsumer.ServiceDiscovery;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;

/**
 *
 * zookeeper registry center
 *
 * @Author: nhsoft.lsd
 */
public class ServiceDiscoveryRegistryDirectoryZk implements ServiceDiscovery {

    private ZkClient zkClient;



    public ServiceDiscoveryRegistryDirectoryZk(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public String getSPIType() {
        return SPIType.REGISTRY_ZOOKEEPER;
    }

    @Override
    public List<URL> getURL(String serviceName) {

        String path = ZookeeperConstants.ROOT_PATH +"/"+ serviceName;
        List<String> children = zkClient.getChildren(path);
        List<URL> urls = new ArrayList<>();
        for (String child : children) {
            try {
                String decode = URLDecoder.decode(child,"UTF-8");
                URL serviceInfo = JSON.parseObject(decode,URL.class);
                urls.add(serviceInfo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return urls;

    }
}
