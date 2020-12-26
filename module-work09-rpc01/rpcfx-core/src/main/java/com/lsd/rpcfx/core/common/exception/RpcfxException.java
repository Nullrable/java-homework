package com.lsd.rpcfx.core.common.exception;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 9:14 PM
 * @Modified By：
 */
public class RpcfxException extends RuntimeException {

    public RpcfxException() {
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(Throwable cause) {
        super(cause);
    }


}
