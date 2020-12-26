package com.lsd.rpcfx.core.server.register;

import com.alibaba.fastjson.JSON;
import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020/12/26 11:50 上午
 * @Modified By：
 */
public class ZookeeperServiceRegister implements ServiceRegister {

    private ZkClient zkClient;

    private String rootPath ="/rfxRpc";

    public ZookeeperServiceRegister(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    @Override
    public void register(ServiceInfo serviceInfo) {
        String serviceClass = serviceInfo.getServiceClass();
        String uri = JSON.toJSONString(serviceInfo);
        try {
            uri = URLEncoder.encode(uri,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String servicePath = rootPath + "/" + serviceClass ;
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
    public void register(List<ServiceInfo> serviceInfos) {

        if (serviceInfos == null || serviceInfos.isEmpty()) {
            return;
        }
        serviceInfos.forEach( s -> register(s));
    }
}
