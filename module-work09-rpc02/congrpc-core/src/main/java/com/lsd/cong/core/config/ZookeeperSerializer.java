package com.lsd.cong.core.config;

import java.io.UnsupportedEncodingException;
import org.I0Itec.zkclient.exception.ZkMarshallingError;

/**
 * @Author: nhsoft.lsd
 */
public class ZookeeperSerializer implements org.I0Itec.zkclient.serialize.ZkSerializer {

    private static final String CHARSET = "UTF-8";

    @Override
    public byte[] serialize(Object data) throws ZkMarshallingError {
        try {
            return String.valueOf(data).getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError(e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            return new String(bytes, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError(e);
        }
    }
}
