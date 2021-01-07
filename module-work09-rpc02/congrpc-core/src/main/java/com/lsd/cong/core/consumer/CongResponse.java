package com.lsd.cong.core.consumer;

import com.lsd.cong.core.exception.CongRpcException;
import java.io.Serializable;

/**
 * @Author: nhsoft.lsd
 */
public class CongResponse implements Serializable {


    private Boolean status;

    private Object result;

    private CongRpcException exception;

    public Boolean getStatus() {
        return status;
    }

    public Object getResult() {
        return result;
    }

    public CongRpcException getException() {
        return exception;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setException(CongRpcException exception) {
        this.exception = exception;
    }
}
