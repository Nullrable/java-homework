package com.lsd.cong.core.exception;

/**
 * @Author: nhsoft.lsd
 */
public class CongRpcException extends RuntimeException {

    public CongRpcException(String message) {
        super(message);
    }

    public CongRpcException(Throwable cause) {
        super(cause);
    }
}
