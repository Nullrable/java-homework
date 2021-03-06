package com.lsd.rpcfx.core.api;

import com.lsd.rpcfx.core.common.exception.RpcfxException;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 11:38 AM
 * @Modified By：
 */
public class RpcfxResponse {

    private Boolean status;

    private Object result;

    private RpcfxException exception;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public RpcfxException getException() {
        return exception;
    }

    public void setException(RpcfxException exception) {
        this.exception = exception;
    }
}
