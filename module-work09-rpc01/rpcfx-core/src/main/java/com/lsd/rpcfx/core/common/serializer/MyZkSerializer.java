package com.lsd.rpcfx.core.common.serializer;

import java.io.UnsupportedEncodingException;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

/**
 * @Author: nhsoft.lsd
 */
public class MyZkSerializer implements ZkSerializer {

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
