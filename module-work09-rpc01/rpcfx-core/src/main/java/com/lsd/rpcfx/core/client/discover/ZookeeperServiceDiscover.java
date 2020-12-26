package com.lsd.rpcfx.core.client.discover;

import com.alibaba.fastjson.JSON;
import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;

/**
 * @Author: nhsoft.lsd
 */
public class ZookeeperServiceDiscover implements ServiceDiscover {

    private ZkClient zkClient;

    private String rootPath ="/rfxRpc";

    public ZookeeperServiceDiscover(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public List<ServiceInfo> getServiceInfos(String serviceClass) {

        String path = rootPath +"/"+ serviceClass;
        List<String> children = zkClient.getChildren(path);
        List<ServiceInfo> serviceInfos = new ArrayList<>();
        for (String child : children) {
            try {
                String decode = URLDecoder.decode(child,"UTF-8");
                ServiceInfo serviceInfo = JSON.parseObject(decode,ServiceInfo.class);
                serviceInfos.add(serviceInfo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return serviceInfos;
    }
}
