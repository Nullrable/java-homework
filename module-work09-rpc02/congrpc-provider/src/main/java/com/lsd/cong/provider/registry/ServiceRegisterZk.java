package com.lsd.cong.provider.registry;

import com.alibaba.fastjson.JSON;
import com.lsd.cong.core.config.ZookeeperConstants;
import com.lsd.cong.core.consumer.URL;
import com.lsd.cong.core.registry.ServiceRegister;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;

/**
 * @Author: nhsoft.lsd
 */
public class ServiceRegisterZk implements ServiceRegister {


    private ZkClient zkClient;


    public ServiceRegisterZk(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public void register(URL url) {
        String uri = JSON.toJSONString(url);
        try {
            uri = URLEncoder.encode(uri,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String servicePath = ZookeeperConstants.ROOT_PATH + "/" + url.getServiceName() ;
        if(!zkClient.exists(servicePath)){
            zkClient.createPersistent(servicePath,true);
        }
        String uriPath = servicePath + "/" + uri;
        if(zkClient.exists(uriPath)){
            zkClient.delete(uriPath);
        }
        zkClient.createEphemeral(uriPath);

        System.out.println("======>注册成功: uriPath: " + uriPath + ",  servicePath: " + servicePath);
    }

    @Override
    public void register(List<URL> urls) {

        if (urls == null || urls.isEmpty()) {
            return;
        }
        urls.forEach( s -> register(s));
    }
}
